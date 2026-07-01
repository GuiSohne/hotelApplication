package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;
import com.guilherme.hotel.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImp implements RoomDAO {
    @Override
    public void saveRoom(Room room){
        String sql = "INSERT INTO rooms "
                +"(number, type, daily_rate, status) VALUES"
                +"(?, ?, ?, ?)";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, room.getNumber());
            stat.setString(2, room.getType());
            stat.setDouble(3, room.getDaily_rate());
            stat.setString(4, room.getStatus());

            stat.executeUpdate();
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Room> listRoom(){
        List<Room> list = new ArrayList<>();
        String sql = "SELECT id, number, type, daily_rate, status "
                + "FROM rooms ORDER BY number";

        try{
            Connection conn = DBConnection.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                Room r = new Room();
                r.setId(rs.getLong("id"));
                r.setNumber(rs.getInt("number"));
                r.setType(rs.getString("type"));
                r.setDaily_rate(rs.getDouble("daily_rate"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public Room searchById(Long id){
        String sql = "Select id, number, type, daily_rate, status "
                +"FROM rooms WHERE id = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setLong(1, id);

            ResultSet rs = stat.executeQuery();

            if(rs.next()){
                Room r = new Room();
                r.setId(rs.getLong("id"));
                r.setNumber(rs.getInt("number"));
                r.setType(rs.getString("type"));
                r.setDaily_rate(rs.getDouble("daily_rate"));
                r.setStatus(rs.getString("status"));

                return r;
            }
            throw new RuntimeException(
                    "No room found with the ID: " + id);
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteRoom(Long id){
        String sql = "DELETE FROM rooms WHERE id = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setLong(1,  id);

            int rows = stat.executeUpdate();

            if(rows == 0){
                throw new RuntimeException( "No rooms found with the id: "+ id);
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateRoom(Room room){

        System.out.println("ID recebido: " + room.getId());
        System.out.println("Status recebido: " + room.getStatus());

        String sql = "UPDATE rooms "
                +"SET number = ?, type = ?, daily_rate = ?, status = ? "
                +"WHERE id = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, room.getNumber());
            stat.setString(2, room.getType());
            stat.setDouble(3, room.getDaily_rate());
            stat.setString(4, room.getStatus());
            stat.setLong(5, room.getId());

            int rows = stat.executeUpdate();

            System.out.println("Linhas afetadas: " + rows);

        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
