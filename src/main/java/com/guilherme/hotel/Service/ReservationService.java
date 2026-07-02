package com.guilherme.hotel.Service;

import com.guilherme.hotel.Dao.ReservationDAO;
import com.guilherme.hotel.Dao.ReservationDAOImp;
import com.guilherme.hotel.Dao.RoomDAO;
import com.guilherme.hotel.Dao.RoomDAOImp;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Model.Room;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationService {

    private ReservationDAO reservationDAO = new ReservationDAOImp();
    private RoomDAO roomDAO = new RoomDAOImp();
    private Room room = new Room();

    public void checkin(Reservation reservation){

        if(reservation.getGuestid() == 0){
            throw new IllegalArgumentException("Selecione um hóspede.");
        }

        if(reservation.getRoomid() == 0){
            throw new IllegalArgumentException("Selecione um quarto.");
        }

        Room room = roomDAO.searchById(reservation.getRoomid());

        if(room == null){
            throw new IllegalArgumentException("Quarto não encontrado.");
        }

        if("Occupied".equalsIgnoreCase(room.getStatus())){
            throw new IllegalArgumentException("Este quarto já está ocupado.");
        }

        long dias = ChronoUnit.DAYS.between(
                reservation.getCheckin(),
                reservation.getCheckout()
        );

        BigDecimal total = BigDecimal.valueOf(dias)
                .multiply(BigDecimal.valueOf(room.getDaily_rate()));

        reservation.setTotalamount(total);

        room.setStatus("Occupied");
        roomDAO.updateRoom(room);

        reservationDAO.saveReservation(reservation);

    }

    public void checkOut(long roomId){
        Room room = roomDAO.searchById(roomId);

        room.setStatus("Available");
        roomDAO.updateRoom(room);
    }

    public List<Reservation> list(){
        return reservationDAO.findAllReservation();
    }
}
