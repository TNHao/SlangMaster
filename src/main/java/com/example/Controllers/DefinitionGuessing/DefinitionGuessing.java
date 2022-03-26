package com.example.Controllers.DefinitionGuessing;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class DefinitionGuessing implements Initializable {
    @FXML
    public Label scoreLabel;
    public Label slangLabel;
    public Label aLabel;
    public Label bLabel;
    public Label cLabel;
    public Label dLabel;

    private SlangList slangList = SlangList.getInstance();
    private int score = 0;
    private int answerIdx = 0;
    private int choosenIdx = 0;

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }

    public void randomQuiz() {
        ArrayList<SlangWord> answers = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            answers.add(slangList.randomSlang());
        }

        Random random = new Random();
        answerIdx = random.nextInt(4);

        slangLabel.setText(answers.get(answerIdx).getSlang());
        aLabel.setText(answers.get(0).getDefinition());
        bLabel.setText(answers.get(1).getDefinition());
        cLabel.setText(answers.get(2).getDefinition());
        dLabel.setText(answers.get(3).getDefinition());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomQuiz();
        scoreLabel.setText("Điểm: " + String.valueOf(score));
    }
}
