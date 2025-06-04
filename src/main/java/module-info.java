module com.example.medico {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens com.example.medico to javafx.fxml;
    opens com.example.medico.controladores to javafx.fxml;
    opens com.example.medico.modelos to javafx.fxml;
    opens com.example.medico.views to javafx.fxml;
    opens com.example.medico.assets to javafx.graphics;

    exports com.example.medico;
    exports com.example.medico.controladores;
    exports com.example.medico.modelos;
}