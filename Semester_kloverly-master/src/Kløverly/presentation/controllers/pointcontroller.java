package Kløverly.presentation.controllers;

import Kløverly.domain.Beboer;
import Kløverly.domain.BeboerModel;
import Kløverly.persistense.Datamanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class pointcontroller implements Initializable {

  @FXML private Label fællespointLabel;
  @FXML private Label milepælLabel; // Denne matcher nu din FXML
  @FXML private ProgressBar fællesProgressBar;
  @FXML private TableView<Beboer> beboerPointTabel;
  @FXML private TableColumn<Beboer, String> navnKolonne;
  @FXML private TableColumn<Beboer, Integer> pointKolonne;

  private BeboerModel model;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.model = Datamanager.hentModel();

    navnKolonne.setCellValueFactory(new PropertyValueFactory<>("navn"));
    pointKolonne.setCellValueFactory(new PropertyValueFactory<>("personligePoint"));

    if (this.model != null) {
      int point = model.getFællesPoint();
      fællespointLabel.setText(point + " Point");

      double progress = (double) point / 500.0;
      fællesProgressBar.setProgress(Math.min(progress, 1.0));

      milepælLabel.setText(model.getMilepælBesked());

      beboerPointTabel.setItems(model.getBeboer());
    }
  }

  @FXML
  public void handleTilbage(ActionEvent event) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
  }
}