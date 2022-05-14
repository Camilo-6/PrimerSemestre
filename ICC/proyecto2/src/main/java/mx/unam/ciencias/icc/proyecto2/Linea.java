package mx.unam.ciencias.icc.proyecto2;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.Registro;

/**
 * Clase para representar lineas. Una linea tiene contenido.
 * La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otra linea.
 */
public class Linea implements Registro<Linea, CampoLinea> {

    /* Contenido de la linea.*/
    private String contenido;

    /**
     * Define el estado inicial de una linea.
     * @param contenido el contenido de la linea.
     */
    public Linea(String contenido) {
        this.contenido = contenido;
    }
    
    /**
     * Regresa el contenido de la linea.
     * @return el contenido de la linea.
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Define el contenido de la linea.
     * @param contenido el nuevo contenido de la linea.
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Regresa una representación en cadena de la linea.
     * @return una representación en cadena de la linea.
     */
    @Override public String toString() {
        return this.contenido;
    }

    /**
     * Nos dice si el objeto recibido es una linea igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que la linea se comparará.
     * @return <code>true</code> si el objeto recibido es una linea con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Linea))
            return false;
        Linea comparar = (Linea)objeto;
        if ((this.contenido.equals(comparar.contenido)) == false)
            return false;
        return true;
    }

    /**
     * Regresa la linea serializada en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Linea#deserializa}.
     * @return la serialización de la linea en una línea de texto.
     */
    @Override public String serializa() {
        return this.contenido+"\n";
    }

    /**
     * Deserializa una línea de texto en las propiedades de la linea. La
     * serialización producida por el método {@link Linea#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de una linea.
     */
    @Override public void deserializa(String linea) {
        if (linea == null) {
            throw new ExcepcionLineaInvalida("la linea es nula");
        }
        if (linea.isEmpty()) {
            throw new ExcepcionLineaInvalida("la linea esta vacía");
        }
        String[] checar = linea.split("\t");
        if (checar.length != 1){
            throw new ExcepcionLineaInvalida("la linea no es un serialización válida");
        }
        this.contenido = checar[0];
    }

    /**
     * Actualiza los valores de la linea con los de la linea recibida.
     * @param linea la linea con el cual actualizar los valores.
     * @throws IllegalArgumentException si la linea es <code>null</code>.
     */
    @Override public void actualiza(Linea linea) {
        if (linea == null) {
            throw new IllegalArgumentException("la linea es null");
        }
        this.contenido = linea.contenido;
    }

    /*
    Metodo auxiliar para caza
    */
    private boolean cazaContenido(Object valor){
        if (!(valor instanceof String)) {
            return false;
        }
        if ((String)valor == "") {
            return false;
        }
        if (contenido.contains((String)valor)) {
            return true;
        }
        return false;
    }

    /**
     * Nos dice si la linea caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoLinea#CONTENIDO} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del contenido de la linea.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoLinea campo, Object valor) {
        if (campo == null) {
            throw new IllegalArgumentException("el campo es null");
        }
        switch (campo) {
            case CONTENIDO:
                return cazaContenido(valor);
            default:
                throw new IllegalArgumentException("el campo no es instancia de CampoEstudiante");
        }
    }

}
