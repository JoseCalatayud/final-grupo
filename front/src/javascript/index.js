const apiUrl = "http://localhost:8080"; // Asegúrate de que la URL sea correcta

// Función para iniciar el juego
function IniciarJuego() {
    const nombre = document.getElementById("tarea").value;
    if (!nombre) {
        alert("Por favor, ingresa tu nombre.");
        return;
    }

    fetch(`${apiUrl}/iniciar`, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ nombre: nombre }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.statusText}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById("Frases").innerText = data;
        })
        .catch(error => console.error("Error al iniciar el juego:", error));
}

// Función para lanzar un intento
function LanzarIntento() {
    const numero = document.getElementById("titulo").value;
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
            document.getElementById("textoRespuestas").innerText = data;
        })
        .catch(error => console.error("Error al realizar el intento:", error));
}

// Función para detener el juego (ejemplo básico)
function DetenerJuego() {
    // Aquí iría la lógica para detener el juego
    alert("Juego detenido.");
}
