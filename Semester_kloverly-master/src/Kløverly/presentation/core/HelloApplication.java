package Kløverly.presentation.core;

import Kløverly.domain.BeboerModel;
import Kløverly.presentation.controllers.HomeMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeMenu.fxml"));
    Scene scene = new Scene(loader.load());

    HomeMenuController controller = loader.getController();

    BeboerModel gemtModel = Kløverly.persistense.Datamanager.hentModel();

    controller.setModel(gemtModel);

    stage.setTitle("Kløverly systemet!");
    stage.setScene(scene);
    stage.show();
  }
}