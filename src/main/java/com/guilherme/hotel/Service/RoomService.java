package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.RoomDAO;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Model.Room;

import java.util.List;

public class RoomService {
    private RoomDAO dao = new RoomDAOImp();

    public void save(Reservation reservation){
        //nome  e usuarios vazios
        if(reservation.getGuestid() == 0 || reservation.getRoomid() == 0){
            throw new IllegalArgumentException("AAAAAAAAAAAA");
        }

        if(reservation.getCheckin() == null){

        }

        dao.save(reservation);
    }

    public List<Room> list(){
        return dao.list();
    }
}
