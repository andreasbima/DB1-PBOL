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
public class FXML_displaymatkulController implements Initializable {

    @FXML
    private TableView<MatkulModel> tbvmatkul;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnakhir;
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

    private void showdata() {
        ObservableList<MatkulModel> data = FXMLDocumentController.dtmatkul.Load();
        if (data != null) {
            tbvmatkul.getColumns().clear();
            tbvmatkul.getItems().clear();
            TableColumn col = new TableColumn("KodeMK");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("KodeMK"));
            tbvmatkul.getColumns().addAll(col);
            col = new TableColumn("NamaMK");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("NamaMK"));
            tbvmatkul.getColumns().addAll(col);
            col = new TableColumn("SKS");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("SKS"));
            tbvmatkul.getColumns().addAll(col);
            col = new TableColumn("Praktek");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("Praktek"));
            tbvmatkul.getColumns().addAll(col);
            tbvmatkul.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kososng", ButtonType.OK);
            a.showAndWait();
            tbvmatkul.getScene().getWindow().hide();
        }
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectFirst();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectAboveCell();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
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
        MatkulModel s = new MatkulModel();
        s = tbvmatkul.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Mau dihapus?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtsiswa.delete(s.getKodeMK())) {
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
    private void sesudahklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectBelowCell();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectLast();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        MatkulModel s = new MatkulModel();
        s = tbvmatkul.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
            Parent root = (Parent) loader.load();
            FXML_InputMatkulController isidt = (FXML_InputMatkulController) loader.getController();
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
