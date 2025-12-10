package Kløverly.presentation.controllers;

import Kløverly.domain.BeboerModel;
import Kløverly.domain.bytteopgaver;
import Kløverly.domain.klimaopgaver;
import Kløverly.persistense.Datamanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import Kløverly.domain.grønneopgaver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class OpgaveListeController implements Initializable {

  @FXML
  private ListView<grønneopgaver> grønneOpgaverListe;
  @FXML
  private ListView<bytteopgaver> bytteOpgaverListe;
  @FXML
  private ListView<klimaopgaver> klimaOpgaverListe;

  @FXML
  public void handleTilbage(ActionEvent event) {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    BeboerModel model = Datamanager.hentModel();

    if (model != null) {
      grønneOpgaverListe.setItems(
          FXCollections.observableArrayList(model.getGrønneOpgaverList())
      );

      bytteOpgaverListe.setItems(
          FXCollections.observableArrayList(model.getBytteOpgaverList())
      );

      klimaOpgaverListe.setItems(
          FXCollections.observableArrayList(model.getKlimaOpgaverList())
      );
    }
  }
}