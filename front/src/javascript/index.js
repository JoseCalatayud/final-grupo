$(document).ready(function () {
    let nombre = '';
    let intentosMaximos = 0;
    let juegoId = 1;  
    const serverUrl = "https://my-json-server.typicode.com/FantasmaMacg/dato/juegos";
    let numeroAdivinar = 0;
  
    $('#Iniciar').click(function () {
        nombre = $('#Nombre').val();
        intentosMaximos = parseInt($('#Intentos').val(), 10);

     
        $.get(serverUrl, function (data) {
            const juego = data.juegos.find(juego => juego.id === juegoId);
            if (juego) {
                numeroAdivinar = juego.numeroAdivinar;
                $('#Resultado')
                    .removeClass('alert-danger')
                    .addClass('alert-info')
                    .text(`Juego iniciado, intenta adivinar el número entre 0 y 10. Tienes ${juego.intentosRestantes} intentos.`);
            }
        }).fail(function () {
            $('#Resultado')
                .removeClass('alert-info')
                .addClass('alert-danger')
                .text('Error al cargar el juego.');
        });
    });

   
    $('#Lanzar').click(function () {
        const numero = parseInt($('#Numero').val(), 10);

        if (!numero && numero !== 0) {
            $('#Resultado')
                .removeClass('alert-info')
                .addClass('alert-danger')
                .text('Por favor, ingresa un número válido.');
            return;
        }

        $.get(`${serverUrl}/1`, function (data) {
            const juego = data.juegos.find(juego => juego.id === juegoId);
            if (juego) {
                let respuesta = '';
                if (numero === numeroAdivinar) {
                    respuesta = '¡Felicidades! Has adivinado el número.';
                    $('#Resultado').removeClass('alert-danger').addClass('alert-info');
                } else if (numero < numeroAdivinar) {
                    respuesta = 'El número es mayor.';
                } else if (numero > numeroAdivinar) {
                    respuesta = 'El número es menor.';
                }

                $('#Resultado').text(respuesta);

                // Actualizar intentos
                juego.intentosRestantes--;
                if (juego.intentosRestantes <= 0) {
                    $('#Resultado').text('Se han agotado los intentos. El juego ha terminado.');
                    $('#Lanzar').prop('disabled', true);
                }
            }
        }).fail(function () {
            $('#Resultado')
                .removeClass('alert-info')
                .addClass('alert-danger')
                .text('Error al procesar el intento.');
        });
    });

  
    $('#Reiniciar').click(function () {
        $.ajax({
            url: `${serverUrl}/1`,
            type: 'DELETE',
            success: function (response) {
                $('#Resultado')
                    .removeClass('alert-danger')
                    .addClass('alert-info')
                    .text('Juego reiniciado');
                $('#Lanzar').prop('disabled', false);
                $('#Numero').val('');
            },
            error: function () {
                $('#Resultado')
                    .removeClass('alert-info')
                    .addClass('alert-danger')
                    .text('Error al reiniciar el juego.');
            }
        });
    });
});

