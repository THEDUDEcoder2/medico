module com.example.medico {
    requires javafx.controls;
    requires javafx.fxml;

    // Apertura de paquetes principales
    opens com.example.medico to javafx.fxml;
    opens com.example.medico.controladores to javafx.fxml;
    opens com.example.medico.modelos to javafx.fxml;

    // Nuevas aperturas CRUCIALES para recursos
    opens com.example.medico.views to javafx.fxml;
    opens com.example.medico.assets to javafx.graphics;

    // Exportaciones
    exports com.example.medico;
    exports com.example.medico.controladores;
    exports com.example.medico.modelos;
}