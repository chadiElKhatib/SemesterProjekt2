package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeMenuController
{

  private BeboerModel model;

  public void setModel(BeboerModel model)
  {
    this.model = model;
  }

  @FXML public void pointSystem()
  {
    NavigationHelper.openWindow("pointcontroller.fxml", "Pointsystem",
        this.model);
  }

  @FXML private void handleOpretBeboer()
  {
    NavigationHelper.openWindow("OpretBeboer.fxml", "Opret beboer", this.model);
  }

  @FXML private void handleBeboerListe()
  {
    NavigationHelper.openWindow("BeboerListe.fxml", "Liste over beboere",
        this.model);
  }

  @FXML private void handleOpretOpgaver()
  {
    NavigationHelper.openWindow("OpretOpgaver.fxml", "Opret Ny Opgave",
        this.model);
  }

  @FXML private void handleLuk()
  {
    Kløverly.persistense.Datamanager.gemModel(this.model);
    System.exit(0);
  }

  @FXML
  public void handleVisOpgaveListe(ActionEvent event) {
    NavigationHelper.openWindow("OpgaveListe.fxml", "Liste over Opgaver", this.model);
  }
}