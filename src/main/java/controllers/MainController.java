package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler2;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import objects.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private double x = 0;
    private double y = 0;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();

    }


    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignInGame;

    @FXML
    private JFXButton btnForgetPass;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private Pane txtEmptyPane;

    @FXML
    private Label lblEmptyText;

    @FXML
    private Label lblNotRegistred;






    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setOnAction((EventHandler) -> {
        String loginText = txtUser.getText().trim();
        String PasswordText = txtPass.getText().trim();

        if (!loginText.equals("") && !PasswordText.equals("")) {

            loginUser(loginText, PasswordText);
        }
        else {txtEmptyPane.setVisible(true);
            lblEmptyText.setVisible(true);
        }});





        btnSignInGame.setOnAction(event -> {
            txtEmptyPane.setVisible(false);
            lblEmptyText.setVisible(false);
            lblNotRegistred.setVisible(false);
            openNewScene(btnForgetPass, "../fxml/signin.fxml");

        });

        btnForgetPass.setOnAction(event ->

            openNewScene(btnForgetPass, "../fxml/sendmail.fxml")

        );

    }

    private void loginUser(String logintext, String passwordText) {
        lblEmptyText.setVisible(false);
        lblNotRegistred.setVisible(false);
        DatabaseHandler2 databaseHandler2 = new DatabaseHandler2();
        User user = new User();
        user.setLogin(logintext);
        user.setPassword(passwordText);
        ResultSet resultSet = databaseHandler2.getUser(user);

        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }

        if (counter > 0) {

            openNewScene(btnLogin, "../fxml/game.fxml");

        } else {
            txtEmptyPane.setVisible(true);
            lblNotRegistred.setVisible(true);
        }

    }

    protected void openNewScene(Node node, String window) {


        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


}


