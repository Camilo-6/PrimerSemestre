
public class PingT extends Thread {

    private String frase;
    private int retraso;
    private int contador;

    public PingT(String frase, int retraso, String nombre) {
        super(nombre);
        this.frase = frase;
        this.retraso = retraso;
        contador = 0;
    }

    public void run() {
        System.out.println("Empezo: "+Thread.currentThread().getName());
        try {
            while (contador <= 10) {
                System.out.print(frase+" ");
                contador++;
                Thread.sleep(retraso);
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public static void main(String[] args) {
        
        System.out.println("Comenzo: "+currentThread().getName());
        new PingT("ping", 33, "PingT").start();
        new PingT("pong", 33, "PongT").start();

    }

}
