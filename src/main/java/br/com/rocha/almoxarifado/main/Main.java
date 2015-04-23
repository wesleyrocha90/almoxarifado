package br.com.rocha.almoxarifado.main;

import br.com.rocha.almoxarifado.controller.LoginController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    if (verificaLogin(new Stage())) {
      exibirPrincipal(new Stage());
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  public boolean verificaLogin(Stage stage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    Scene scene = new Scene(root);
    stage.setTitle("Login");
    stage.setScene(scene);
    stage.getIcons().add(new Image(getClass().getResource("/icons/" + "warehouse_512.png").toString()));
    stage.showAndWait();

    LoginController loginController = (LoginController) fxmlLoader.getController();
    stage.close();
    return loginController.isLoginValido();
  }

  public void exibirPrincipal(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Principal.fxml"));
    Scene scene = new Scene(root);
    stage.setTitle("Principal");
    stage.setMaximized(true);
    stage.setScene(scene);
    stage.getIcons().add(new Image(getClass().getResource("/icons/" + "warehouse_512.png").toString()));
    stage.show();
  }
}
