package controllers;

import Start.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import objects.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class SendMailController implements Initializable {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnSendPass;

    @FXML
    private Pane emptyPane1;

    @FXML
    private Pane emptyPane2;

    @FXML
    private Pane emptyPane3;

    @FXML
    private Pane emptyPane4;

    private Session mailSession;

    private MimeMessage message;

    private String logintext;

    private String passwordtext;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnSendPass.setOnAction((EventHandler) -> {
            emptyPane1.setVisible(false);
            emptyPane2.setVisible(false);
            emptyPane3.setVisible(false);
            emptyPane4.setVisible(false);


            String emailText = txtEmail.getText().trim();
            if (!emailText.equals("")) {

                if (txtEmail.getText().matches("(.+@.+\\..+)")) {
                    eMailUser(emailText);
                } else {
                    emptyPane2.setVisible(true);
                }

            } else {
                emptyPane1.setVisible(true);
            }

            /*
             */

        });
    }

    private void eMailUser(String emailText) {
        DatabaseHandler2 databaseHandler2 = new DatabaseHandler2();
        User user = new User();
        user.setEmail(emailText);
        ResultSet resultSet = databaseHandler2.loginAndPass(user);


        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
                else {
                    logintext = resultSet.getString(2);
                    passwordtext = resultSet.getString(3);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }



        if (counter > 0) {

            try {

                final Properties properties = new Properties();
                try {
                    properties.load(Main.class.getClassLoader().getResourceAsStream("mail.properties"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mailSession = Session.getDefaultInstance(properties);
                message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress("electroworldgame@gmail.com"));
                message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(txtEmail.getText(), "objects.User")));
                message.setSubject("Login and Password");

                message.setText("Hello, " + logintext + "!\nYour Password is: " + passwordtext);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                Transport tr = mailSession.getTransport();
                tr.connect("electroworldgame@gmail.com", "130989021271");
                tr.sendMessage(message, message.getAllRecipients());
                emptyPane4.setVisible(true);
                tr.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        } else {
            emptyPane3.setVisible(true);
        }
    }

}


