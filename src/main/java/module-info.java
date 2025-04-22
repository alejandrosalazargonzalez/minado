module es.alejandrosalazargonzalez.minado {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive org.controlsfx.controls;
    requires javafx.graphics;

    opens es.alejandrosalazargonzalez.minado to javafx.fxml;
    exports es.alejandrosalazargonzalez.minado;
    exports es.alejandrosalazargonzalez.minado.config;
    exports es.alejandrosalazargonzalez.minado.controller;
    exports es.alejandrosalazargonzalez.minado.controller.abstractas;
    exports es.alejandrosalazargonzalez.minado.model;
    exports es.alejandrosalazargonzalez.minado.model.conexion;
    opens es.alejandrosalazargonzalez.minado.controller to javafx.fxml;
}