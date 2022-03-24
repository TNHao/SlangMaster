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

        ScreenController.addScreen("dailySlang", new FXMLLoader(getClass().getResource("dailySlang.fxml")));
        ScreenController.addScreen("dashboard", new FXMLLoader(getClass().getResource("dashboard.fxml")));
        ScreenController.addScreen("definitionGuessing", new FXMLLoader(getClass().getResource("definitionGuessing.fxml")));
        ScreenController.addScreen("history", new FXMLLoader(getClass().getResource("history.fxml")));
        ScreenController.addScreen("slangFinding", new FXMLLoader(getClass().getResource("slangFinding.fxml")));
        ScreenController.addScreen("slangGuessing", new FXMLLoader(getClass().getResource("slangGuessing.fxml")));
        ScreenController.addScreen("slangLookup", new FXMLLoader(SlangMasterApplication.class.getResource("slangLookup.fxml")));

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}