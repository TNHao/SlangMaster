module com.example.slangmaster {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.slangmaster to javafx.fxml;
    exports com.example.slangmaster;
}