package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationHelper {

    public static void openWindow(String fxmlName, String title, BeboerModel model) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    NavigationHelper.class.getResource("/fxml/" + fxmlName)
            );

            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller != null && model != null) {
                try {
                    controller.getClass()
                            .getMethod("setModel", BeboerModel.class)
                            .invoke(controller, model);
                } catch (NoSuchMethodException ignored) {
                }
            }

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

