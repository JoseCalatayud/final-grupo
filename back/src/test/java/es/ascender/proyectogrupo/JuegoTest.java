package es.ascender.proyectogrupo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertEquals("Es un numero mayor. Llevas 1 intentos", juego.comprobarIntento(numeroSolucion-1));
        assertEquals("Es un numero menor. Llevas 2 intentos", juego.comprobarIntento(numeroSolucion+11));
        assertEquals("Enhorabuena, has ganado!!! Lo conseguiste en 3 intentos", juego.comprobarIntento(numeroSolucion));
    }
    @Test 
    void testComprobarIntentoUltimoIntento(){
        int numeroSolucion = juego.getNumeroAleatorio();
        juego.getJugador().setIntentos(4);
        assertEquals("Era un numero mayor. Agotaste los intentos", juego.comprobarIntento(numeroSolucion-1));
        juego.getJugador().setIntentos(4);
        assertEquals("Era un numero menor. Agotaste los intentos", juego.comprobarIntento(numeroSolucion+1));
        juego.getJugador().setIntentos(4);
        assertEquals("Enhorabuena, has ganado!!! Lo conseguiste en "+ juego.getIntentosMax()+ " intentos", juego.comprobarIntento(numeroSolucion));
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
}
