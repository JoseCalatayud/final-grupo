package es.ascender.proyectogrupo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a Adivina el Numero");
        System.out.println("Dime tu nombre");
        Juego juego = new Juego(6, sc.nextLine());
        System.out.println("Hola " + juego.getJugador().getNombre() + ", tienes que adivinar un numero entre 0 y 10.");
        System.out.println("Tienes 6 intentos");


        while (juego.getJugador().getIntentos() < 6 ) {
            System.out.println("Introduce un numero");
            int numero = sc.nextInt();
            System.out.println(juego.comprobarIntento(numero));
            if (juego.evaluarNumero(numero)==3){
                break;
            }
        }
        sc.close();
 
            


    
}}