package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Usuario;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@ViewController("/fxml/UsuarioCadastro.fxml")
public class UsuarioCadastroController extends AbstractCadastroController<Usuario>{
  
  @FXML TextField fieldUsuario;
  @FXML TextField fieldSenha;
  
  @Override
  public void telaParaObjeto(Usuario objeto) {
    objeto.setUsuario(fieldUsuario.getText());
    objeto.setSenha(fieldSenha.getText());
  }

  @Override
  public void objetoParaTela(Usuario objeto) {
    fieldUsuario.setText(objeto.getUsuario());
    fieldSenha.setText(objeto.getSenha());
  }
}
