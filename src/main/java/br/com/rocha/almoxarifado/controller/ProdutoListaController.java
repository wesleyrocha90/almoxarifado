package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractListaController;
import br.com.rocha.almoxarifado.entity.Produto;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.ViewController;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.annotation.PostConstruct;

@ViewController("/fxml/ProdutoLista.fxml")
public class ProdutoListaController extends AbstractListaController<Produto>{
  
  @FXML TableColumn<Produto, String> colunaCodigo;
  @FXML TableColumn<Produto, String> colunaNome;
  @FXML TableColumn<Produto, Integer> colunaQuantidade;
  
  @PostConstruct
  public void init(){
    colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
  }
  
  @Override
  public Supplier<ObservableList<Produto>> supplier() {
    return () -> FXCollections.observableArrayList(QueryUtil.selectListByNamedQuery("Produto.findAll"));
  }
}
