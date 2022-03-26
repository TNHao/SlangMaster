package com.example.Controllers.ScreenController;

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

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
