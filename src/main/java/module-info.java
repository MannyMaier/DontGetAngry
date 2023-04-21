module at.spengergasse.dontgetangry {
    requires javafx.controls;
    requires javafx.fxml;

// requirements for hibernate
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;

    opens at.spengergasse.dontgetangry to javafx.fxml;

    opens at.spengergasse.aufgabe07.entities to org.hibernate.orm.core;

    exports at.spengergasse.dontgetangry;
}