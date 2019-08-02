package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import objects.User;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInController implements Initializable {


    @FXML
    private JFXTextField txtLogin;

    @FXML
    private JFXTextField txtPass;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private Pane panePusto;

    @FXML
    private Label lblNKemail;

    @FXML
    private Label lblPustText;

    @FXML
    private Pane paneRegistred;

    @FXML
    private Pane exUser;


    private DatabaseHandler2 databaseHandler2 = new DatabaseHandler2();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSignIn.setOnAction((EventHandler) -> {
            lblPustText.setVisible(false);
            panePusto.setVisible(false);
            lblNKemail.setVisible(false);
            paneRegistred.setVisible(false);
            exUser.setVisible(false);
            if (txtLogin.getText().matches("\\w{3,25}") & txtPass.getText().matches("\\w{3,25}") & txtEmail.getText().matches(".{6,}")) {
                lblPustText.setVisible(false);
                panePusto.setVisible(false);
                if (txtLogin.getText().matches("[0-9A-Za-z_]+") & txtEmail.getText().matches("(.+@.+\\..+)")) {

                    User user = new User();
                    user.setLogin(txtLogin.getText().trim());
                    user.setEmail(txtEmail.getText().trim());
                    System.out.println(user.getLogin() + " " + user.getEmail());
                    ResultSet resultSet = databaseHandler2.getUser2(user);

                    int counter = 0;
                    while (true) {
                        try {
                            if (!resultSet.next()) {
                                break;
                            }
                            counter++;

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                    if (counter == 0) {
                        databaseHandler2.putUser(txtLogin.getText(), txtPass.getText(), txtEmail.getText());
                        paneRegistred.setVisible(true);
                    } else {
                        exUser.setVisible(true);
                    }

                } else {
                    lblNKemail.setVisible(true);
                    panePusto.setVisible(true);
                }
            } else {
                lblPustText.setVisible(true);
                panePusto.setVisible(true);
            }


        });
    }
}
