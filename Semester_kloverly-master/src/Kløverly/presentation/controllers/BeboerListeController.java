package Kløverly.presentation.controllers;

import Kløverly.domain.Beboer;
import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BeboerListeController  {

  @FXML
  private ListView<Beboer> liste;

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;

    if (model != null) {
      liste.setItems(model.getBeboer());
    }
  }

  @FXML
  private void handleSlet() {
    Beboer valgt = liste.getSelectionModel().getSelectedItem();

    if (valgt != null && model != null) {
      model.sletBeboer(valgt);

      liste.setItems(model.getBeboer());

      System.out.println("Slettede beboer: " + valgt);
    } else {
      System.out.println("Ingen beboer valgt.");
    }
  }

  @FXML
  private void handleRediger() {
    Beboer valgt = liste.getSelectionModel().getSelectedItem();
    if (valgt != null) {
      System.out.println("Rediger funktion er ikke lavet endnu for: " + valgt);
    }
  }

  @FXML
  private void handleTilbage(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}