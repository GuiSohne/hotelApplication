package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Guest;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class FrmReservationController {

    @FXML
    private ChoiceBox<Guest> choiceGuest;

    @FXML
    private ChoiceBox<Room> choiceQuarto;

    @FXML
    private TextField txtFieldValorDiaria;

    @FXML
    private DatePicker Datapickerentrada;

    @FXML
    private DatePicker Datapickersaida;

    @FXML
    private Button btnentrada;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtStatus;

    @FXML
    private ListView<Reservation> listViewReservas;

    private final ReservationDAOImp reservationDAO =
            new ReservationDAOImp();

    private final RoomDAOImp roomDAO =
            new RoomDAOImp();

    private void carregarQuartos() {

        choiceQuarto.getItems().clear();

        choiceQuarto.getItems().addAll(
                roomDAO.listRoom()
        );
    }
    @FXML
    public void initialize() {

        // Carrega os quartos
        carregarQuartos();

        // Atualiza informações ao selecionar quarto
        choiceQuarto.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, room) -> {

                    if (room != null) {

                        txtTipo.setText(
                                room.getType()
                        );

                        txtStatus.setText(
                                room.getStatus()
                        );

                        txtFieldValorDiaria.setText(
                                String.valueOf(
                                        room.getDaily_rate()
                                )
                        );
                    }
                });

        GuestDAOImp guestDAO = new GuestDAOImp();

        choiceGuest.getItems().addAll(
                guestDAO.findAllGuest()
        );

        carregarReservas();



        btnentrada.setOnAction(
                event -> salvarReserva()
        );
    }

    @FXML
    private void fazerCheckOut() {

        Room room = choiceQuarto.getValue();

        if (room == null) {
            mostrarErro("Selecione um quarto.");
            return;
        }

        room = roomDAO.searchById(room.getId());

        if (room.getStatus().equalsIgnoreCase("Available")) {
            mostrarErro("Este quarto já está livre.");
            return;
        }

        room.setStatus("Available");
        roomDAO.updateRoom(room);

        txtStatus.setText("Available");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Check-out realizado. Quarto liberado.");
        alert.showAndWait();
    }

    private void salvarReserva() {

        try {

            Room room = choiceQuarto.getValue();

            if (room == null) {
                mostrarErro("Selecione um quarto.");
                return;
            }

            room = roomDAO.searchById(room.getId()); // garante estado atualizado

            if (!room.getStatus().equalsIgnoreCase("Available")) {
                mostrarErro("Quarto já está ocupado.");
                return;
            }

            long dias = ChronoUnit.DAYS.between(
                    Datapickerentrada.getValue(),
                    Datapickersaida.getValue()
            );

            if (dias <= 0) {
                mostrarErro("Datas inválidas!");
                return;
            }

            BigDecimal total = BigDecimal.valueOf(dias)
                    .multiply(BigDecimal.valueOf(room.getDaily_rate()));

            Reservation reservation =
                    new Reservation();

            Guest guest = choiceGuest.getValue();

            if (guest == null) {
                mostrarErro("Selecione um usuário.");
                return;
            }

            reservation.setGuestid(
                    (int) guest.getId()
            );

            reservation.setRoomid(
                    room.getId()
            );

            reservation.setCheckin(
                    Datapickerentrada.getValue()
            );

            reservation.setCheckout(
                    Datapickersaida.getValue()
            );

            reservation.setTotalamount(
                    total
            );

            reservationDAO.saveReservation(
                    reservation
            );

            // Atualiza status do quarto
            room.setStatus("Occupied");

            roomDAO.updateRoom(room);

            carregarQuartos();     // recarrega os quartos do banco
            carregarReservas();    // recarrega as reservas

            txtStatus.setText(
                    room.getStatus()
            );

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setHeaderText(null);

            alert.setContentText(
                    "Reserva cadastrada com sucesso!"
            );

            alert.showAndWait();

        } catch (Exception ex) {

            mostrarErro(
                    ex.getMessage()
            );
        }
    }

    private void carregarReservas() {

        listViewReservas.getItems().clear();

        listViewReservas.getItems().addAll(
                reservationDAO.findAllReservation()
        );
    }

    private void mostrarErro(String mensagem) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setHeaderText("Erro");

        alert.setContentText(
                mensagem
        );

        alert.showAndWait();
    }

    // ==========================
    // NAVEGAÇÃO ENTRE TELAS
    // ==========================

    @FXML
    private void abrirDashboard(ActionEvent event)
            throws IOException {

        abrirTela(
                "/views/frmDashboard.fxml"
        );
    }

    @FXML
    private void abrirGuest(ActionEvent event)
            throws IOException {

        abrirTela(
                "/views/frmGuests.fxml"
        );
    }

    @FXML
    private void abrirReservation(ActionEvent event)
            throws IOException {

        abrirTela(
                "/views/frmReservation.fxml"
        );
    }

    @FXML
    private void abrirRoom(ActionEvent event)
            throws IOException {

        abrirTela(
                "/views/frmRooms.fxml"
        );
    }

    private void abrirTela(String caminho)
            throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass()
                                .getResource(caminho)
                );

        Scene scene =
                new Scene(
                        loader.load()
                );

        Stage stage =
                (Stage) choiceGuest
                        .getScene()
                        .getWindow();

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void logout(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource(
                                            "/views/frmLogin.fxml"
                                    )
                    );

            Scene scene =
                    new Scene(
                            loader.load()
                    );

            Stage stage =
                    (Stage) ((Button)
                            event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}