package com.example.Controllers.SlangLookUpController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SlangLookUpController {
    @FXML
    public TextField slangTextField;
    public Label labelDefinition;
    public Button editBtn;
    public Button deleteBtn;

    public void onLookUpClick(ActionEvent actionEvent) {
        SlangList slangList = SlangList.getInstance();
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());

        String string;

        if (slangWord != null) {
            string = slangWord.getSplittedDefinition();
            editBtn.setDisable(false);
            deleteBtn.setDisable(false);
        } else {
            string = "Không tìm thấy kết quả cho: " + slangTextField.getText();
            editBtn.setDisable(true);
            deleteBtn.setDisable(true);
        }

        labelDefinition.setText(string);
    }

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }
}
