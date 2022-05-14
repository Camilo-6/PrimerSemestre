
public class PingR implements Runnable {

    private String frase;
    private int retraso;
    private int contador;

    public PingR(String frase, int retraso) {
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
        
        Runnable ping = new PingR("ping", 33);
        Runnable pong = new PingR("pong", 100);
        new Thread(ping, "PingR").start();
        new Thread(pong, "PongR").start();

    }

}

