module com.yazidsistems.app.tema_4 {
    requires javafx.controls;
    requires javafx.fxml;

    // Acceso para la aplicación (lo que arreglamos antes)
    exports com.yazidsistems.app.tema_4.application;
    opens com.yazidsistems.app.tema_4.application to javafx.graphics, javafx.fxml;

    // ¡NUEVO! Acceso para tus controladores y vistas FXML:
    exports com.yazidsistems.app.tema_4.controller;
    opens com.yazidsistems.app.tema_4.controller to javafx.fxml;
}

