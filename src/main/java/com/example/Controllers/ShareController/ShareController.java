package com.example.Controllers.ShareController;

import com.example.History.History;
import com.example.History.HistoryList;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import com.example.Utils.Utils;
import com.example.slangmaster.SlangMasterApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class ShareController {
    protected static SlangList slangList = SlangList.getInstance();
    protected static HistoryList historyList = HistoryList.getInstance();

    public static void onEditClick(SlangWord selectedSlang) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit slang");
        dialog.setHeaderText("Chỉnh sửa slang.");
        ImageView imageView = new ImageView(SlangMasterApplication.class.getResource("/image/1160758.png").toString());
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
        slang.setText(selectedSlang.getSlang());
        TextField definition = new TextField();
        definition.setText(selectedSlang.getDefinition());

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

        result.ifPresent(slangDefinition -> {
            slangList.removeSlang(selectedSlang.getSlang());
            SlangWord oldSlang = slangList.slangLookUp(slangDefinition.getKey());

            if (oldSlang != null && oldSlang != selectedSlang) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Overwrite");
                alert.setHeaderText("Slang đã tồn tại.");
                alert.setContentText("Bạn có muốn ghi đè?");

                Optional<ButtonType> res = alert.showAndWait();
                if (res.get() == ButtonType.CANCEL) {
                    slangList.addSlang(selectedSlang);
                    return;
                } else {
                    boolean status = slangList.removeSlang(oldSlang.getSlang());
                    if (!status) {
                        Utils.showResultAlert("Edit slang", "", "Ôi! Đã có lỗi xảy ra.", false);
                    }
                }
            }

            if (oldSlang != null) slangList.removeSlang(oldSlang.getSlang());
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

    public static void onDeleteClick(SlangWord selectedSlang) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete slang");
        alert.setHeaderText("Xoá slang '" + selectedSlang.getSlang() + "'");
        alert.setContentText("Bạn chắc chắn muốn xóa?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            boolean status = slangList.removeSlang(selectedSlang.getSlang());
            if (status)
                Utils.writeListToFile(slangList.getList());
            historyList.addHistory(
                    new History(Utils.getLocaleDateTime(),
                            Constant.ActionTypes.delete,
                            Utils.getStatusString(status),
                            selectedSlang.toCompactString())
            );

            selectedSlang = null;
            Utils.showResultAlert(
                    "Delete Slang",
                    "Yeah! Xóa Slang thành công.",
                    "Ôi! Đã có lỗi xảy ra.",
                    status);
        }
    }
}
