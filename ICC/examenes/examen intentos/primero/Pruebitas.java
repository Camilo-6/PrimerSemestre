
public class Pruebitas {

    public static Estudiante nombreGrande(ListaEstudiante lista){
        if (lista.esVacia()) {
            return null;
        }
        ListaEstudiante.Nodo explorador = lista.getCabeza();
        Estudiante elemento = lista.getCabeza().get();
        return nombreAuxi(lista, explorador, elemento);
    }

    private static Estudiante nombreAuxi(ListaEstudiante lista, ListaEstudiante.Nodo explorador, Estudiante elemento){
        if (explorador == null) {
            return elemento;
        }
        if (elemento.getNombre().length() <= explorador.get().getNombre().length()) {
            elemento = explorador.get();
        }
        return nombreAuxi(lista, explorador.getSiguiente(), elemento);
    }

    public static double promedio(ListaEstudiante lista){
        if (lista.esVacia()) {
            return Double.NaN;
        }
        ListaEstudiante.Nodo explorador = lista.getCabeza();
        double promedio = 0; 
        return (promedioAuxi(lista, explorador, promedio)/lista.getLongitud());
    }

    private static double promedioAuxi(ListaEstudiante lista, ListaEstudiante.Nodo explorador, double promedio){
        if (explorador == null) {
            return promedio;
        }
        promedio = promedio + explorador.get().getPromedio();
        return promedioAuxi(lista, explorador.getSiguiente(), promedio);
    }
     
    public static String stringRaro(Lista lista){
        String regresar = "";
        Lista.Nodo explorador = lista.getCabeza();
        return stringRaroAuxi(lista, explorador, regresar);
    }

    private static String stringRaroAuxi(Lista lista, Lista.Nodo explorador, String regresar){
        if (explorador == null) {
            return regresar;
        }
        if (explorador.get() instanceof String) {
            regresar = regresar + "\n" + (String)explorador.get();
        }
        return stringRaroAuxi(lista, explorador.getSiguiente(), regresar);
    }

    public static Lista mayores(Lista lista, int n){
        Lista regresar = new Lista();
        if (lista.esVacia()) {
            return regresar;
        }
        Lista.Nodo explorador = lista.getCabeza();
        return mayoresAuxi(regresar, n, explorador);
    }

    private static Lista mayoresAuxi(Lista lista, int n, Lista.Nodo explorador){
        if (explorador == null) {
            return lista;
        }
        if (explorador.get() instanceof Integer) {
            if ((Integer)explorador.get() >= n) {
                lista.agregaFinal(explorador.get());
            }
        }
        explorador = explorador.getSiguiente();
        return mayoresAuxi(lista, n, explorador);
    }

    public static String dificil(Lista lista){
        String regresar = "";
        Lista.Nodo explorador = lista.getCabeza();
        int n = 0;
        return dificilAuxi(lista, explorador, regresar, n);
    }

    private static String dificilAuxi (Lista lista, Lista.Nodo explorador, String regresar, int n){
        if (explorador == null) {
            return regresar;
        }
        if (explorador.get() instanceof Integer) {
            for (int i = 0; i < n; i++) {
                regresar = regresar + "\t";
            }
            regresar = regresar + explorador.get();
            if (explorador.getSiguiente() != null) {
                regresar = regresar + "\n";
            }
        } else {
            if (explorador.get() instanceof Lista) {
                Lista interna = (Lista)explorador.get();
                Lista.Nodo explorar = interna.getCabeza();
                String regresarDos = "";
                regresar = regresar + dificilAuxi(interna, explorar, regresarDos, n+1);
                if (explorador.getSiguiente() != null) {
                    regresar = regresar + "\n";
                }
            }
        }
        return dificilAuxi(lista, explorador.getSiguiente(), regresar, n);
    }

    public static Lista tresEnteros(Lista lista){
        Lista regresar = new Lista();
        if (lista.esVacia()) {
            return regresar;
        }
        Lista.Nodo explorador = lista.getCabeza();
        Lista.Nodo comparar = buscarEnt(lista, explorador);
        if (comparar.get() instanceof Integer) {
            Integer comprar = (Integer)comparar.get();
            regresar.agregaFinal(buscar1(lista, explorador, comprar));
            regresar.agregaFinal(buscar2(lista, explorador, comprar));
            Integer promedio = 0;
            Integer cuenta = 0;
            regresar.agregaFinal(buscar3(lista, explorador, promedio, cuenta));
        }
        return regresar;
    }

    private static Lista.Nodo buscarEnt(Lista lista, Lista.Nodo explorador){
        if (explorador == null) {
            return null;
        }
        if (explorador.get() instanceof Integer) {
            return explorador;
        }
        return buscarEnt(lista, explorador.getSiguiente());
    }

    private static Integer buscar1(Lista lista, Lista.Nodo explorador, Integer comprar){
        if (explorador == null) {
            return comprar;
        }
        if (explorador.get() instanceof Integer) {
            if ((Integer)explorador.get() < comprar){
                comprar = (Integer)explorador.get();
            }
        }
        return buscar1(lista, explorador.getSiguiente(), comprar);
    }

    private static Integer buscar2(Lista lista, Lista.Nodo explorador, Integer comprar){
        if (explorador == null) {
            return comprar;
        }
        if (explorador.get() instanceof Integer) {
            if ((Integer)explorador.get() > comprar){
                comprar = (Integer)explorador.get();
            }
        }
        return buscar2(lista, explorador.getSiguiente(), comprar);
    }

    private static Integer buscar3(Lista lista, Lista.Nodo explorador, Integer promedio, Integer cuenta){
        if (explorador == null) {
            return promedio/cuenta;
        }
        if (explorador.get() instanceof Integer) {
            promedio = promedio + (Integer)explorador.get();
            cuenta++;
        }
        return buscar3(lista, explorador.getSiguiente(), promedio, cuenta);
    }

    private static int fact(int n) {
        int r =1, c=1;
        while (c < n) {
            r*=++c;
        }
        return r;
    }
    public static void main(String[] args) {
    /*     Lista l = new Lista();
        l.agregaFinal(123);
        l.agregaFinal(-112);
        l.agregaFinal(233);
        l.agregaFinal(23);
        System.out.println(tresEnteros(l));

       int n = 3;
        int cont = 0;
        Lista lista = new Lista();
        for (int i = 0; i < n; i++) {
            lista.agregaFinal(cont++);
            Lista l1 = new Lista();
            for (int j = 0; j < n; j++) {
                l1.agregaFinal(cont++);
                Lista l2 = new Lista();
                for (int k = 0; k < n; k++)
                    l2.agregaFinal(cont++);
                    l1.agregaFinal(l2);
                }
            lista.agregaFinal(l1);
        }
        System.out.println(dificil(lista)); */

        System.out.println(fact(1));
        System.out.println(fact(2));
        System.out.println(fact(3));
        System.out.println(fact(4));
    }
}
