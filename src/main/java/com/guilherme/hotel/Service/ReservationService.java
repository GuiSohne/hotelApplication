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

    //Realizar checkin
    public void checkin(Reservation reservation){
        //Verifica se o hospede foi selecionado e após se um quarto foi selecionado
        if(reservation.getGuestid() == 0){
            throw new IllegalArgumentException("Select a guest.");
        }

        if(reservation.getRoomid() == 0){
            throw new IllegalArgumentException("Select a room.");
        }

        //Após pegar o quarto pesquisa
        Room room = roomDAO.searchById(reservation.getRoomid());

        //Verifica se o quarto existe, e depois se está ocupado
        if(room == null){
            throw new IllegalArgumentException("The room was not found.");
        }

        if("Occupied".equalsIgnoreCase(room.getStatus())){
            throw new IllegalArgumentException("This room is already occupied..");
        }

        //Calcula valor total
        long dias = ChronoUnit.DAYS.between(
                reservation.getCheckin(),
                reservation.getCheckout()
        );

        BigDecimal total = BigDecimal.valueOf(dias)
                .multiply(BigDecimal.valueOf(room.getDaily_rate()));

        reservation.setTotalamount(total);

        //Coloca o quarto como ocupado e atualiza o mesmo
        room.setStatus("Occupied");
        roomDAO.updateRoom(room);

        reservationDAO.saveReservation(reservation);

    }
    //Realizar checkout
    public void checkOut(long roomId){
        //pesquisa o quarto e após tira a ocupação.
        Room room = roomDAO.searchById(roomId);

        room.setStatus("Available");
        roomDAO.updateRoom(room);
    }

    //Listar reservas
    public List<Reservation> list(){
        return reservationDAO.findAllReservation();
    }
}
