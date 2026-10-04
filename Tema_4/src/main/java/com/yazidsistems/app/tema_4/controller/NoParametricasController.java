package com.yazidsistems.app.tema_4.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NoParametricasController {

    @FXML private TextField txtCatA;
    @FXML private TextField txtCatB;
    @FXML private TextField txtCatC;
    @FXML private TextField txtAlfa;

    @FXML private Label lblTotalN;
    @FXML private Label lblChiCalculado;
    @FXML private Label lblValorP;
    @FXML private Label lblConclusion;
    @FXML private Label lblError;

    @FXML
    private void calcularChiCuadrada(ActionEvent event) {
        lblError.setText("");

        try {
            int obsA = Integer.parseInt(txtCatA.getText());
            int obsB = Integer.parseInt(txtCatB.getText());
            int obsC = Integer.parseInt(txtCatC.getText());
            double alfa = Double.parseDouble(txtAlfa.getText());

            if (obsA < 0 || obsB < 0 || obsC < 0) {
                lblError.setText("Las frecuencias observadas no pueden ser negativas.");
                return;
            }
            if (alfa <= 0 || alfa >= 1) {
                lblError.setText("El nivel de significancia (α) debe estar entre 0 y 1 (Ej. 0.05).");
                return;
            }

            // 1. Calcular tamaño de muestra (N) y valor esperado uniforme (E)
            double n = obsA + obsB + obsC;
            if (n == 0) {
                lblError.setText("La suma de las frecuencias debe ser mayor a cero.");
                return;
            }
            double esperado = n / 3.0; // Distribución teórica uniforme para 3 categorías

            // 2. Calcular Estadístico Chi-Cuadrada: Σ (O - E)² / E
            double chiA = Math.pow(obsA - esperado, 2) / esperado;
            double chiB = Math.pow(obsB - esperado, 2) / esperado;
            double chiC = Math.pow(obsC - esperado, 2) / esperado;
            double chiTotal = chiA + chiB + chiC;

            // 3. Estimar valor p para Distribución Chi-Cuadrada con gl = 2
            // Usamos la función de supervivencia exacta para gl=2 que es: p = exp(-χ² / 2)
            double pValor = Math.exp(-chiTotal / 2.0);

            // Desplegar información numérica
            lblTotalN.setText(String.format("Total de la muestra (N): %.0f", n));
            lblChiCalculado.setText(String.format("Estadístico χ² Calculado: %.4f", chiTotal));
            lblValorP.setText(String.format("Valor p aproximado: %.4f", pValor));

            // 4. Evaluar Regla de Decisión Estadística
            if (pValor < alfa) {
                lblConclusion.setText("Conclusión: Rechazar H₀. Las frecuencias observadas difieren significativamente de una distribución uniforme.");
                lblConclusion.setStyle("-fx-text-fill: #e67e22; -fx-font-weight: bold;");
            } else {
                lblConclusion.setText("Conclusión: No rechazar H₀. No hay evidencia de diferencias significativas; los datos se ajustan a la distribución uniforme.");
                lblConclusion.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            }

        } catch (NumberFormatException e) {
            lblError.setText("Por favor, introduce números enteros en las categorías y un decimal en α.");
        }
    }
}

