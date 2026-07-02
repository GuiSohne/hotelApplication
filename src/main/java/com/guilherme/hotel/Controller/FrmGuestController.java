package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Model.Guest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class FrmGuestController {

    @FXML
    private TextField txtFieldNome;

    @FXML
    private TextField txtFieldCPF;

    @FXML
    private TextField txtFieldEmail;

    @FXML
    private TextField txtFieldTelefone;

    @FXML
    private PasswordField passFieldSenha;

    @FXML
    private ListView<Guest> listViewGuests;

    private final GuestDAOImp dao = new GuestDAOImp();

    @FXML
    public void initialize() {



        listViewGuests.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, guest) -> {

                    if (guest != null) {
                        txtFieldNome.setText(guest.getName());
                        txtFieldCPF.setText(guest.getCpf());
                        txtFieldEmail.setText(guest.getEmail());
                        txtFieldTelefone.setText(guest.getPhone());
                        passFieldSenha.setText(guest.getPassword());
                    }
                });
    }

    @FXML
    private void adicionarGuest() {

        Guest guest = new Guest();

        guest.setName(txtFieldNome.getText());
        guest.setCpf(txtFieldCPF.getText());
        guest.setEmail(txtFieldEmail.getText());
        guest.setPhone(txtFieldTelefone.getText());
        guest.setPassword(passFieldSenha.getText());

        dao.saveGuest(guest);

        limparCampos();
        carregarLista();

        mostrarMensagem("Usuário cadastrado com sucesso!");
    }

    @FXML
    private void listarGuests() {
        carregarLista();
    }

    @FXML
    private void excluirGuest() {

        Guest guestSelecionado =
                listViewGuests.getSelectionModel().getSelectedItem();

        if (guestSelecionado == null) {
            mostrarErro("Selecione um usuário.");
            return;
        }

        dao.deleteGuest(guestSelecionado.getId());

        carregarLista();
        limparCampos();

        mostrarMensagem("Usuário removido.");
    }

    @FXML
    private void editarGuest() {

        Guest guestSelecionado =
                listViewGuests.getSelectionModel().getSelectedItem();

        if (guestSelecionado == null) {
            mostrarErro("Selecione um usuário.");
            return;
        }

        guestSelecionado.setName(txtFieldNome.getText());
        guestSelecionado.setCpf(txtFieldCPF.getText());
        guestSelecionado.setEmail(txtFieldEmail.getText());
        guestSelecionado.setPhone(txtFieldTelefone.getText());
        guestSelecionado.setPassword(passFieldSenha.getText());

        dao.updateGuest(guestSelecionado);

        carregarLista();

        mostrarMensagem("Usuário atualizado.");
    }

    private void carregarLista() {

        ObservableList<Guest> itens =
                FXCollections.observableArrayList(
                        dao.findAllGuest()
                );

        listViewGuests.setItems(itens);
    }

    private void limparCampos() {

        txtFieldNome.clear();
        txtFieldCPF.clear();
        txtFieldEmail.clear();
        txtFieldTelefone.clear();
        passFieldSenha.clear();
    }

    private void mostrarMensagem(String msg) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarErro(String msg) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // ==========================
    // NAVEGAÇÃO ENTRE TELAS
    // ==========================

    @FXML
    private void abrirDashboard(ActionEvent event) throws IOException {
        abrirTela("/views/frmDashboard.fxml");
    }

    @FXML
    private void abrirGuest(ActionEvent event) throws IOException {
        abrirTela("/views/frmGuests.fxml");
    }

    @FXML
    private void abrirReservation(ActionEvent event) throws IOException {
        abrirTela("/views/frmReservation.fxml");
    }

    @FXML
    private void abrirRoom(ActionEvent event) throws IOException {
        abrirTela("/views/frmRooms.fxml");
    }

    private void abrirTela(String caminho) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource(caminho));

        Scene scene = new Scene(loader.load());

        Stage stage =
                (Stage) txtFieldCPF.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass()
                            .getResource("/views/frmLogin.fxml"));

            Scene scene = new Scene(loader.load());

            Stage stage =
                    (Stage) ((Button) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}