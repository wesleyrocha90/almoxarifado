package br.com.rocha.almoxarifado.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
public class Funcionario extends EntidadeBase implements Serializable{
  
  @Column(name = "CODIGO")
  @Getter @Setter private String codigo;
  
  @Column(name = "NOME")
  @Getter @Setter private String nome;
}
