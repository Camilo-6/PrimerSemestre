package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Pormedio del estudiante. */
    private double promedio;
    /* Edad del estudiante.*/
    private int edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.promedio = promedio;
        this.edad = edad;
        // Aquí va su código.
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        return nombre;
        // Aquí va su código.
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
        // Aquí va su código.
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        return cuenta;
        // Aquí va su código.
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
        // Aquí va su código.
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        return promedio;
        // Aquí va su código.
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
        // Aquí va su código.
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        return edad;
        // Aquí va su código.
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        this.edad = edad;
        // Aquí va su código.
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        String datitos = String.format("Nombre   : %s\n" +
                                       "Cuenta   : %09d\n" +
                                       "Promedio : %2.2f\n" +
                                       "Edad     : %d",
                                       nombre, cuenta, promedio, edad);
	    return datitos;
        // Aquí va su código.
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;
        if ((this.nombre.equals(estudiante.nombre)) == false){
            return false;
        } else if ((this.cuenta == estudiante.cuenta) == false) {
            return false;
        } else if ((this.promedio == estudiante.promedio) == false){
            return false;
        } else if ((this.edad == estudiante.edad) == false){
            return false;
        }
        return true;
        // Aquí va su código.
    }

    /**
     * Regresa el estudiante serializado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deserializa}.
     * @return la serialización del estudiante en una línea de texto.
     */
    @Override public String serializa() {
        String regresar = String.format("%s\t%d\t%2.2f\t%d\n",
                                     nombre, cuenta, promedio, edad);
        return regresar;
        // Aquí va su código.
    }

    /**
     * Deserializa una línea de texto en las propiedades del estudiante. La
     * serialización producida por el método {@link Estudiante#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de un estudiante.
     */
    @Override public void deserializa(String linea) {
        if (linea == null) {
            throw new ExcepcionLineaInvalida("la linea es nula");
        }
        if (linea.isEmpty()) {
            throw new ExcepcionLineaInvalida("la linea esta vacía");
        }
        String porChecar = linea.strip();
        String[] checar = porChecar.split("\t");
        if (checar.length != 4){
            throw new ExcepcionLineaInvalida("la linea no es un serialización válida");
        }
        this.nombre = checar[0];
        try {
            this.cuenta = Integer.parseInt(checar[1]);
            this.promedio = Double.parseDouble(checar[2]);
            this.edad = Integer.parseInt(checar[3]);
        } catch (NumberFormatException e) {
            throw new ExcepcionLineaInvalida("la linea no es un serialización válida");
        }
        // Aquí va su código.
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    @Override public void actualiza(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("el estudiante es null");
        }
        this.nombre = estudiante.nombre;
        this.cuenta = estudiante.cuenta;
        this.promedio = estudiante.promedio;
        this.edad = estudiante.edad;
        // Aquí va su código.
    }

    /*
    Metodos auxiliares para caza
    */
    private boolean cazaNombre(Object valor){
        if (!(valor instanceof String)) {
            return false;
        }
        if ((String)valor == "") {
            return false;
        }
        if (nombre.contains((String)valor)) {
            return true;
        }
        return false;
    }
    private boolean cazaCuenta(Object valor){
        if (!(valor instanceof Integer)) {
            return false;
        }
        if ((Integer)valor <= cuenta) {
            return true;
        }
        return false;
    }
    private boolean cazaPromedio(Object valor){
        if (!(valor instanceof Double)) {
            return false;
        }
        if ((Double)valor <= promedio) {
            return true;
        }
        return false;
    }
    private boolean cazaEdad(Object valor){
        if (!(valor instanceof Integer)) {
            return false;
        }
        if (((Integer)valor) <= edad) {
            return true;
        }
        return false;
    }

    /**
     * Nos dice si el estudiante caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoEstudiante campo, Object valor) {
        if (campo == null) {
            throw new IllegalArgumentException("el campo es null");
        }
        switch (campo) {
            case NOMBRE:
                return cazaNombre(valor);
            case CUENTA:
                return cazaCuenta(valor);
            case PROMEDIO:
                return cazaPromedio(valor);
            case EDAD:
                return cazaEdad(valor);
            default:
                throw new IllegalArgumentException("el campo no es instancia de CampoEstudiante");
        }
        // Aquí va su código.
    }
}
