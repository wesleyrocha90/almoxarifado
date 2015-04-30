package br.com.rocha.almoxarifado.controller.flow;

import br.com.rocha.almoxarifado.entity.EntidadeBase;
import br.com.rocha.almoxarifado.util.QueryUtil;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import java.lang.reflect.ParameterizedType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

public abstract class AbstractCadastroController<E extends EntidadeBase> {

  @FXML
  @ActionTrigger("salvar")
  private Button salvar;
  
  @FXML
  @ActionTrigger("salvarEFechar")
  private Button salvarEFechar;
  
  @FXML
  @ActionTrigger("cancelar")
  private Button cancelar;
  
  @ActionHandler
  private FlowActionHandler actionHandler;
  
  @Inject
  private DataModelFlow<E> modelo;
  
  private Class<?> classeLista;
  
  public abstract void telaParaObjeto(E objeto);
  public abstract void objetoParaTela(E objeto);
  public boolean antesDeSalvar(){
    return true;
  }
  
  @ActionMethod("salvar")
  private void salvarAction(){
    if(antesDeSalvar()){
      telaParaObjeto(modelo.getDado());
      salvar();
    }
  }
  
  @ActionMethod("salvarEFechar")
  private void salvarEFecharAction(){
    if(antesDeSalvar()){
      try {
        telaParaObjeto(modelo.getDado());
        salvar();
        actionHandler.navigate(classeLista);
      } catch (VetoException | FlowException ex) {
        System.out.println(ex);
      }
    }
  }
  
  @ActionMethod("cancelar")
  private void cancelarAction(){
    try {
      actionHandler.navigate(classeLista);
    } catch (VetoException | FlowException ex) {
      System.out.println(ex);
    }
  }
  
  private void salvar(){
    if(modelo.getDado() != null){
      if(modelo.getDado().getId() == null){
          QueryUtil.saveEntity(modelo.getDado());
      }else{
        QueryUtil.updateEntity(modelo.getDado());
      }
      modelo.getDados().add(modelo.getDado());
    }
  }
      
  @PostConstruct
  private void initialize(){
    if(modelo != null){
      if(modelo.getIndiceDado() >= 0){
        modelo.setDado(modelo.getDados().get(modelo.getIndiceDado()));
      }else{
        try {
          ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
          String parameterClassName = parameterizedType.getActualTypeArguments()[0].getTypeName();
          modelo.setDado((E) Class.forName(parameterClassName).newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
          System.out.println(ex);
        }
      }
      objetoParaTela(modelo.getDado());
    }
  }
  
  {
    try {
      classeLista = 
          Class.forName(this.getClass().getCanonicalName().replace("CadastroController", "ListaController"));
    } catch (ClassNotFoundException ex) {
      System.out.println(ex);
    }
  }
}
