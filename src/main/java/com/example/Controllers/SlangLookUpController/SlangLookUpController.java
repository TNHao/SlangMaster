package com.example.Controllers.SlangLookUpController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.History.History;
import com.example.History.HistoryList;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import com.example.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

public class SlangLookUpController {
    @FXML
    public TextField slangTextField;
    public Label labelDefinition;
    public Button editBtn;
    public Button deleteBtn;
    SlangList slangList = SlangList.getInstance();
    HistoryList historyList = HistoryList.getInstance();

    public void onLookUpClick(ActionEvent actionEvent) {
        SlangList slangList = SlangList.getInstance();
        SlangWord slangWord = slangList.slangLookUp(slangTextField.getText());

        String string;
        String actionDetail;

        if (slangWord != null) {
            string = slangWord.getSplittedDefinition();
            editBtn.setDisable(false);
            deleteBtn.setDisable(false);
            actionDetail = slangWord.toCompactString();
        } else {
            string = "Không tìm thấy kết quả cho: " + slangTextField.getText();
            editBtn.setDisable(true);
            deleteBtn.setDisable(true);
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
}
