package mx.unam.ciencias.icc;

import java.util.InputMismatchException;
import java.util.Scanner;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Proyecto
 */
public class Proyecto1 {

    /* Hace búsquedas por nombre y precio en la base de datos. */
    private static void busquedas(BaseDeDatosVideojuego bdd) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        System.out.printf("Entra un nombre para buscar: ");
        String nombre = sc.next();

        Lista r = bdd.buscaRegistros(CampoVideojuego.NOMBRE, nombre);
        if (r.esVacia()) {
            System.out.printf("\nNo se hallaron videojuegos " +
                              "con el nombre \"%s\".\n",
                              nombre);
        } else {
            System.out.printf("\nSe hallaron los siguientes " +
                              "videojuegos con el nombre \"%s\":\n\n",
                              nombre);
            Lista.Nodo nodo = r.getCabeza();
            while (nodo != null) {
                System.out.println(nodo.get().toString() + "\n");
                nodo = nodo.getSiguiente();
            }
        }

        System.out.printf("Entra un precio para buscar: ");
        double precio = 0.0;
        try {
            precio = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.out.printf("Se entró un precio inválido. " +
                              "Se interpretará como cero.\n");
        }

        r = bdd.buscaRegistros(CampoVideojuego.PRECIO, Double.valueOf(precio));
        if (r.esVacia()) {
            System.out.printf("\nNo se hallaron videojuegos " +
                              "con el precio mayor o igual a %.2f.\n",
                              precio);
        } else {
            System.out.printf("\nSe hallaron los siguientes videojuegos " +
                              "con el precio mayor o igual a %.2f:\n\n",
                              precio);
            Lista.Nodo nodo = r.getCabeza();
            while (nodo != null) {
                System.out.println(nodo.get().toString() + "\n");
                nodo = nodo.getSiguiente();
            }
        }
    }

    /* Crea una base de datos y la llena a partir de los datos que el usuario
       escriba a través del teclado. Después la guarda en disco duro y la
       regresa. */
    private static BaseDeDatosVideojuego escritura(String nombreArchivo) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe.\n" +
                              "Presiona Ctrl-C si no quieres reescribirlo, " +
                              "o Enter para continuar...\n", nombreArchivo);
            sc.nextLine();
        }

        System.out.println("Entra videojuegos a la base de datos.\n" +
                           "Cuando desees terminar, deja el nombre en blanco.\n");

        BaseDeDatosVideojuego bdd = new BaseDeDatosVideojuego();

        do {
            String nombre;
            int cantidad = 0;
            double precio = 0.0;
            int anio = 0;

            System.out.printf("Nombre   : ");
            nombre = sc.next();
            if (nombre.equals(""))
                break;
            try {
                System.out.printf("Cantidad : ");
                cantidad = sc.nextInt();
                System.out.printf("Precio   : ");
                precio = sc.nextDouble();
                System.out.printf("Año      : ");
                anio = sc.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("\nNúmero inválido: se descartará " +
                                   "este videojuego.\n");
                continue;
            }
            Videojuego e = new Videojuego(nombre,
                                          cantidad,
                                          precio,
                                          anio);
            bdd.agregaRegistro(e);
            System.out.println();
        } while (true);

        int n = bdd.getNumRegistros();
        if (n == 1)
            System.out.printf("\nSe agregó 1 videojuego.\n");
        else
            System.out.printf("\nSe agregaron %d videojuegos.\n", n);

        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("\nBase de datos guardada exitosamente en \"%s\".\n",
                          nombreArchivo);

        return bdd;
    }

    /* Crea una base de datos y la llena cargándola del disco duro. Después la
       regresa. */
    private static BaseDeDatosVideojuego lectura(String nombreArchivo) {
        BaseDeDatosVideojuego bdd = new BaseDeDatosVideojuego();

        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("Base de datos cargada exitosamente de \"%s\".\n\n",
                          nombreArchivo);

        Lista r = bdd.getRegistros();
        Lista.Nodo nodo = r.getCabeza();
        while (nodo != null) {
            System.out.println(nodo.get().toString() + "\n");
            nodo = nodo.getSiguiente();
        }

        return bdd;
    }

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar proyecto1.jar [-g|-c] <archivo>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            uso();

        String bandera = args[0];
        String nombreArchivo = args[1];

        if (!bandera.equals("-g") && !bandera.equals("-c"))
            uso();

        BaseDeDatosVideojuego bdd;

        if (bandera.equals("-g"))
            bdd = escritura(nombreArchivo);
        else
            bdd = lectura(nombreArchivo);

        busquedas(bdd);
    }
}
