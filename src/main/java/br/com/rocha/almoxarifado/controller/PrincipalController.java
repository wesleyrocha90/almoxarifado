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
    Tab tab = new Tab("Entrada / Saída");
    boolean temTabua = false;
    for (Tab tabua : tabPane.getTabs()) {
      if (tabua.getText().equals(tab.getText())) {
        tabPane.getSelectionModel().select(tabua);
        temTabua = true;
      }
    }
    if (!temTabua) {
      try {
        Flow flow = new Flow(RetiradaListaController.class)
            .withLink(RetiradaListaController.class, "registrarRetirada", RetiradaCadastroController.class)
            .withLink(RetiradaListaController.class, "registrarEntrada", EntradaCadastroController.class)
            .withLink(RetiradaCadastroController.class, "cancelar", RetiradaListaController.class)
            .withLink(EntradaCadastroController.class, "cancelar", RetiradaListaController.class);
        tab.setContent(flow.start());
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
      } catch (SecurityException | FlowException ex) {
        System.out.println(ex);
      }
    }
  }
  
  @FXML
  public void onProdutoAction(ActionEvent event){
    openTab("Produto", "Produto");
  }
  
  @FXML
  public void onFuncionarioAction(ActionEvent event){
    openTab("Funcionário", "Funcionario");
  }
  
  @FXML
  public void onUsuarioAction(ActionEvent event){
    openTab("Usuário", "Usuario");
  }
  
  private void openTab(String tituloTab, String fxmlFlow){
    Tab tab = new Tab(tituloTab);
    boolean temTabua = false;
    for (Tab tabua : tabPane.getTabs()) {
      if (tabua.getText().equals(tab.getText())) {
        tabPane.getSelectionModel().select(tabua);
        temTabua = true;
      }
    }
    if (!temTabua) {
      try {
        Flow flow = FlowFactory.createFlow(fxmlFlow);
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
