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
public class FXML_InputMatkulController implements Initializable {

    @FXML
    private TextField txtkodemk;
    @FXML
    private TextField txtnamamk;
    @FXML
    private TextField txtsks;
    @FXML
    private TextField txtpraktek;
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
    boolean editdata = false;

    public void execute(MatkulModel d) {
        if (!d.getKodeMK().isEmpty()) {
            editdata = true;
            txtkodemk.setText(d.getKodeMK());
            txtnamamk.setText(d.getNamaMK());
            txtsks.setText(d.getSKS());
            txtpraktek.setText(d.getPraktek());
            txtkodemk.setEditable(false);
            txtnamamk.requestFocus();
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        MatkulModel s = new MatkulModel();
        s.setKodeMK(txtkodemk.getText());
        s.setNamaMK(txtnamamk.getText());
        s.setSKS(txtsks.getText());
        s.setPraktek(txtpraktek.getText());
        FXMLDocumentController.dtmatkul.setMatkulModel(s);
        if (editdata) {
            if (FXMLDocumentController.dtmatkul.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtkodemk.setEditable(true);
                batalklik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtmatkul.validasi(s.getKodeMK()) <= 0) {
            if (FXMLDocumentController.dtmatkul.insert()) {
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
            txtkodemk.requestFocus();
        }

    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkodemk.setText("");
        txtnamamk.setText("");
        txtsks.setText("");
        txtpraktek.setText("");
        txtkodemk.requestFocus();
    }

    @FXML
    private void keluar(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

}
