/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_displaynilaiController implements Initializable {

    @FXML
    private TableView<NilaiModel> tbvnilai;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }

    public void showdata() {
        ObservableList<NilaiModel> data = FXMLDocumentController.dtnilai.Load();
        if (data != null) {
            tbvnilai.getColumns().clear();
            tbvnilai.getItems().clear();
            TableColumn col = new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("npm"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("KodeMK");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("kodemk"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Tanggal");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("tanggal"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Nilai");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("nilai"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Hadir");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("hadir"));
            tbvnilai.getColumns().addAll(col);
            tbvnilai.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbvnilai.getScene().getWindow().hide();;
        }
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectFirst();
        tbvnilai.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectAboveCell();
        tbvnilai.requestFocus();
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectBelowCell();
        tbvnilai.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectLast();
        tbvnilai.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        NilaiModel s = new NilaiModel();
        s = tbvnilai.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtnilai.delete(s.getNpm(), s.getKodemk())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showdata();
            awalklik(event);
        }
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        NilaiModel s = new NilaiModel();
        s = tbvnilai.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputNilaiController isidt = (FXML_InputNilaiController) loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdata();
        awalklik(event);
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

}
