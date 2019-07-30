package controllers;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    Stage stage;


    int companyFinance;

    int companydebt;

    int periods = 0;

    int quit1 = 0;

    int quit2 = 0;


    String m1;

    String m2;

    String d1;

    String d2;

    String mounth;

    DatabaseHandler databaseHandler = new DatabaseHandler();


    double x = 0;

    double y = 0;


    @FXML
    void dragged(MouseEvent event) {
        MainController mainController = new MainController();
        mainController.dragged(event);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();

    }


    MyTask task = new MyTask();

    Shake mikAnim;
    Shake donAnim;


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

                if (quit1 == 0 & quit2 == 0) {
// Вызовем метод "getIndicators"  для Микки

                    m1 = getCharacterIndicators();

                    mikTxt.setText(m1);


// Вызываем тот же метод и для Дональда. Метод "getIndicators" будет содержать рандомные данные,
// заниженные в меньшую сторону, относительно данных, что показывает счетчик.

                    d1 = getCharacterIndicators();

                    donTxt.setText(d1);

// Теперь вызовем методы для расчёта показателей счетчиков наших персонажей.

                    m2 = getCounterIndicators();

                    schetTxt.setText(m2);

                    d2 = getCounterIndicators();

                    schetTxtD.setText(d2);


// Теперь подсчетаем финансы компании и долги. Финансы компании изначально будут рассчитываться
//как показатели обоих персонажей х8.

                    companyFinance = companyFinance + Integer.valueOf(m1) * 8 + Integer.valueOf(d1) * 8;

                    String cF = String.valueOf(companyFinance);

                    finanKomp.setText(cF);

                    companydebt = companydebt + (40 * 9 / 2 )*2*8;

                    String cD = String.valueOf(companydebt);

                    dolgComp.setText(cD);

                    databaseHandler.putInfo(mounth, cF, cD, m1, m2, d1, d2);
                } else if (quit1 == 1 & quit2 == 0) {

                    btnNarMik.setDisable(true);

                    m1 = "0";

                    mikTxt.setText(m1);

                    d1 = getCharacterIndicators();

                    donTxt.setText(d1);

                    m2 = "0";

                    schetTxt.setText(m2);

                    d2 = getCounterIndicators();

                    schetTxtD.setText(d2);

                    companyFinance = companyFinance + Integer.valueOf(d1) * 8;

                    String cF = String.valueOf(companyFinance);

                    finanKomp.setText(cF);

                    companydebt = companydebt + (40 * 9 / 2 + (int)(Math.random()*30))*8;

                    String cD = String.valueOf(companydebt);

                    dolgComp.setText(cD);


                    databaseHandler.putInfo(mounth, cF, cD, m1, m2, d1, d2);


                } else if (quit1 == 0 & quit2 == 1) {

                    btnNarDon.setDisable(true);

                    m1 = getCharacterIndicators();

                    mikTxt.setText(m1);

                    d1 = "0";

                    donTxt.setText(d1);

                    m2 = getCounterIndicators();

                    schetTxt.setText(m2);

                    d2 = "0";

                    schetTxtD.setText(d2);

                    companyFinance = companyFinance + Integer.valueOf(m1) * 8;

                    String cF = String.valueOf(companyFinance);

                    finanKomp.setText(cF);

                    companydebt = companydebt + (40 * 9 / 2 + (int)(Math.random()*30))*8;

                    String cD = String.valueOf(companydebt);

                    dolgComp.setText(cD);

                    pravDanD.setText(("0"));

                    databaseHandler.putInfo(mounth, cF, cD, m1, m2, d1, d2);


                } else if (quit1 == 1 & quit2 == 1) {


                    btnNarDon.setDisable(true);
                    btnNarMik.setDisable(true);

                    m1 = "0";

                    mikTxt.setText(m1);

                    d1 = "0";

                    donTxt.setText(d1);

                    m2 = "0";

                    schetTxt.setText(m2);

                    d2 = "0";

                    schetTxtD.setText(d2);

                    companyFinance = companyFinance + Integer.valueOf(d1) * 8 + Integer.valueOf(m1)*8;

                    String cF = String.valueOf(companyFinance);

                    finanKomp.setText(cF);

                    companydebt = companydebt + (40 * 9 / 2 + (int)(Math.random()*30))*2*8;

                    String cD = String.valueOf(companydebt);

                    dolgComp.setText(cD);

                    databaseHandler.putInfo(mounth, cF, cD, m1, m2, d1, d2);

                    btnFinOtch.setDisable(false);

                    imgGO.setVisible(true);

                    imgStrela.setVisible(true);

                    task.cancel();


                }
            }
        }
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


    private String getCounterIndicators() {
        int counterIndicators = 40 * 5 + (int)(Math.random()*30);
        String s = String.valueOf(counterIndicators);
        return s;
    }

    private String getCharacterIndicators() {
        int characterIndicators = 40 * 9 /2 - (int)(Math.random()*30);
        String s = String.valueOf(characterIndicators);
        return s;
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
                    mikAnim.stopAnim();
                    btnNarMik.setDisable(true);
                    btnPerMik.setDisable(true);
                    quit1 = 1;
                    companyFinance = companyFinance + Integer.valueOf(m2);
                    finanKomp.setText(String.valueOf(companyFinance));
                    dolgComp.setText(String.valueOf(companydebt));
                    System.out.println(periods);
                    String k1 = String.valueOf(periods);
                    String k2 = String.valueOf(companyFinance);
                    String k3 = String.valueOf(companydebt);
                    String k4 = String.valueOf(m2);
                    String k5 = String.valueOf(m2);
                    databaseHandler.update2(k1, k2, k3, k4, k5);
                } else {
                    companyFinance = companyFinance - Integer.valueOf(m1) * 8 + correctIndicators * 8;
                    finanKomp.setText(String.valueOf(companyFinance));
                }
                pravDan.setEditable(false);
                System.out.println(periods);
                String k1 = String.valueOf(periods);
                String k2 = String.valueOf(companyFinance);
                String k3 = String.valueOf(correctIndicators);
                databaseHandler.update1(k1, k2, k3);
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
                    quit2 = 1;
                    companyFinance = companyFinance + Integer.valueOf(d2) * 8;
                    finanKomp.setText(String.valueOf(companyFinance));
                    dolgComp.setText(String.valueOf(companydebt));
                    System.out.println(periods);
                    String k1 = String.valueOf(periods);
                    String k2 = String.valueOf(companyFinance);
                    String k3 = String.valueOf(companydebt);
                    String k4 = String.valueOf(d2);
                    String k5 = String.valueOf(d2);
                    databaseHandler.update3(k1, k2, k3, k4, k5);
                } else {
                    companyFinance = companyFinance - Integer.valueOf(d1) * 8 + correctIndicatorsD * 8;
                    finanKomp.setText(String.valueOf(companyFinance));
                }
                pravDanD.setEditable(false);
                System.out.println(periods);
                String k1 = String.valueOf(periods);
                String k2 = String.valueOf(companyFinance);
                String k3 = String.valueOf(correctIndicatorsD);
                databaseHandler.update4(k1, k2, k3);
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
                showFinOthcet.setScene(new Scene(fxmlEdit));
                showFinOthcet.initModality(Modality.WINDOW_MODAL);
                showFinOthcet.initOwner(stage);
            }

            showFinOthcet.showAndWait(); // для ожидания закрытия окна

        });
    }


}





