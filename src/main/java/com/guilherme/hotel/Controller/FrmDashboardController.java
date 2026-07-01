package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FrmDashboardController {

    @FXML
    private Label lblUsuario;

    @FXML
    private Label lblReserva;

    @FXML
    private Label lblQuartoOcupado;

    @FXML
    private Label lblQuartoLivre;

    @FXML
    private Label lblQuartoSimples;

    @FXML
    private Label lblQuartoDuplo;

    @FXML
    private Label lblQuartoLuxo;

    @FXML
    public void initialize() {

        GuestDAOImp guestDAO = new GuestDAOImp();
        RoomDAOImp roomDAO = new RoomDAOImp();
        ReservationDAOImp reservationDAO = new ReservationDAOImp();

        lblUsuario.setText(
                String.valueOf(
                        guestDAO.findAllGuest().size()
                )
        );

        lblReserva.setText(
                String.valueOf(
                        reservationDAO.findAllReservation().size()
                )
        );

        int occupied = 0;
        int availible = 0;

        int simple = 0;
        int doubles = 0;
        int luxury = 0;

        for (Room room : roomDAO.listRoom()) {

            if ("Occupied".equalsIgnoreCase(room.getStatus())) {
                occupied++;
            }

            if ("Available".equalsIgnoreCase(room.getStatus()))  {
                availible++;
            }

            if (room.getType().equalsIgnoreCase("Single")) {
                simple++;
            }

            if (room.getType().equalsIgnoreCase("Double")) {
                doubles++;
            }

            if (room.getType().equalsIgnoreCase("Luxury")) {
                luxury++;
            }
        }

        lblQuartoOcupado.setText(String.valueOf(occupied));
        lblQuartoLivre.setText(String.valueOf(availible));

        lblQuartoSimples.setText(String.valueOf(simple));
        lblQuartoDuplo.setText(String.valueOf(doubles));
        lblQuartoLuxo.setText(String.valueOf(luxury));
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
                (Stage) lblUsuario.getScene().getWindow();

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