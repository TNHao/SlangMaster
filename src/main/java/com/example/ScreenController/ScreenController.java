package com.example.ScreenController;

import com.example.DailySlangController.DailySlangController;
import com.example.slangmaster.SlangMasterApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {
    public static void activate(String name, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(SlangMasterApplication.class.getResource(name));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        switch (name) {
            case "dailySlang": {
                DailySlangController dailySlangController = loader.getController();
                dailySlangController.setText("sfbfgns");
                break;
            }
            default:
                break;
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
