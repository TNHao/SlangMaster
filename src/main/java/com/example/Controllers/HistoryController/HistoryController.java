package com.example.Controllers.HistoryController;

import com.example.Controllers.ScreenController.ScreenController;
import com.example.History.History;
import com.example.History.HistoryList;
import com.example.Utils.Constant;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    public TableView<History> table;
    //    @FXML
//    public TableColumn<String, Integer> idColumn;
    @FXML
    public TableColumn<History, String> timeColumn;
    @FXML
    public TableColumn<History, String> actionColumn;
    @FXML
    public TableColumn<History, String> statusColumn;
    @FXML
    public TableColumn<History, String> detailColumn;

    private ObservableList<History> list;

    public void onReturnClick(ActionEvent actionEvent) throws IOException {
        ScreenController.activate(Constant.dashboard, actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HistoryList historyList = HistoryList.getInstance();

        list = FXCollections.observableArrayList();

        for (History history : historyList.getList()) {
            list.add(history);
        }

        timeColumn.setCellValueFactory(new PropertyValueFactory<History, String>("time"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<History, String>("actionType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<History, String>("status"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<History, String>("detail"));

        timeColumn.setResizable(false);
        actionColumn.setResizable(false);
        statusColumn.setResizable(false);
        detailColumn.setResizable(false);

        timeColumn.setSortable(false);
        actionColumn.setSortable(false);
        statusColumn.setSortable(false);
        detailColumn.setSortable(false);

        table.setItems(list);
    }
}
