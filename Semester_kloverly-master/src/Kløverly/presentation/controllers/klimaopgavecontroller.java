package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class klimaopgavecontroller {

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;
    System.out.println("Model er sat i KlimaOpgaver!");
  }

  @FXML
  public void handleTilbage(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}