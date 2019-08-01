package database;

import objects.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler2 {


    public Connection getDbConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game?serverTimezone=UTC", "root", "1234");
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Error");
        }
        return connection;
    }

    public void putUser(String login, String password, String email) {
        String insert = "INSERT INTO people(login,password,email) VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.setString(3, email);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "select * from people where login=? and password=?";

        try{
            PreparedStatement prSt= getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resultSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }



    }

