package br.com.rocha.almoxarifado.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@Access(AccessType.PROPERTY)
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
public class Funcionario extends EntidadeBase implements Externalizable{
  
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
  
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    super.writeExternal(out);
    out.writeObject(getCodigo());
    out.writeObject(getNome());
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    super.readExternal(in);
    setCodigo((String) in.readObject());
    setNome((String) in.readObject());
  }
  
}
