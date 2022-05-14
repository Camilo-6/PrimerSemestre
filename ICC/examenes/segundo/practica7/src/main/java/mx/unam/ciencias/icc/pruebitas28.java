package mx.unam.ciencias.icc;

public class pruebitas28{

    private static void reversa(String[] arreglo) {
        for (int i = 0; i < arreglo.length/2; i++) {
            String auxi = arreglo[i];
            arreglo[i] = arreglo[arreglo.length-1-i];
            arreglo[arreglo.length-1-i] = auxi;
        }
    }

    private static <T> Lista<T> arreGen(T[] arreglo) {
        Lista<T> listita = new Lista<>();
        for (int i = 0; i < arreglo.length; i++) {
            listita.agregaFinal(arreglo[i]);
        }
        return listita;
    }

    public static void main(String[] args) {
        String nombre[] = {"Primera", "Segunda", "Tercera", "Cuarta", "Quinta"}; 
        for (String a : nombre) {
            System.out.println(a);
        }
        
    }
}