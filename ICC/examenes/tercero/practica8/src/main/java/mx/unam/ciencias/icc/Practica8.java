package mx.unam.ciencias.icc;


public class Practica8 extends Thread{

    private int numero;
    private int maximo;

    public Practica8(int max) {
        numero = 0;
        maximo = max;
    }

    public void run() {
        try {
            while (numero <= maximo) {
                System.out.println(numero++);
                sleep(1000);
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void uso() {
        System.out.println("Uso: java -jar target/cronometro.jar <entero>");
        System.exit(0);
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            uso();
        }

        try {
            int max = Integer.parseInt(args[0]);
            new Practica8(max).start();
        } catch (NumberFormatException e) {
            uso();
        }

    }
}
