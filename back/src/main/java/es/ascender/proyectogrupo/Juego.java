package es.ascender.proyectogrupo;

public class Juego {

    private int numeroAleatorio;
    private int intentosMax;
    private Jugador jugador;

    public Juego(int intentosMax, String nombre) {
        this.numeroAleatorio = (int) (Math.random() * 10);
        this.intentosMax = intentosMax;
        this.jugador = new Jugador(nombre);

    }

    public Jugador getJugador() {
        return jugador;
    }

    public String comprobarIntento(int numero) {
        int intentosJugador = jugador.getIntentos() + 1;
        jugador.setIntentos(intentosJugador);
        if (intentosJugador <= intentosMax) {
            return evaluarNumero(numero);
        } else {
            return "Intentos Superados";
        }
    }

    public String evaluarNumero(int numero) {
        String respuesta="";
        if (numeroAleatorio > numero) {
            respuesta = "El numero es mayor";
        } else if (numeroAleatorio < numero) {
            respuesta = "El numero es menor";
        } else if (numeroAleatorio == numero){
            respuesta = "Has acertado";
        }
        return respuesta;
    }

    
}
