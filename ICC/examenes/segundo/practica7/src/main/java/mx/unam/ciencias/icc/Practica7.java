package mx.unam.ciencias.icc;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Práctica 7: Iteradores.
 */
public class Practica7 {

    public static void main(String[] args) {

        
        Lista<String> lista = new Lista<>();
        lista.agregaFinal("hola");
        lista.agregaFinal("Buenos");
        lista.agregaFinal("dIas");
        lista.agregaFinal("como estaS?");
        lista.agregaFinal("que BieEn");
        for (String e : lista) {
            System.out.println(e);
        }
        System.out.println();
        lista.paraCada(e -> System.out.println(e.toLowerCase()));
        System.out.println();
        lista.paraCada(e -> System.out.println(e.toUpperCase()));
        
    }
}
