package mx.unam.ciencias.icc;

public class ParImpar extends Thread{

    int numero;

    public ParImpar(int num){
        numero = num;
    }

    private boolean esPar(int num){
        return numero%2==0;
    }

    public void par() {
        synchronized(this){
            while (true) {
                while (!esPar(numero)) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("algo salio mal :(");
                    }
                }
                System.out.println(numero);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("algo salio mal :(");
                }
                numero++;
                notify();
            }
        }
    }

    public void impar() {
        synchronized(this){
            while (true) {
                while (esPar(numero)) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println("algo salio mal :(");
                    }
                }
                System.out.println(numero);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("algo salio mal :(");
                }
                numero++;
                notify();
            }
        }
    }
    
    public static void main(String[] args) {

        ParImpar numeros = new ParImpar(1);
        Thread impar = new Thread(new Runnable() {
            public void run() {
                numeros.impar();
            }
        });
        Thread par = new Thread(new Runnable() {
            public void run() {
                numeros.par();
            }
        });

        impar.start();
        par.start();

    }
}
