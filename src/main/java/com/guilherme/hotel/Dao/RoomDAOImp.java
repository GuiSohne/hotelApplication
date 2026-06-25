package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;
import com.guilherme.hotel.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImp implements RoomDAO {
    @Override
    public void save(Room room){
        String sql = "INSERT INTO rooms "
                +"(number, type, daily_rate, status) VALUES"
                +"(?, ?, ?, ?)";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, room.getNumber());
            stat.setString(2, room.getType());
            stat.setString(3, room.getStatus());
            stat.setDouble(4, room.getDaily_rate());
            stat.executeUpdate();
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Room> list(){
        List<Room> list = new ArrayList<>();
        String sql = "SELECT id, number, type, status, daily_rate"
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
                r.setStatus(rs.getString("status"));
                r.setDaily_rate(rs.getDouble("daily_rate"));
                list.add(r);
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return list;
    }

    @Override
    public Room searchById (Long id){
        String sql = "SELECT id, number, type, daily_rate, status "
                +"FROM rooms"
                +"WHERE id = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setLong(1, id);
            ResultSet rs = stat.executeQuery(sql);



        }
    }

}
