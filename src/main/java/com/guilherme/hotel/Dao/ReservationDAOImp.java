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
            stat.setLong(2, reservation.getRoomid());
            stat.setDate(3, Date.valueOf(reservation.getCheckin()));
            stat.setDate(4, Date.valueOf(reservation.getCheckout()));
            stat.setBigDecimal(5, reservation.getTotalamount());

            stat.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Reservation> findAllReservation(){

        List<Reservation> list = new ArrayList<>();

        String sql =
                "SELECT id, guest_id, room_id, check_in, check_out, total_amount " +
                        "FROM reservations ORDER BY id";

        try {

            Connection conn = DBConnection.getConnection();

            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {

                Reservation r = new Reservation();

                r.setId(rs.getLong("id"));
                r.setGuestid(rs.getInt("guest_id"));
                r.setRoomid(rs.getInt("room_id"));
                r.setCheckin(rs.getDate("check_in").toLocalDate());
                r.setCheckout(rs.getDate("check_out").toLocalDate());
                r.setTotalamount(rs.getBigDecimal("total_amount"));

                list.add(r);
            }

        } catch (SQLException ex) {

            throw new RuntimeException(ex);
        }

        return list;
    }

    @Override
    public Reservation SearchByIdReservation(Long id) {
        String sql = "SELECT id, guest_id, room_id, check_in, check_out, total_amount "
                + "FROM reservations WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setLong(1, id);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                Reservation r = new Reservation();
                r.setGuestid(rs.getInt("guest_id"));
                r.setRoomid(rs.getInt("room_id"));
                r.setCheckin(rs.getDate("check_in").toLocalDate());
                r.setCheckout(rs.getDate("check_out").toLocalDate());
                r.setTotalamount(rs.getBigDecimal("total_amount"));

                return r;
            }

            throw new RuntimeException(
                    "No Reservations found with the ID: " + id
            );

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void deleteReservation(Long id) {
        String sql = "DELETE FROM reservations WHERE id = ?";

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
        String sql = "UPDATE reservations "
                + "SET  guest_id= ?, room_id = ?, check_in = ?, check_out = ?, total_amount = ? "
                + "WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, reservation.getGuestid());
            stat.setLong(2, reservation.getRoomid());
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





