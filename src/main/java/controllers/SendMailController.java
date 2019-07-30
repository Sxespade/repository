package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sun.applet.Main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SendMailController implements Initializable {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnSendPass;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnSendPass.setOnAction((EventHandler) -> {


            final Properties properties = new Properties();
            try {
                properties.load(Main.class.getClassLoader().getResourceAsStream("mail.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            try {
                message.setFrom(new InternetAddress("electroworldgame@gmail.com"));
                message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(txtEmail.getText(), "objects.User")));
                message.setSubject("Hello");
                message.setText("Hi this is test message from ElectroWorld");
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                Transport tr = mailSession.getTransport();
                tr.connect("electroworldgame@gmail.com", "130989021271");
                tr.sendMessage(message, message.getAllRecipients());
                tr.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }


        });
    }

    }


