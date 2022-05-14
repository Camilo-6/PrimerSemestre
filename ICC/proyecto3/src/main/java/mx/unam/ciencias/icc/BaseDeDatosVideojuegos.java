package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de videojuegos.
 */
public class BaseDeDatosVideojuegos
    extends BaseDeDatos<Videojuego, CampoVideojuego> {

    /**
     * Crea un videojuego en blanco.
     * @return un videojuego en blanco.
     */
    @Override public Videojuego creaRegistro() {
        return new Videojuego(null, 0, 0.0, 0);
    }
}
