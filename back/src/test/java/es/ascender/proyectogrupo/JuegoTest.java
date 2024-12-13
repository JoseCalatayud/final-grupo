package es.ascender.proyectogrupo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JuegoTest {
    private Juego juego;
    

    @BeforeEach
    void setUp() {
        juego = new Juego(5,"TestPlayer");
    }

    @Test
    void testGetJugador() {
        assertEquals("TestPlayer", juego.getJugador().getNombre());
    }

    @Test 
    void testIntentionInicial() {
        assertEquals(0, juego.getJugador().getIntentos());
    }

    @Test
    void testComprobarIntentoAcierto() {
       int numeroAleatorio = juego.numeroAleatorio;

       String resultado = juego.comprobarIntento(numeroAleatorio);
       assertEquals("Has acertado", resultado);
        
    }



    



}
