package mx.unam.ciencias.icc;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Práctica 7: Iteradores.
 */
public class Practica7enumer {


    private static void enumerar(String nombreArchivo) {
        try {
            int wowi = 1;
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
                    System.out.println(wowi + " " + datos);
                    wowi++;
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
        System.out.println("Uso: java -jar practica7.jar <archivo>");
        System.exit(1);
    }

    public static void main(String[] args) {
        
        if (args.length != 1)
            uso();

        String nombreArchivo = args[0];
        enumerar(nombreArchivo);
    }
}
