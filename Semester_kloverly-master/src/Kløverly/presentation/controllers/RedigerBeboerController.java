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


public class RedigerBeboerController {

    @FXML
    private TextField navnField;

    @FXML
    private TextField telefonField;

    @FXML
    private DatePicker fødselsdato;

    @FXML
    private Label statusLabel;

    private Beboer beboer;
    private BeboerModel model;

    public void setData(Beboer beboer, BeboerModel model) {
        this.beboer = beboer;
        this.model = model;

        navnField.setText(beboer.getNavn());
        telefonField.setText(beboer.getTelefon());

        try {
            LocalDate dato = LocalDate.parse(beboer.getDato());
            fødselsdato.setValue(dato);
        } catch (Exception e) {
            fødselsdato.setValue(null);
        }
    }

    @FXML
    private void handleGem(ActionEvent event) {

        String nytNavn = navnField.getText();
        String nyTelefon = telefonField.getText();
        LocalDate nyDato = fødselsdato.getValue();

        if (nytNavn.isEmpty() || nyTelefon.isEmpty() || nyDato == null) {
            statusLabel.setText("Udfyld alle felter.");
            return;
        }

        beboer.setNavn(nytNavn);
        beboer.setTelefon(nyTelefon);
        beboer.setDato(nyDato.toString());

        if (model != null) {
            Datamanager.gemModel(model);
        }

        lukVindue(event);
    }

    @FXML
    private void handleAnnuller(ActionEvent event) {
        lukVindue(event);
    }

    private void lukVindue(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}

