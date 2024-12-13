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

    @Test
    void testComprobarIntentoMayor() {
        int numero = juego.numeroAleatorio +1;
        
        String resultado = juego.comprobarIntento(numero);
        assertEquals("El numero es mayor", resultado);
    }

    @Test 
    void testComprobarIntentoMenor() {
        int numero = juego.numeroAleatorio -1;

        String resultado = juego.comprobarIntento(numero);
        assertEquals("El numero es menor", resultado);
    }

    @Test
    void testIntentoExeededAttempts() {
        for (int i = 0; i<0; i++) {
            juego.comprobarIntento(-1);
        }
        String resultado = juego.comprobarIntento(-1);
        assertEquals("resultado", resultado);
    }

   /// @Test
   // void testEvaluarNumeroAcierto() {
     //   int numero = juego.numeroAleatorio;
//
  //      String resultado = juego.evaluarNumero(numero)
    //}



    



}
