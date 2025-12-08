package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class klimaopgavecontroller {

  private BeboerModel model;

  // VIGTIGT: Denne metode gør at NavigationHelper kan sende data videre
  public void setModel(BeboerModel model) {
    this.model = model;
    System.out.println("Model er sat i KlimaOpgaver!");
  }

  @FXML
  public void handleTilbage(ActionEvent event) {
    // Lukker vinduet
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}