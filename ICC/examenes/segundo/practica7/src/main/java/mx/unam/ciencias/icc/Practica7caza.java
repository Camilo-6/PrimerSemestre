package mx.unam.ciencias.icc;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Práctica 7: Iteradores.
 */
public class Practica7caza {


    private static void cazaRaro(String cazando,String nombreArchivo) {
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            String datos = in.readLine();
            while (datos != null) {
                datos = datos.strip();
                if (datos.isEmpty()) {
                    return;
                }
                if (datos != null) {
                    
                    if (datos.toLowerCase().contains(cazando.toLowerCase())) {
                        System.out.println(datos);
                    }
                }
                datos = in.readLine();
            }
            in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude abrir del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }
    }

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar practica7.jar <cadena> <archivo>");
        System.exit(1);
    }

    public static void main(String[] args) {
        
        if (args.length != 2)
            uso();

        String cadena = args[0];
        String nombreArchivo = args[1];
        cazaRaro(cadena, nombreArchivo);
    }
}
