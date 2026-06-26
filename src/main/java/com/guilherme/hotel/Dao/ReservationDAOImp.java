package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Guest;
import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImp implements GuestDAO{
    @Override
    public void Save(Reservation reservation){
        String sql = "INSERT INTO reservations "
                +"(guest_id, room_id, check_in, check_out, total_amount) "
                +"VALUES (?, ?, ?, ?, ?)";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, reservation.getGuestid());
            stat.setInt(2, reservation.getRoomid());
            stat.setDate(3, reservation.getCheckin());
            stat.setDate(4, reservation.getCheckout());
            stat.setDouble(5, reservation.getTotalamount());
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Guest> findAll(){
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT  id, guest_id, room_id, check_in, check_out, total_amount"
                + " FROM reservations ORDER BY id";
        try{
            Connection conn = DBConnection.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                Reservation r = new Reservation();
                r.setId(rs.getInt("id"));
                r.setGuestid(rs.getInt("guestId"));
                r.setRoomid(rs.getInt("roomId"));
                r.setCheckin(rs.get("checkin"));
                r.setCheckout(rs.getDate("checkout"));
                r.setTotalamount(rs.getDouble("totalamount"));
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return list;
    }



}
