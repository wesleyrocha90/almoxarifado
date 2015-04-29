package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Usuario;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.apache.commons.lang.StringUtils;

@ViewController("/fxml/UsuarioCadastro.fxml")
public class UsuarioCadastroController extends AbstractCadastroController<Usuario>{
  
  @FXML TextField fieldUsuario;
  @FXML TextField fieldSenha;

  @Override
  public boolean antesDeSalvar() {
    if(StringUtils.isEmpty(fieldUsuario.getText()) || StringUtils.isEmpty(fieldSenha.getText())){
      Alert alert = new Alert(Alert.AlertType.ERROR, "Todos os campos precisam ser preenchidos", ButtonType.OK);
      alert.setTitle("Erro ao salvar");
      alert.setHeaderText(null);
      alert.show();
      return false;
    }else{
      return true;
    }
  }
  
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
