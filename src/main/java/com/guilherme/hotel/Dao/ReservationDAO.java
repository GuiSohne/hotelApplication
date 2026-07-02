package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Reservation;

import java.util.List;

public interface ReservationDAO {
    //Salvar reserva
    void saveReservation(Reservation reservation);

    //Listar reservas
    List<Reservation> findAllReservation();

    //Pesquisar reserva por id
    Reservation SearchByIdReservation(Long id);

    //Deletar reserva
    void deleteReservation(Long id);

    //Atualizar reserva
    void updateReservation(Reservation reservation);
}
