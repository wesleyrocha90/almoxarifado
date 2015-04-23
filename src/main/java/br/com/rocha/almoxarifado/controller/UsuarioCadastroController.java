package br.com.rocha.almoxarifado.controller;

import br.com.rocha.almoxarifado.controller.flow.AbstractCadastroController;
import br.com.rocha.almoxarifado.entity.Usuario;

public class UsuarioCadastroController extends AbstractCadastroController<Usuario>{

  @Override
  public Usuario telaParaObjeto() {
    return new Usuario();
  }

  @Override
  public void objetoParaTela(Usuario objeto) {
    
  }
}
