package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.RoomDAO;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Rooms;

import java.util.List;

public class RoomService {
    private RoomDAO dao = new RoomDAOImp();

    public void save(Rooms rooms){
        //nome quarto colocado
        if(rooms.getNumber() == 0){
            throw new IllegalArgumentException("name preenchido");
        }
        //preço
        if(rooms.getDaily_rate() <= 0){
            throw new IllegalArgumentException("preco preenchido");
        }
        dao.save(rooms);
    }

    public List<Rooms> list(){
        return dao.list();
    }
}
