package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.FlowFactory;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PrincipalController implements Initializable {
  
  @FXML TabPane tabPane;
  @FXML Button botaoEntradaSaida;
  @FXML Button botaoProduto;
  @FXML Button botaoFuncionario;
  @FXML Button botaoUsuario;
  
  @FXML
  public void onEntradaSaidaAction(ActionEvent event){
    System.out.println("Entrada / Saída");
  }
  
  @FXML
  public void onProdutoAction(ActionEvent event){
    System.out.println("Produto");
  }
  
  @FXML
  public void onFuncionarioAction(ActionEvent event){
    System.out.println("Funcionário");
  }
  
  @FXML
  public void onUsuarioAction(ActionEvent event){
    Tab tab = new Tab("Usuário");
    boolean temTabua = false;
    for (Tab tabua : tabPane.getTabs()) {
      if (tabua.getText().equals(tab.getText())) {
        tabPane.getSelectionModel().select(tabua);
        temTabua = true;
      }
    }
    if (!temTabua) {
      try {
        Flow flow = FlowFactory.createFlow("Usuario");
        tab.setContent(flow.start());
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
      } catch (SecurityException | FlowException ex) {
        System.out.println(ex);
      }
    }
  }
  
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
