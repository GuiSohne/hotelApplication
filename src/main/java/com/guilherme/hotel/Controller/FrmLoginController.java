package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Model.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FrmLoginController {

    @FXML
    private TextField txtFieldCPF;

    @FXML
    private PasswordField passFieldSenha;

    @FXML
    private void fazerLogin(ActionEvent event) {

        String cpf = txtFieldCPF.getText();
        String senha = passFieldSenha.getText();

        if (cpf.isEmpty() || senha.isEmpty()) {
            mostrarErro("CPF e senha são obrigatórios.");
            return;
        }

        try {

            GuestDAOImp dao = new GuestDAOImp();
            Guest guest = dao.searchByCPFGuest(cpf);

            if (guest != null &&
                    guest.getPassword() != null &&
                    guest.getPassword().equals(senha)) {

                abrirDashboard();

            } else {
                mostrarErro("CPF ou senha inválidos.");
            }

        } catch (Exception ex) {
            mostrarErro("CPF ou senha inválidos.");
        }
    }

    @FXML
    private void abrirDashboard(ActionEvent event) {
        try {
            abrirDashboard();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void abrirDashboard() throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass()
                        .getResource("/views/frmDashboard.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage =
                (Stage) txtFieldCPF.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}