package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de videojuegos.
 */
public class BaseDeDatosVideojuego extends BaseDeDatos {

    /**
     * Crea un videojuego en blanco.
     * @return un videojuego en blanco.
     */
    @Override public Registro creaRegistro() {
        Videojuego regresar = new Videojuego(null, 0, 0.0, 0);
        return regresar;
    }
}
