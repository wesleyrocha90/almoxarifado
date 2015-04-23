package br.com.rocha.almoxarifado.controller.flow;

import br.com.rocha.almoxarifado.entity.EntidadeBase;
import io.datafx.controller.injection.scopes.FlowScoped;
import java.util.function.Supplier;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

@FlowScoped
public class DataModelFlow<E extends EntidadeBase> {
  
  private Supplier<ObservableList<E>> supplier;
  private ListProperty<E> dados;
  private ObjectProperty<E> dado;
  private IntegerProperty indiceDado;

  public void setSupplier(Supplier<ObservableList<E>> supplier){
    this.supplier = supplier;
    dados = new SimpleListProperty<>(supplier.get());
  }
  
  public ListProperty<E> getDados(){
    return dados;
  }
  
  public E getDado(){
    return dadoProperty().get();
  }
  
  public void setDado(E dado){
    dadoProperty().set(dado);
  }
  
  public ObjectProperty<E> dadoProperty() {
    return (dado == null) ? dado = new SimpleObjectProperty<>() : dado;
  }
  
   public int getIndiceDado() {
    return indiceDadoProperty().get();
  }

  public void setIndiceDado(int indiceDado) {
    indiceDadoProperty().set(indiceDado);
  }

  public IntegerProperty indiceDadoProperty() {
    return (indiceDado == null) ? indiceDado = new SimpleIntegerProperty() : indiceDado;
  }
}
