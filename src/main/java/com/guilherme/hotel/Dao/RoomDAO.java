package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Rooms;

import java.util.List;


public interface RoomDAO {
    void save(Rooms room);
    List<Rooms> list();
}
