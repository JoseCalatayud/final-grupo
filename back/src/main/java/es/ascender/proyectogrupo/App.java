package es.ascender.proyectogrupo;

import java.util.Scanner;
//e
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Bienvenido a Adivina el Número");
            System.out.println("Elija una de las siguientes opciones");
            System.out.println("1. JUGAR");
            System.out.println("2. SALIR");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    String nombre; 

                    do {
                        System.out.println("Introduzca su nombre");
                        nombre = sc.nextLine();
                    } while (nombre.trim().isEmpty());
                    int numeroSuperior;

                    do {
                        System.out.println("introduzca numero maximo a adivinar");
                        while (!sc.hasNextInt()) {
                            System.out.println("Por favor, introduzca un número válido.");
                            sc.next();
                        }
                        numeroSuperior = sc.nextInt();
                    } while (numeroSuperior < 0);

                    int intentosMax;
                    do {
                        System.out.println("En cuantos intentos cree que puede adivinarlo");
                        while (!sc.hasNextInt()) {
                            System.out.println("Por favor, introduzca un número válido.");
                            sc.next();
                        }
                        intentosMax = sc.nextInt();
                    } while (intentosMax < 0);

                    Juego juego = new Juego(intentosMax, nombre, numeroSuperior);

                    while (juego.getJugador().getIntentos() < juego.getIntentosMax()) {
                        System.out.println("Introduce un número");
                        while(!sc.hasNextInt()){
                            System.out.println("Por favor, introduce un número válido");
                            sc.next();
                        }
                        int numero = sc.nextInt();
                        System.out.println(juego.comprobarIntento(numero));
                        
                        if (juego.evaluarNumero(numero) == 3) {
                            break;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Te esperamos de nuevo pronto");
                    break;
                default:
                    System.out.println("No es una opcion adecuada");
            }
        } while (opcion != 2);
        sc.close();
    }

}