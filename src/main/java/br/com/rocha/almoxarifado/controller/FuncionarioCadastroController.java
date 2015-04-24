package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Funcionario;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@ViewController("/fxml/FuncionarioCadastro.fxml")
public class FuncionarioCadastroController extends AbstractCadastroController<Funcionario>{
  
  @FXML TextField fieldCodigo;
  @FXML TextField fieldNome;
  
  @Override
  public void telaParaObjeto(Funcionario objeto) {
    objeto.setCodigo(fieldCodigo.getText());
    objeto.setNome(fieldNome.getText());
  }

  @Override
  public void objetoParaTela(Funcionario objeto) {
    fieldCodigo.setText(objeto.getCodigo());
    fieldNome.setText(objeto.getNome());
  }
}
