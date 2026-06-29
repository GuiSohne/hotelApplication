package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Reservation;

import java.util.List;

public interface ReservationDAO {
    void saveReservation(Reservation reservation);

    List<Reservation> findAllReservation();

    Reservation SearchByIdReservation(Long id);

    void deleteReservation(Long id);

    void updateReservation(Reservation reservation);
}
