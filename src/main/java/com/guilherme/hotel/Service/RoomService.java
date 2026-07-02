package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.RoomDAO;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Room;

import java.util.List;

public class RoomService {
    private RoomDAO dao = new RoomDAOImp();

    //Salvar quarto
    public void save(Room room){
        //Verifica se o número do quarto foi colocado
        if(room.getNumber() <= 0 ){
            throw new IllegalArgumentException("You need to enter the number.");
        }
        //Verifica se o preço do quarto foi colocado
        if(room.getDaily_rate() <= 0){
            throw new IllegalArgumentException("You need to enter the price.");
        }
        dao.saveRoom(room);
    }

    //Listar quartos
    public List<Room> list(){
        return dao.listRoom();
    }
}
