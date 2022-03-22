package com.example.DashboardController;

import com.example.ScreenController.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {
    @FXML
    public void onSlangLookUpClick(ActionEvent actionEvent) {
        ScreenController.activate("slangLookup", actionEvent);
    }

    @FXML
    public void onSlangFindingClick(ActionEvent actionEvent) {
        ScreenController.activate("slangFinding", actionEvent);
    }

    @FXML
    public void onAddClick(ActionEvent actionEvent) {
//        ScreenController.activate();
    }

    @FXML
    public void onResetClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onDefinitionClick(ActionEvent actionEvent) {
        ScreenController.activate("definitionGuessing", actionEvent);
    }

    @FXML
    public void onHistoryClick(ActionEvent actionEvent) {
        ScreenController.activate("history", actionEvent);
    }

    @FXML
    public void onDailySlangClick(ActionEvent actionEvent) {
        ScreenController.activate("dailySlang", actionEvent);
    }

    @FXML
    public void onSlangGuessingClick(ActionEvent actionEvent) {
        ScreenController.activate("slangGuessing", actionEvent);
    }
}
