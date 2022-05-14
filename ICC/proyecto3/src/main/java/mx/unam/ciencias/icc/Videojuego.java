package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar videojuegos. Un videojuego tiene nombre, cantidad en
 * inventario, precio y año de salida. La clase implementa {@link Registro}, por lo que
 * puede representarse con una línea de texto y definir sus propiedades con una
 * línea de texto; además de determinar si sus campos cazan valores arbitrarios.
 */
public class Videojuego implements Registro<Videojuego, CampoVideojuego> {

    /* Nombre del videojuego. */
    private StringProperty nombre;
    /* Cantidad en inventario. */
    private IntegerProperty cantidad;
    /* Precio. */
    private DoubleProperty precio;
    /* Año de lanzamiento.*/
    private IntegerProperty año;

    /**
     * Define el estado inicial de un videojuego.
     * @param nombre el nombre del videojuego.
     * @param cantidad la cantidad en inventario del videojuego.
     * @param precio el precio del videojuego.
     * @param año el año de lanzamiento del videojuego.
     */
    public Videojuego(String nombre,
                      int    cantidad,
                      double precio,
                      int    año) {
        this.nombre = new SimpleStringProperty(nombre);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.precio = new SimpleDoubleProperty(precio);
        this.año = new SimpleIntegerProperty(año);
    }

    /**
     * Regresa el nombre del videojuego.
     * @return el nombre del videojuego.
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * Define el nombre del videojuego.
     * @param nombre el nuevo nombre del videojuego.
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * Regresa la propiedad del nombre.
     * @return la propiedad del nombre.
     */
    public StringProperty nombreProperty() {
        return this.nombre;
    }

    /**
     * Regresa la cantidad en inventario del videojuego.
     * @return la cantidad en inventario del videojuego.
     */
    public int getCantidad() {
        return cantidad.get();
    }

    /**
     * Define la cantidad en inventario del videojuego.
     * @param cantidad la nueva cantidad en inventario del videojuego.
     */
    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    /**
     * Regresa la propiedad de la cantidad en inventario.
     * @return la propiedad de la cantidad en inventario.
     */
    public IntegerProperty cantidadProperty() {
        return this.cantidad;
    }

    /**
     * Regresa el precio del videojuego.
     * @return el precio del videojuego.
     */
    public double getPrecio() {
        return precio.get();
    }

    /**
     * Define el precio del videojuego.
     * @param precio el precio del videojuego.
     */
    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    /**
     * Regresa la propiedad del precio.
     * @return la propiedad del precio.
     */
    public DoubleProperty precioProperty() {
        return this.precio;
    }

    /**
     * Regresa el año de lanzamiento del videojuego.
     * @return el año de lanzamiento del videojuego.
     */
    public int getAño() {
        return año.get();
    }

    /**
     * Define el año de lanzamiento del videojuego.
     * @param año el año de lanzamiento del videojuego.
     */
    public void setAño(int año) {
        this.año.set(año);
    }

    /**
     * Regresa la propiedad del año de lanzamiento.
     * @return la propiedad del año de lanzamiento.
     */
    public IntegerProperty añoProperty() {
        return this.año;
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
                                       nombre.get(), cantidad.get(), precio.get(), año.get());
	    return datitos;
    }

    /**
     * Nos dice si el objeto recibido es un videojuego igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el videojuego se comparará.
     * @return <code>true</code> si el objeto o es un videojuego con las mismas
     *         propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Videojuego))
            return false;
        Videojuego videojuego = (Videojuego)objeto;
        if ((this.nombre.get().equals(videojuego.nombre.get())) == false){
            return false;
        } else if ((this.cantidad.get() == videojuego.cantidad.get()) == false) {
            return false;
        } else if ((this.precio.get() == videojuego.precio.get()) == false){
            return false;
        } else if ((this.año.get() == videojuego.año.get()) == false){
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
                                     nombre.get(), cantidad.get(), precio.get(), año.get());
        return regresar;
    }

    /**
     * Deserializa una línea de texto en las propiedades del videojuego. La
     * serialización producida por el método {@link Videojuego#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de un videojuego.
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
        setNombre(checar[0]);
        try {
            setCantidad(Integer.parseInt(checar[1]));
            setPrecio(Double.parseDouble(checar[2]));
            setAño(Integer.parseInt(checar[3]));
        } catch (NumberFormatException e) {
            throw new ExcepcionLineaInvalida("la linea no es un serialización válida");
        }
    }

    /**
     * Actualiza los valores del videojuego con los del videojuego recibido.
     * @param videojuego el videojuego con el cual actualizar los valores.
     * @throws IllegalArgumentException si el videojuego es <code>null</code>.
     */
    public void actualiza(Videojuego videojuego) {
        if (videojuego == null) {
            throw new IllegalArgumentException("el videojuego es null");
        }
        setNombre(videojuego.getNombre());
        setCantidad(videojuego.getCantidad());
        setPrecio(videojuego.getPrecio());
        setAño(videojuego.getAño());
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
        if (nombre.get().contains((String)valor)) {
            return true;
        }
        return false;
    }
    private boolean cazaCantidad(Object valor){
        if (!(valor instanceof Integer)) {
            return false;
        }
        if ((Integer)valor <= cantidad.get()) {
            return true;
        }
        return false;
    }
    private boolean cazaPrecio(Object valor){
        if (!(valor instanceof Double)) {
            return false;
        }
        if ((Double)valor <= precio.get()) {
            return true;
        }
        return false;
    }
    private boolean cazaAño(Object valor){
        if (!(valor instanceof Integer)) {
            return false;
        }
        if (((Integer)valor) <= año.get()) {
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
     *              valor entero es menor o igual a la cantidad en inventario del
     *              videojuego.</li>
     *           <li><code>campo</code> es {@link CampoVideojuego#PRECIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al precio del
     *              videojuego.</li>
     *           <li><code>campo</code> es {@link CampoVideojuego#AÑO} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al el año de lanzamiento del
     *              videojuego.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoVideojuego campo, Object valor) {
        if (campo == null) {
            throw new IllegalArgumentException("el campo es null");
        }
        switch (campo) {
            case NOMBRE:
                return cazaNombre(valor);
            case CANTIDAD:
                return cazaCantidad(valor);
            case PRECIO:
                return cazaPrecio(valor);
            case AÑO:
                return cazaAño(valor);
            default:
                throw new IllegalArgumentException("el campo no es instancia de CampoVideojuego");
        }
    }
}
