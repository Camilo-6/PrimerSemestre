package mx.unam.ciencias.icc;

/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
            this.elemento = elemento;
            // Aquí va su código.
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return anterior;
            // Aquí va su código.
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return siguiente;
            // Aquí va su código.
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Estudiante get() {
            return elemento;
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
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Estudiante elemento) {
        if (elemento == null) {
            return;
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
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Estudiante elemento) {
        if (elemento == null) {
            return;
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
    Metodos auxiliares para buscar el nodo donde esta un estudiante (elemento)
    Obtener Nodo dando Estudiante
    */
    private Nodo buscaNodo(Estudiante buscado) {
        if (buscado == null) {
            return null;
        }
        Nodo explorador = cabeza;
        return auxiBuscaNodo(buscado, explorador);
    }

    private Nodo auxiBuscaNodo(Estudiante buscado, Nodo explorador) {
        if (explorador == null) {
            return null;
        }
        if (explorador.elemento.equals(buscado)) {
            return explorador;
        }
        return auxiBuscaNodo(buscado, explorador.siguiente);
    }

    /*
    Metodo auxiliar para buscar el i-esimo nodo (elemento)
    Obtener Nodo dando int
    */
    private Nodo buscaNodoConI(int i) {
        if (i < 0 || i >= longitud) {
            return null;
        }
        Nodo explorador = cabeza;
        int contando = 0;
        return auxiBuscaNodoConI(i, explorador, contando);
    }

    private Nodo auxiBuscaNodoConI (int i, Nodo explorador, int contando) {
        if (explorador == null) {
            return null;
        }
        if (i == contando) {
            return explorador;
        }
        return auxiBuscaNodoConI(i, explorador.siguiente, contando+1);
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
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Estudiante elemento) {
        if (elemento == null) {
            return;
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
    public void elimina(Estudiante elemento) {
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
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
        if (esVacia()) {
            return null;
        }
        Estudiante porEliminar = cabeza.elemento;
        desapareceNodo(cabeza);
        return porEliminar;
        // Aquí va su código.
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaUltimo() {
        if (esVacia()) {
            return null;
        }
        Estudiante porEliminar = rabo.elemento;
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
    public boolean contiene(Estudiante elemento) {
        if (elemento == null) {
            return false;
        }
        if (buscaNodo(elemento) != null) {
            return true;
        }
        return false;
        // Aquí va su código.
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public ListaEstudiante reversa() {
        ListaEstudiante clonada = new ListaEstudiante();
        Nodo explorador = cabeza;
        return auxiReversa(explorador, clonada);
        // Aquí va su código.
    }

    private ListaEstudiante auxiReversa(Nodo explorador, ListaEstudiante clonada) {
        if (explorador == null) {
            return clonada;
        }
        clonada.agregaInicio(explorador.elemento);
        return auxiReversa(explorador.siguiente, clonada);
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaEstudiante copia() {
        ListaEstudiante clonada = new ListaEstudiante();
        Nodo explorador = cabeza;
        return auxiCopia(explorador, clonada);
        // Aquí va su código.
    }

    private ListaEstudiante auxiCopia(Nodo explorador, ListaEstudiante clonada) {
        if (explorador == null) {
            return clonada;
        }
        clonada.agregaFinal(explorador.elemento);
        return auxiCopia(explorador.siguiente, clonada);
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
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
        if (esVacia()) {
            return null;
        } else {
            return cabeza.elemento;
        }
        // Aquí va su código.
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
        if (esVacia()) {
            return null;
        } else {
            return rabo.elemento;
        }
        // Aquí va su código.
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
        if (i < 0 || i >= longitud) {
            return null;
        }
        Nodo explorador = cabeza;
        int contando = 0;
        return auxiGet(i, explorador, contando);
        // Aquí va su código.
    }
    
    private Estudiante auxiGet(int i, Nodo explorador, int contando) {
        if (explorador == null) {
            return null;
        }
        if (i == contando) {
            return explorador.elemento;
        }
        return auxiGet(i, explorador.siguiente, contando+1);
    }
    
    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Estudiante elemento) {
        int medidor = 0;
        Nodo explorador = cabeza;
        return auxiIndiceDe(elemento, explorador, medidor);
        // Aquí va su código.
    }
    
    private int auxiIndiceDe(Estudiante elemento, Nodo explorador, int medidor) {
        if (explorador == null) {
            return -1;            
        }
        if (explorador.elemento.equals(elemento)) {
            return medidor;
        }
        return auxiIndiceDe(elemento, explorador.siguiente, medidor+1);
    }
    
    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        if (esVacia()) {
            String cadenitaRegreso = "[]";
            return cadenitaRegreso;
        }
        String cadenitaRegreso = "[";
        Nodo explorador = cabeza;
        return auxiToString(explorador, cadenitaRegreso);
        // Aquí va su código.
    }

    private String auxiToString(Nodo explorador, String cadenitaRegreso) {
        if (explorador == null) {
            cadenitaRegreso += "]";
            return cadenitaRegreso;
        }
        if (explorador.elemento != null) {
            cadenitaRegreso += explorador.elemento;
        }
        if (explorador.siguiente != null) {
            cadenitaRegreso += ", ";
        }
        return auxiToString(explorador.siguiente, cadenitaRegreso);
    }

    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <code>true</code> si la lista es igual a la recibida;
     *         <code>false</code> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
        if (lista == null) {
            return false;
        } else if (lista.getLongitud() != longitud) {
            return false;
        } else if (lista.getLongitud() == 0 && longitud == 0) {
            return true;
        }
        Nodo explorador = cabeza;
        Nodo gemelo = lista.cabeza;
        return auxiEquals(explorador, gemelo);
        // Aquí va su código.
    }

    private boolean auxiEquals(Nodo explorador, Nodo gemelo) {
        if (explorador == null) {
            return true;
        }
        if (explorador.elemento.equals(gemelo.elemento) == false) {
            return false;
        }
        return auxiEquals(explorador.siguiente, gemelo.siguiente);
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
        // Aquí va su código.
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
        // Aquí va su código.
    }
}
