module org.example.physicbox {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.physicbox to javafx.fxml;
    exports org.example.physicbox;
}