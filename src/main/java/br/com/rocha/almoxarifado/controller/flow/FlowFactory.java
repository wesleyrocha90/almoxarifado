package br.com.rocha.almoxarifado.controller.flow;

import io.datafx.controller.flow.Flow;

public class FlowFactory {
  
  public static Flow createFlow(String nomeDasClasses){
    try {
      Class<?> classeLista = Class.forName(
          "br.com.rocha.almoxarifado.controller." + nomeDasClasses + "ListaController");
      Flow flow = new Flow(classeLista);
      return flow;
    } catch (ClassNotFoundException ex) {
      System.out.println(ex);
    }
    return null;
  }
}
