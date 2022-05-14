package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Videojuego}.
 */
public enum CampoVideojuego {

    /** El nombre del videojuego. */
    NOMBRE,
    /** La cantidad de inventario del videojuego. */
    CANTIDAD,
    /** El precio del videojuego. */
    PRECIO,
    /** El año de lanzamiento del videojuego. */
    ANIO;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        switch (this) {
            case NOMBRE:
                return "Nombre";
            case CANTIDAD:
                return "# Cantidad";
            case PRECIO:
                return "Precio";
            case ANIO:
                return "Año";
            default:
                return null;
        }
    }
}