package br.com.rocha.almoxarifado.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class ProdutoFX extends EntidadeBase {
  
  private StringProperty codigo;
  public StringProperty codigoProperty() {
      return codigo;
  }
  public String getCodigo() {
      return codigoProperty().get();
  }
  public void setCodigo(String codigo) {
      codigoProperty().set(codigo);
  }
  
  private StringProperty nome;
  
  private IntegerProperty quantidade;
}
