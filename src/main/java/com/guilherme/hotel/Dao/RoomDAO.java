package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;

import java.util.List;


public interface RoomDAO {
    //Salvar quarto
    void saveRoom(Room room);

    //Listar quartos
    List<Room> listRoom();

    //Pesquisar quarto por id
    Room searchById(Long id);

    //Deletar quarto
    void deleteRoom(int number);

    //Atualizar quarto
    void updateRoom(Room room);

}
