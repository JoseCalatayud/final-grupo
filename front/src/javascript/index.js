const apiUrl = "http://localhost:8080"; // Asegúrate de que la URL sea correcta

let jugadores = [];
let turnoActual = 0;

// Función para agregar un jugador
function agregarJugador() {
    const nombre = document.getElementById("nombreJugador").value;
    if (!nombre) {
        alert("Por favor, ingresa un nombre.");
        return;
    }

    // Agregar el nombre a la lista de jugadores
    jugadores.push(nombre);
    actualizarListaJugadores();

    // Enviar solicitud para iniciar el juego si es el primer jugador
    if (jugadores.length === 1) {
        iniciarJuego(nombre);
    }

    document.getElementById("nombreJugador").value = ''; // Limpiar campo de entrada
}

// Actualizar la lista de jugadores en la pantalla
function actualizarListaJugadores() {
    const lista = document.getElementById("jugadoresLista");
    lista.innerHTML = '';
    jugadores.forEach(jugador => {
        const li = document.createElement("li");
        li.textContent = jugador;
        lista.appendChild(li);
    });
}

// Función para iniciar el juego
function iniciarJuego(nombre) {
    fetch(`${apiUrl}/iniciar`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ nombre: nombre }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            document.getElementById("turnoJugador").innerText = nombre; // Mostrar turno actual
        })
        .catch(error => console.error("Error al iniciar el juego:", error));
}

// Función para lanzar un intento
function lanzarIntento() {
    const numero = document.getElementById("numeroIntento").value;
    if (!numero || isNaN(numero)) {
        alert("Por favor, ingresa un número válido.");
        return;
    }

    fetch(`${apiUrl}/intento`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ numero: numero }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById("resultado").innerText = data;
            actualizarTurno();
        })
        .catch(error => console.error("Error al realizar el intento:", error));
}

// Función para actualizar el turno actual
function actualizarTurno() {
    turnoActual = (turnoActual + 1) % jugadores.length; // Alternar entre los jugadores
    document.getElementById("turnoJugador").innerText = jugadores[turnoActual];
}
