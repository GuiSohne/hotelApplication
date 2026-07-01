package com.guilherme.hotel.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/hotel";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "guisohne2009";

    public static Connection getConnection() {
    try{
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}


