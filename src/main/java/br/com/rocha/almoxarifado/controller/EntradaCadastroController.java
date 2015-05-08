package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.control.ButtonCell;
import br.com.rocha.almoxarifado.entity.Produto;
import br.com.rocha.almoxarifado.util.Maps;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;

@ViewController("/fxml/EntradaCadastro.fxml")
public class EntradaCadastroController {
  
  private Produto produto;
  private ObservableList<Produto> produtos;
  
  @ActionTrigger("salvar")
  @FXML private Button salvar;
  @ActionTrigger("cancelar")
  @FXML private Button cancelar;
  
  @ActionTrigger("pesquisarProduto")
  @FXML private Button botaoPesquisar;
  @FXML private TextField textoCodigo;
  @FXML private TextField textoNome;
  @FXML private TextField textoQuantidade;
  @ActionTrigger("inserirProduto")
  @FXML private Button botaoInserir;
  
  @FXML private TableView<Produto> tabelaProdutos;
  @FXML private TableColumn<Produto, String> colunaCodigo;
  @FXML private TableColumn<Produto, String> colunaNome;
  @FXML private TableColumn<Produto, String> colunaQuantidade;
  @FXML private TableColumn<Produto, Boolean> colunaRemover;
  
  @ActionHandler
  private FlowActionHandler actionHandler;
  
  @PostConstruct
  public void init(){
    produtos = FXCollections.observableArrayList();
    tabelaProdutos.setItems(produtos);
    colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
    
    colunaRemover.setCellValueFactory(param -> new SimpleBooleanProperty(param.getValue() != null));
    colunaRemover.setCellFactory(param -> new ButtonCell());
  }
  
  @ActionMethod("salvar")
  public void salvarAction(){
    try {
      if(produtos.size() > 0){
        for (Produto p : produtos) {
          Produto pb = QueryUtil.selectSingleByNamedQuery("Produto.findById", Maps.asMap("id", p.getId()));
          pb.setQuantidade(pb.getQuantidade() + p.getQuantidade());
          QueryUtil.saveEntity(pb);
        }
        actionHandler.navigateBack();
      }else{
        Alert alert = new Alert(Alert.AlertType.WARNING, "Nenhum produto inserido.", ButtonType.OK);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.show();
      }
    } catch (VetoException | FlowException ex) {
      System.out.println(ex);
    }
  }
  
  @ActionMethod("pesquisarProduto")
  public void botaoPesquisarAction(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPesquisa.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      ProdutoPesquisaController ppc = (ProdutoPesquisaController) fxmlLoader.getController();
      ppc.setStage(stage);
      stage.setTitle("Pesquisar produto");
      stage.setScene(scene);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
      
      if(ppc.getProduto() != null){
        produto = (Produto) ppc.getProduto().clone();
        textoCodigo.setText(produto.getCodigo());
        textoNome.setText(produto.getNome());
        textoQuantidade.setText(produto.getQuantidade().toString());
        textoQuantidade.requestFocus();
      }
      
    } catch (IOException ex) {
      System.out.println(ex);
    }
  }
  
  @ActionMethod("inserirProduto")
  public void botaoInserirAction(){
    try{
      if(StringUtils.isNotEmpty(textoCodigo.getText()) && 
          StringUtils.isNotEmpty(textoNome.getText()) &&
          StringUtils.isNotEmpty(textoQuantidade.getText())){
        produto.setQuantidade(Integer.parseInt(textoQuantidade.getText()));
        produtos.add(produto);
        textoCodigo.setText("");
        textoNome.setText("");
        textoQuantidade.setText("");
      }else{
        Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha os dados do produto primeiro.", ButtonType.OK);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.show();
      }
    }catch(Exception ex){
      System.out.println(ex);
    }
  }
}
