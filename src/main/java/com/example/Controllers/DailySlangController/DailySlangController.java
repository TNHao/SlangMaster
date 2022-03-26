package com.example.Controllers.DailySlangController;

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
import java.util.ResourceBundle;

public class DailySlangController implements Initializable {
    private SlangList slangList = SlangList.getInstance();

    @FXML
    public Label slangText;
    public Label definitionText;

    public void setText() {
        SlangWord slangWord = slangList.randomSlang();
        slangText.setText(slangWord.getSlang());
        definitionText.setText(slangWord.getDefinition());
    }

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setText();
    }

    public void onNextSlangClick(ActionEvent actionEvent) {
        setText();
    }
}
