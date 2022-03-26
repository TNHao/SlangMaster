package com.example.Controllers.SlangFindingController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.Controllers.ShareController.ShareController;
import com.example.History.History;
import com.example.History.HistoryList;
import com.example.SlangList.SlangList;
import com.example.SlangWord.SlangWord;
import com.example.Utils.Constant;
import com.example.Utils.Utils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SlangFindingController implements Initializable {
    @FXML
    public TextField definitionText;
    public ListView<SlangWord> listView;
    public Button editBtn;
    public Button deleteBtn;
    public Pane editPane;
    public Pane deletePane;

    private SlangList slangList = SlangList.getInstance();
    private ObservableList<SlangWord> list = FXCollections.observableArrayList();
    private HistoryList historyList = HistoryList.getInstance();
    private SlangWord selectedSlang = null;

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }

    private void setDisable(boolean status){
        editBtn.setDisable(status);
        deleteBtn.setDisable(status);
        editPane.setDisable(status);
        deletePane.setDisable(status);
    }

    public void onFindingClick(ActionEvent actionEvent) {
        list.clear();
        selectedSlang = null;
        setDisable(false);

        ArrayList<SlangWord> slangs = slangList.slangFinding(definitionText.getText());

        for (SlangWord slangWord : slangs) {
            list.add(slangWord);
        }

        listView.setItems(list);
        historyList.addHistory(new History(
                Utils.getLocaleDateTime(),
                Constant.ActionTypes.lookup,
                Utils.getStatusString(true),
                definitionText.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisable(true);
        listView.setCellFactory(param -> new ListCell<SlangWord>(){
            @Override
            protected void updateItem(SlangWord item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null);
                }else{
                    setMinWidth(param.getWidth());
                    setMaxWidth(param.getWidth());
                    setPrefWidth(param.getWidth());

                    setWrapText(true);
                    setText("slang= " + item.getSlang() + ", meaning= " + item.getDefinition());
                }
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Selected: " + listView.getSelectionModel().getSelectedItem());
                selectedSlang = listView.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Chưa có slang nào được chọn!");
        alert.setContentText("Vui lòng click chọn một slang để tiếp tục.");
        alert.show();
    }

    public void onEditClick(ActionEvent actionEvent) {
        if (selectedSlang == null) {
            showAlert();
            return;
        };

        ShareController.onEditClick(selectedSlang);
//        Dialog<Pair<String, String>> dialog = new Dialog<>();
//        dialog.setTitle("Edit slang");
//        dialog.setHeaderText("Chỉnh sửa slang.");
//        ImageView imageView = new ImageView(this.getClass().getResource("/image/1160758.png").toString());
//        imageView.setFitHeight(50);
//        imageView.setFitWidth(50);
//        dialog.setGraphic(imageView);
//
//        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
//        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
//
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20, 10, 10, 10));
//
//        TextField slang = new TextField();
//        slang.setText(selectedSlang.getSlang());
//        TextField definition = new TextField();
//        definition.setText(selectedSlang.getDefinition());
//
//        slang.setMinWidth(250);
//        definition.setMinWidth(250);
//
//        grid.add(new Label("Slang:"), 0, 0);
//        grid.add(slang, 1, 0);
//        grid.add(new Label("Định nghĩa:"), 0, 1);
//        grid.add(definition, 1, 1);
//
//        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
//
//        dialog.getDialogPane().setContent(grid);
//
//        Platform.runLater(() -> slang.requestFocus());
//
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == saveButtonType) {
//                return new Pair<>(slang.getText(), definition.getText());
//            }
//            return null;
//        });
//
//        Optional<Pair<String, String>> result = dialog.showAndWait();
//
//        result.ifPresent(slangDefinition -> {
//            SlangWord oldSlang = slangList.slangLookUp(slangDefinition.getKey());
//
//            if (oldSlang != null && oldSlang != selectedSlang) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Overwrite");
//                alert.setHeaderText("Slang đã tồn tại.");
//                alert.setContentText("Bạn có muốn ghi đè?");
//
//                Optional<ButtonType> res = alert.showAndWait();
//                if (res.get() == ButtonType.CANCEL) {
//                    return;
//                } else {
//                    boolean status = slangList.removeSlang(oldSlang.getSlang());
//                    if (!status) {
//                        Utils.showResultAlert("Edit slang", "", "Ôi! Đã có lỗi xảy ra.", false);
//                    }
//                }
//            }
//
//            slangList.removeSlang(oldSlang.getSlang());
//            SlangWord newSlang = new SlangWord(slangDefinition.getKey(), slangDefinition.getValue());
//            boolean status = slangList.addSlang(newSlang);
//
//            if (status) Utils.writeListToFile(slangList.getList());
//            historyList.addHistory(
//                    new History(Utils.getLocaleDateTime(),
//                            Constant.ActionTypes.edit,
//                            Utils.getStatusString(status),
//                            newSlang.toCompactString())
//            );
//            Utils.showResultAlert(
//                    "Edit Slang",
//                    "Yeah! Chỉnh sửa Slang thành công.",
//                    "Ôi! Đã có lỗi xảy ra.",
//                    status);
//        });
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        if (selectedSlang == null) {
            showAlert();
            return;
        };

        ShareController.onDeleteClick(selectedSlang);
    }
}
