package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoVideojuego;

/**
 * Clase para el controlador del contenido del diálogo para buscar videojuegos.
 */
public class ControladorFormaBusquedaVideojuegos extends ControladorForma {

    /* El combo del campo. */
    @FXML private ComboBox<CampoVideojuego> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        String s = entradaValor.getText();
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case NOMBRE:
            m = "Buscar por nombre necesita al menos un carácter";
            break;
        case CANTIDAD:
            m = "Buscar por cantidad en inventario necesita un número entre " +
                "0 y 9999";
            break;
        case PRECIO:
            m = "Buscar por precio necesita un número entre 0.0 y 100,000.0";
            break;
        case AÑO:
            m = "Buscar por año de lanzamiento necesita un número entre 1900 y 2999";
            break;
        }
        return new Tooltip(m);
    }

    /* Verifica el valor. */
    private boolean verificaValor(String s) {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:   return verificaNombre(s);
        case CANTIDAD:   return verificaCantidad(s);
        case PRECIO: return verificaPrecio(s);
        case AÑO:     return verificaAño(s);
        default:       return false;
        }
    }

    /* Verifica que el nombre a buscar sea válido. */
    private boolean verificaNombre(String n) {
        return n != null && !n.isEmpty();
    }

    /* Verifica que la cantidad en inventario a buscar sea válida. */
    private boolean verificaCantidad(String c) {
        if (c == null || c.isEmpty())
            return false;
        int cantidad = -1;
        try {
            cantidad = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return cantidad >= 0 && cantidad <= 9999;
    }

    /* Verifica que el precio a buscar sea válido. */
    private boolean verificaPrecio(String p) {
        if (p == null || p.isEmpty())
            return false;
        double precio = -1.0;
        try {
            precio = Double.parseDouble(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return precio >= 0.0 && precio <= 100000.0;
    }

    /* Verifica que el año de lanzamiento a buscar sea válido. */
    private boolean verificaAño(String e) {
        if (e == null || e.isEmpty())
            return false;
        int año = -1;
        try {
            año = Integer.parseInt(e);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return año >= 1900 && año <= 2999;
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoVideojuego getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case NOMBRE:   return entradaValor.getText();
        case CANTIDAD:   return Integer.parseInt(entradaValor.getText());
        case PRECIO: return Double.parseDouble(entradaValor.getText());
        case AÑO:     return Integer.parseInt(entradaValor.getText());
        default:       return entradaValor.getText(); // No debería ocurrir.
        }
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
