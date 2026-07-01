package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Guest;
import com.guilherme.hotel.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GuestDAOImp implements GuestDAO {
    //inserir hospede no banco
    @Override
    public void saveGuest(Guest guest) {
        String sql = "INSERT INTO guest "
                + "(name, cpf, phone, email, password) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, guest.getName());
            stat.setString(2, guest.getCpf());
            stat.setString(3, guest.getPhone());
            stat.setString(4, guest.getEmail());
            stat.setString(5, guest.getPassword());
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //lista os hospedes
    @Override
    public List<Guest> findAllGuest() {
        List<Guest> list = new ArrayList<>();
        String sql = "SELECT id, name, cpf, email, phone, password "
                + "FROM guest ORDER BY name";
        try {
            Connection conn = DBConnection.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Guest g = new Guest();
                g.setId(rs.getLong("id"));
                g.setName(rs.getString("name"));
                g.setCpf(rs.getString("cpf"));
                g.setEmail(rs.getString("email"));
                g.setPhone(rs.getString("phone"));
                g.setPassword(rs.getString("password"));
                list.add(g);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }


    //pesquisa hospede por cpf
    @Override
    public Guest searchByCPFGuest(String cpf) {
        String sql = "SELECT id, name, cpf, email, phone, password "
                + "FROM guest WHERE cpf = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, cpf);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                Guest g = new Guest();
                g.setId(rs.getLong("id"));
                g.setName(rs.getString("name"));
                g.setCpf(rs.getString("cpf"));
                g.setEmail(rs.getString("email"));
                g.setPhone(rs.getString("phone"));
                g.setPassword(rs.getString("password"));

                return g;
            }

            throw new RuntimeException(
                    "No guest found with the CPF: " + cpf
            )
;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    //deleta hospede
    @Override
    public void deleteGuest(long id) {
        String sql = "DELETE FROM guest WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setLong(1, id);

            int rows = stat.executeUpdate();

            if (rows == 0){
                throw new RuntimeException(
                        "No guest found with the ID: " + id
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    //atualiza os hospedes
    @Override
    public void updateGuest(Guest guest) {
        String sql = "UPDATE guest "
                + "SET name = ?, cpf = ?, phone = ?, email = ?, password = ? "
                + "WHERE id = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, guest.getName());
            stat.setString(2, guest.getCpf());
            stat.setString(3, guest.getPhone());
            stat.setString(4, guest.getEmail());
            stat.setString(5, guest.getPassword());
            stat.setLong(6, guest.getId());

            int rows = stat.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException(
                        "No guest found with the ID:  "
                                + guest.getId()
                );
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}

