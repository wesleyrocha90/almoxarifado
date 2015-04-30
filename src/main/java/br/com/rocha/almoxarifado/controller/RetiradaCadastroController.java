package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.Produto;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionTrigger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@ViewController("/fxml/RetiradaCadastro.fxml")
public class RetiradaCadastroController {
  
//  @ActionTrigger("salvar")
  @FXML private Button salvar;
//  @ActionTrigger("salvarEFechar")
  @FXML private Button salvarEFechar;
  @ActionTrigger("cancelar")
  @FXML private Button cancelar;  
  
  @FXML private DatePicker textoData;
  @FXML private Button botaoPesquisarFuncionario;
  @FXML private TextField textoCodigoFuncionario;
  @FXML private TextField textoFuncionario;
  @FXML private Button botaoPesquisarProduto;
  @FXML private TextField textoCodigoProduto;
  @FXML private TextField textoNome;
  @FXML private TextField textoQuantidade;
  @FXML private Button botaoInserir;
  
  @FXML private TableView<Produto> tabelaProdutos;
  @FXML private TableColumn<Produto, String> colunaCodigo;
  @FXML private TableColumn<Produto, String> colunaNome;
  @FXML private TableColumn<Produto, String> colunaQuantidade;
  @FXML private TableColumn<Produto, Boolean> colunaRemover;
  
}
