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
@Table(name = "USUARIO")
@Access(AccessType.PROPERTY)
@NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.senha = :senha")
public class Usuario extends EntidadeBase implements Externalizable{
    
  @Column(name = "USUARIO")
  public String getUsuario() { return usuario.get(); }
  public void setUsuario(String value) { usuario.set(value); }
  public StringProperty usuarioProperty() { return usuario; }
  private final StringProperty usuario = new SimpleStringProperty();
  
  @Column(name = "SENHA")
  public String getSenha() { return senha.get(); }
  public void setSenha(String value) { senha.set(value); }
  public StringProperty senhaProperty() { return senha; }
  private final StringProperty senha = new SimpleStringProperty();

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    super.writeExternal(out);
    out.writeObject(getUsuario());
    out.writeObject(getSenha());
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    super.readExternal(in);
    setUsuario((String) in.readObject());
    setSenha((String) in.readObject());
  }
}
