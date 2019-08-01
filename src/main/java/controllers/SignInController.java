package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


import java.net.URL;
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


    private DatabaseHandler2 databaseHandler2 = new DatabaseHandler2();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSignIn.setOnAction((EventHandler) -> {
            lblPustText.setVisible(false);
            panePusto.setVisible(false);
            lblNKemail.setVisible(false);
            if (txtLogin.getText().matches("\\w{3,25}") & txtPass.getText().matches("\\w{3,25}") & txtEmail.getText().matches(".{6,}")) {
                lblPustText.setVisible(false);
                panePusto.setVisible(false);
                if (txtLogin.getText().matches("[0-9A-Za-z_]+") & txtEmail.getText().matches("(.+@.+\\..+)")) {

                    databaseHandler2.putUser(txtLogin.getText(), txtPass.getText(), txtEmail.getText());

                    MainController mainController = new MainController();

                    mainController.openNewScene(btnSignIn, "fxml/main.fxml");

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
