package com.example.Controllers.SlangGuessingController;

import com.example.Controllers.GameGuessingController.GameGuessingController;
import com.example.SlangWord.SlangWord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

public class SlangGuessingController extends GameGuessingController {
    @FXML
    public Label definitionLabel;

    @Override
    public void randomQuiz() {
        ArrayList<SlangWord> answers = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            answers.add(slangList.randomSlang());
        }

        Random random = new Random();
        answerIdx = random.nextInt(4);

        definitionLabel.setText(answers.get(answerIdx).getDefinition());
        aLabel.setText(answers.get(0).getSlang());
        bLabel.setText(answers.get(1).getSlang());
        cLabel.setText(answers.get(2).getSlang());
        dLabel.setText(answers.get(3).getSlang());
    }
}
