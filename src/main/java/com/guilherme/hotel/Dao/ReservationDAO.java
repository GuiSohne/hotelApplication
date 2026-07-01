package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Reservation;

import java.util.List;

public interface ReservationDAO {
    //salvar reserva
    void saveReservation(Reservation reservation);

    //listar reservas
    List<Reservation> findAllReservation();

    //pesquisar reserva por id
    Reservation SearchByIdReservation(Long id);

    //deletar reserva
    void deleteReservation(Long id);

    //atualizar reserva
    void updateReservation(Reservation reservation);
}
