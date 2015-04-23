package br.com.rocha.almoxarifado.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class EntidadeBase implements Externalizable{
  
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() { return id.get(); }
  public void setId(Long value) { id.set(value); }
  public LongProperty idProperty() { return id; }
  private final LongProperty id = new SimpleLongProperty();

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeLong(getId());
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    setId(in.readLong());
  }
}
