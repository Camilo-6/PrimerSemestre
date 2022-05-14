package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad.
 */
public class Estudiante {

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
        this.nombre    = nombre;
        this.cuenta    = cuenta;
        this.promedio  = promedio;
        this.edad      = edad;
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
    public String toString() {
        String datitos = String.format("Nombre   : %s\n" +
                                       "Cuenta   : %09d\n" +
                                       "Promedio : %2.2f\n" +
                                       "Edad     : %d",
                                       nombre, cuenta, promedio, edad);
	    return datitos;
        // Aquí va su código.
    }

    /**
     * Nos dice si el estudiante recibido es igual al que manda llamar el
     * método.
     * @param estudiante el estudiante con el cual comparar.
     * @return <code>true</code> si el estudiante recibido tiene las mismas
     *         propiedades que el estudiante que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    public boolean equals(Estudiante estudiante) {
        if (estudiante == null){
            return false;
        } else if ((this.nombre.equals(estudiante.nombre)) == false){
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
}
