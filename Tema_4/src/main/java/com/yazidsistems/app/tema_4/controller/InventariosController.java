package com.yazidsistems.app.tema_4.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InventariosController {

    @FXML
    private TextField txtDemanda;       // D
    @FXML
    private TextField txtCostoPedido;   // S
    @FXML
    private TextField txtCostoMantener; // H

    @FXML
    private Label lblLoteOptimo;
    @FXML
    private Label lblPedidosAnuales;
    @FXML
    private Label lblCostoOrdenar;
    @FXML
    private Label lblCostoMantener;
    @FXML
    private Label lblCostoTotal;
    @FXML
    private Label lblError;

    @FXML
    private void calcularInventario(ActionEvent event) {
        lblError.setText(""); // Limpiar mensajes de error previos

        try {
            double d = Double.parseDouble(txtDemanda.getText());
            double s = Double.parseDouble(txtCostoPedido.getText());
            double h = Double.parseDouble(txtCostoMantener.getText());

            // Validar que las variables sean positivas y que H no sea cero
            if (d <= 0 || s <= 0 || h <= 0) {
                lblError.setText("Todos los valores deben ser números mayores a cero.");
                return;
            }

            // Fórmula de Cantidad Económica de Pedido (EOQ / Lote Óptimo de Wilson)
            double qOptimo = Math.sqrt((2 * d * s) / h);

            // Cálculos secundarios derivados
            double numeroPedidos = d / qOptimo;
            double costoAnualOrdenar = (d / qOptimo) * s;
            double costoAnualMantener = (qOptimo / 2) * h;
            double costoTotalAlt = costoAnualOrdenar + costoAnualMantener;

            // Mostrar los resultados en la interfaz con formato a dos decimales
            lblLoteOptimo.setText(String.format("Cantidad Óptima de Pedido (Q*): %.2f unidades", qOptimo));
            lblPedidosAnuales.setText(String.format("Número de pedidos al año: %.2f", numeroPedidos));
            lblCostoOrdenar.setText(String.format("Costo anual de ordenar: $ %.2f", costoAnualOrdenar));
            lblCostoMantener.setText(String.format("Costo anual de mantener: $ %.2f", costoAnualMantener));
            lblCostoTotal.setText(String.format("Costo Total Anual de Inventario: $ %.2f", costoTotalAlt));

        } catch (NumberFormatException e) {
            lblError.setText("Por favor, ingresa únicamente números válidos en los campos.");
        }
    }
}

