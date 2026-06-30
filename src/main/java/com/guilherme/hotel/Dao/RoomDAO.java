package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;

import java.util.List;


public interface RoomDAO {
    void saveRoom(Room room);

    List<Room> listRoom();

    Room searchById(Long id);

    void deleteRoom(Long id);

    void updateRoom(Room room);

}
