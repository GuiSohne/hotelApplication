package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.ReservationDAO;
import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Model.Room;

import java.util.List;

public class ReservationService {
    private ReservationDAO dao = new ReservationDAOImp();

    public void saveRoom(Reservation reservation){
        //nome  e usuarios vazios
        if(reservation.getGuestid() == 0 || reservation.getRoomid() == 0){
            throw new IllegalArgumentException("AAAAAAAAAAAA");
        }

        if(reservation.getCheckin() == null){

        }

        dao.saveReservation(reservation);
    }

    public List<Reservation> list(){
        return dao.findAllReservation();
    }
}
