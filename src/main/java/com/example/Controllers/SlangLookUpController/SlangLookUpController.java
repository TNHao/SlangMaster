package com.example.Controllers.SlangLookUpController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.Controllers.ShareController.ShareController;
import com.example.History.History;
import com.example.History.HistoryList;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import com.example.Utils.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SlangLookUpController implements Initializable {
    @FXML
    public TextField slangTextField;
    public Label labelDefinition;
    public Button editBtn;
    public Button deleteBtn;
    public Pane editPane;
    public Pane deletePane;
    SlangList slangList = SlangList.getInstance();
    HistoryList historyList = HistoryList.getInstance();

    private void setDisable(boolean status){
        editBtn.setDisable(status);
        deleteBtn.setDisable(status);
        editPane.setDisable(status);
        deletePane.setDisable(status);
    }

    public void onLookUpClick(ActionEvent actionEvent) {
        SlangList slangList = SlangList.getInstance();
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());

        String string;
        String actionDetail;

        if (slangWord != null) {
            string = slangWord.getSplittedDefinition();
            setDisable(false);
            actionDetail = slangWord.toCompactString();
        } else {
            string = "Không tìm thấy kết quả cho: " + slangTextField.getText();
            setDisable(true);
            actionDetail = string;
        }

        labelDefinition.setText(string);
        historyList.addHistory(
                new History(Utils.getLocaleDateTime(),
                        Constant.ActionTypes.lookup,
                        Utils.getStatusString(slangWord != null),
                        actionDetail)
        );
    }

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());
        ShareController.onDeleteClick(slangWord);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisable(true);
    }

    public void onEditClick(ActionEvent actionEvent) {
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());
        ShareController.onEditClick(slangWord);
    }
}
