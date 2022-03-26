package com.example.Controllers.DefinitionGuessingController;

import com.example.Controllers.GameGuessingController.GameGuessingController;
import com.example.SlangWord.SlangWord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

public class DefinitionGuessingController extends GameGuessingController {
    @FXML
    public Label slangLabel;

    @Override
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
}
