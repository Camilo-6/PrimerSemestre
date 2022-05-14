package mx.unam.ciencias.icc.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import mx.unam.ciencias.icc.EventoBaseDeDatos;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.Registro;

/**
 * Clase para conexiones de la base de datos.
 */
public class Conexion<R extends Registro<R, ?>> {

    /* Contador de números seriales. */
    private static int contadorSerial;

    /* La entrada de la conexión. */
    private BufferedReader in;
    /* La salida de la conexión. */
    private BufferedWriter out;
    /* La base de datos. */
    private BaseDeDatos<R, ?> bdd;
    /* Lista de escuchas de conexión. */
    private Lista<EscuchaConexion<R>> escuchas;
    /* El enchufe. */
    private Socket enchufe;
    /* Si la conexión está activa. */
    private boolean activa;
    /* El número serial único de la conexión. */
    private int serial;

    /**
     * Define el estado inicial de una nueva conexión.
     * @param bdd la base de datos.
     * @param enchufe el enchufe de la conexión ya establecida.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public Conexion(BaseDeDatos<R, ?> bdd, Socket enchufe) throws IOException {
        in = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(enchufe.getOutputStream()));
            this.bdd = bdd;
            escuchas = new Lista<EscuchaConexion<R>>();
            this.enchufe = enchufe;
            this.activa = true;
            this.serial = ++contadorSerial;
        // Aquí va su código.
    }

    /**
     * Recibe los mensajes de la conexión. El método no termina hasta que la
     * conexión sea cerrada. Al ir leyendo su entrada, la conexión convertirá lo
     * que lea en mensajes y reportará cada uno a los escuchas.
     */
    public void recibeMensajes() {
        try {
            while (activa) {
                String linea = in.readLine();
                if (linea == null) {
                    break;
                }
                for (EscuchaConexion<R> escucha : escuchas) {
                    escucha.mensajeRecibido(this, Mensaje.getMensaje(linea));
                }
            }
        } catch (IOException e) {
            if (isActiva()) {
                for (EscuchaConexion<R> escucha2 : escuchas) {
                    escucha2.mensajeRecibido(this, Mensaje.INVALIDO);
                }
            }
        }
        for (EscuchaConexion<R> escucha3 : escuchas) {
            escucha3.mensajeRecibido(this, Mensaje.DESCONECTAR);
        }
        // Aquí va su código.
    }

    /**
     * Recibe la base de datos del otro lado de la conexión.
     * @throws IOException si la base de datos no puede recibirse.
     */
    public void recibeBaseDeDatos() throws IOException {
        try {
            bdd.carga(in);
        } catch (IOException e) {
            throw new IOException();
        }
        // Aquí va su código.
    }

    /**
     * Envía la base de datos al otro lado de la conexión.
     * @throws IOException si la base de datos no puede enviarse.
     */
    public void enviaBaseDeDatos() throws IOException {
        try {
            bdd.guarda(out);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            throw new IOException();
        }
        // Aquí va su código.
    }

    /**
     * Recibe un registro del otro lado de la conexión.
     * @return un registro del otro lado de la conexión.
     * @throws IOException si el registro no puede recibirse.
     */
    public R recibeRegistro() throws IOException {
        try {
            R registro = bdd.creaRegistro();
            registro.deserializa(in.readLine());
            return registro;
        } catch (IOException e) {
            throw new IOException();
        }
        // Aquí va su código.
    }

    /**
     * Envía un registro al otro lado de la conexión.
     * @param registro el registro a enviar.
     * @throws IOException si el registro no puede enviarse.
     */
    public void enviaRegistro(R registro) throws IOException {
        out.write(registro.serializa());
        out.flush();
        // Aquí va su código.
    }

    /**
     * Envía mensaje registro al otro lado de la conexión.
     * @param mensaje el mensaje a enviar.
     * @throws IOException si el mensaje no puede enviarse.
     */
    public void enviaMensaje(Mensaje mensaje) throws IOException {
        out.write(mensaje.toString());
        out.newLine();
        out.flush();
        // Aquí va su código.
    }

    /**
     * Regresa un número serial para cada conexión.
     * @return un número serial para cada conexión.
     */
    public int getSerial() {
        return serial;
        // Aquí va su código.
    }

    /**
     * Cierra la conexión.
     */
    public void desconecta() {
        try {
            activa = false;
            enchufe.close();
        } catch (IOException e) {
        }
        // Aquí va su código.
    }

    /**
     * Nos dice si la conexión es activa.
     * @return <code>true</code> si la conexión es activa; <code>false</code> en
     *         otro caso.
     */
    public boolean isActiva() {
        return activa;
        // Aquí va su código.
    }

    /**
     * Agrega un escucha de conexión.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaConexion<R> escucha) {
        this.escuchas.agregaFinal(escucha);
        // Aquí va su código.
    }
}
