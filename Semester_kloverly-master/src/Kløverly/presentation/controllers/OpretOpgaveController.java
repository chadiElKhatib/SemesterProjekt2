package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import Kløverly.domain.grønneopgaver;
import Kløverly.domain.bytteopgaver;
import Kløverly.domain.klimaopgaver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OpretOpgaveController {

  // --- FELTER FRA FXML ---
  @FXML private ComboBox<String> kategoriValg;
  @FXML private TextField titelFelt;
  @FXML private TextArea beskrivelseFelt;
  @FXML private Label status; // Hvis du har en status-label (valgfri)

  private BeboerModel model;

  // --- SETUP METODE ---
  public void setModel(BeboerModel model) {
    this.model = model;

    // Vi fylder dropdown-menuen op med valgmulighederne
    kategoriValg.getItems().clear();
    kategoriValg.getItems().addAll("Grøn Opgave", "Bytte Opgave", "Klima Opgave");
  }

  // --- KNAP: GEM OPGAVE ---
  @FXML
  public void handleGem(ActionEvent event) {
    // 1. Hent værdier
    String kategori = kategoriValg.getValue();
    String titel = titelFelt.getText();
    String beskrivelse = beskrivelseFelt.getText();

    // 2. Tjek om noget mangler
    if (kategori == null || titel.isEmpty()) {
      System.out.println("Fejl: Du skal vælge kategori og titel!");
      // Hvis du har en label i din FXML: status.setText("Udfyld felter!");
      return;
    }

    // 3. Opret og gem den rigtige type opgave
    if (model != null) {
      switch (kategori) {
        case "Grøn Opgave":
          model.addGrønOpgave(new grønneopgaver(titel, beskrivelse));
          break;
        case "Bytte Opgave":
          model.addBytteOpgave(new bytteopgaver(titel, beskrivelse));
          break;
        case "Klima Opgave":
          model.addKlimaOpgave(new klimaopgaver(titel, beskrivelse));
          break;
      }
      System.out.println("Gemte: " + titel + " i kategorien " + kategori);

      // 4. Luk vinduet efter vi har gemt
      lukVindue(event);
    } else {
      System.out.println("Fejl: Modellen er null (ingen forbindelse til databasen).");
    }
  }

  // --- KNAP: FORTRYD / TILBAGE ---
  @FXML
  public void handleTilbage(ActionEvent event) {
    lukVindue(event);
  }

  // Hjælpe-metode til at lukke vinduet
  private void lukVindue(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}