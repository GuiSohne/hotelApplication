package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Guest;

import java.util.List;

public interface GuestDAO {
    //Salvar hospede
    void saveGuest(Guest guest);

    //Listar hospedes
    List<Guest> findAllGuest();

    //Listar hospede por cpf
    Guest searchByCPFGuest(String cpf);

    //Deletar hospedes
    void deleteGuest(long id);

    //Atualizar hospedes
    void updateGuest(Guest guest);


}
