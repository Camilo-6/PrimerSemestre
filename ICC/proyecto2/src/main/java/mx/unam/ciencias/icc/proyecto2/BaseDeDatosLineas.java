package mx.unam.ciencias.icc.proyecto2;
import mx.unam.ciencias.icc.BaseDeDatos;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosLineas extends BaseDeDatos<Linea, CampoLinea> {

    /**
     * Crea una linea vacia?
     * @return una linea vacia?
     */
    @Override public Linea creaRegistro() {
        Linea regresar = new Linea(null);
        return regresar;
    }
    
}
