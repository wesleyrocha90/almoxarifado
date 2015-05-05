package br.com.rocha.almoxarifado.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUTO")
@NamedQueries({
  @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
  @NamedQuery(name = "Produto.findById", query = "SELECT p FROM Produto p WHERE p.id = :id")
})
public class Produto extends EntidadeBase implements Serializable, Cloneable{
  
  @Column(name = "CODIGO")
  @Getter @Setter private String codigo;
  
  @Column(name = "NOME")
  @Getter @Setter private String nome;
  
  @Column(name = "QUANTIDADE")
  @Getter @Setter private Integer quantidade;

  @Override
  public Object clone() {
    Produto produto = new Produto();
    produto.setId(getId());
    produto.setCodigo(getCodigo());
    produto.setNome(getNome());
    produto.setQuantidade(getQuantidade());
    return produto;
  }
}
