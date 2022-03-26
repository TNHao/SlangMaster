package com.example.Controllers.GameGuessingController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.SlangList.SlangList;
import com.example.Utils.Constant;
import com.example.Utils.Toast;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameGuessingController implements Initializable {
    @FXML
    public Label scoreLabel;
    public Label aLabel;
    public Label bLabel;
    public Label cLabel;
    public Label dLabel;

    protected SlangList slangList = SlangList.getInstance();
    protected int score = 0;
    protected int answerIdx = 0;
    protected int choosenIdx = 0;

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }

    public void showToast(Stage primaryStage, String mess, boolean status) {
        int toastMsgTime = 1500;
        int fadeInTime = 500;
        int fadeOutTime = 500;
        Toast.makeText(primaryStage, mess, toastMsgTime, fadeInTime, fadeOutTime, status);
    }

    public void randomQuiz() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomQuiz();
        scoreLabel.setText("Điểm: " + String.valueOf(score));
    }

    public void checkAnswer(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if (choosenIdx == answerIdx) {
            score++;
            showToast(primaryStage, "Yeah! Bạn đoán đúng rồi.", true);
        } else {
            score = 0;
            showToast(primaryStage, "Oh no! Chúc bạn may mắn lần sau.", false);
        }

        scoreLabel.setText("Điểm: " + String.valueOf(score));
        randomQuiz();
    }

    public void aClick(ActionEvent actionEvent) {
        choosenIdx = 0;
        checkAnswer(actionEvent);
    }

    public void bClick(ActionEvent actionEvent) {
        choosenIdx = 1;
        checkAnswer(actionEvent);
    }

    public void cClick(ActionEvent actionEvent) {
        choosenIdx = 2;
        checkAnswer(actionEvent);
    }

    public void dClick(ActionEvent actionEvent) {
        choosenIdx = 3;
        checkAnswer(actionEvent);
    }
}
