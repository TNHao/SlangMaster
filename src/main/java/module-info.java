module com.example.slangmaster {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.slangmaster to javafx.fxml;
    exports com.example.slangmaster;
    exports com.example.History;
    exports com.example.Controllers.DashboardController;
    exports com.example.Controllers.ScreenController;
    exports com.example.Controllers.DailySlangController;
    exports com.example.Controllers.SlangLookUpController;
    exports com.example.Controllers.HistoryController;
    exports com.example.Controllers.DefinitionGuessing;
}