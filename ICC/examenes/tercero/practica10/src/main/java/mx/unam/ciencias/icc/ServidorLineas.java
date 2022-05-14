package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLineas {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: ./bin/servidor-lineas <puerto>");
            System.exit(1);
        }

        int puerto = Integer.parseInt(args[0]);

        try ( 
            ServerSocket serverSocket = new ServerSocket(puerto);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
        
            String recibido;
            int numero = 1;
            
            while ((recibido = in.readLine()) != null) {
                System.out.println(numero+" "+recibido);
                numero++;
            }
        } catch (IOException e) {
            System.out.println("algo salio mal :(");
        }
    }
}