package es.ascender.proyectogrupo;

public class Juego {

    private int numeroAleatorio;
    private int intentosMax;
    private int numeroSuperior;
    private Jugador jugador;

    public Juego(int intentosMax, String nombre, int numeroSuperior) {
        this.numeroAleatorio = (int) (Math.random() * numeroSuperior);
        this.intentosMax = intentosMax;
        this.jugador = new Jugador(nombre);
        this.numeroSuperior = numeroSuperior;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getNumeroAleatorio() {
        return numeroAleatorio;
    }

    public int getIntentosMax() {
        return intentosMax;
    }

    public void setIntentosMax(int intentosMax) {
        this.intentosMax = intentosMax;
    }

    public int getNumeroSuperior() {
        return numeroSuperior;
    }

    public void setNumeroSuperior(int numeroSuperior) {
        this.numeroSuperior = numeroSuperior;
    }

    public String comprobarIntento(int numero) {
        sumarIntento();
        String respuesta = "";
        if (!esUltimoIntento()) {
            int caso = evaluarNumero(numero);
            switch (caso) {
                case 1:
                    respuesta = "Es un numero mayor. Llevas " + jugador.getIntentos() + " intentos";
                    break;
                case 2:
                    respuesta = "Es un numero menor. Llevas " + jugador.getIntentos() + " intentos";
                    break;
                case 3:
                    respuesta = "Enhorabuena, has ganado!!! Lo conseguiste en " + jugador.getIntentos() + " intentos";
                    break;
            }
        } else if (esUltimoIntento()) {
            int caso = evaluarNumero(numero);
            switch (caso) {
                case 1:
                    respuesta = "Era un numero mayor. Agotaste los intentos";
                    break;
                case 2:
                    respuesta = "Era un numero menor. Agotaste los intentos";
                    break;
                case 3:
                    respuesta = "Enhorabuena, has ganado!!! Lo conseguiste en " + jugador.getIntentos() + " intentos";
                    break;
            }

        }
        return respuesta;
    }

    public void sumarIntento() {
        jugador.setIntentos(jugador.getIntentos() + 1);
    }

    public boolean esUltimoIntento() {
        return jugador.getIntentos() == intentosMax;
    }

    public int evaluarNumero(int numero) {
        int respuesta = 0;
        if (numeroAleatorio > numero) {
            respuesta = 1;
        } else if (numeroAleatorio < numero) {
            respuesta = 2;
        } else if (numeroAleatorio == numero) {
            respuesta = 3;
        }
        return respuesta;

    }

    public void darPuntuacion() {
        int valorIntento = (int) intentosMax / 10;
        int puntuacion = (intentosMax - jugador.getIntentos()) * valorIntento;
        jugador.setPuntuacion(puntuacion);
    }

}
