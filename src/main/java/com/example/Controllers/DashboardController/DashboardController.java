package com.example.Controllers.DashboardController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class DashboardController {
    @FXML
    public void onSlangLookUpClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.slangLookup, actionEvent);
    }

    @FXML
    public void onSlangFindingClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.slangFinding, actionEvent);
    }

    @FXML
    public void onAddClick(ActionEvent actionEvent) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add new slang");
        dialog.setHeaderText("Thêm một slang mới.");
        ImageView imageView = new ImageView(this.getClass().getResource("/image/1665680.png").toString());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        dialog.setGraphic(imageView);

// Set the button types.
        ButtonType loginButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField slang = new TextField();
        slang.setPromptText("Slang");
        TextField definition = new TextField();
        definition.setPromptText("Định nghĩa");

        slang.setMinWidth(250);
        definition.setMinWidth(250);

        grid.add(new Label("Slang:"), 0, 0);
        grid.add(slang, 1, 0);
        grid.add(new Label("Định nghĩa:"), 0, 1);
        grid.add(definition, 1, 1);

// Enable/Disable login button depending on whether a slang was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        slang.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the slang field by default.
        Platform.runLater(() -> slang.requestFocus());

// Convert the result to a slang-definition-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(slang.getText(), definition.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        SlangList slangList = SlangList.getInstance();

        result.ifPresent(slangDefinition -> {
            SlangWord oldSlang = slangList.slangLookUp(slangDefinition.getKey());

            if (oldSlang != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Overwrite");
                alert.setHeaderText("Slang đã tồn tại.");
                alert.setContentText("Bạn có muốn ghi đè?");

                Optional<ButtonType> res = alert.showAndWait();
                if (res.get() == ButtonType.CANCEL) {
                    return;
                } else {
                    // TODO: remove existing slang
                }
            } else {
                boolean status = slangList.addSlang(new SlangWord(slangDefinition.getKey(), slangDefinition.getValue()));
                if (status) {
                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                    success.setTitle("Add slang");
                    success.setHeaderText("Thêm thành công.");
                    success.show();
                } else {
                    Alert fail = new Alert(Alert.AlertType.ERROR);
                    fail.setTitle("Add slang");
                    fail.setHeaderText("Đã có lỗi xảy ra.");
                    fail.show();
                }
            }
        });
    }

    @FXML
    public void onResetClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset slang list");
        alert.setHeaderText("Khôi phục danh sách slang ban đầu");
        alert.setContentText("Bạn muốn khôi phục?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("asas");
        } else {
            System.out.println("aaaa");
        }
    }

    @FXML
    public void onDefinitionClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.definitionGuessing, actionEvent);
    }

    @FXML
    public void onHistoryClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.history, actionEvent);
    }

    @FXML
    public void onDailySlangClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dailySlang, actionEvent);
    }

    @FXML
    public void onSlangGuessingClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.slangGuessing, actionEvent);
    }
}