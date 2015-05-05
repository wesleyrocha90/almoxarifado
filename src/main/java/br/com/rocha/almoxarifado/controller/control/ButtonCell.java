package br.com.rocha.almoxarifado.controller.control;

import br.com.rocha.almoxarifado.entity.Produto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<Produto, Boolean> {

  final Button cellButton = new Button("Delete");

  public ButtonCell(EventHandler<ActionEvent> actionEvent) {
    cellButton.setOnAction(actionEvent);
  }

  @Override
  protected void updateItem(Boolean t, boolean empty) {
    super.updateItem(t, empty);
    if (!empty) {
      setGraphic(cellButton);
    }
  }
}
