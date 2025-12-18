package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import Kløverly.persistense.Datamanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeMenuController {

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;
  }

  @FXML
  public void pointSystem() {
    NavigationHelper.openWindow( "pointcontroller.fxml", "Pointsystem", this.model);
  }

  @FXML
  private void handleOpretBeboer() {
    NavigationHelper.openWindow("OpretBeboer.fxml", "Opret beboer", this.model);
  }

  @FXML
  private void handleBeboerListe() {
    NavigationHelper.openWindow("BeboerListe.fxml", "Liste over beboere", this.model);
  }


  @FXML
  private void handleOpretOpgaver() {
    NavigationHelper.openWindow("OpretOpgaver.fxml", "Opret Ny Opgave", this.model);
  }
  
  @FXML private void handleGrønneOpgaver() { NavigationHelper.openWindow("grønneopgaver.fxml", "Grønne Opgaver", this.model); }
  @FXML private void handleBytteOpgaver() { NavigationHelper.openWindow("bytteopgaver.fxml", "Bytte Opgaver", this.model); }
  @FXML private void handleKlimaOpgaver() { NavigationHelper.openWindow("klimaopgaver.fxml", "Klima Opgaver", this.model); }

  @FXML
  private void handleLuk() {
    Kløverly.persistense.Datamanager.gemModel(this.model);
    System.exit(0);
  }
  @FXML
  public void handleVisOpgaveListe(ActionEvent event) {
    BeboerModel model = Datamanager.hentModel();
    NavigationHelper.openWindow("OpgaveListe.fxml", "Liste over Opgaver", model);
  }
  }