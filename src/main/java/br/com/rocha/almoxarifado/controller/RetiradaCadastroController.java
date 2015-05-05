package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.entity.Funcionario;
import br.com.rocha.almoxarifado.entity.ItemRetirada;
import br.com.rocha.almoxarifado.entity.Produto;
import br.com.rocha.almoxarifado.entity.Retirada;
import br.com.rocha.almoxarifado.util.Maps;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;

@ViewController("/fxml/RetiradaCadastro.fxml")
public class RetiradaCadastroController {
  
  private Funcionario funcionario;
  private Produto produto;
  private ObservableList<Produto> produtos;
  
  @ActionTrigger("salvar")
  @FXML private Button salvar;
  @ActionTrigger("cancelar")
  @FXML private Button cancelar;
  
  @FXML private DatePicker textoData;
  @ActionTrigger("pesquisarFuncionario")
  @FXML private Button botaoPesquisarFuncionario;
  @FXML private TextField textoCodigoFuncionario;
  @FXML private TextField textoFuncionario;
  @ActionTrigger("pesquisarProduto")
  @FXML private Button botaoPesquisarProduto;
  @FXML private TextField textoCodigoProduto;
  @FXML private TextField textoNomeProduto;
  @FXML private TextField textoQuantidadeProduto;
  @ActionTrigger("inserirProduto")
  @FXML private Button botaoInserir;
  
  @FXML private TableView<Produto> tabelaProdutos;
  @FXML private TableColumn<Produto, String> colunaCodigo;
  @FXML private TableColumn<Produto, String> colunaNome;
  @FXML private TableColumn<Produto, String> colunaQuantidade;
  @FXML private TableColumn<Produto, Boolean> colunaRemover;
  
  @ActionHandler
  private FlowActionHandler actionHandler;
  
  @PostConstruct
  public void init(){
    produtos = FXCollections.observableArrayList();
    tabelaProdutos.setItems(produtos);
    colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
  }
  
  @ActionMethod("salvar")
  public void salvarAction(){
    try {
      if(textoData.getValue() != null && 
          StringUtils.isNotEmpty(textoCodigoFuncionario.getText()) && 
          StringUtils.isNotEmpty(textoFuncionario.getText())){
        if(produtos.size() > 0){
          List<ItemRetirada> itensRetirada = new ArrayList<>();
          for (Produto p : produtos) {
            Produto pb = QueryUtil.selectSingleByNamedQuery("Produto.findById", Maps.asMap("id", p.getId()));
            pb.setQuantidade(pb.getQuantidade() - p.getQuantidade());
            QueryUtil.saveEntity(pb);

            ItemRetirada item = new ItemRetirada();
            item.setProduto(pb);
            item.setQuantidade(p.getQuantidade());
            itensRetirada.add(item);
          }

          Retirada retirada = new Retirada();
          retirada.setDataRetirada(textoData.getValue());
          retirada.setFuncionario(funcionario);
          retirada.setUsuario(QueryUtil.selectSingleByNamedQuery("Usuario.findLogado"));
          retirada.setItensRetirada(itensRetirada);

          QueryUtil.saveEntity(retirada);

          actionHandler.navigateBack();
        }else{
          Alert alert = new Alert(Alert.AlertType.WARNING, "Nenhum produto inserido.", ButtonType.OK);
          alert.setTitle("Aviso");
          alert.setHeaderText(null);
          alert.show();
        }
      }else{
        Alert alert = new Alert(Alert.AlertType.WARNING, "Informe todos os dados pra registrar a saída.", ButtonType.OK);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.show();
      }
    } catch (VetoException | FlowException ex) {
      System.out.println(ex);
    }
  }
  
  @ActionMethod("pesquisarFuncionario")
  public void botaoPesquisarFuncionarioAction(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FuncionarioPesquisa.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      FuncionarioPesquisaController ppc = (FuncionarioPesquisaController) fxmlLoader.getController();
      ppc.setStage(stage);
      stage.setTitle("Pesquisar produto");
      stage.setScene(scene);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
      
      if(ppc.getFuncionario() != null){
        funcionario = ppc.getFuncionario();
        textoCodigoFuncionario.setText(funcionario.getCodigo());
        textoFuncionario.setText(funcionario.getNome());
      }
        
    } catch (IOException ex) {
      System.out.println(ex);
    }
  }
  
  @ActionMethod("pesquisarProduto")
  public void botaoPesquisarProdutoAction(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ProdutoPesquisa.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root);
      Stage stage = new Stage();
      ProdutoPesquisaController ppc = (ProdutoPesquisaController) fxmlLoader.getController();
      ppc.setStage(stage);
      stage.setTitle("Pesquisar produto");
      stage.setScene(scene);
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
      
      if(ppc.getProduto() != null){
        produto = (Produto) ppc.getProduto().clone();
        textoCodigoProduto.setText(produto.getCodigo());
        textoNomeProduto.setText(produto.getNome());
        textoQuantidadeProduto.setText(produto.getQuantidade().toString());
        textoQuantidadeProduto.requestFocus();
      }
      
    } catch (IOException ex) {
      System.out.println(ex);
    }
  }
  
  @ActionMethod("inserirProduto")
  public void botaoInserirAction(){
    try{
      if(StringUtils.isNotEmpty(textoCodigoProduto.getText()) && 
          StringUtils.isNotEmpty(textoNomeProduto.getText()) &&
          StringUtils.isNotEmpty(textoQuantidadeProduto.getText())){
        Integer quantidade = Integer.parseInt(textoQuantidadeProduto.getText());
        if(produto.getQuantidade() >= quantidade){
          produto.setQuantidade(quantidade);
          produtos.add(produto);
          textoCodigoProduto.setText("");
          textoNomeProduto.setText("");
          textoQuantidadeProduto.setText("");
        }else{
          Alert alert = new Alert(Alert.AlertType.WARNING, "Quantidade maior do que o estoque.", ButtonType.OK);
          alert.setTitle("Aviso");
          alert.setHeaderText(null);
          alert.show();
        }
      }else{
        Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha os dados do produto primeiro.", ButtonType.OK);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.show();
      }
    }catch(Exception ex){
      System.out.println(ex);
    }
  }
}
