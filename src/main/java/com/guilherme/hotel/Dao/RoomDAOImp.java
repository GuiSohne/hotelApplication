package com.guilherme.hotel.Dao;

import com.guilherme.hotel.Model.Room;
import com.guilherme.hotel.Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImp implements RoomDAO {
    //Inserir quarto no banco
    @Override
    public void saveRoom(Room room){

        //Query SQL para salvar quarto
        String sql = "INSERT INTO rooms "
                +"(number, type, daily_rate) VALUES"
                +"(?, ?, ?)";

        // Abre conexão com o banco e prepara para salvar
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1, room.getNumber());
            stat.setString(2, room.getType());
            stat.setDouble(3, room.getDaily_rate());


            //Executa
            stat.executeUpdate();

            //Exceção caso  houver erro na atualização no banco
        }catch(SQLException ex){
            throw new RuntimeException("Database error while saving room");
        }
    }

    //Listar todos os quartos
    @Override
    public List<Room> listRoom(){

        //Query SQL para listar e a criação de uma lista
        List<Room> list = new ArrayList<>();
        String sql = "SELECT id, number, type, daily_rate, status "
                + "FROM rooms ORDER BY number";

        // Abre conexão com o banco e prepara a listagem
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

            //Exceção caso  houver erro na atualização no banco
        }catch(SQLException ex){
            throw new RuntimeException("Database error while listing room");
        }
        return list;
    }

    //Pesquisar quarto por id
    @Override
    public Room searchById(Long id){
        //Query SQL para pesquisar pelo id
        String sql = "Select id, number, type, daily_rate, status "
                +"FROM rooms WHERE id = ?";

        //Abre conexão com o banco e prepara para pesquisar
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

            //Caso não encontrar nenhum quarto com o respectivo ID
            throw new RuntimeException(
                    "No room found with the ID: " + id);

            //Exceção caso  houver erro na atualização no banco
        }catch (SQLException ex){
            throw new RuntimeException("Database error while searching room");
        }
    }

    //Deleta o quarto
    @Override
    public void deleteRoom(int number){
        //Query SQL para deletar pelo id
        String sql = "DELETE FROM rooms WHERE number = ?";

        // Abre conexão com o banco e prepara para deletar
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1,  number);

            //criação de variável, caso nenhum linha for modificada, não houve a exclusão do quarto
            int rows = stat.executeUpdate();
            if(rows == 0){
                throw new RuntimeException( "No rooms found with the number: "+ number);
            }

            //Exceção caso  houver erro na atualização no banco
        }catch (SQLException ex){
            throw new RuntimeException("Database error while deleting room");
        }
    }


    //Atualiza os dados do quarto
    @Override
    public void updateRoom(Room room){

        //Query SQL para atualizar pelo id
        String sql = "UPDATE rooms "
                +"SET  type = ?, daily_rate = ?, status = ? "
                +"WHERE id = ?";

         // Abre conexão com o banco e prepara a atualização
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, room.getType());
            stat.setDouble(2, room.getDaily_rate());
            stat.setString(3, room.getStatus());
            stat.setLong(4, room.getId()   );

            //Exceção caso  houver erro na atualização no banco
        }catch(SQLException ex){
            throw new RuntimeException("Database error while updating room");
        }
    }

}
