package com.romanmatsov.homework1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by r on 6/4/2017.
 */
public class Main {

    public static void main(String[] args) {
        DBWorker worker = new DBWorker();

        String query = " select * from users;";

        try {
            Statement statement = worker.getConnection().createStatement();
            ResultSet resultset = statement.executeQuery(query);

            while(resultset.next()){
                User user = new User();
                int id = resultset.getInt(1);
                user.setId(id);
                String name = resultset.getString(2);
                user.setUsername(name);
                String password = resultset.getString(3);
                user.setPassword(password);

                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
