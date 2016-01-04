package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.control.Decorador;
import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Funcionario;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;

@ViewController("/fxml/FuncionarioCadastro.fxml")
public class FuncionarioCadastroController extends AbstractCadastroController<Funcionario>{
  
  @FXML TextField fieldCodigo;
  @FXML TextField fieldNome;
  
  @PostConstruct
  public void postConstruct() {
      Decorador.decorarNos(Decorador.Tipo.OBRIGATORIO, fieldCodigo, fieldNome);
  }
  
  @Override
  public boolean antesDeSalvar() {
    if(StringUtils.isEmpty(fieldCodigo.getText()) 
        || StringUtils.isEmpty(fieldNome.getText())){
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
