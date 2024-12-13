$(document).ready(function () {
    let nombre = '';
    let intentosMaximos = 0;

  
    $('#Iniciar').click(function () {
        nombre = $('#Nombre').val();
        intentosMaximos = parseInt($('#Intentos').val(), 10);

        $.ajax({
            url: '',
            type: 'POST',
            data: ({ nombre: nombre, intentosMaximos: intentosMaximos }),
            success: function (Iniciar) {
                $('#Resultado').removeClass('alert-danger').addClass('alert-info').text(Iniciar.mensaje);
            },
            error: function (error) {
               
            }
        });
    });

    
    $('#Lanzar').click(function () {
        const numero = parseInt($('#Numero').val(), 10);

        if (!numero && numero !== 0) {
            
            return;
        }

        $.ajax({
            url: '',
            type: 'POST',            
            data:({ numero: numero }),
            success: function (Respuesta) {
                $('#Resultado').removeClass('alert-danger').addClass('alert-info').text(Respuesta.mensaje);

                
            },
            error: function (error) {
               
            }
        });
    });

   
    $('#Reiniciar').click(function () {
        $.ajax({
            url: '',
            type: 'POST',
            success: function (reiniciar) {
                $('#Resultado').removeClass('alert-danger').addClass('alert-info').text(reiniciar.mensaje);
                $('#Lanzar').prop('disabled', false);
                $('#Numero').val('');
            },
            error: function (error) {
               
            }
        });
    });
});

