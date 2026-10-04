package com.yazidsistems.app.tema_4.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ParametricasController {

    @FXML private TextField txtMediaMuestra;
    @FXML private TextField txtDesviacion;
    @FXML private TextField txtN;
    @FXML private TextField txtMediaHipo;
    @FXML private TextField txtAlfa;

    @FXML private Label lblEstadisticoT;
    @FXML private Label lblGradosLibertad;
    @FXML private Label lblValorP;
    @FXML private Label lblConclusion;
    @FXML private Label lblError;

    @FXML
    private void calcularPruebaT(ActionEvent event) {
        lblError.setText("");

        try {
            double xBar = Double.parseDouble(txtMediaMuestra.getText());
            double s = Double.parseDouble(txtDesviacion.getText());
            int n = Integer.parseInt(txtN.getText());
            double mu0 = Double.parseDouble(txtMediaHipo.getText());
            double alfa = Double.parseDouble(txtAlfa.getText());

            // Validaciones estadísticas de frontera
            if (n <= 1) {
                lblError.setText("El tamaño de muestra (n) debe ser mayor a 1.");
                return;
            }
            if (s <= 0) {
                lblError.setText("La desviación estándar debe ser mayor a 0.");
                return;
            }
            if (alfa <= 0 || alfa >= 1) {
                lblError.setText("El nivel de significancia (α) debe estar entre 0 y 1 (Ej. 0.05).");
                return;
            }

            // Calcular Grados de Libertad y Error Estándar
            int gl = n - 1;
            double errorEstandar = s / Math.sqrt(n);

            // Calcular Estadístico t
            double tCalculado = (xBar - mu0) / errorEstandar;

            // Aproximación del valor p a dos colas (utilizando aproximación normal para muestras grandes/medianas)
            double pValor = calcularValorPAproximado(Math.abs(tCalculado));

            // Desplegar resultados numéricos
            lblEstadisticoT.setText(String.format("Estadístico t calculado: %.4f", tCalculado));
            lblGradosLibertad.setText("Grados de libertad (gl): " + gl);
            lblValorP.setText(String.format("Valor p (Dos colas): %.4f", pValor));

            // Evaluar regla de decisión estadística
            if (pValor < alfa) {
                lblConclusion.setText("Conclusión: Rechazar H₀. Hay evidencia significativa para afirmar que la media difiere de " + mu0);
                lblConclusion.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
            } else {
                lblConclusion.setText("Conclusión: No rechazar H₀. No hay suficiente evidencia para afirmar que la media difiere de " + mu0);
                lblConclusion.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
            }

        } catch (NumberFormatException e) {
            lblError.setText("Verifique los datos introducidos. 'n' debe ser entero, los demás decimales.");
        }
    }

    /**
     * Función matemática auxiliar para estimar la probabilidad de la cola usando
     * la aproximación de la distribución normal estándar acumulada (Z).
     */
    private double calcularValorPAproximado(double t) {
        // Albin's approximation para la cola de la distribución acumulada normal
        double t2 = t * t;
        double ans = 1.0 / (1.0 + Math.exp(Math.sqrt(Math.PI) * t * (1.0 + 0.044715 * t2)));
        return ans * 2.0; // Multiplicado por 2 debido a que es una prueba bidireccional (dos colas)
    }
}
