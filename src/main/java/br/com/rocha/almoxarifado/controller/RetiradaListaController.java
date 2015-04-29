package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.ItemRetirada;
import br.com.rocha.almoxarifado.entity.Retirada;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.ViewController;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.annotation.PostConstruct;

@ViewController("/fxml/RetiradaLista.fxml")
public class RetiradaListaController {
  
  private ObservableList<Retirada> retiradas;
  private ObservableList<ItemRetirada> itensRetirada;
  
  @FXML private TableView<Retirada> tabelaRetiradas;
  @FXML private TableColumn<Retirada, String> colunaData;
  @FXML private TableColumn<Retirada, String> colunaUsuario;
  @FXML private TableColumn<Retirada, String> colunaFuncionario;
  
  @FXML private TableView<ItemRetirada> tabelaItensRetirada;
  @FXML private TableColumn<ItemRetirada, String> colunaProduto;
  @FXML private TableColumn<ItemRetirada, String> colunaQuantidade;
  
  @FXML private Button botaoRegistrarRetirada; 
  @FXML private Button botaoRegistrarEntrada;
  @FXML private DatePicker filtroPorData;
  @FXML private ComboBox filtroPorUsuario;
  @FXML private ComboBox filtroPorFuncionario;
  @FXML private ComboBox filtroPorProduto;
  @FXML private Button botaoRemoverFiltro;
  
  @PostConstruct
  public void init(){
    colunaData.setCellValueFactory((p) -> 
        new ReadOnlyStringWrapper(p.getValue().getDataRetirada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    colunaUsuario.setCellValueFactory((p) ->
        new ReadOnlyStringWrapper(p.getValue().getUsuario().getUsuario()));
    colunaFuncionario.setCellValueFactory((p) ->
        new ReadOnlyStringWrapper(p.getValue().getFuncionario().getNome()));
    
    colunaProduto.setCellValueFactory((p) ->
        new ReadOnlyStringWrapper(p.getValue().getProduto().getNome()));
    colunaQuantidade.setCellValueFactory((p) ->
        new ReadOnlyStringWrapper(p.getValue().getQuantidade().toString()));
    
    retiradas = FXCollections.observableArrayList(QueryUtil.selectListByNamedQuery("Retirada.findAll"));
    itensRetirada = FXCollections.observableArrayList();
    tabelaRetiradas.setItems(retiradas);
    tabelaRetiradas.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
      if(newValue.intValue() >= 0){
        itensRetirada.setAll(tabelaRetiradas.getItems().get(newValue.intValue()).getItensRetirada());
      }
    });
    tabelaItensRetirada.setItems(itensRetirada);
    
    initFiltros();
  }

  private void initFiltros() {
    filtroPorUsuario.setItems(FXCollections.observableArrayList(
        retiradas.stream()
            .map(r -> r.getUsuario().getUsuario())
            .collect(Collectors.toSet())));
    filtroPorFuncionario.setItems(FXCollections.observableArrayList(
        retiradas.stream()
            .map(r -> r.getFuncionario().getNome())
            .collect(Collectors.toSet())));
    filtroPorProduto.setItems(FXCollections.observableArrayList(
        retiradas.stream()
            .flatMap(r -> r.getItensRetirada().stream())
            .map(ir -> ir.getProduto().getNome())
            .collect(Collectors.toSet())));
  }
}
