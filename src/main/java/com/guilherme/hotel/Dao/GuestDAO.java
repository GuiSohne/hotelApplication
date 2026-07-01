package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Guest;

import java.util.List;

public interface GuestDAO {
    //salvar hospede
    void saveGuest(Guest guest);

    //listar hospedes
    List<Guest> findAllGuest();

    //listar hospede por cpf
    Guest searchByCPFGuest(String cpf);

    //deletar hospedes
    void deleteGuest(long id);

    //atualizar hospedes
    void updateGuest(Guest guest);


}
