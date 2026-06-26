package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Guest;

import java.util.List;

public interface GuestDAO {
    void save(Guest guest);

    List<Guest> findAll();

    Guest searchByCPF(String cpf);

    void delete(long id);

    void update(Guest guest);


}
