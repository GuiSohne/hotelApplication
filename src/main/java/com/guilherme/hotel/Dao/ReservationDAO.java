package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Reservation;

import java.util.List;

public interface ReservationDAO {
    void save(Reservation reservation);

    List<Reservation> findAll();

    Reservation SearchById(Long id);

    void delete(Long id);

    void update(Reservation reservation);
}
