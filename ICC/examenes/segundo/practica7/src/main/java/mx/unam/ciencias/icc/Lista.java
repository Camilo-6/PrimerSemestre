package mx.unam.ciencias.icc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            this.elemento = elemento;
            // Aquí va su código.
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            this.anterior = null;
            this.siguiente = cabeza;
            // Aquí va su código.
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
            // Aquí va su código.
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T regresar = siguiente.elemento;
            this.anterior = this.siguiente;
            this.siguiente = siguiente.siguiente;
            return regresar;
            // Aquí va su código.
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
            // Aquí va su código.
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            T regresar = anterior.elemento;
            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;
            // Aquí va su código.
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            this.anterior = null;
            this.siguiente = cabeza;
            // Aquí va su código.
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            this.anterior = rabo;
            this.siguiente = null;
            // Aquí va su código.
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
        // Aquí va su código.
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return cabeza == null;
        // Aquí va su código.
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("el elemento es nulo");
        }
        Nodo nuevito = new Nodo(elemento);
        if (esVacia()) {
            cabeza = rabo = nuevito;
        } else {
            rabo.siguiente = nuevito;
            nuevito.anterior = rabo;
            rabo = nuevito;
        }
        longitud++;
        // Aquí va su código.
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("el elemento es nulo");
        }
        Nodo nuevito = new Nodo(elemento);
        if (esVacia()) {
            cabeza = rabo = nuevito;
        } else {
            cabeza.anterior = nuevito;
            nuevito.siguiente = cabeza;
            cabeza = nuevito;
        }
        longitud++;
        // Aquí va su código.
    }

    /*
    Metodo auxiliar para buscar el nodo donde esta un objeto (elemento)
    Obtener Nodo dando objeto
    */
    private Nodo buscaNodo(T buscado) {
        if (buscado == null) {
            return null;
        }
        Nodo explorador = cabeza;
        while (explorador != null) {
            if (explorador.elemento.equals(buscado)) {
                return explorador;
            }
            explorador = explorador.siguiente;
        }
        return null;
    }

    /*
    Metodo auxiliar para buscar el nodo donde esta un objeto (elemento)
    Obtener Nodo dando int
    */
    private Nodo buscaNodoConI(int i) {
        if (i < 0 || i >= longitud) {
            return null;
        }
        Nodo explorador = cabeza;
        int contando = 0;
        while (explorador != null) {
            if (contando == i) {
                return explorador;
            }
            explorador = explorador.siguiente;
            contando++;
        }
        return null;
    }

    /*
    Metodo auxiliar para eliminar un nodo
    Eliminar Nodo dando el Nodo
    */
    private void desapareceNodo(Nodo condenado) {
        if (condenado == null) {
            return;
        }
        if (cabeza == rabo && cabeza == null) {
            return;
        }
        longitud--;
        if (cabeza == rabo) {
            cabeza = rabo = null;
        } else if (condenado == cabeza){
            cabeza.siguiente.anterior = null;
            cabeza = cabeza.siguiente;
        } else if (condenado == rabo) {
            rabo.anterior.siguiente = null;
            rabo = rabo.anterior;
        } else {
            condenado.anterior.siguiente = condenado.siguiente;
            condenado.siguiente.anterior = condenado.anterior;
        }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("el elemento es nulo");
        }
        if (i <= 0) {
            agregaInicio(elemento);
        } else if (i >= longitud) {
            agregaFinal(elemento);
        } else {
            Nodo intercambio = buscaNodoConI(i);
            Nodo nuevo = new Nodo(elemento);
            intercambio.anterior.siguiente = nuevo;
            nuevo.anterior = intercambio.anterior;
            intercambio.anterior = nuevo;
            nuevo.siguiente = intercambio;
            longitud++;
        }
        // Aquí va su código.
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        if (elemento == null) {
            return;
        }
        Nodo marcado = buscaNodo(elemento);
        if (marcado == null) {
            return;
        }
        desapareceNodo(marcado);
        // Aquí va su código.
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if (esVacia()) {
            throw new NoSuchElementException("la lista es vacía");
        }
        T porEliminar = cabeza.elemento;
        desapareceNodo(cabeza);
        return porEliminar;
        // Aquí va su código.
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        if (esVacia()) {
            throw new NoSuchElementException("la lista es vacía");
        }
        T porEliminar = rabo.elemento;
        desapareceNodo(rabo);
        return porEliminar;
        // Aquí va su código.
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        return buscaNodo(elemento) != null;
        // Aquí va su código.
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> clonada = new Lista<T>();
        Nodo explorador = cabeza;
        while (explorador != null) {
            clonada.agregaInicio(explorador.elemento);
            explorador = explorador.siguiente;
        }
        return clonada;
        // Aquí va su código.
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> clonada = new Lista<T>();
        Nodo explorador = cabeza;
        while (explorador != null) {
            clonada.agregaFinal(explorador.elemento);
            explorador = explorador.siguiente;
        }
        return clonada;
        // Aquí va su código.
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
        // Aquí va su código.
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if (esVacia()) {
            throw new NoSuchElementException("la lista es vacía");
        } else {
            return cabeza.elemento;
        }
        // Aquí va su código.
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if (esVacia()) {
            throw new NoSuchElementException("la lista es vacía");
        } else {
            return rabo.elemento;
        }
        // Aquí va su código.
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if (i < 0 || i >= longitud) {
            throw new ExcepcionIndiceInvalido("el indice no esta en el rango de la lista");
        }
        Nodo explorador = cabeza;
        int contando = 0;
        while (explorador != null) {
            if (contando == i) {
                return explorador.elemento;
            }
            explorador = explorador.siguiente;
            contando++;
        }
        return null;
        // Aquí va su código.
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        int medidor = 0;
        Nodo explorador = cabeza;
        while (explorador != null) {
            if (explorador.elemento.equals(elemento)) {
                return medidor;
            }
            explorador = explorador.siguiente;
            medidor++;
        }
        return -1;
        // Aquí va su código.
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if (esVacia()) {
            String cadenitaRegreso = "[]";
            return cadenitaRegreso;
        }
        String cadenitaRegreso = "[";
        Nodo explorador = cabeza;
        while (explorador != null) {
            if (explorador.elemento != null) {
                cadenitaRegreso += explorador.elemento;
            }
            if (explorador.siguiente != null) {
                cadenitaRegreso += ", ";
            }
            explorador = explorador.siguiente;
        }
        cadenitaRegreso += "]";
        return cadenitaRegreso;
        // Aquí va su código.
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if (lista.getLongitud() != longitud) {
            return false;
        } else if (lista.getLongitud() == 0 && longitud == 0) {
            return true;
        } else {
            Nodo explorador = cabeza;
            Nodo gemelo = lista.cabeza;
            while (explorador != null) {
                if (explorador.elemento.equals(gemelo.elemento) == false) {
                    return false;
                }
                explorador = explorador.siguiente;
                gemelo = gemelo.siguiente;
            }
            return true;
        }
        // Aquí va su código.
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    @FunctionalInterface
    public interface Procesador<T> {
        public void procesa(T t);
    }
    
    public void paraCada(Procesador<T> procesador) {
            for (T elem : this) {
                procesador.procesa(elem);
            }
    }
}
