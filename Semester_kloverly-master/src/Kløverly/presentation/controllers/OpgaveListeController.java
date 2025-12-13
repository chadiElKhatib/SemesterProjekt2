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

    private BeboerModel model;


    public void setModel(BeboerModel model) {
        this.model = model;


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

    @FXML
    private ListView<grønneopgaver> grønneOpgaverListe;
    @FXML
    private ListView<bytteopgaver> bytteOpgaverListe;
    @FXML
    private ListView<klimaopgaver> klimaOpgaverListe;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleTilbage(ActionEvent event) {
        lukVindue(event);
    }

    @FXML
    public void handleSlet(ActionEvent event) {
        if (model == null) {
            System.out.println("FEJL: Model ikke sat!");
            return;
        }



        grønneopgaver valgtGrøn = grønneOpgaverListe.getSelectionModel().getSelectedItem();
        if (valgtGrøn != null) {
            model.getGrønneOpgaverList().remove(valgtGrøn);
            Datamanager.gemModel(model);
            grønneOpgaverListe.setItems(FXCollections.observableArrayList(model.getGrønneOpgaverList()));
            return;
        }


        bytteopgaver valgtBytte = bytteOpgaverListe.getSelectionModel().getSelectedItem();
        if (valgtBytte != null) {
            model.getBytteOpgaverList().remove(valgtBytte);
            Datamanager.gemModel(model);
            bytteOpgaverListe.setItems(FXCollections.observableArrayList(model.getBytteOpgaverList()));
            return;
        }


        klimaopgaver valgtKlima = klimaOpgaverListe.getSelectionModel().getSelectedItem();
        if (valgtKlima != null) {
            model.getKlimaOpgaverList().remove(valgtKlima);
            Datamanager.gemModel(model);
            klimaOpgaverListe.setItems(FXCollections.observableArrayList(model.getKlimaOpgaverList()));
            return;
        }

        System.out.println("Ingen opgave valgt til sletning!");
    }

    private void lukVindue(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}