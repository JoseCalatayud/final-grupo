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

    public boolean evaluarIntentos() {

        if (jugador.getIntentos() < intentosMax) {
            return true;
        }
        return false;
    }

    // public String comprobarIntento(int numero) {
    // String respuesta = "";
    // jugador.setIntentos(jugador.getIntentos() + 1);
    // if (evaluarIntentos() && (evaluarNumero(numero) == 1)) {
    // respuesta = "Es un numero mayor";
    // } else if (evaluarIntentos() && (evaluarNumero(numero) == 2)) {
    // respuesta = "Es un numero menor";
    // } else if (!evaluarIntentos()) {
    // respuesta = "Has agotado tus intentos";
    // } else if (evaluarIntentos() && evaluarNumero(numero) == 3) {
    // respuesta = "Has acertado!!!";
    // }
    // return respuesta;
    // }
    public String comprobarIntento(int numero) {
        int caso = evaluarNumero(numero);
        String respuesta = "";
        switch (caso) {
            case 1:
                respuesta = "Es un numero mayor. Llevas "+ jugador.getIntentos() + " intentos";
            case 2:
                respuesta = "Es un numero menor";
            case 3:
                respuesta = "Enhorabuena, has ganado!!!";
            case 4:
                respuesta = "Has agotado tus intentos";

        }
        return respuesta;
    }

    public int evaluarNumero(int numero) {
        int respuesta = 0;
        if (evaluarIntentos()) {
            if (numeroAleatorio > numero) {
                respuesta = 1;
            } else if (numeroAleatorio < numero) {
                respuesta = 2;
            } else if (numeroAleatorio == numero) {
                respuesta = 3;
            }
        } else {
            respuesta = 4;
        }
        return respuesta;

    }

}
