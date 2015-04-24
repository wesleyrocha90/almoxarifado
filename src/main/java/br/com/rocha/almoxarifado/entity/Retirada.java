package br.com.rocha.almoxarifado.entity;

import br.com.rocha.almoxarifado.converter.LocalDatePersistenceConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RETIRADA")
@NamedQuery(name = "Retirada.findAll", query = "SELECT r FROM Retirada r")
public class Retirada extends EntidadeBase implements Serializable{

  @Column(name = "DATA_RETIRADA")
  @Convert(converter = LocalDatePersistenceConverter.class)
  @Getter @Setter private LocalDateTime dataRetirada;

  @ManyToOne
  @JoinColumn(referencedColumnName = "ID")
  @Getter @Setter private Usuario usuario;

  @ManyToOne
  @JoinColumn(referencedColumnName = "ID")
  @Getter @Setter private Funcionario funcionario;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "retirada")
  @Getter @Setter private List<ItemRetirada> itensRetirada;
}
