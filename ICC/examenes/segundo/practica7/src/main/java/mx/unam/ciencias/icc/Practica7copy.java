package mx.unam.ciencias.icc;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Práctica 7: Iteradores.
 */
public class Practica7copy {


    private static void enumerar(String nombreArchivo) {
        try {
            int wowi = 0;
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
                    try {
                        int numerito = Integer.parseInt(datos);
                        wowi = wowi + numerito;
                    } catch (NumberFormatException e) {
                        datos = in.readLine();
                        continue;
                    }
                }
                datos = in.readLine();
            }
            System.out.println(wowi);
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
