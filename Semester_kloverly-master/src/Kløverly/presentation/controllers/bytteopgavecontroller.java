package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class bytteopgavecontroller {

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;
    System.out.println("Model er sat i BytteOpgaver!");
  }

  @FXML
  public void handleTilbage(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}