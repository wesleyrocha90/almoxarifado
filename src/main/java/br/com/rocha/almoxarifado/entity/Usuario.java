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
@Table(name = "USUARIO")
@NamedQueries({
  @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
  @NamedQuery(name = "Usuario.findByLoginSenha", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.senha = :senha"),
  @NamedQuery(name = "Usuario.findLogado", query = "SELECT u FROM Usuario u WHERE u.logado = TRUE")
})
public class Usuario extends EntidadeBase implements Serializable{
    
  @Column(name = "USUARIO")
  @Getter @Setter private String usuario;
  
  @Column(name = "SENHA")
  @Getter @Setter private String senha;
  
  @Column(name = "LOGADO")
  @Getter @Setter private boolean logado;
}
