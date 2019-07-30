package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler2 {
    private String dbUser = "root";
    private String dbPass = "1234";
    private String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/game?serverTimezone=UTC";


    public Connection getDbConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, dbUser, dbPass);
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


    ArrayList<User> arrayList = new ArrayList<>();
    User user;


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

