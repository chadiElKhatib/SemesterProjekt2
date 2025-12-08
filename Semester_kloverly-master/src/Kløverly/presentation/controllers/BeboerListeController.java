package Kløverly.presentation.controllers;

import Kløverly.domain.Beboer;
import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class BeboerListeController {

  // Forbinder til ListViewet i din FXML-fil (husk fx:id="liste")
  @FXML
  private ListView<Beboer> liste;

  private BeboerModel model;

  // Denne metode kaldes automatisk af din NavigationHelper
  public void setModel(BeboerModel model) {
    this.model = model;

    // Her henter vi listen fra modellen og viser den på skærmen med det samme
    if (model != null) {
      liste.setItems(model.getBeboer());
    }
  }

  @FXML
  private void handleSlet() {
    // 1. Find den beboer, brugeren har markeret
    Beboer valgt = liste.getSelectionModel().getSelectedItem();

    if (valgt != null && model != null) {
      // 2. Slet fra databasen (modellen)
      model.sletBeboer(valgt);

      // 3. Opdater listen på skærmen, så beboeren forsvinder visuelt
      liste.setItems(model.getBeboer());

      System.out.println("Slettede beboer: " + valgt);
    } else {
      System.out.println("Ingen beboer valgt.");
    }
  }

  // Hvis du har en "Rediger" knap i din FXML (fra dit tidligere screenshot)
  @FXML
  private void handleRediger() {
    Beboer valgt = liste.getSelectionModel().getSelectedItem();
    if (valgt != null) {
      System.out.println("Rediger funktion er ikke lavet endnu for: " + valgt);
      // Her kan du senere kode logikken til at åbne et redigeringsvindue
    }
  }

  // Knap til at lukke vinduet og gå tilbage
  @FXML
  private void handleTilbage(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}