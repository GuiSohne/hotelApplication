package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Reservation;
import com.guilherme.hotel.Utils.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImp implements ReservationDAO{
    //Inserir reserva no banco
    @Override
    public void saveReservation(Reservation reservation){

        //Query SQL para salvar reserva
        String sql = "INSERT INTO reservations "
                +"(guest_id, room_id, check_in, check_out, total_amount) "
                +"VALUES (?, ?, ?, ?, ?)";

        // Abre conexão com o banco e prepara para salvar
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, reservation.getGuestid());
            stat.setLong(2, reservation.getRoomid());
            stat.setDate(3, Date.valueOf(reservation.getCheckin()));
            stat.setDate(4, Date.valueOf(reservation.getCheckout()));
            stat.setBigDecimal(5, reservation.getTotalamount());

            //Executa
            stat.executeUpdate();

            //Exceção caso  houver erro na atualização no banco
        } catch (SQLException ex){
            throw new RuntimeException("Database error while saving room");
        }
    }

    //Listar reservas
    @Override
    public List<Reservation> findAllReservation(){

        //Query SQL para listar e a criação de  uma lista
        List<Reservation> list = new ArrayList<>();
        String sql =
                "SELECT id, guest_id, room_id, check_in, check_out, total_amount " +
                        "FROM reservations ORDER BY id";

        // Abre conexão com o banco e prepara a listagem
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

            //Exceção caso  houver erro na atualização no banco
        } catch (SQLException ex) {
            throw new RuntimeException("Database error while listing room");
        }

        return list;
    }

    //Pesquisar reserva por id
    @Override
    public Reservation SearchByIdReservation(Long id) {

        //Query SQL para pesquisar pelo id
        String sql = "SELECT id, guest_id, room_id, check_in, check_out, total_amount "
                + "FROM reservations WHERE id = ?";

        //Abre conexão com o banco e prepara para pesquisar
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

            //Caso não encontrar nenhuma reserva com o respectivo ID
            throw new RuntimeException(
                    "No Reservations found with the ID: " + id
            );

            //Exceção caso  houver erro na atualização no banco
        } catch (SQLException ex) {
            throw new RuntimeException("Database error while deleting room");
        }
    }


    //Deletar reserva
    @Override
    public void deleteReservation(Long id) {
        String sql = "DELETE FROM reservations WHERE id = ?";

        // Abre conexão com o banco e prepara para deletar
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setLong(1, id);

            //criação de variável, caso nenhum linha for modificada, não houve a exclusão da reserva
            int rows = stat.executeUpdate();
            if (rows == 0){
                throw new RuntimeException(
                        "No Reservations found with the Number: " + id
                );
            }

            //Exceção caso  houver erro na atualização no banco
        } catch (SQLException ex) {
            throw new RuntimeException("Database error while deleting room");
        }

    }

    //Atualizar reserva
    @Override
    public void updateReservation(Reservation reservation) {

        //Query SQL para atualizar
        String sql = "UPDATE reservations "
                + "SET  guest_id= ?, room_id = ?, check_in = ?, check_out = ?, total_amount = ? "
                + "WHERE id = ?";

        // Abre conexão com o banco e prepara para atualizar
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, reservation.getGuestid());
            stat.setLong(2, reservation.getRoomid());
            stat.setDate(3, Date.valueOf(reservation.getCheckin()) );
            stat.setDate(4, Date.valueOf(reservation.getCheckout()));
            stat.setBigDecimal(5, reservation.getTotalamount() );
            stat.setLong(6, reservation.getId());

            //criação de variável, caso nenhum linha for modificada, não houve a atualização da reserva
            int rows = stat.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException(
                        "No Reservetation found with the ID:  "
                                + reservation.getId()
                );
            }

            //Exceção caso  houver erro na atualização no banco
        } catch (SQLException ex) {
            throw new RuntimeException("Database error while deleting room");
        }

    }
    }