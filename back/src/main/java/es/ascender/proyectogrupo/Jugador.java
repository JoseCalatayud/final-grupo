package es.ascender.proyectogrupo;

public class Jugador {

    private String nombre;
    private int intentos;
    private int puntuacion;
    
    public int getPuntuacion() {
        return puntuacion;
    }

    

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.intentos = 0;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getIntentos() {
        return intentos;
    }
    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }
    public void setPuntuacion(int puntuacion){
        this.puntuacion = puntuacion;
    }
    
    
}
