package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Guest;

import java.util.List;

public interface GuestDAO {
    void saveGuest(Guest guest);

    List<Guest> findAllGuest();

    Guest searchByCPFGuest(String cpf);

    void deleteGuest(long id);

    void updateGuest(Guest guest);


}
