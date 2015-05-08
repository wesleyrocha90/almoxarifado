package br.com.rocha.almoxarifado.controller.control;

import br.com.rocha.almoxarifado.entity.Produto;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;

public class ButtonCell extends TableCell<Produto, Boolean> {

  final Button cellButton = new Button();

  public ButtonCell() {
    ImageView icon = new ImageView(getClass().getResource("/icons/minus_16.png").toString());
    icon.setFitHeight(10);
    icon.setFitWidth(10);
    cellButton.setGraphic(icon);
    
    cellButton.setOnAction(event -> {
      this.getTableView().getItems().remove(this.getTableRow().getIndex());
    });
  }

  @Override
  protected void updateItem(Boolean t, boolean empty) {
    super.updateItem(t, empty);
    if (!empty) {
      setGraphic(cellButton);
    }
  }
}
