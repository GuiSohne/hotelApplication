package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Room;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrmRoomController implements Initializable {

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtValorDiaria;

    @FXML
    private ChoiceBox<String> choiceTipo;



    @FXML
    private ListView<String> listViewQuartos;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnListar;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;



    private RoomDAOImp roomDAO = new RoomDAOImp();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int number;

        choiceTipo.setItems(
                FXCollections.observableArrayList(
                        "Single",
                        "Double",
                        "Luxury"
                )
        );





        btnAdd.setOnAction(event -> addRoom());

        btnListar.setOnAction(event -> listRooms());

        btnDelete.setOnAction(event -> deleteRoom());

        btnUpdate.setOnAction(event -> updateRoom());
    }

    private void addRoom() {

        try {

            Room room = new Room();

            room.setNumber(
                    Integer.parseInt(txtNumero.getText())
            );

            room.setType(
                    choiceTipo.getValue()
            );

            room.setDaily_rate(
                    Double.parseDouble(txtValorDiaria.getText())
            );



            roomDAO.saveRoom(room);

            ClearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Room registered!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void deleteRoom() {

        try {

            int number = Integer.parseInt(txtNumero.getText());

            roomDAO.deleteRoom(number);

            ClearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Room deleted!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void updateRoom() {

        try {

            Room room = new Room();

            room.setNumber(Integer.parseInt(txtNumero.getText()));
            room.setType(choiceTipo.getValue());
            room.setDaily_rate(Double.parseDouble(txtValorDiaria.getText()));
            room.setStatus(("Available"));

            roomDAO.updateRoom(room);

            ClearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Room updated!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void listRooms() {

        listViewQuartos.getItems().clear();

        for (Room room : roomDAO.listRoom()) {

            listViewQuartos.getItems().add(
                    "Nº " + room.getNumber()
                            + " | "
                            + room.getType()
                            + " | "
                            + room.getStatus()
                            + " |  $ "
                            + room.getDaily_rate()
            );
        }
    }



    private void ClearFields() {

        txtNumero.clear();
        txtValorDiaria.clear();

        choiceTipo.setValue(null);

    }

    //navegar entre telas

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
                (Stage) txtNumero.getScene().getWindow();

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