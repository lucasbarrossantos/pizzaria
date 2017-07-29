$(function () {


    //Masked Input ============================================================================================================================
    var $demoMaskedInput = $('.demo-masked-input');

    //Date
    $demoMaskedInput.find('.date').inputmask('dd/mm/yyyy', {placeholder: '__/__/____'});

    //Time
    $demoMaskedInput.find('.time12').inputmask('hh:mm t', {placeholder: '__:__ _m', alias: 'time12', hourFormat: '12'});
    $demoMaskedInput.find('.time24').inputmask('hh:mm', {placeholder: '__:__ _m', alias: 'time24', hourFormat: '24'});

    //Date Time
    $demoMaskedInput.find('.datetime').inputmask('d/m/y h:s', {
        placeholder: '__/__/____ __:__',
        alias: "datetime",
        hourFormat: '24'
    });

    //Mobile Phone Number
    $demoMaskedInput.find('.mobile-phone-number').inputmask('+99 (999) 999-99-99', {placeholder: '+__ (___) ___-__-__'});
    //Phone Number
    $demoMaskedInput.find('.phone-number').inputmask('+99 (999) 999-99-99', {placeholder: '+__ (___) ___-__-__'});

    //Dollar Money
    $demoMaskedInput.find('.money-dollar').inputmask('99,99 $', {placeholder: '__,__ $'});
    //Euro Money
    $demoMaskedInput.find('.money-euro').inputmask('99,99 €', {placeholder: '__,__ €'});

    //IP Address
    $demoMaskedInput.find('.ip').inputmask('999.999.999.999', {placeholder: '___.___.___.___'});

    //CNPJ
    $demoMaskedInput.find('.cnpj').inputmask('99.999.999/9999-99', {placeholder: '__.___.___/____-__'});

    //CEP
    $demoMaskedInput.find('.cep-cidade').inputmask('99999-999', {placeholder: '_____-___'});

    //DINHEIRO
    $demoMaskedInput.find('.js-moeda').maskMoney({
        decimal: ',',
        thousands: '.',
        allowZero: true
    });

    //NUMERO
    $demoMaskedInput.find('.js-numero').maskMoney({
        thousands : '.',
        allowZero : false
    });

    //Credit Card
    $demoMaskedInput.find('.credit-card').inputmask('9999 9999 9999 9999', {placeholder: '____ ____ ____ ____'});

    //Email
    $demoMaskedInput.find('.email').inputmask({alias: "email"});

    //Serial Key
    $demoMaskedInput.find('.key').inputmask('****-****-****-****', {placeholder: '____-____-____-____'});
    //===========================================================================================================================================

});