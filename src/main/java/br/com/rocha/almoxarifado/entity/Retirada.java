package br.com.rocha.almoxarifado.entity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDateTime;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "RETIRADA")
@Access(AccessType.PROPERTY)
@NamedQuery(name = "Retirada.findAll", query = "SELECT r FROM Retirada r")
public class Retirada extends EntidadeBase implements Externalizable{
	
  @Column(name = "DATA_RETIRADA")
  public LocalDateTime getDataRetirada() { return dataRetirada.get(); }
  public void setDataRetirada(LocalDateTime value) { dataRetirada.set(value); }
  public ObjectProperty<LocalDateTime> dataRetiradaProperty() { return dataRetirada; }
  private ObjectProperty<LocalDateTime> dataRetirada = new SimpleObjectProperty<>();

  @ManyToOne
  @JoinColumn(referencedColumnName = "ID")
  public Usuario getUsuario() { return usuario.get(); }
  public void setUsuario(Usuario value) { usuario.set(value); }
  public ObjectProperty<Usuario> usuarioProperty() { return usuario; }
  private ObjectProperty<Usuario> usuario = new SimpleObjectProperty<>();

  @ManyToOne
  @JoinColumn(referencedColumnName = "ID")
  public Funcionario getFuncionario() { return funcionario.get(); }
  public void setFuncionario(Funcionario value) { funcionario.set(value); }
  public ObjectProperty<Funcionario> funcionarioProperty() { return funcionario; }
  private ObjectProperty<Funcionario> funcionario = new SimpleObjectProperty<>();

  @ManyToMany
  @JoinTable(name = "RETIRADA_PRODUTOS", 
      joinColumns = { @JoinColumn(name = "RETIRADA_ID") },
      inverseJoinColumns = { @JoinColumn(name = "PRODUTO_ID") })
  public List<Produto> getProdutos() { return produtos; }
  public void setProdutos(List<Produto> value) { produtos.setAll(value); }
  private ObservableList<Produto> produtos = FXCollections.emptyObservableList();

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    super.writeExternal(out);
    out.writeObject(getUsuario());
    out.writeObject(getFuncionario());
    out.writeObject(getProdutos());
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    super.readExternal(in);
    setUsuario((Usuario) in.readObject());
    setFuncionario((Funcionario) in.readObject());
    setProdutos((ObservableList<Produto>) in.readObject());
  }
}
