package Kløverly.presentation.controllers;

import Kløverly.domain.*;
import Kløverly.persistense.Datamanager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class OpgaveListeController implements Initializable
{

  @FXML private ListView<grønneopgaver> grønneOpgaverListe;
  @FXML private ListView<bytteopgaver> bytteOpgaverListe;
  @FXML private ListView<klimaopgaver> klimaOpgaverListe;

  @FXML private ComboBox<Beboer> beboerValgCombo;

  private BeboerModel model;

  public void setModel(BeboerModel model)
  {
    this.model = model;

    if (this.model != null)
    {
      grønneOpgaverListe.setItems(
          FXCollections.observableArrayList(this.model.getGrønneOpgaverList()));
      bytteOpgaverListe.setItems(
          FXCollections.observableArrayList(this.model.getBytteOpgaverList()));
      klimaOpgaverListe.setItems(
          FXCollections.observableArrayList(this.model.getKlimaOpgaverList()));
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.model = Datamanager.hentModel();

    if (this.model != null) {

      beboerValgCombo.setItems(
          FXCollections.observableArrayList(this.model.getBeboer())
      );

      beboerValgCombo.setCellFactory(lv -> new ListCell<Beboer>() {
        @Override
        protected void updateItem(Beboer item, boolean empty) {
          super.updateItem(item, empty);
          setText(empty ? "" : item.getNavn());
        }
      });
      beboerValgCombo.setButtonCell(beboerValgCombo.getCellFactory().call(null));
    }
  }

  @FXML public void handleTilbage(ActionEvent event)
  {
    lukVindue(event);
  }

  @FXML
  public void handleGodkend(ActionEvent event) {

    grønneopgaver valgtGrøn = grønneOpgaverListe.getSelectionModel().getSelectedItem();
    bytteopgaver valgtBytte = bytteOpgaverListe.getSelectionModel().getSelectedItem();
    klimaopgaver valgtKlima = klimaOpgaverListe.getSelectionModel().getSelectedItem();

    if (model == null) {
      System.out.println("FEJL: Modellen er ikke indlæst.");
      return;
    }

    boolean opgaveGodkendt = false;
    int point = 0;

    Beboer valgtBeboer = beboerValgCombo.getSelectionModel().getSelectedItem();

    if (valgtGrøn != null) {
      point = valgtGrøn.getPoint();
      model.addFællesPoint(point);
      model.getGrønneOpgaverList().remove(valgtGrøn);
      System.out.println("Godkendt Grøn Opgave: " + valgtGrøn.getTitel() + ". " + point + " point til Fælles Pulje.");
      opgaveGodkendt = true;

    } else if (valgtBytte != null || valgtKlima != null) {

      if (valgtBeboer == null) {
        System.out.println("FEJL: Vælg en beboer, før Bytte/Klima opgave godkendes.");
        return;
      }

      if (valgtBytte != null) {
        point = valgtBytte.getPoint();
        model.addPersonligePoint(valgtBeboer.getNavn(), point);
        model.getBytteOpgaverList().remove(valgtBytte);
        System.out.println("Godkendt Bytte Opgave: " + valgtBytte.getTitel() + ". " + point + " point tildelt " + valgtBeboer.getNavn());

      } else if (valgtKlima != null) {
        point = valgtKlima.getPoint();

        System.out.println("DIAGNOSTIK: Klar til at tildele Klima Point til " + valgtBeboer.getNavn());

        model.addPersonligePoint(valgtBeboer.getNavn(), point);
        model.getKlimaOpgaverList().remove(valgtKlima);
        System.out.println("Godkendt Klima Opgave: " + valgtKlima.getTitel() + ". " + point + " point tildelt " + valgtBeboer.getNavn());
      }

      opgaveGodkendt = true;

    } else {
      System.out.println("Ingen opgave valgt til godkendelse.");
    }

    if (opgaveGodkendt) {
      Datamanager.gemModel(model);

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

  @FXML public void handleSlet(ActionEvent event)
  {

    grønneopgaver valgtGrøn = grønneOpgaverListe.getSelectionModel()
        .getSelectedItem();
    bytteopgaver valgtBytte = bytteOpgaverListe.getSelectionModel()
        .getSelectedItem();
    klimaopgaver valgtKlima = klimaOpgaverListe.getSelectionModel()
        .getSelectedItem();

    if (model == null)
    {
      System.out.println("FEJL: Modellen er ikke indlæst.");
      return;
    }

    boolean opgaveSlettet = false;

    if (valgtGrøn != null)
    {
      model.getGrønneOpgaverList().remove(valgtGrøn);
      opgaveSlettet = true;

    }
    else if (valgtBytte != null)
    {
      model.getBytteOpgaverList().remove(valgtBytte);
      opgaveSlettet = true;

    }
    else if (valgtKlima != null)
    {
      model.getKlimaOpgaverList().remove(valgtKlima);
      opgaveSlettet = true;

    }
    else
    {
      System.out.println("Ingen opgave valgt til sletning.");
    }

    if (opgaveSlettet)
    {
      Datamanager.gemModel(model);

      grønneOpgaverListe.setItems(
          FXCollections.observableArrayList(model.getGrønneOpgaverList()));
      bytteOpgaverListe.setItems(
          FXCollections.observableArrayList(model.getBytteOpgaverList()));
      klimaOpgaverListe.setItems(
          FXCollections.observableArrayList(model.getKlimaOpgaverList()));

      System.out.println("Opgave slettet og data gemt.");
    }
  }

  private void lukVindue(ActionEvent event)
  {
    Node source = (Node) event.getSource();
    Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
  }
}