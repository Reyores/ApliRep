module com.projet.aplirep {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.projet.aplirep to javafx.fxml;
    exports com.projet.aplirep;
    exports com.examples;
    opens com.examples to javafx.fxml;
}