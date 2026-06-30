package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class FrmDashboardController {

    @FXML private Label lblUsuario;
    @FXML private Label lblReserva;

    @FXML private Label lblQuartoOcupado;
    @FXML private Label lblQuartoLivre;

    @FXML private Label lblQuartoSimples;
    @FXML private Label lblQuartoDuplo;
    @FXML private Label lblQuartoLuxo;

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

        int ocupados = 0;
        int livres = 0;

        int simples = 0;
        int duplo = 0;
        int luxo = 0;

        for(Room room : roomDAO.listRoom()) {

            if(room.getStatus().equalsIgnoreCase("Occupied")) {
                ocupados++;
            }

            if(room.getStatus().equalsIgnoreCase("Available")) {
                livres++;
            }

            if(room.getType().equalsIgnoreCase("Single")) {
                simples++;
            }

            if(room.getType().equalsIgnoreCase("Double")) {
                duplo++;
            }

            if(room.getType().equalsIgnoreCase("Luxury")) {
                luxo++;
            }
        }

        lblQuartoOcupado.setText(String.valueOf(ocupados));
        lblQuartoLivre.setText(String.valueOf(livres));

        lblQuartoSimples.setText(String.valueOf(simples));
        lblQuartoDuplo.setText(String.valueOf(duplo));
        lblQuartoLuxo.setText(String.valueOf(luxo));
    }


}