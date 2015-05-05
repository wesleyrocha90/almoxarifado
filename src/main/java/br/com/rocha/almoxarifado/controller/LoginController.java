package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.Usuario;
import br.com.rocha.almoxarifado.util.Maps;
import br.com.rocha.almoxarifado.util.QueryUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.persistence.NoResultException;

public class LoginController implements Initializable {

  @FXML
  private TextField textField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label erro;

  private boolean loginValido;

  @FXML
  private void buttonEntrarOnAction(ActionEvent event) {
    erro.setText("");
    String login = textField.getText();
    String senha = passwordField.getText();
    if ("".equals(login)) {
      erro.setText("Usuário não informado");
    } else if ("".equals(senha)) {
      erro.setText("Senha não informada");
    } else {
      try {
        Usuario usuario = QueryUtil.selectSingleByNamedQuery("Usuario.findByLoginSenha",
                Maps.asMap("usuario", login, "senha", senha));
        if (usuario != null) {
          loginValido = true;
          usuario.setLogado(true);
          QueryUtil.saveEntity(usuario);
          
          erro.getScene().getWindow().hide();
        } else {
          erro.setText("Usuário ou senha incorretos");
        }
      } catch (NoResultException ex) {
        erro.setText("Usuário ou senha incorretos");
      }
    }
  }

  @FXML
  private void textFieldUSuarioOnKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
      if ("".equals(passwordField.getText())) {
        passwordField.requestFocus();
      } else {
        buttonEntrarOnAction(null);
      }
    }
  }

  @FXML
  private void textFieldSenhaOnKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
      if ("".equals(textField.getText())) {
        textField.requestFocus();
      } else {
        buttonEntrarOnAction(null);
      }
    }
  }

  public boolean isLoginValido() {
    return loginValido;
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    erro.setText("");
    loginValido = false;
  }
}
