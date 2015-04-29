package br.com.rocha.almoxarifado.controller.flow;

import br.com.rocha.almoxarifado.entity.EntidadeBase;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang.StringUtils;

public abstract class AbstractListaController<E extends EntidadeBase> {
  
  @FXML
  @ActionTrigger("editar")
  private TableView<E> tabela;
  
  @FXML
  @ActionTrigger("criar")
  private Button criar;
  
  @FXML
  @ActionTrigger("remover")
  private Button remover;
  
  @FXML
  private ComboBox<String> opcaoFiltro;
  @FXML
  @ActionTrigger("pesquisar")
  private TextField textoFiltro;
  
  @ActionHandler
  private FlowActionHandler actionHandler;
  
  @Inject
  private DataModelFlow<E> modelo;
  
  private Class<?> classeCadastro;

  public abstract Supplier<ObservableList<E>> supplier();
  public abstract ObservableList<String> opcoesFiltro();
  public abstract Predicate<E> filtro(String textFiltro, String opcaoFiltro);
  
  
  @ActionMethod("editar")
  private void editarAction(){
    try {
      actionHandler.navigate(classeCadastro);
    } catch (VetoException | FlowException ex) {
      System.out.println(ex);
    }
  }
  
  @ActionMethod("criar")
  private void criarAction(){
    try {
      tabela.getSelectionModel().clearSelection();
      actionHandler.navigate(classeCadastro);
    } catch (VetoException | FlowException ex) {
      System.out.println(ex);
    }
  };
  
  @ActionMethod("remover")
  private void removerAction(){
    remover();
  }
  
  @ActionMethod("pesquisar")
  public void pesquisarAction(){
    if(StringUtils.isNotEmpty(textoFiltro.getText())){
      modelo.getDados().setAll(supplier().get());
      ObservableList<E> listaFiltrada = modelo.getDados().filtered(
          filtro(textoFiltro.getText(), opcaoFiltro.getSelectionModel().getSelectedItem()));
      modelo.getDados().setAll(FXCollections.observableArrayList(listaFiltrada));
    }else{
      modelo.getDados().setAll(supplier().get());
    }
  }
  
  private void remover(){
    if(modelo.getIndiceDado() >= 0){    
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Confirma exclusão");
      alert.setHeaderText(null);
      alert.setContentText("Tem certeza que deseja excluir o item selecionado ?");
      Optional<ButtonType> result = alert.showAndWait();
      if(result.get() == ButtonType.OK){
        QueryUtil.removeEntity(modelo.getDados().get(modelo.getIndiceDado()));
        modelo.getDados().remove(modelo.getDados().get(modelo.getIndiceDado()));
      }
    }
  }
  
  @PostConstruct
  private void initialize(){
    if(modelo != null){
      modelo.setSupplier(supplier());
      tabela.itemsProperty().bind(modelo.getDados());
      modelo.indiceDadoProperty().bind(tabela.getSelectionModel().selectedIndexProperty());
    }
    opcaoFiltro.getItems().setAll(opcoesFiltro());
    opcaoFiltro.getSelectionModel().selectFirst();
  }
  
  {
    try {
      classeCadastro = 
          Class.forName(this.getClass().getCanonicalName().replace("ListaController", "CadastroController"));
    } catch (ClassNotFoundException ex) {
      System.out.println(ex);
    }
  }
}
