module at.spengergasse.dontgetangry {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.spengergasse.dontgetangry to javafx.fxml;
    exports at.spengergasse.dontgetangry;
}