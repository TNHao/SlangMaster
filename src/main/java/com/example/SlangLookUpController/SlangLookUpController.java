package com.example.SlangLookUpController;

import com.example.ScreenController.ScreenController;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SlangLookUpController {
    @FXML
    public TextField slangTextField;

    @FXML
    public Label labelDefinition;

    public void onLookUpClick(ActionEvent actionEvent) {
        SlangList slangList = SlangList.getInstance();
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());

        String string = "Không tìm thấy kết quả cho: " + slangTextField.getText();

        if (slangWord != null)
            string = slangWord.getSplittedDefinition();

        labelDefinition.setText(string);
    }

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }
}
