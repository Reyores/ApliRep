module com.projet.aplirep {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;

    exports com.magasin;
    exports com.projet.aplirep;
    exports com.controllers;

    opens com.controllers to javafx.fxml;
}