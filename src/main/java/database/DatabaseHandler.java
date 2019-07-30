package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import objects.StrokaOtcheta;

import java.sql.*;

public class DatabaseHandler {
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

    public void putInfo(String mounth, String financyComp, String dolgComp, String mikkiDannie, String mikkiSchet, String donaldDannie, String donaldSchet) {
        String insert = "INSERT INTO game(mounth,financy_companii,dolgy_companii,mikki_dannie,mikki_schetchik," +
                "donaldDannie,donaldSchet) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);


            prSt.setString(1, mounth);
            prSt.setString(2, financyComp);
            prSt.setString(3, dolgComp);
            prSt.setString(4, mikkiDannie);
            prSt.setString(5, mikkiSchet);
            prSt.setString(6, donaldDannie);
            prSt.setString(7, donaldSchet);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<StrokaOtcheta> observableList = FXCollections.observableArrayList();
    StrokaOtcheta strokaOtcheta;


    public ObservableList getInfo2() throws SQLException {
        Statement statement = getDbConnection().createStatement();
        {
            ResultSet resultset = statement.executeQuery("select game.id,game.mounth,game.financy_companii,game.dolgy_companii,game.mikki_schetchik,game.mikki_dannie,game.donaldDannie,game.donaldSchet from game;");
            while (resultset.next()) {
                Integer id = resultset.getInt("id");
                String mounth = resultset.getString("mounth");
                String financyComp = resultset.getString("financy_companii");
                String dolgComp = resultset.getString("dolgy_companii");
                String mikki_schetchik = resultset.getString("mikki_dannie");
                String mikki_dannie = resultset.getString("mikki_schetchik");
                String financy_companii = resultset.getString("donaldDannie");
                String dolgy_companii = resultset.getString("donaldSchet");
                strokaOtcheta = new StrokaOtcheta(id, mounth, financyComp, dolgComp, mikki_schetchik, mikki_dannie,
                        financy_companii, dolgy_companii);
                observableList.add(strokaOtcheta);

            }
        }
        return observableList;
    }


    public void update1(String i, String financyComp, String mikkiDannie) {

        Statement statement = null;
        try {
            statement = getDbConnection().createStatement();
            {
                statement.executeUpdate("update game set financy_companii =" + financyComp + "  where id =" + i);
                statement.executeUpdate("update game set mikki_dannie =" + mikkiDannie + "  where id =" + i);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }


    public void update2(String i, String financyComp, String dolgComp, String mikkiDannie, String mikkiSchetchik) {

        Statement statement = null;
        try {
            statement = getDbConnection().createStatement();
            {
                statement.executeUpdate("update game set financy_companii =" + financyComp + "  where id =" + i);
                statement.executeUpdate("update game set dolgy_companii =" + dolgComp + "  where id =" + i);
                statement.executeUpdate("update game set mikki_dannie =" + mikkiDannie + "  where id =" + i);
                statement.executeUpdate("update game set mikki_schetchik =" + mikkiSchetchik + "  where id =" + i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update3(String i, String financyComp, String dolgComp, String donaldDannie, String donaldSchetchik) {

        Statement statement = null;
        try {
            statement = getDbConnection().createStatement();
            {
                statement.executeUpdate("update game set financy_companii =" + financyComp + "  where id =" + i);
                statement.executeUpdate("update game set dolgy_companii =" + dolgComp + "  where id =" + i);
                statement.executeUpdate("update game set donaldDannie =" + donaldDannie + "  where id =" + i);
                statement.executeUpdate("update game set donaldSchet =" + donaldSchetchik + "  where id =" + i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update4(String i, String financyComp, String donaldDannie) {

        Statement statement = null;
        try {
            statement = getDbConnection().createStatement();
            {
                statement.executeUpdate("update game set financy_companii =" + financyComp + "  where id =" + i);
                statement.executeUpdate("update game set donaldDannie =" + donaldDannie + "  where id =" + i);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }





    public void cleraDB() {
        {
            String clear = "TRUNCATE TABLE game";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(clear);
                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
