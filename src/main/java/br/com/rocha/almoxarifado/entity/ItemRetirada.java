package br.com.rocha.almoxarifado.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITEM_RETIRADA")
public class ItemRetirada extends EntidadeBase{
  
  @OneToOne
  @JoinColumn(referencedColumnName = "ID")
  @Getter @Setter private Produto produto;
  
  @Column(name = "QUANTIDADE")
  @Getter @Setter private Integer quantidade;
  
  @ManyToOne()
  @JoinColumn(referencedColumnName = "ID")
  private Retirada retirada;
}
