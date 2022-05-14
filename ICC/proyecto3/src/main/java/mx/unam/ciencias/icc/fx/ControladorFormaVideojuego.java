package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mx.unam.ciencias.icc.Videojuego;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * videojuegos.
 */
public class ControladorFormaVideojuego extends ControladorForma {

    /* La entrada verificable para el nombre. */
    @FXML private EntradaVerificable entradaNombre;
    /* La entrada verificable para la cantidad en inventario. */
    @FXML private EntradaVerificable entradaCantidad;
    /* La entrada verificable para el precio. */
    @FXML private EntradaVerificable entradaPrecio;
    /* La entrada verificable para el año de lanzamiento. */
    @FXML private EntradaVerificable entradaAño;

    /* El valor del nombre. */
    private String nombre;
    /* El valor de la cantidad en inventario. */
    private int cantidad;
    /* El valor del precio. */
    private double precio;
    /* El valor del año de lanzamiento. */
    private int año;

    /* El videojuego creado o editado. */
    private Videojuego videojuego;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaNombre.setVerificador(n -> verificaNombre(n));
        entradaCantidad.setVerificador(c -> verificaCantidad(c));
        entradaPrecio.setVerificador(p -> verificaPrecio(p));
        entradaAño.setVerificador(e -> verificaAño(e));

        entradaNombre.textProperty().addListener(
            (o, v, n) -> verificaVideojuego());
        entradaCantidad.textProperty().addListener(
            (o, v, n) -> verificaVideojuego());
        entradaPrecio.textProperty().addListener(
            (o, v, n) -> verificaVideojuego());
        entradaAño.textProperty().addListener(
            (o, v, n) -> verificaVideojuego());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaVideojuego();
        aceptado = true;
        escenario.close();
    }

    /**
     * Define el videojuego del diálogo.
     * @param videojuego el nuevo videojuego del diálogo.
     */
    public void setVideojuego(Videojuego videojuego) {
        this.videojuego = videojuego;
        if (videojuego == null)
            return;
        this.videojuego = new Videojuego(null, 0, 0, 0);
        this.videojuego.actualiza(videojuego);
        entradaNombre.setText(videojuego.getNombre());
        entradaCantidad.setText(String.valueOf(videojuego.getCantidad()));
        String p = String.format("%.2f", videojuego.getPrecio());
        entradaPrecio.setText(p);
        entradaAño.setText(String.valueOf(videojuego.getAño()));
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaVideojuego() {
        boolean n = entradaNombre.esValida();
        boolean c = entradaCantidad.esValida();
        boolean p = entradaPrecio.esValida();
        boolean e = entradaAño.esValida();
        botonAceptar.setDisable(!n || !c || !p || !e);
    }

    /* Verifica que el nombre sea válido. */
    private boolean verificaNombre(String n) {
        if (n == null || n.isEmpty())
            return false;
        nombre = n;
        return true;
    }

    /* Verifica que la cantidad en inventario sea válida. */
    private boolean verificaCantidad(String c) {
        if (c == null || c.isEmpty())
            return false;
        try {
            cantidad = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return cantidad >= 0 && cantidad <= 9999;
    }

    /* Verifica que el precio sea válido. */
    private boolean verificaPrecio(String p) {
        if (p == null || p.isEmpty())
            return false;
        try {
            precio = Double.parseDouble(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return precio >= 0.0 && precio <= 100000.0;
    }

    /* Verifica que el año de lanzamiento sea válido. */
    private boolean verificaAño(String e) {
        if (e == null || e.isEmpty())
            return false;
        try {
            año = Integer.parseInt(e);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return año >= 1900 && año <= 2999;
    }

    /* Actualiza al videojuego, o lo crea si no existe. */
    private void actualizaVideojuego() {
        if (videojuego != null) {
            videojuego.setNombre(nombre);
            videojuego.setCantidad(cantidad);
            videojuego.setPrecio(precio);
            videojuego.setAño(año);
        } else {
            videojuego = new Videojuego(nombre, cantidad, precio, año);
        }
    }

    /**
     * Regresa el videojuego del diálogo.
     * @return el videojuego del diálogo.
     */
    public Videojuego getVideojuego() {
        return videojuego;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaNombre.requestFocus();
    }
}
