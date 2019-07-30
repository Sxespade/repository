package controllers;

import java.io.IOException;
import java.util.*;
import animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController {


    private Stage showFinOthcet;

    @FXML
    private JFXTextField schetTxt;

    @FXML
    private JFXTextField calenTxt;

    @FXML
    private JFXTextField mikTxt;

    @FXML
    private JFXTextField finanKomp;


    @FXML
    private JFXTextField pravDan;

    @FXML
    private JFXTextField dolgComp;


    @FXML
    private JFXTextField calenTxtD;

    @FXML
    private JFXTextField schetTxtD;

    @FXML
    private JFXTextField donTxt;

    @FXML
    private JFXTextField pravDanD;


    @FXML
    private ImageView mikky;

    @FXML
    private ImageView imgGO;

    @FXML
    private ImageView donald;

    @FXML
    private ImageView imgStrela;

    @FXML
    private ImageView imgWin;


    @FXML
    private JFXButton btnNarMik;

    @FXML
    private JFXButton btnPerMik;

    @FXML
    private JFXButton btnFinOtch;

    @FXML
    private JFXButton btnNarDon;

    @FXML
    private JFXButton btnPerDon;


    @FXML
    private Label labNarMic1;

    @FXML
    private Label labNarMic2;

    @FXML
    private Label labNarDon;

    @FXML
    private Label badLbl;

    @FXML
    private Label badLblD;



    private int companyFinance;

    private int companydebt;

    private int periods = 0;

    private int quit1 = 1;

    private int quit2 = 1;


    private String m1;

    private String m2;

    private String d1;

    private String d2;

    private String mounth;

    private DatabaseHandler databaseHandler = new DatabaseHandler();


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

    private MyTask task = new MyTask();

    private Shake mikAnim;
    private Shake donAnim;


    @FXML
    synchronized void initialize() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(task, 0, 10000);

        mikAnim = new Shake(mikky);
        donAnim = new Shake(donald);

        mikAnim.playAnim();
        donAnim.playAnim();

        databaseHandler.cleraDB();

        showFinOtchet();

        badLbl.setVisible(false);

    }

    class MyTask extends TimerTask {
        @Override
        public synchronized void run() {


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

// Обновим поля.

            pravDan.setText("");

            pravDanD.setText("");

            pravDan.setEditable(false);

            pravDanD.setEditable(false);


// Обновляем состояние кнопки для нового периода ТаймерТаска.

            btnNarMik.setDisable(false);

            btnNarDon.setDisable(false);

// Каждый новый период таймера прибавляем 1 к periods

            periods++;

// Контроль над кнопками

            btnControlMikki();

            btnControlDonald();


// Создаем стринговый массив из месяцов

            String[] arr = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                    "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

// Когда доходим до Декабря, игра заканчивается, все останавливается, появляется финансовый
//отчёт

            if (periods == 13) {
                task.cancel();
                mikAnim.stopAnim();
                donAnim.stopAnim();
                btnNarMik.setDisable(true);
                btnPerMik.setDisable(true);
                btnPerDon.setDisable(true);
                btnNarDon.setDisable(true);
                btnFinOtch.setDisable(false);
                if (companyFinance > companydebt) {
                    imgWin.setVisible(true);
                    imgStrela.setVisible(true);
                } else {
                    imgGO.setVisible(true);
                    imgStrela.setVisible(true);
                }
            } else {

                mounth = arr[periods - 1];
                calenTxt.setText(mounth);
                calenTxtD.setText(mounth);

                if (quit1 == 1 & quit2 == 1) {

                    mainData(1,1);

                } else if (quit1 == 0 & quit2 == 1) {

                    btnNarMik.setDisable(true);

                    mainData(0,1);

                } else if (quit1 == 1 & quit2 == 0) {

                    btnNarDon.setDisable(true);

                    mainData(1,0);


                } else if (quit1 == 0 & quit2 == 0) {


                    btnNarDon.setDisable(true);
                    btnNarMik.setDisable(true);

                    mainData(0,0);

                    btnFinOtch.setDisable(false);

                    imgGO.setVisible(true);

                    imgStrela.setVisible(true);

                    task.cancel();


                }
            }
        }
    }


    private void mainData(int num1, int num2) {

        int n1 = getCharacterIndicator()*num1; //for Mickey;

        int n2 = getCounterIndicator()*num1; //for Mickey;

        int n3 = getCharacterIndicator()*num2; //for Donald;

        int n4 = getCounterIndicator()*num2; //for Donald;


        companyFinance = companyFinance + n1 * num1 * 8 + n3 * num2 * 8;

        companydebt = companydebt + (40 * 9 / 2) * num1 * 8 + (40 * 9 / 2) * num2 * 8;


        m1 = String.valueOf(n1);

        mikTxt.setText(m1);

        m2 = String.valueOf(n2);

        schetTxt.setText(m2);

        d1 = String.valueOf(n3);

        donTxt.setText(d1);

        d2 = String.valueOf(n4);

        schetTxtD.setText(d2);

        String cF = String.valueOf(companyFinance);

        finanKomp.setText(cF);

        String cD = String.valueOf(companydebt);

        dolgComp.setText(cD);


        databaseHandler.putInfo(mounth, cF, cD, m1, m2, d1, d2);
    }


    private int getCharacterIndicator() {
        return 40 * 9 / 2 - (int) (Math.random() * 30);
    }

    private int getCounterIndicator() {
        return 40 * 5 + (int) (Math.random() * 30);
    }

// Контроль над кнопками "Антимухлёж"

    private void btnControlMikki() {
        btnFinOtch.setDisable(true);
        btnPerMik.setDisable(true);
        btnNarMik.setOnAction((EventHandler) -> {
            labNarMic1.setVisible(true);
            labNarMic2.setVisible(true);
            pravDan.setEditable(true);
            editmethod();
        });
    }


    private void btnControlDonald() {
        btnPerDon.setDisable(true);
        btnNarDon.setOnAction((EventHandler) -> {
            labNarDon.setVisible(true);
            pravDanD.setEditable(true);
            editmethod2();
        });
    }



    private void editmethod() {
        btnPerMik.setDisable(false);
        btnPerMik.setOnAction((ActionEvent) -> {
            labNarMic1.setVisible(false);
            labNarMic2.setVisible(false);
            String pravDannie = pravDan.getText();
            if (pravDannie.matches("\\d{1,12}")) {
                int correctIndicators = Integer.valueOf(pravDan.getText());
                if (correctIndicators > Integer.valueOf(m2)) {
                    correctIndicators = Integer.valueOf(m2);
                    badLbl.setVisible(true);

                    btnNarMik.setDisable(true);
                    btnPerMik.setDisable(true);

                    quit1 = 0;

                    companyFinance = companyFinance + Integer.valueOf(m2);
                    finanKomp.setText(String.valueOf(companyFinance));

                    String k1 = String.valueOf(companyFinance);
                    String k2 = String.valueOf(m2);


                    databaseHandler.update1(String.valueOf(periods),k1, k2);
                    mikAnim.stopAnim();

                } else {
                    companyFinance = companyFinance - Integer.valueOf(m1) * 8 + correctIndicators * 8;
                    finanKomp.setText(String.valueOf(companyFinance));
                }
                pravDan.setEditable(false);

                databaseHandler.update1(String.valueOf(periods), String.valueOf(companyFinance), String.valueOf(correctIndicators));
                btnPerMik.setDisable(true);
                btnNarMik.setDisable(true);
            } else {
                pravDan.setText("0");
            }
        });
    }




    private void editmethod2() {
        btnPerDon.setDisable(false);
        btnPerDon.setOnAction((ActionEvent) -> {
            labNarDon.setVisible(false);
            String pravDannie = pravDanD.getText();
            if (pravDannie.matches("\\d{1,12}")) {
                int correctIndicatorsD = Integer.valueOf(pravDanD.getText());
                if (correctIndicatorsD > Integer.valueOf(d2)) {
                    correctIndicatorsD = Integer.valueOf(d2);
                    badLblD.setVisible(true);

                    donAnim.stopAnim();

                    btnNarDon.setDisable(true);
                    btnPerDon.setDisable(true);

                    quit2 = 0;
                    companyFinance = companyFinance + Integer.valueOf(d2) * 8;
                    finanKomp.setText(String.valueOf(companyFinance));

                    String k1 = String.valueOf(companyFinance);
                    String k2 = String.valueOf(d2);

                    databaseHandler.update2(String.valueOf(periods), k1, k2);
                } else {
                    companyFinance = companyFinance - Integer.valueOf(d1) * 8 + correctIndicatorsD * 8;
                    finanKomp.setText(String.valueOf(companyFinance));
                }
                pravDanD.setEditable(false);

                String k1 = String.valueOf(periods);
                String k2 = String.valueOf(companyFinance);
                String k3 = String.valueOf(correctIndicatorsD);

                databaseHandler.update2(k1, k2, k3);

                btnPerDon.setDisable(true);
                btnNarDon.setDisable(true);
            } else {
                pravDanD.setText("0");
            }
        });
    }


    private void showFinOtchet() {
        btnFinOtch.setOnAction((ActionEvent) -> {
            if (showFinOthcet == null) {
                showFinOthcet = new Stage();
                showFinOthcet.setTitle("Финансовая отчетность и показатели");
                showFinOthcet.setResizable(false);
                Parent fxmlEdit = null;
                try {
                    fxmlEdit = FXMLLoader.load(getClass().getResource("../fxml/finotchet.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                showFinOthcet.setScene(new Scene(fxmlEdit));
                showFinOthcet.initModality(Modality.WINDOW_MODAL);
                showFinOthcet.initOwner(stage);
            }

            showFinOthcet.showAndWait();

        });
    }


}





