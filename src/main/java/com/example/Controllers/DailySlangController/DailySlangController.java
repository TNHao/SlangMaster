package com.example.Controllers.DailySlangController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.Utils.Constant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class DailySlangController{
    @FXML
    public Label slangText;

    public void setText(String string){
        slangText.setText(string);
    }

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }
}
