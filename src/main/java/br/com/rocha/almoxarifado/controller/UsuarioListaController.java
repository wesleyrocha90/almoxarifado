package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractListaController;
import br.com.rocha.almoxarifado.entity.Usuario;
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

@ViewController("/fxml/UsuarioLista.fxml")
public class UsuarioListaController extends AbstractListaController<Usuario>{
  
  @FXML TableColumn<Usuario, String> colunaUsuario;
  
  @PostConstruct
  public void init(){
    colunaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
  }
  
  @Override
  public Supplier<ObservableList<Usuario>> supplier() {
    return () -> FXCollections.observableArrayList(QueryUtil.selectListByNamedQuery("Usuario.findAll"));
  }
  
  @Override
  public ObservableList<String> opcoesFiltro() {
    return FXCollections.observableArrayList("Usuário");
  }
  
  @Override
  public Predicate<Usuario> filtro(String textFiltro, String opcaoFiltro) {
    return (Usuario u) -> {
      switch(opcaoFiltro){
        case "Usuário":
          return u.getUsuario().toUpperCase().contains(textFiltro.toUpperCase());
      }
      return false;
    };
  }
}
