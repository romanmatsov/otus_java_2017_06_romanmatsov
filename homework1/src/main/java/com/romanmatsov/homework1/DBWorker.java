package com.romanmatsov.homework1;

import java.sql.*;

/**
 * Created by r on 6/4/2017.
 */
public class DBWorker {

    private final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";

    public Connection getConnection() {
        return connection;
    }

    private Connection connection;

    public DBWorker(){
        try {
            connection = DriverManager.getConnection(HOST, USERNAME,PASSWORD);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
