module com.example.med_consultant {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.med_consultant to javafx.fxml;
    //exports com.med_consultant;
    exports com.med_consultant.frontend;
    opens com.med_consultant.frontend to javafx.fxml;
    exports com.med_consultant.backend;
    opens com.med_consultant.backend to javafx.fxml;
    exports com.med_consultant;
}