/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FXML_InputSiswaController implements Initializable {

    boolean editdata = false;

    public void execute(SiswaModel d) {
        if (!d.getNPM().isEmpty()) {
            editdata = true;
            txtnpm.setText(d.getNPM());
            txtnama.setText(d.getNama());
            txtalamat.setText(d.getAlamat());
            txtnpm.setEditable(false);
            txtnama.requestFocus();
        }
    }

    @FXML
    private TextField txtnpm;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtalamat;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnkeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        SiswaModel s = new SiswaModel();
        s.setNPM(txtnpm.getText());
        s.setNama(txtnama.getText());
        s.setAlamat(txtalamat.getText());
        FXMLDocumentController.dtsiswa.setSiswaModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtsiswa.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtnpm.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtsiswa.validasi(s.getNPM()) <= 0) {
            if (FXMLDocumentController.dtsiswa.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtnpm.requestFocus();
        }
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtnpm.setText("");
        txtnama.setText("");
        txtalamat.setText("");
        txtnpm.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

}
