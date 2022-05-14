package mx.unam.ciencias.icc;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Leer extends Thread{

    private String linea;
    private BufferedReader in;
    private boolean leido = false;

    public static void uso() {
        System.out.println("Uso: java -jar target/leer.jar <archivo>");
    }

    public Leer(String nombre){
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(nombre)));
        } catch (IOException e) {
            System.out.println("algo salio mal :(");
        }
    }

    public void leer() {
        try {
            synchronized(this){
                while ((linea = in.readLine()) != null) {
                    while (leido) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            System.out.println("algo salio mal :(");
                        }
                    }
                    leido = true;
                    notify();
                }
                in.close();
            }
        } catch (IOException e) {
            System.out.println("algo salio mal :(");
        }
    }

    public void escribir() {
        try {
            sleep(300);
        } catch (InterruptedException e) {
        }
        synchronized(this){
            while (linea != null) {
                while (!leido) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("algo salio mal :(");
                    }
                }
                if (linea != null) {
                    System.out.println(linea);
                }
                leido = false;
                notify();
            }
        }
    }
    
    public static void main(String[] args) {

        if (args.length != 1) {
            uso();
            System.exit(0);
        }

        Leer lineas = new Leer(args[0]);
        
        Thread leer = new Thread(new Runnable() {
            public void run() {
                lineas.leer();
            }
        });
        Thread escribir = new Thread(new Runnable() {
            public void run() {
                lineas.escribir();
            }
        });

        leer.start();
        escribir.start();
    }
}
