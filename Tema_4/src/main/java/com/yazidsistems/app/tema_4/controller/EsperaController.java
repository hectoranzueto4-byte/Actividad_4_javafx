package com.yazidsistems.app.tema_4.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EsperaController {

    @FXML
    private TextField txtLambda; // λ (Llegadas)
    @FXML
    private TextField txtMu;     // μ (Servicio)

    @FXML
    private Label lblUtilizacion;
    @FXML
    private Label lblClientesCola;
    @FXML
    private Label lblClientesSistema;
    @FXML
    private Label lblTiempoCola;
    @FXML
    private Label lblTiempoSistema;
    @FXML
    private Label lblError;

    @FXML
    private void calcularMetricas(ActionEvent event) {
        lblError.setText(""); // Limpiar errores previos

        try {
            double lambda = Double.parseDouble(txtLambda.getText());
            double mu = Double.parseDouble(txtMu.getText());

            if (lambda <= 0 || mu <= 0) {
                lblError.setText("Las tasas deben ser números mayores a cero.");
                return;
            }

            if (lambda >= mu) {
                lblError.setText("Error: La tasa de llegada (λ) debe ser menor que la tasa de servicio (μ) o la cola será infinita.");
                return;
            }

            // Fórmulas de Teoría de Colas (M/M/1)
            double rho = lambda / mu;                       // Utilización
            double l = lambda / (mu - lambda);              // Clientes en el sistema
            double lq = (lambda * lambda) / (mu * (mu - lambda)); // Clientes en cola
            double w = 1 / (mu - lambda);                   // Tiempo en el sistema (horas)
            double wq = lambda / (mu * (mu - lambda));      // Tiempo en cola (horas)

            // Convertir tiempos a minutos para mejor lectura
            double wMinutos = w * 60;
            double wqMinutos = wq * 60;

            // Mostrar resultados formateados a 2 decimales
            lblUtilizacion.setText(String.format("Utilización del Sistema (ρ): %.2f%%", rho * 100));
            lblClientesCola.setText(String.format("Clientes promedio en cola (Lq): %.2f", lq));
            lblClientesSistema.setText(String.format("Clientes promedio en sistema (L): %.2f", l));
            lblTiempoCola.setText(String.format("Tiempo promedio en cola (Wq): %.2f min", wqMinutos));
            lblTiempoSistema.setText(String.format("Tiempo promedio en sistema (W): %.2f min", wMinutos));

        } catch (NumberFormatException e) {
            lblError.setText("Por favor, ingresa números válidos en ambos campos.");
        }
    }
}
