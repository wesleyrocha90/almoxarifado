package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.Produto;
import br.com.rocha.almoxarifado.util.QueryUtil;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;

public class ProdutoPesquisaController implements Initializable{
  
  private Stage thisStage;
  private ObservableList<Produto> modelo;
  private Produto produto;
  
  @FXML private TableView<Produto> tabela;
  @FXML TableColumn<Produto, String> colunaCodigo;
  @FXML TableColumn<Produto, String> colunaNome;
  @FXML TableColumn<Produto, Integer> colunaQuantidade;
  
  @FXML
  private ComboBox<String> opcaoFiltro;
  @FXML
  private TextField textoFiltro;
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    modelo = FXCollections.observableArrayList();
    modelo.addAll(supplier().get());
    tabela.setItems(modelo);
    
    opcaoFiltro.getItems().setAll(opcoesFiltro());
    opcaoFiltro.getSelectionModel().selectFirst();
    colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
  }
  
  public Supplier<ObservableList<Produto>> supplier() {
    return () -> FXCollections.observableArrayList(QueryUtil.selectListByNamedQuery("Produto.findAll"));
  }

  public ObservableList<String> opcoesFiltro() {
    return FXCollections.observableArrayList("Nome", "Código");
  }

  public Predicate<Produto> filtro(String textFiltro, String opcaoFiltro) {
    return (Produto p) -> {
      switch(opcaoFiltro){
        case "Código":
          return p.getCodigo().equals(textFiltro);
        case "Nome":
          return p.getNome().toUpperCase().contains(textFiltro.toUpperCase());
      }
      return false;
    };
  }
  
  @FXML public void pesquisarAction(ActionEvent event){
    if(StringUtils.isNotEmpty(textoFiltro.getText())){
      modelo.setAll(supplier().get());
      ObservableList<Produto> listaFiltrada = modelo.filtered(
          filtro(textoFiltro.getText(), opcaoFiltro.getSelectionModel().getSelectedItem()));
      modelo.setAll(FXCollections.observableArrayList(listaFiltrada));
      tabela.setItems(modelo);
    }else{
      modelo.setAll(supplier().get());
      tabela.setItems(modelo);
    }
  }
  
  @FXML public void selecionarAction(ActionEvent event){
    produto = tabela.getSelectionModel().getSelectedItem();
    if(produto != null){
        thisStage.close();
    }else{
      Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um item primeiro.", ButtonType.OK);
      alert.setTitle("Aviso");
      alert.setHeaderText(null);
      alert.show();
    }
  }
  
  public void setStage(Stage stage){
    thisStage = stage;
  }
  
  public Produto getProduto(){
    return produto;
  }
}
