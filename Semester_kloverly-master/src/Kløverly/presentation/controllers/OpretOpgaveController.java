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

  // OpretOpgaveController.java
  // Tilføj denne FXML-reference:
  @FXML
  private TextField pointFelt; // Husk at linke til et TextField med fx:id="pointFelt" i din FXML


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

  // OpretOpgaveController.java (Linje 37)

  @FXML
  public void handleGem(ActionEvent event) {
    // 1. Hent værdier
    String kategori = kategoriValg.getValue();
    String titel = titelFelt.getText();
    String beskrivelse = beskrivelseFelt.getText();
    String pointTekst = pointFelt.getText(); // <-- NYT: Læs inputfeltet

    int point = 0;

    // 2. Tjek om noget mangler OG valider point
    if (kategori == null || titel.isEmpty() || pointTekst.isEmpty()) {
      System.out.println("Fejl: Du skal vælge kategori, titel og point!");
      // Hvis du har en status label, brug den her: status.setText("Udfyld felter!");
      return;
    }

    try {
      // Forsøg at konvertere tekst til et positivt heltal
      point = Integer.parseInt(pointTekst);
      if (point <= 0) {
        System.out.println("Fejl: Point skal være positivt!");
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Fejl: Point skal være et gyldigt tal!");
      return;
    }

    // 3. Opret og gem den rigtige type opgave
    if (model != null) {
      switch (kategori) {
        case "Grøn Opgave":
          // Inkluder point i konstruktøren
          model.addGrønOpgave(new grønneopgaver(titel, beskrivelse, point));
          // Antages at tildele personlige point
          break;
        case "Bytte Opgave":
          // Inkluder point i konstruktøren
          model.addBytteOpgave(new bytteopgaver(titel, beskrivelse, point));
          // Antages at tildele personlige point
          break;
        case "Klima Opgave":
          // Inkluder point i konstruktøren
          model.addKlimaOpgave(new klimaopgaver(titel, beskrivelse, point));
          // Antages at tildele fælles point
          break;
      }
      System.out.println("Gemte: " + titel + " (" + point + " point) i kategorien " + kategori);

      // 4. Luk vinduet efter vi har gemt
      lukVindue(event);
    } else {
      System.out.println("FEJL: Modellen er null (ingen forbindelse til databasen).");
    }
  }

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