package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import Kløverly.domain.grønneopgaver;
import Kløverly.domain.bytteopgaver;
import Kløverly.domain.klimaopgaver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OpretOpgaveController {

    public Button GemOpgave;
    @FXML
  private TextField pointFelt;

  @FXML private ComboBox<String> kategoriValg;
  @FXML private TextField titelFelt;
  @FXML private TextArea beskrivelseFelt;
  @FXML private Label status;

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;

    kategoriValg.getItems().clear();
    kategoriValg.getItems().addAll("Grøn Opgave", "Bytte Opgave", "Klima Opgave");
  }

  @FXML
  public void handleGem(ActionEvent event) {
    // 1. Hent værdier
    String kategori = kategoriValg.getValue();
    String titel = titelFelt.getText();
    String beskrivelse = beskrivelseFelt.getText();
    String pointTekst = pointFelt.getText();




    int point = 0;

    if (kategori == null || titel.isEmpty() || pointTekst.isEmpty()) {
      System.out.println("Fejl: Du skal vælge kategori, titel og point!");
      return;
    }

    try {
      point = Integer.parseInt(pointTekst);
      if (point <= 0) {
        System.out.println("Fejl: Point skal være positivt!");
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("Fejl: Point skal være et gyldigt tal!");
      return;
    }

    if (model != null) {
      switch (kategori) {
        case "Grøn Opgave":
          model.addGrønOpgave(new grønneopgaver(titel, beskrivelse, point));
          break;
        case "Bytte Opgave":
          model.addBytteOpgave(new bytteopgaver(titel, beskrivelse, point));
          break;
        case "Klima Opgave":
          model.addKlimaOpgave(new klimaopgaver(titel, beskrivelse, point));
          break;
      }
      System.out.println("Gemte: " + titel + " (" + point + " point) i kategorien " + kategori);

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