package controllers;

import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import objects.StrokaOtcheta;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FinOtchetController implements Initializable {

    @FXML
    public TableView<StrokaOtcheta> tableFO;

    @FXML
    private TableColumn<StrokaOtcheta, Integer> idColumn;

    @FXML
    public TableColumn<StrokaOtcheta, String> colMounth;

    @FXML
    public TableColumn<StrokaOtcheta, String> colFinancy;

    @FXML
    public TableColumn<StrokaOtcheta, String> colDolgi;

    @FXML
    public TableColumn<StrokaOtcheta, String> colMikki;

    @FXML
    public TableColumn<StrokaOtcheta, String> colSchMikki;

    @FXML
    public TableColumn<StrokaOtcheta, String> colDonald;

    @FXML
    public TableColumn<StrokaOtcheta, String> colSchDon;


    private DatabaseHandler databaseHandler = new DatabaseHandler();

    private ObservableList<StrokaOtcheta> backupList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMounth.setCellValueFactory(new PropertyValueFactory<>("mounth"));
        colFinancy.setCellValueFactory(new PropertyValueFactory<>("financy"));
        colDolgi.setCellValueFactory(new PropertyValueFactory<>("dolgi"));
        colMikki.setCellValueFactory(new PropertyValueFactory<>("mikkiOtch"));
        colSchMikki.setCellValueFactory(new PropertyValueFactory<>("mikSchetchik"));
        colDonald.setCellValueFactory(new PropertyValueFactory<>("donaldOtch"));
        colSchDon.setCellValueFactory(new PropertyValueFactory<>("donSchetchik"));

        try {
            databaseHandler.getInfo2();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            backupList.addAll(databaseHandler.observableList);
            tableFO.setItems(backupList);
    }

}




