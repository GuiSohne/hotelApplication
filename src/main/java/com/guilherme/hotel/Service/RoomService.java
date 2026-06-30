package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.RoomDAO;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Room;

import java.util.List;

public class RoomService {
    private RoomDAO dao = new RoomDAOImp();

    public void save(Room room){
        //nome quarto colocado
        if(room.getNumber() == 0){
            throw new IllegalArgumentException("name preenchido");
        }
        //preço
        if(room.getDaily_rate() <= 0){
            throw new IllegalArgumentException("preco preenchido");
        }
        dao.saveRoom(room);
    }

    public List<Room> list(){
        return dao.listRoom();
    }
}
