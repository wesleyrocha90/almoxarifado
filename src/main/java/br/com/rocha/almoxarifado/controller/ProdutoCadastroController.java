package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Produto;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@ViewController("/fxml/ProdutoCadastro.fxml")
public class ProdutoCadastroController extends AbstractCadastroController<Produto>{
  
  @FXML TextField fieldCodigo;
  @FXML TextField fieldNome;
  @FXML TextField fieldQuantidade;
  
  @Override
  public void telaParaObjeto(Produto objeto) {
    objeto.setCodigo(fieldCodigo.getText());
    objeto.setNome(fieldNome.getText());
    objeto.setQuantidade(new Integer(fieldQuantidade.getText()));
  }

  @Override
  public void objetoParaTela(Produto objeto) {
    fieldCodigo.setText(objeto.getCodigo());
    fieldNome.setText(objeto.getNome());
    fieldQuantidade.setText(objeto.getQuantidade() == null ? "" : objeto.getQuantidade().toString());
  }
}
