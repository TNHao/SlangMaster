package com.example.DailySlangController;

import com.example.ScreenController.ScreenController;
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
        ScreenController.activate("dashboard", actionEvent);
    }
}
