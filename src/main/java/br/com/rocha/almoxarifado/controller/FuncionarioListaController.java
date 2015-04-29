package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractListaController;
import br.com.rocha.almoxarifado.entity.Funcionario;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.ViewController;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.annotation.PostConstruct;

@ViewController("/fxml/FuncionarioLista.fxml")
public class FuncionarioListaController extends AbstractListaController<Funcionario>{
  
  @FXML TableColumn<Funcionario, String> colunaCodigo;
  @FXML TableColumn<Funcionario, String> colunaNome;
  
  @PostConstruct
  public void init(){
    colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
  }
  
  @Override
  public Supplier<ObservableList<Funcionario>> supplier() {
    return () -> FXCollections.observableArrayList(QueryUtil.selectListByNamedQuery("Funcionario.findAll"));
  }

  @Override
  public ObservableList<String> opcoesFiltro() {
    return FXCollections.observableArrayList("Código", "Nome");
  }

  @Override
  public Predicate<Funcionario> filtro(String textFiltro, String opcaoFiltro) {
    return (Funcionario f) -> {
      switch(opcaoFiltro){
        case "Código":
          return f.getCodigo().equals(textFiltro);
        case "Nome":
          return f.getNome().toUpperCase().contains(textFiltro.toUpperCase());
      }
      return false;
    };
  }
}
