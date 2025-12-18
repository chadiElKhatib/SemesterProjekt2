package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import Kløverly.domain.bytteopgaver;
import Kløverly.domain.grønneopgaver;
import Kløverly.domain.klimaopgaver;
import Kløverly.persistense.Datamanager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OpretOpgaveController {

  @FXML
  public void initialize() {
    if (pointSlider != null && sliderVærdiLabel != null) {

      pointSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
        int afrundetPoint = (int) Math.round(newValue.doubleValue());

        sliderVærdiLabel.setText("Point: " + afrundetPoint);
      });

      int startPoint = (int) Math.round(pointSlider.getValue());
      sliderVærdiLabel.setText("Point: " + startPoint);
    }
  }

  public Button GemOpgave;
  @FXML
  private Slider pointSlider;

  @FXML
  private Label sliderVærdiLabel;
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


  public void handleGem(ActionEvent event) {
    String kategori = kategoriValg.getValue();
    String titel = titelFelt.getText();
    String beskrivelse = beskrivelseFelt.getText();

    int point = (int) Math.round(pointSlider.getValue());

    if (kategori == null || titel.isEmpty()) {
      System.out.println("Fejl: Du skal vælge kategori og titel!");
      return;
    }

    if (model != null) {
      switch (kategori)
      {
        case "Grøn Opgave":
          model.addGrønOpgave(new grønneopgaver(titel, beskrivelse, point));
          for (grønneopgaver grønneopgaver : FXCollections.observableArrayList(
              this.model.getGrønneOpgaverList()))
          {
            if (grønneopgaver == null);
          }

          break;
        case "Bytte Opgave":
          model.addBytteOpgave(new bytteopgaver(titel, beskrivelse, point));
          break;
        case "Klima Opgave":
          model.addKlimaOpgave(new klimaopgaver(titel, beskrivelse, point));
          break;
      }
      Datamanager.gemModel(model);
      System.out.println("Gemte: " + titel + " (" + point + " point) i kategorien " + kategori);

      lukVindue(event);
    } else {
      System.out.println("FEJL: Modellen er null (ingen forbindelse til databasen).");
    }
  }

  @FXML
  public void handleAnnuller(ActionEvent event) {
    lukVindue(event);
  }

  @FXML
  public void handleTilbage(ActionEvent event) {
    lukVindue(event);
  }

  private void lukVindue(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}