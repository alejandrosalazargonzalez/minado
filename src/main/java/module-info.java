module es.alejandrosalazargonzalez.minado{
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires transitive java.sql;

    opens es.alejandrosalazargonzalez.minado to javafx.fxml;
    exports es.alejandrosalazargonzalez.minado;
    exports es.alejandrosalazargonzalez.minado.config;
    exports es.alejandrosalazargonzalez.minado.controller;
    exports es.alejandrosalazargonzalez.minado.controller.abstractas;
    exports es.alejandrosalazargonzalez.minado.model;
    exports es.alejandrosalazargonzalez.minado.model.abstractas;
    opens es.alejandrosalazargonzalez.minado.controller to javafx.fxml;
}