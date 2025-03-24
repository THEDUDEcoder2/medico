module com.example.medico {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.medico to javafx.fxml;
    exports com.example.medico;
}