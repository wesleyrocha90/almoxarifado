package br.com.rocha.almoxarifado.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUTO")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto extends EntidadeBase implements Serializable{
  
  @Column(name = "CODIGO")
  @Getter @Setter private String codigo;
  
  @Column(name = "NOME")
  @Getter @Setter private String nome;
  
  @Column(name = "QUANTIDADE")
  @Getter @Setter private Integer quantidade;
}
