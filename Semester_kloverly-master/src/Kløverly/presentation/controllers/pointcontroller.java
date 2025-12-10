package Kløverly.presentation.controllers;

import Kløverly.domain.Beboer;
import Kløverly.domain.BeboerModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class pointcontroller implements Initializable {

  @FXML private Label fællespointLabel;
  @FXML private ProgressBar fællesProgressBar;
  @FXML private TableView<Beboer> beboerPointTabel;
  @FXML private TableColumn<Beboer, String> navnKolonne;
  @FXML private TableColumn<Beboer, Integer> pointKolonne;

  private BeboerModel model;

  public void setModel(BeboerModel model) {
    this.model = model;
    if (model != null) {
      updateTableView();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    setupTableViewColumns();
  }

  private void setupTableViewColumns() {
    navnKolonne.setCellValueFactory(new PropertyValueFactory<>("navn"));
    pointKolonne.setCellValueFactory(new PropertyValueFactory<>("point"));
  }

  private void updateTableView() {
    if (model != null) {
      ObservableList<Beboer> beboere = model.getBeboer();
      beboerPointTabel.setItems(beboere);
    }
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