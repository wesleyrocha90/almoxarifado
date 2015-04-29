package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Produto;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.apache.commons.lang.StringUtils;

@ViewController("/fxml/ProdutoCadastro.fxml")
public class ProdutoCadastroController extends AbstractCadastroController<Produto>{
  
  @FXML TextField fieldCodigo;
  @FXML TextField fieldNome;
  @FXML TextField fieldQuantidade;
  
  @Override
  public boolean antesDeSalvar() {
    if(StringUtils.isEmpty(fieldCodigo.getText()) 
        || StringUtils.isEmpty(fieldNome.getText())
        || StringUtils.isEmpty(fieldQuantidade.getText())){
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
