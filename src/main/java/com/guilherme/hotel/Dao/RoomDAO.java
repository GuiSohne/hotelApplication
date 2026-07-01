package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;

import java.util.List;


public interface RoomDAO {
    //salvar quarto
    void saveRoom(Room room);

    //listar quartos
    List<Room> listRoom();

    //pesquisar quarto por id
    Room searchById(Long id);

    //deletar quarto
    void deleteRoom(Long id);

    //atualizar quarto
    void updateRoom(Room room);

}
