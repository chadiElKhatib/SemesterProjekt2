package Kløverly.presentation.controllers;

import Kløverly.domain.Beboer;
import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate; // VIGTIG IMPORT for at dato virker!

public class OpretBeboerController {

  // --- FXML FELTER (Skal matche fx:id i din FXML fil) ---
  @FXML public TextField navnField;
  @FXML public TextField telefonField;
  @FXML public DatePicker fødselsdato;
  @FXML public Label status;

  private BeboerModel model;

  // Modtager data-modellen fra NavigationHelper
  public void setModel(BeboerModel model) {
    this.model = model;
  }

  // --- KNAP: GEM BEBOER ---
  @FXML
  public void handleGem(ActionEvent event) {
    // 1. Hent værdier fra skærmen
    String navn = navnField.getText();
    String tlf = telefonField.getText();
    LocalDate datoValue = fødselsdato.getValue();

    // 2. Tjek om felterne er tomme
    if (navn.isEmpty() || tlf.isEmpty() || datoValue == null) {
      status.setText("Fejl: Udfyld alle felter!");
      return; // Stop her, hvis noget mangler
    }

    // 3. Konverter LocalDate til String (vigtigt fix!)
    String datoString = datoValue.toString();

    // 4. Opret beboer objektet (Navn, Tlf, Dato)
    Beboer nyBeboer = new Beboer(navn, tlf, datoString);

    // 5. Gem i modellen (databasen)
    if (model != null) {
      model.opretBeboer(nyBeboer);
      status.setText("Succes: " + navn + " er gemt!");

      // 6. Ryd felterne så man kan skrive en ny
      navnField.clear();
      telefonField.clear();
      fødselsdato.setValue(null);
    } else {
      System.out.println("Fejl: Modellen er null!");
    }
  }

  // --- KNAP: ANNULLER / LUK ---
  @FXML
  public void handleAnnuller(ActionEvent event) {
    // Lukker vinduet
    lukVindue(event);
  }

  // Hjælpe-metode til at lukke vinduet
  private void lukVindue(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}