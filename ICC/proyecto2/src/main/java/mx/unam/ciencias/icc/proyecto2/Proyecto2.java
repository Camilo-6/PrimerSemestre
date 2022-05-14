package mx.unam.ciencias.icc.proyecto2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.Normalizer;
import mx.unam.ciencias.icc.Lista;

public class Proyecto2 {
    
    /* Metodo para limpiar una cadena de acentos, diéresis, eñes y espacios */
    private static String arreglar(String cadena) {
        String cadenita = Normalizer.normalize(cadena, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return cadenita.replace(" ", "");
    }

    /* Metodo para leer de la entrada estandar y volverlo en una lista */
    private static Lista<Linea> estandar() {
        Lista<Linea> listita = new Lista<>();
        try {
            InputStreamReader isIn = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(isIn);
            String datos = in.readLine();
            while (datos != null) {
                if (datos != null) {
                    Linea datitos = new Linea(datos);
                    listita.agregaFinal(datitos);
                }
                datos = in.readLine();
            }
            in.close();
        } catch (IOException ioe) {
            System.out.printf("Ocurrio un error. Se abortara la ejecucion.\n");
            System.exit(1);
        }
        return listita;
    }

    /* Metodo para leer archivos y volverlo en una lista */
    private static Lista<Linea> leyendo(Lista<String> arch) {
        Lista<Linea> listita = new Lista<>();
        for (String cad : arch) {
            try {
                FileInputStream fileIn = new FileInputStream(cad);
                InputStreamReader isIn = new InputStreamReader(fileIn);
                BufferedReader in = new BufferedReader(isIn);
                String datos = in.readLine();
                while (datos != null) {
                    if (datos != null) {
                        Linea datitos = new Linea(datos);
                        listita.agregaFinal(datitos);
                    }
                    datos = in.readLine();
                }
                in.close();
            } catch (IOException ioe) {
                System.out.printf("No pude abrir del archivo \"%s\". Se abortara la ejecucion.\n",
                                  cad);
                System.exit(1);
            }
        }
        return listita;
    }

    /* Metodo para mostrar una lista y terminar el programa */
    private static <T> void mostar(Lista<T> listita){
        for (T elem : listita) {
            System.out.println(elem);
        }
        System.exit(0);
    }

    /* Metodo para guardar la salida en un archivo y terminar el programa */
    private static void guardar(String nomArc, Lista<Linea> listita) {
        try {
            FileOutputStream fileOut = new FileOutputStream(nomArc);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            for (Linea lin : listita) {
                try {
                    out.write(lin.serializa());
                } catch (IOException e) {
                    throw new IOException("ocurrio un error de salida");
                }
            }
            out.close();
        } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".\n", nomArc);
            System.exit(1);
        }
        System.exit(0);;
    }

    /* Metodo para ver si hay banderas -r */
    private static Boolean buscaR(String[] args, int o) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-r") && i != o+1) {
                return true;
            }
        }
        return false;
    }

    /* Metodo para buscar si hay bandera -o */
    private static int buscaO(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                return i;
            }
        }
        return -2;
    }

    /* Metodo para buscar si hay bandera -o segundo */
    private static int buscaO2(String[] args) {
        int regreso = -2;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                regreso = i;
            }
        }
        return regreso;
    }

    /* Metodo para odernar las listas */
    private static Lista<Linea> ordenar(Lista<Linea> original) {
        Lista<Linea> listita = original.mergeSort(
                (cad1, cad2) -> arreglar(cad1.getContenido().strip()).compareToIgnoreCase(arreglar(cad2.getContenido().strip()))
        );
        return listita;
    }

    /* Metodo para ver los archivos por leer */
    private static Lista<String> archivos(String[] args, int o) {
        Lista<String> listita = new Lista<>();
        for (int i = 0; i < args.length; i++) {
            if (!args[i].equals("-r") && i != o && i != o+1) {
                listita.agregaFinal(args[i]);
            }
        }
        return listita;
    }
  
    public static void main(String[] args) {
    
        int o = buscaO(args);
        int donde = o+1;
        int o2 = buscaO2(args);

        if (donde > args.length-1) {
            System.out.println("No se proporciono un identificador para el archivo de salida. Se abortara la ejecucion.");
            System.exit(0);
        }
        if (o != o2 && o+1 != o2) {
            System.out.println("Se proporciono muchos archivos para la salida. Se abortara la ejecucion.");
            System.exit(0);
        }

        Boolean r = buscaR(args, o);
        Lista<String> archivitos = archivos(args, o);

        if (archivitos.esVacia()){
            Lista<Linea> listita = estandar();
            listita = ordenar(listita);
            if (r == true) {
                listita = listita.reversa();
            }
            if (o >= 0) {
                guardar(args[donde], listita);
            }
            mostar(listita);
        } else {
            Lista<Linea> listita = leyendo(archivitos);
            listita = ordenar(listita);
            if (r == true) {
                listita = listita.reversa();
            }
            if (o >= 0) {
                guardar(args[donde], listita);
            }
            mostar(listita);
        }
    }
}
