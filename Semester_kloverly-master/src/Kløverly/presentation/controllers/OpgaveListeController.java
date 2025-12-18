package Kløverly.presentation.controllers;

import Kløverly.domain.*;
import Kløverly.persistense.Datamanager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class OpgaveListeController implements Initializable {

  @FXML private ListView<grønneopgaver> grønneOpgaverListe;
  @FXML private ListView<bytteopgaver> bytteOpgaverListe;
  @FXML private ListView<klimaopgaver> klimaOpgaverListe;
  @FXML private ComboBox<Beboer> beboerValgCombo;

  private BeboerModel model;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.model = Datamanager.hentModel();
    if (this.model != null) {
      beboerValgCombo.setItems(this.model.getBeboer());
      opdaterGUI();
    }
  }

  public void setModel(BeboerModel model) {
    this.model = model;
    opdaterGUI();
  }

  @FXML
  public void handleGodkend(ActionEvent event) {
    grønneopgaver valgtGrøn = grønneOpgaverListe.getSelectionModel().getSelectedItem();
    bytteopgaver valgtBytte = bytteOpgaverListe.getSelectionModel().getSelectedItem();
    klimaopgaver valgtKlima = klimaOpgaverListe.getSelectionModel().getSelectedItem();
    Beboer valgtBeboer = beboerValgCombo.getSelectionModel().getSelectedItem();

    boolean ændring = false;

    if (valgtGrøn != null) {
      model.addFællesPoint(valgtGrøn.getPoint());
      model.getGrønneOpgaverList().remove(valgtGrøn);

      if (model.getFællesPoint() >= 500) {
        java.time.LocalDateTime nu = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String tidspunkt = nu.format(formatter);

        model.setMilepælBesked("Sidst ramt 500 point: " + tidspunkt);
        model.setFællesPoint(0); // Nulstiller puljen
        System.out.println("Mål nået! Point nulstillet.");
      }
      ændring = true;
    }
    else if (valgtBeboer != null) {
      if (valgtBytte != null) {
        model.addPersonligePoint(valgtBeboer.getNavn(), valgtBytte.getPoint());
        model.getBytteOpgaverList().remove(valgtBytte);
        ændring = true;
      } else if (valgtKlima != null) {
        model.addPersonligePoint(valgtBeboer.getNavn(), valgtKlima.getPoint());
        model.getKlimaOpgaverList().remove(valgtKlima);
        ændring = true;
      }
    }

    if (ændring) {
      Datamanager.gemModel(model);
      opdaterGUI();
    }
  }

  @FXML
  public void handleSlet(ActionEvent event) {
    grønneopgaver g = grønneOpgaverListe.getSelectionModel().getSelectedItem();
    bytteopgaver b = bytteOpgaverListe.getSelectionModel().getSelectedItem();
    klimaopgaver k = klimaOpgaverListe.getSelectionModel().getSelectedItem();

    if (g != null) model.getGrønneOpgaverList().remove(g);
    else if (b != null) model.getBytteOpgaverList().remove(b);
    else if (k != null) model.getKlimaOpgaverList().remove(k);

    Datamanager.gemModel(model);
    opdaterGUI();
  }

  private void opdaterGUI() {
    if (model != null) {
      grønneOpgaverListe.setItems(FXCollections.observableArrayList(model.getGrønneOpgaverList()));
      bytteOpgaverListe.setItems(FXCollections.observableArrayList(model.getBytteOpgaverList()));
      klimaOpgaverListe.setItems(FXCollections.observableArrayList(model.getKlimaOpgaverList()));
    }
  }

  @FXML public void handleTilbage(ActionEvent e) { ((Stage)((Node)e.getSource()).getScene().getWindow()).close(); }
}