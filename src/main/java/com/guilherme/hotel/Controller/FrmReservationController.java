package com.guilherme.hotel.Controller;

import com.guilherme.hotel.Dao.GuestDAOImp;
import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Guest;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Model.Room;
import com.guilherme.hotel.Service.ReservationService;
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
    private ReservationService reservationService = new ReservationService();

    @FXML
    private ListView<Reservation> listViewReservas;

    @FXML
    private Label lblTotal;

    @FXML
    public void listarReservas(){

        listViewReservas.getItems().clear();

        listViewReservas.getItems().addAll(
                reservationService.list()
        );

    }


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

    private void salvarReserva() {try {

        Room room = choiceQuarto.getValue();

        if (room == null) {
            mostrarErro("Selecione um quarto.");
            return;
        }

        Guest guest = choiceGuest.getValue();

        if (guest == null) {
            mostrarErro("Selecione um hóspede.");
            return;
        }

        Reservation reservation = new Reservation();

        reservation.setGuestid((int) guest.getId());
        reservation.setRoomid(room.getId());
        reservation.setCheckin(Datapickerentrada.getValue());
        reservation.setCheckout(Datapickersaida.getValue());

        // O Service faz toda a regra de negócio
        reservationService.checkin(reservation);

        // Atualiza o valor total na tela
        lblTotal.setText("R$ " + reservation.getTotalamount());

        // Recarrega os dados
        carregarQuartos();
        carregarReservas();

        // Atualiza o status do quarto na tela
        Room roomAtualizado = roomDAO.searchById(room.getId());
        txtStatus.setText(roomAtualizado.getStatus());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Reserva cadastrada com sucesso!");
        alert.showAndWait();

    } catch (Exception ex) {

        mostrarErro(ex.getMessage());

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
