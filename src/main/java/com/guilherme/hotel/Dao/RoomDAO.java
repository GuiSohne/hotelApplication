package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;

import java.util.List;


public interface RoomDAO {
    void save(Room room);
    List<Room> list();
}
