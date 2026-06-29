package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Utils.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImp implements ReservationDAO{
    @Override
    public void saveReservation(Reservation reservation){
        String sql = "INSERT INTO reservations "
                +"(guest_id, room_id, check_in, check_out, total_amount) "
                +"VALUES (?, ?, ?, ?, ?)";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, reservation.getGuestid());
            stat.setInt(2, reservation.getRoomid());
            stat.setDate(3, Date.valueOf(reservation.getCheckin()));
            stat.setDate(4, Date.valueOf(reservation.getCheckout()));
            stat.setBigDecimal(5, reservation.getTotalamount());
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Reservation> findAllReservation(){
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
                r.setCheckin(rs.getDate("checkin").toLocalDate());
                r.setCheckout(rs.getDate("checkout").toLocalDate());
                r.setTotalamount(rs.getBigDecimal("totalamount"));
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public Reservation SearchByIdReservation(Long id) {
        String sql = "SELECT id, guest_id, room_id, check_in, check_out, total_amount "
                + "FROM guest WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, id.toString());

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                Reservation r = new Reservation();
                r.setId(rs.getLong("id"));
                r.setGuestid(rs.getInt("guestid"));
                r.setRoomid(rs.getInt("roomid"));
                r.setCheckin(rs.getDate("checkin").toLocalDate());
                r.setCheckout(rs.getDate("Checkout").toLocalDate());
                r.setTotalamount(rs.getBigDecimal("totalAmount"));

                return r;
            }

            throw new RuntimeException(
                    "No Reservations found with the ID: " + id
            )
                    ;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void deleteReservation(Long id) {
        String sql = "DELETE FROM guest WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setLong(1, id);

            int rows = stat.executeUpdate();

            if (rows == 0){
                throw new RuntimeException(
                        "No Reservations found with the ID: " + id
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE guest "
                + "SET  guestid= ?, roomid = ?, checkin = ?, checkout = ?, totalamount = ? "
                + "WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, reservation.getGuestid());
            stat.setInt(2, reservation.getRoomid());
            stat.setDate(3, Date.valueOf(reservation.getCheckin()) );
            stat.setDate(4, Date.valueOf(reservation.getCheckout()));
            stat.setBigDecimal(5, reservation.getTotalamount() );
            stat.setLong(6, reservation.getId());

            int rows = stat.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException(
                        "No Reservetation found with the ID:  "
                                + reservation.getId()
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    }





