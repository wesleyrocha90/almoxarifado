package br.com.rocha.almoxarifado.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto extends EntidadeBase implements Externalizable{
  
  @Column(name = "CODIGO")
  public String getCodigo() { return codigo.get(); }
  public void setCodigo(String value) { codigo.set(value); }
  public StringProperty codigoProperty() { return codigo; }
  private final StringProperty codigo = new SimpleStringProperty();
  
  @Column(name = "NOME")
  public String getNome() { return nome.get(); }
  public void setNome(String value) { nome.set(value); }
  public StringProperty nomeProperty() { return nome; }
  private final StringProperty nome = new SimpleStringProperty();
  
  @Column(name = "QUANTIDADE")
  public Integer getQuantidade() { return quantidade.get(); }
  public void setQuantidade(Integer value) { quantidade.set(value); }
  public IntegerProperty quantidadeProperty() { return quantidade; }
  private final IntegerProperty quantidade = new SimpleIntegerProperty();
  
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    super.writeExternal(out);
    out.writeObject(getCodigo());
    out.writeObject(getNome());
    out.writeInt(getQuantidade());
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    super.readExternal(in);
    setCodigo((String) in.readObject());
    setNome((String) in.readObject());
    setQuantidade(in.readInt());
  }
}
