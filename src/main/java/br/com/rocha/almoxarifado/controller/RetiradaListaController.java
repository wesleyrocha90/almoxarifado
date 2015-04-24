package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.Retirada;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.ViewController;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javax.annotation.PostConstruct;

@ViewController("/fxml/RetiradaLista.fxml")
public class RetiradaListaController {
  
  private List<Retirada> retiradas;
  
  @FXML TreeTableView<Retirada> tabelaRetiradas;
  @FXML TreeTableColumn<Retirada, String> colunaData;
  @FXML TreeTableColumn<Retirada, String> colunaFuncionario;
//  @FXML TreeTableColumn colunaItens;
//  @FXML TreeTableColumn colunaProduto;
//  @FXML TreeTableColumn colunaQuantidade;
  
  @PostConstruct
  public void init(){
    retiradas = FXCollections.observableArrayList(QueryUtil.selectListByNamedQuery("Retirada.findAll"));
    
    colunaData.setCellValueFactory(p -> 
        new ReadOnlyStringWrapper(p.getValue().getValue().toString()));
    
    TreeItem<Retirada> root = new TreeItem<>(new Retirada());
    retiradas.forEach((r) -> root.getChildren().add(new TreeItem<>(r)));
    
    tabelaRetiradas.setRoot(root);
  }
}
