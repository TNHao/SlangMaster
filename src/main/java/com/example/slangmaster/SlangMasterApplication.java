package com.example.slangmaster;

import com.example.ScreenController.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SlangMasterApplication extends Application {
//    ScreenController screenController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SlangMasterApplication.class.getResource("dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        ScreenController.addScreen("dailySlang", FXMLLoader.load(getClass().getResource("dailySlang.fxml")));
        ScreenController.addScreen("dashboard", FXMLLoader.load(getClass().getResource("dashboard.fxml")));
        ScreenController.addScreen("definitionGuessing", FXMLLoader.load(getClass().getResource("definitionGuessing.fxml")));
        ScreenController.addScreen("history", FXMLLoader.load(getClass().getResource("history.fxml")));
        ScreenController.addScreen("slangFinding", FXMLLoader.load(getClass().getResource("slangFinding.fxml")));
        ScreenController.addScreen("slangGuessing", FXMLLoader.load(getClass().getResource("slangGuessing.fxml")));
        ScreenController.addScreen("slangLookup", FXMLLoader.load(SlangMasterApplication.class.getResource("slangLookup.fxml")));

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}