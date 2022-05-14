package mx.unam.ciencias.icc;

/**
 * Clase para representar videojuegos. Un videojuego tiene nombre, cantidad de
 * inventario, precio y año de salida. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otro videojuego.
 */
public class Videojuego implements Registro {

    /* Nombre del videojuego. */
    private String nombre;
    /* Cantidad de inventario. */
    private int cantidad;
    /* Precio. */
    private double precio;
    /* Año de lanzamiento.*/
    private int anio;

    /**
     * Define el estado inicial de un videojuego.
     * @param nombre el nombre del videojuego.
     * @param cantidad la cantidad de inventario del videojuego.
     * @param precio el precio del videojuego.
     * @param anio el año de lanzamiento del videojuego.
     */
    public Videojuego(String nombre,
                      int    cantidad,
                      double precio,
                      int    anio) {
        this.nombre     = nombre;
        this.cantidad   = cantidad;
        this.precio     = precio;
        this.anio       = anio;
    }

    /**
     * Regresa el nombre del videojuego.
     * @return el nombre del videojuego.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del videojuego.
     * @param nombre el nuevo nombre del videojuego.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa la cantidad de inventario del videojuego.
     * @return la cantidad de inventario del videojuego.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define la cantidad de inventario del videojuego.
     * @param cantidad la nueva cantidad de inventario del videojuego.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Regresa el precio del videojuego.
     * @return el precio del videojuego.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Define el precio del videojuego.
     * @param precio el nuevo precio del videojuego.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Regresa el año de lanzamiento del videojuego.
     * @return el año de lanzamiento del videojuego.
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Define el año de lanzamiento del videojuego.
     * @param anio el nuevo año de lanzamiento del videojuego.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Regresa una representación en cadena del videojuego.
     * @return una representación en cadena del videojuego.
     */
    @Override public String toString() {
        String datitos = String.format("Nombre   : %s\n" +
                                       "Cantidad : %d\n" +
                                       "Precio   : $%.2f\n" +
                                       "Año      : %d",
                                       nombre, cantidad, precio, anio);
	    return datitos;
    }

    /**
     * Nos dice si el objeto recibido es un videojuego igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el videojuego se comparará.
     * @return <code>true</code> si el objeto recibido es un videojuego con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Videojuego))
            return false;
        Videojuego juego = (Videojuego)objeto;
        if ((this.nombre.equals(juego.nombre)) == false){
            return false;
        } else if ((this.cantidad == juego.cantidad) == false) {
            return false;
        } else if ((this.precio == juego.precio) == false){
            return false;
        } else if ((this.anio == juego.anio) == false){
            return false;
        }
        return true;
    }

    /**
     * Regresa el videojuego serializado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Videojuego#deserializa}.
     * @return la serialización del videojuego en una línea de texto.
     */
    @Override public String serializa() {
        String regresar = String.format("%s\t%d\t%.2f\t%d\n",
                                     nombre, cantidad, precio, anio);
        return regresar;
    }

    /**
     * Deserializa una línea de texto en las propiedades del videojuego. La
     * serialización producida por el método {@link Videojuego#serializa} debe
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
            this.cantidad = Integer.parseInt(checar[1]);
            this.precio = Double.parseDouble(checar[2]);
            this.anio = Integer.parseInt(checar[3]);
        } catch (NumberFormatException e) {
            throw new ExcepcionLineaInvalida("la linea no es un serialización válida");
        }
    }

    /**
     * Actualiza los valores del videojuego con los del registro recibido.
     * @param registro el registro con el cual actualizar los valores.
     * @throws IllegalArgumentException si el registro no es instancia de {@link
     *         Videojuego}.
     */
    @Override public void actualiza(Registro registro) {
        if (!(registro instanceof Videojuego)) {
            throw new IllegalArgumentException("el registro no es instacia de Videojuego");
        }
        Videojuego nuevo = (Videojuego)registro;
        this.nombre = nuevo.nombre;
        this.cantidad = nuevo.cantidad;
        this.precio = nuevo.precio;
        this.anio = nuevo.anio;
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
    private boolean cazaCantidad(Object valor){
        if (!(valor instanceof Integer)) {
            return false;
        }
        if ((Integer)valor <= cantidad) {
            return true;
        }
        return false;
    }
    private boolean cazaPrecio(Object valor){
        if (!(valor instanceof Double)) {
            return false;
        }
        if ((Double)valor <= precio) {
            return true;
        }
        return false;
    }
    private boolean cazaAnio(Object valor){
        if (!(valor instanceof Integer)) {
            return false;
        }
        if (((Integer)valor) <= anio) {
            return true;
        }
        return false;
    }

    /**
     * Nos dice si el videojuego caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoVideojuego#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del videojuego.</li>
     *           <li><code>campo</code> es {@link CampoVideojuego#CANTIDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cantidad de inventario del
     *              videojuego.</li>
     *           <li><code>campo</code> es {@link CampoVideojuego#PRECIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al precio del
     *              videojuego.</li>
     *           <li><code>campo</code> es {@link CampoVideojuego#ANIO} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al el año de lanzamiento del
     *              videojuego.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo no es instancia de {@link
     *         CampoVideojuego}.
     */
    @Override public boolean caza(Enum campo, Object valor) {
        if (!(campo instanceof CampoVideojuego)) {
            throw new IllegalArgumentException("el campo no es instancia de CampoVideojuego");
        }
        CampoVideojuego campito = (CampoVideojuego)campo;
        switch (campito) {
            case NOMBRE:
                return cazaNombre(valor);
            case CANTIDAD:
                return cazaCantidad(valor);
            case PRECIO:
                return cazaPrecio(valor);
            case ANIO:
                return cazaAnio(valor);
            default:
                throw new IllegalArgumentException("el campo no es instancia de CampoVideojuego");
        }
    }
}


