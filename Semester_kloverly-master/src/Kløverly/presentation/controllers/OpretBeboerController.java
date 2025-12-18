package Kløverly.presentation.controllers;

import Kløverly.domain.Beboer;
import Kløverly.domain.BeboerModel;
import Kløverly.persistense.Datamanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OpretBeboerController {

  private Beboer BeboerTilRedigering;

  @FXML public TextField navnField;
  @FXML public TextField telefonField;
  @FXML public DatePicker fødselsdato;
  @FXML public Label status;

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;
  }

  @FXML
  public void handleGem(ActionEvent event) {
    String navn = navnField.getText();
    String tlf = telefonField.getText();
    LocalDate datoValue = fødselsdato.getValue();

    if (navn.isEmpty() || tlf.isEmpty() || datoValue == null) {
      status.setText("Fejl: Udfyld alle felter!");
      return;
    }

    String datoString = datoValue.toString();

    Beboer nyBeboer = new Beboer(navn, tlf, datoString);

    if (model != null) {
      model.opretBeboer(nyBeboer);
      Datamanager.gemModel(model);
      status.setText("Succes: " + navn + " er gemt!");

      navnField.clear();
      telefonField.clear();
      fødselsdato.setValue(null);
    } else {
      System.out.println("Fejl: Modellen er null!");
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