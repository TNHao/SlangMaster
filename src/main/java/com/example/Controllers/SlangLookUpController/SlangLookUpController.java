package com.example.Controllers.SlangLookUpController;

import com.example.Controllers.ScreenController.ScreenController;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete slang");
        alert.setHeaderText("Xoá slang '" + slangTextField.getText() + "'");
        alert.setContentText("Bạn chắc chắn muốn xóa?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            boolean status = slangList.removeSlang(slangTextField.getText());
            if (status)
                Utils.writeListToFile(slangList.getList());
            historyList.addHistory(
                    new History(Utils.getLocaleDateTime(),
                            Constant.ActionTypes.delete,
                            Utils.getStatusString(status),
                            slangTextField.getText() + '`' + labelDefinition.getText())
            );
            Utils.showResultAlert(
                    "Delete Slang",
                    "Yeah! Xóa Slang thành công.",
                    "Ôi! Đã có lỗi xảy ra.",
                    status);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisable(true);
    }

    public void onEditClick(ActionEvent actionEvent) {
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());
        slangList.removeSlang(slangWord.getSlang());

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit slang");
        dialog.setHeaderText("Chỉnh sửa slang.");
        ImageView imageView = new ImageView(this.getClass().getResource("/image/1160758.png").toString());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        dialog.setGraphic(imageView);

        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField slang = new TextField();
        slang.setText(slangWord.getSlang());
        TextField definition = new TextField();
        definition.setText(slangWord.getDefinition());

        slang.setMinWidth(250);
        definition.setMinWidth(250);

        grid.add(new Label("Slang:"), 0, 0);
        grid.add(slang, 1, 0);
        grid.add(new Label("Định nghĩa:"), 0, 1);
        grid.add(definition, 1, 1);

        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> slang.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
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
                    boolean status = slangList.removeSlang(oldSlang.getSlang());
                    if (!status) {
                        Utils.showResultAlert("Edit slang", "", "Ôi! Đã có lỗi xảy ra.", false);
                    }
                }
            }

            SlangWord newSlang = new SlangWord(slangDefinition.getKey(), slangDefinition.getValue());
            boolean status = slangList.addSlang(newSlang);

            if (status) Utils.writeListToFile(slangList.getList());
            historyList.addHistory(
                    new History(Utils.getLocaleDateTime(),
                            Constant.ActionTypes.edit,
                            Utils.getStatusString(status),
                            newSlang.toCompactString())
            );
            Utils.showResultAlert(
                    "Edit Slang",
                    "Yeah! Chỉnh sửa Slang thành công.",
                    "Ôi! Đã có lỗi xảy ra.",
                    status);
        });
    }
}
