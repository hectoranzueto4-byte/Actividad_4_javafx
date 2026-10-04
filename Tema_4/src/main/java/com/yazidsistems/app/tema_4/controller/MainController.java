package com.yazidsistems.app.tema_4.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private StackPane areaContenido; // El contenedor donde se intercambiarán las vistas

    @FXML
    private void cargarPantalla1(ActionEvent event) {
        cambiarVista("/com/yazidsistems/app/tema_4/espera-view.fxml");
    }

    @FXML
    private void cargarPantalla2(ActionEvent event) {
        cambiarVista("/com/yazidsistems/app/tema_4/inventarios-view.fxml");
    }

    @FXML
    private void cargarPantalla3(ActionEvent event) {
        cambiarVista("/com/yazidsistems/app/tema_4/parametricas-view.fxml");
    }

    @FXML
    private void cargarPantalla4(ActionEvent event) {
        cambiarVista("/com/yazidsistems/app/tema_4/no-parametricas-view.fxml");
    }

    // Método auxiliar reutilizable para cargar cualquier FXML en el centro de la pantalla
    private void cambiarVista(String fxmlArchivo) {
        try {
            // Carga el archivo FXML secundario
            Parent vistaSecundaria = FXMLLoader.load(getClass().getResource(fxmlArchivo));

            // Limpia el área central y añade la nueva vista
            areaContenido.getChildren().clear();
            areaContenido.getChildren().add(vistaSecundaria);

        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + fxmlArchivo);
            e.printStackTrace();
        }
    }
}
