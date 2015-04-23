package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractListaController;
import br.com.rocha.almoxarifado.entity.Usuario;
import br.com.rocha.almoxarifado.util.QueryUtil;
import java.util.function.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.annotation.PostConstruct;

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
}
