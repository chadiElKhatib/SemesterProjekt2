package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class bytteopgavecontroller { // Husk: Navnet skal matche filnavnet præcist!

  private BeboerModel model;

  // VIGTIGT: Denne metode bruges af din NavigationHelper
  public void setModel(BeboerModel model) {
    this.model = model;
    System.out.println("Model er sat i BytteOpgaver!");
  }

  @FXML
  public void handleTilbage(ActionEvent event) {
    // Lukker vinduet
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }

  // Her kan du tilføje metoder til at oprette nye bytteopgaver senere
}