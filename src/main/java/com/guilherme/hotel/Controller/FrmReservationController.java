package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Model.Room;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class FrmReservationController {

    @FXML
    private TextField txtFieldUsuario;

    @FXML
    private TextField txtFieldQuarto;

    @FXML
    private TextField txtFieldValor;

    @FXML
    private DatePicker Datapickerentrada;

    @FXML
    private DatePicker Datapickersaida;

    @FXML
    private Button btnentrada;

    private final ReservationDAOImp reservationDAO =
            new ReservationDAOImp();

    private final RoomDAOImp roomDAO =
            new RoomDAOImp();

    @FXML
    public void initialize() {

        btnentrada.setOnAction(event -> salvarReserva());

    }

    private void salvarReserva() {

        try {

            Long roomId =
                    Long.parseLong(txtFieldQuarto.getText());

            Room room =
                    roomDAO.searchById(roomId);

            long dias = ChronoUnit.DAYS.between(
                    Datapickerentrada.getValue(),
                    Datapickersaida.getValue());

            BigDecimal total =
                    BigDecimal.valueOf(
                            dias * room.getDaily_rate()
                    );

            Reservation reservation =
                    new Reservation();

            reservation.setGuestid(
                    Integer.parseInt(
                            txtFieldUsuario.getText()));

            reservation.setRoomid(
                    Integer.parseInt(
                            txtFieldQuarto.getText()));

            reservation.setCheckin(
                    Datapickerentrada.getValue());

            reservation.setCheckout(
                    Datapickersaida.getValue());

            reservation.setTotalamount(total);

            reservationDAO.saveReservation(reservation);

            txtFieldValor.setText(
                    total.toString());

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText(null);
            alert.setContentText(
                    "Reserva cadastrada com sucesso!");

            alert.showAndWait();

        } catch (Exception ex) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Erro");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }
}