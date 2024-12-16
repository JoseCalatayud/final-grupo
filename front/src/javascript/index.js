$(document).ready(function () {
    let nombre = '';
    let intentosMaximos = 0;
    const serverUrl = "https://my-json-server.typicode.com/FantasmaMacg/dato/juegos";
    let numeroAdivinar = 0;
    $('#Lanzar').prop('disabled', true);
    $('#Slanzar').hide();
    let RangoDificultad = 10;
    $('#Numero').val('');
    $('#Nombre').val('');
    $('#Intentos').val('');
    $('#Dificultad').val('Dificultad');


   
    $('#Iniciar').click(function () {
        nombre = $('#Nombre').val();
        intentosMaximos = parseInt($('#Intentos').val());
        if (isNaN(intentosMaximos)){
            $('#Resultado1')
                .removeClass('alert-info')
                .addClass('alert-danger')
                .text('Por favor, ingresa un número.');
        }
        else{
            $('#Lanzar').prop('disabled', false);
            $('#Slanzar').show();
            $('#Resultado1').hide();
            RangoDificultad = parseInt($('#Dificultad').val());

        }
        
       
       

     
        let nuevoJuego = {
            nombre: nombre,
            intentosRestantes: intentosMaximos,
            rangoDificultad: RangoDificultad
            
        };

        $.ajax({
            url: serverUrl,
            type: 'POST',
            
            data: (nuevoJuego),
            success: function (response) {
                $('#jugador')
                    
                    .text(`Jugador ${response.nombre}`);
                numeroAdivinar = response.numeroAdivinar;

                $('#Resultado')
                    .removeClass('alert-danger')
                    .addClass('alert-info')
                    .text(`Juego iniciado con éxito. ¡Intenta adivinar el número! Te quedan ${response.intentosRestantes} intentos.`);
                   
            },
            error: function () {
                $('#Resultado')
                    .removeClass('alert-info')
                    .addClass('alert-danger')
                    .text('Error al iniciar el juego.');
            }
        });
    });

   
    $('#Lanzar').click(function () {
        const numero = parseInt($('#Numero').val(), 10);

        if (isNaN(numero)) {
            $('#Resultado')
                .removeClass('alert-info')
                .addClass('alert-danger')
                .text('Por favor, ingresa un número válido.');
            return;
        }

        $.ajax({
            url: `${serverUrl}/1`, 
            type: 'PATCH',
           
            data: JSON.stringify({ numero: numero }),
            success: function (response) {
                let mensaje = '';
                numeroAdivinar = response.numeroAdivinar;

                if (numero === numeroAdivinar) {
                    mensaje = '¡Felicidades! Has adivinado el número.';
                    $('#Resultado').removeClass('alert-danger').addClass('alert-info');
                    $('#Lanzar').prop('disabled', true);
                } else if (numero < numeroAdivinar) {
                    $('#Numero').val('');
                    mensaje = 'El número es mayor.';
                    $('#Resultado').removeClass('alert-info').addClass('alert-warning');
                } else if (numero > numeroAdivinar) {
                    $('#Numero').val('');
                    mensaje = 'El número es menor.';
                    $('#Resultado').removeClass('alert-info').addClass('alert-warning');
                }

                mensaje += ` Te quedan ${response.intentosRestantes} intentos.`;
                $('#Resultado').text(mensaje);

               
                if (response.intentosRestantes <= 0) {
                    $('#Resultado').text('Se han agotado los intentos. El juego ha terminado.');
                    $('#Lanzar').prop('disabled', true);
                }
            },
            error: function () {
                $('#Resultado')
                    .removeClass('alert-info')
                    .addClass('alert-danger')
                    .text('Error al enviar el número.');
            }
        });
    });

 
    $('#Reiniciar').click(function () {
        $.ajax({
            url: `${serverUrl}/1`,
            type: 'DELETE',
            success: function () {
                $('#Resultado')
                    .removeClass('alert-danger')
                    .addClass('alert-info')
                    .text('Juego reiniciado');
                $('#Lanzar').prop('disabled', false);
                $('#Nombre').val('');
                $('#Intentos').val('');
                 $('#Dificultad').val('');
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
