package br.com.rocha.almoxarifado.controller.flow;

import br.com.rocha.almoxarifado.util.QueryUtil;
import javax.inject.Inject;

public class SaveActionTask implements Runnable{
  
  @Inject
  private DataModelFlow model;
  
  @Override
  public void run() {
    if(model.getDado() != null){
      if(model.getDado().getId() == null){
        QueryUtil.saveEntity(model.getDado());
      }else{
        QueryUtil.updateEntity(model.getDado());
      }
      model.getDados().add(model.getDado());
    }
  }
}
