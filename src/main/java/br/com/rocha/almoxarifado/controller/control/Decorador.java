package br.com.rocha.almoxarifado.controller.control;

import java.util.function.Predicate;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

public class Decorador {

  public static void decorarNos(Decorador.Tipo tipo, Node... nos) {
    for (Node no : nos) {
      if (no instanceof TextInputControl) {
        ((TextInputControl) no).textProperty().addListener((ob, ov, nv) -> {
          if (tipo.test(nv)) {
            no.getStyleClass().add(tipo.getStyleClass());
          } else {
            no.getStyleClass().removeAll(tipo.getStyleClass());
          }
        });
      }
    }
  }

  public enum Tipo {

    OBRIGATORIO("campoObrigatorio", (s) -> s == null || "".equals(s));
    
    private String styleClass;
    private Predicate<String> test;
    
    private Tipo(String styleClass, Predicate<String> test){
        this.styleClass = styleClass;
        this.test = test;
    }
    
    public String getStyleClass() {
        return this.styleClass;
    }
    
    public boolean test(String s){
        return this.test.test(s);
    }
  }
}
