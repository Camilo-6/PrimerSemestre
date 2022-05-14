package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Get {

    public static void uso() {
        System.out.println("Uso: java -jar target/get.jar <servidor> <puerto>");
        System.exit(0);
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            uso();
        }

        String servidor = args[0];
        try {
            int puerto = Integer.parseInt(args[1]);
            Socket sss = new Socket(servidor, puerto);
            String enviar = "GET /";
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sss.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(sss.getInputStream()));
            out.write(enviar);
            out.newLine();
            out.flush();
            String linea = in.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = in.readLine();
            }

        } catch (Exception e) {
            System.out.println("algo salio mal :(");
        }
        
    }
}
