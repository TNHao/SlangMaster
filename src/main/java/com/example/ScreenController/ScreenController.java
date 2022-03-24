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
import java.util.HashMap;

public class ScreenController {
    private static HashMap<String, FXMLLoader> screenMap = new HashMap<>();

    public static void addScreen(String name, FXMLLoader pane) {
        screenMap.put(name, pane);
    }

    public static void removeScreen(String name) {
        screenMap.remove(name);
    }

    public static void activate(String name, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(SlangMasterApplication.class.getResource(name + ".fxml"));
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
