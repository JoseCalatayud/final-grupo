package es.ascender.proyectogrupo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServidorJuego {

    private static Juego juego;

    public static void main(String[] args) throws IOException {
      
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

       
        server.createContext("/iniciar", new IniciarJuegoHandler());
        server.createContext("/intento", new RealizarIntentoHandler());
        server.createContext("/info", new ObtenerInformacionHandler());

        
        server.setExecutor(null); 
        System.out.println("Servidor iniciado en el puerto 8080...");
        server.start();
    }

    
    static class IniciarJuegoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); 

            if ("POST".equals(exchange.getRequestMethod())) {
                Map<String, String> params = parsePostParameters(exchange);
                String nombre = params.get("nombre");

                if (nombre == null || nombre.isEmpty()) {
                    sendResponse(exchange, "El nombre es obligatorio", 400);
                    return;
                }

                juego = new Juego(3, nombre);
                String response = "Juego iniciado para el jugador: " + nombre;
                sendResponse(exchange, response, 200);
            } else {
                sendResponse(exchange, "Método no permitido", 405);
            }
        }
    }

    
    static class RealizarIntentoHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            if ("POST".equals(exchange.getRequestMethod())) {
                if (juego == null) {
                    sendResponse(exchange, "El juego no ha sido iniciado.", 400);
                    return;
                }

                Map<String, String> params = parsePostParameters(exchange);
                try {
                    int numero = Integer.parseInt(params.get("numero"));
                    String resultado = juego.comprobarIntento(numero);
                    sendResponse(exchange, resultado, 200);
                } catch (NumberFormatException e) {
                    sendResponse(exchange, "Número inválido.", 400);
                }
            } else {
                sendResponse(exchange, "Método no permitido", 405);
            }
        }
    }

    
    static class ObtenerInformacionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            if ("GET".equals(exchange.getRequestMethod())) {
                if (juego == null) {
                    sendResponse(exchange, "El juego no ha sido iniciado.", 400);
                    return;
                }

                Jugador jugador = juego.getJugador();
                String response = String.format("{\"nombre\":\"%s\", \"intentos\":%d}",
                        jugador.getNombre(), jugador.getIntentos());

                sendResponse(exchange, response, 200);
            } else {
                sendResponse(exchange, "Método no permitido", 405);
            }
        }
    }

   
    private static Map<String, String> parsePostParameters(HttpExchange exchange) throws IOException {
        String query = new String(exchange.getRequestBody().readAllBytes());
        Map<String, String> result = new HashMap<>();
        for (String pair : query.split("&")) {
            String[] keyValue = pair.split("=");
            result.put(keyValue[0], keyValue[1]);
        }
        return result;
    }

   
    private static void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
