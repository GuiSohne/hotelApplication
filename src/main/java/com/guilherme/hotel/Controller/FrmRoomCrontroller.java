package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Room;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FrmRoomCrontroller implements Initializable {

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtValor;

    @FXML
    private ChoiceBox<String> choiceTipo;

    @FXML
    private ChoiceBox<String> choiceStatus;

    @FXML
    private ListView<String> listViewQuartos;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnListar;

    private RoomDAOImp roomDAO = new RoomDAOImp();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        choiceTipo.setItems(
                FXCollections.observableArrayList(
                        "Single",
                        "Double",
                        "Luxury"
                )
        );

        choiceStatus.setItems(
                FXCollections.observableArrayList(
                        "Available",
                        "Occupied"
                )
        );

        btnAdd.setOnAction(event -> adicionarQuarto());

        btnListar.setOnAction(event -> listarQuartos());
    }

    private void adicionarQuarto() {

        try {

            Room room = new Room();

            room.setNumber(
                    Integer.parseInt(txtNumero.getText())
            );

            room.setType(
                    choiceTipo.getValue()
            );

            room.setDaily_rate(
                    Double.parseDouble(txtValor.getText())
            );

            room.setStatus(
                    choiceStatus.getValue()
            );

            roomDAO.saveRoom(room);

            limparCampos();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Quarto cadastrado!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void listarQuartos() {

        listViewQuartos.getItems().clear();

        for (Room room : roomDAO.listRoom()) {

            listViewQuartos.getItems().add(
                    "Nº " + room.getNumber()
                            + " | "
                            + room.getType()
                            + " | "
                            + room.getStatus()
            );
        }
    }

    private void limparCampos() {

        txtNumero.clear();
        txtValor.clear();

        choiceTipo.setValue(null);
        choiceStatus.setValue(null);
    }
}