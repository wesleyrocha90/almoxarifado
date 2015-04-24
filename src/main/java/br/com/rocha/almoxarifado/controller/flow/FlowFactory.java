package br.com.rocha.almoxarifado.controller.flow;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.action.FlowActionChain;
import io.datafx.controller.flow.action.FlowLink;
import io.datafx.controller.flow.action.FlowMethodAction;
import io.datafx.controller.flow.action.FlowTaskAction;

public class FlowFactory {
  
  public static Flow createFlow(String nomeDasClasses){
    try {
      Class<?> classeLista = Class.forName(
          "br.com.rocha.almoxarifado.controller." + nomeDasClasses + "ListaController");
      Class<?> classeCadastro = Class.forName(
          "br.com.rocha.almoxarifado.controller." + nomeDasClasses + "CadastroController");
      
      Flow flow = new Flow(classeLista)
          .withLink(classeLista, "editar", classeCadastro)
          .withAction(classeLista, "criar", new FlowActionChain(
              new FlowMethodAction(classeLista.getMethod("onBotaoCriarNovoAction")),
              new FlowLink(classeCadastro)))
          .withTaskAction(classeLista, "remover", RemoveActionTask.class)
          .withAction(classeCadastro, "salvar", new FlowActionChain(
              new FlowMethodAction(classeCadastro.getMethod("onSaveAction")),
              new FlowTaskAction(SaveActionTask.class)))
          .withAction(classeCadastro, "salvarEFechar", new FlowActionChain(
              new FlowMethodAction(classeCadastro.getMethod("onSaveAction")),
              new FlowTaskAction(SaveActionTask.class),
              new FlowLink(classeLista)))
          .withLink(classeCadastro, "cancelar", classeLista);
      return flow;
      
    } catch (ClassNotFoundException | NoSuchMethodException ex) {
      System.out.println(ex);
    }
    return null;
  }
}
