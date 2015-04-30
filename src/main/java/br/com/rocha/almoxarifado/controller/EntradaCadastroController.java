package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.Produto;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@ViewController("/fxml/EntradaCadastro.fxml")
public class EntradaCadastroController {
  
//  @ActionTrigger("salvar")
  @FXML private Button salvar;
//  @ActionTrigger("salvarEFechar")
  @FXML private Button salvarEFechar;
  @ActionTrigger("cancelar")
  @FXML private Button cancelar;
  
  @FXML private Button botaoPesquisar;
  @FXML private TextField textoCodigo;
  @FXML private TextField textoNome;
  @FXML private TextField textoQuantidade;
  @FXML private Button botaoInserir;
  
  @FXML private TableView<Produto> tabelaProdutos;
  @FXML private TableColumn<Produto, String> colunaCodigo;
  @FXML private TableColumn<Produto, String> colunaNome;
  @FXML private TableColumn<Produto, String> colunaQuantidade;
  @FXML private TableColumn<Produto, Boolean> colunaRemover;
  
}
