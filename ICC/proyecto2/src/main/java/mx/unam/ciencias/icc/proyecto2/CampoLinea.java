package mx.unam.ciencias.icc.proyecto2;

/**
 * Enumeración para los campos de un {@link Linea}.
 */
public enum CampoLinea {
    
    /** El contenido de la linea. */
    CONTENIDO;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        switch (this) {
            case CONTENIDO:
                return "Contenido";
            default:
                return null;
        }
        // Aquí va su código.
    }

}
