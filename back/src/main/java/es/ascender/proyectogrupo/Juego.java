package es.ascender.proyectogrupo;



public class Juego {

    private int numeroAleatorio;
    private int intentosMax;
    private Jugador jugador;


    public Juego(int intentosMax, String nombre) {
        this.numeroAleatorio = (int) (Math.random()*10);
        this.intentosMax = intentosMax;
        this.jugador = new Jugador(nombre);

    }
    

    public String comprobarNumero (int numero){
        int intentosJugador = jugador.getIntentos() + 1;
        jugador.setIntentos(intentosJugador);
        if(jugador.getIntentos()>intentosMax)
        if(numeroAleatorio>numero){            
            return "El numero es mayor";
        }else if(numeroAleatorio<numero){
            return "El numero es menor";        }
        return "Enhorabuena, has acertado";

    }




    
    

}
