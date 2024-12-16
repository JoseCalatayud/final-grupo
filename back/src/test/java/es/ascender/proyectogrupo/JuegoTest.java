package es.ascender.proyectogrupo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JuegoTest {
    private Juego juego;
    


    

    @BeforeEach
    void setUp() {
        juego = new Juego(5,"TestPlayer",50);
    }

    @Test
    void testGetJugador() {
        assertEquals("TestPlayer", juego.getJugador().getNombre());
        assertEquals(0, juego.getJugador().getIntentos());
    }

    @Test 
    void testIntentionInicial() {
        assertEquals(0, juego.getJugador().getIntentos());
    }

    @Test
    void testevaluarNumero() {
       int numeroAleatorio = juego.getNumeroAleatorio();
       int numeroMayor = numeroAleatorio + 10;
       int numeroMenor = numeroAleatorio - 10;

       int resultadoMenor = juego.evaluarNumero(numeroMenor);
       int resultadoMayor = juego.evaluarNumero(numeroMayor);
       int resultadoAcertado = juego.evaluarNumero(numeroAleatorio);
       assertEquals(1, resultadoMenor);
       assertEquals(2, resultadoMayor);
       assertEquals(3, resultadoAcertado);
        
    }


    @Test 
    void testesUltimoIntento() {
        juego.getJugador().setIntentos(5);
        assertTrue(juego.esUltimoIntento());
        juego.getJugador().setIntentos(2);
        assertFalse(juego.esUltimoIntento());
        
    }

    @Test 
    void testComprobarIntento(){
        int numeroSolucion = juego.getNumeroAleatorio();
        assertEquals("Es un numero mayor. Te quedan 4 intentos", juego.comprobarIntento(numeroSolucion-1));
        assertEquals("Es un numero menor. Te quedan 3 intentos", juego.comprobarIntento(numeroSolucion+11));
        assertEquals("Enhorabuena, has ganado!!! Lo conseguiste en " + (juego.getJugador().getIntentos() + 1 ) + " intentos. Tu puntuación es 60 puntos.", juego.comprobarIntento(numeroSolucion));
    }
    @Test 
    void testComprobarIntentoUltimoIntento(){
        int numeroSolucion = juego.getNumeroAleatorio();
        juego.getJugador().setIntentos(4);
        assertEquals("Era un numero mayor. Agotaste los intentos. ", juego.comprobarIntento(numeroSolucion-1));
        juego.getJugador().setIntentos(4);
        assertEquals("Era un numero menor. Agotaste los intentos", juego.comprobarIntento(numeroSolucion+1));
        juego.getJugador().setIntentos(4);
        assertEquals("Enhorabuena, has ganado!!! Lo conseguiste en " + (juego.getJugador().getIntentos() + 1 ) + " intentos. Tu puntuación es 20 puntos.", juego.comprobarIntento(numeroSolucion));
    }
    @Test
    void testContarIntentos(){
        juego.comprobarIntento(1);
        juego.comprobarIntento(2);
        juego.comprobarIntento(3);
        juego.comprobarIntento(4);
        assertTrue(4 == juego.getJugador().getIntentos());
    }
    @Test
    void testsumarIntento(){
        assertTrue(juego.getJugador().getIntentos()==0);
        juego.sumarIntento();
        juego.sumarIntento();
        juego.sumarIntento();
        assertEquals(3, juego.getJugador().getIntentos());
    }
    @Test
    void TestsetIntentos(){
        assertEquals(0,juego.getJugador().getIntentos());
        juego.getJugador().setIntentos(6);
        assertEquals(6,juego.getJugador().getIntentos());
    }

    @Test 
    void testEsUltimoIntento() {
        Jugador jugador = juego.getJugador();
        jugador.setIntentos(5);
        assertEquals(juego.esUltimoIntento(), true);
    }

    @Test 
    void testNoEsUltimoIntento() {
        Jugador jugador = juego.getJugador();
        jugador.setIntentos(4);
        assertEquals(juego.esUltimoIntento(), false);
    }

    @Test
    void testSetIntentosMax() {
        int intentos = 10;
        juego.setIntentosMax(intentos);
        assertEquals(intentos, juego.getIntentosMax());
    }

    @Test
    void testGetNumeroSuperior() {
        int numeroSuperior = 100;
        juego.setNumeroSuperior(numeroSuperior);
        assertEquals(numeroSuperior, juego.getNumeroSuperior());
    }

    @Test
    void testDarPuntuacion_50Porcentajes() {
        juego.setIntentosMax(10); //5 intentos con exito de 10
        double resultado = juego.darPuntuacion(5);
        assertEquals(60.0, resultado, 0.01, "El porcentaje debe ser 50.0%");
        assertEquals(60, juego.getJugador().getPuntuacion(), "La puntuacion de los jugadores debe ser del 60");
    }

    @Test
    void testDarPuntuacion_100Porcentajes() {
        juego.setIntentosMax(10); 
        double resultado = juego.darPuntuacion(9); //10 intentos con exito de 10
        assertEquals(100.0, resultado, 0.01, "El porcentaje debe ser 100.0%");
        assertEquals(100, juego.getJugador().getPuntuacion(), "La puntuacion de los jugadores debe ser del 100");
    }

    @Test
    void testDarPuntuacion_0Porcentajes() {
        juego.setIntentosMax(10); 
        double resultado = juego.darPuntuacion(0); //0 intentos con exito de 10
        assertEquals(10.0, resultado, 0.01, "El porcentaje debe ser 10.0%");
        assertEquals(10, juego.getJugador().getPuntuacion(), "La puntuacion de los jugadores debe ser del 0");
    }

    @Test
    void testDarPuntuacion_DatosIncorrectos() {
        juego.setIntentosMax(10); 
        assertThrows(IllegalArgumentException.class, () -> juego.darPuntuacion(12), "Deberia lanzar una excepcion si los intentos exitosos exceden el intentosNax");
    }

}
