var Pizzaria = Pizzaria || {};


Pizzaria.formatarMoeda = function (valor) {
    numeral.locale('pt-br');
    console.log('Vai nao', numeral('4.50').format('0,0.00'));
    return numeral(valor).format('0,0.00');
};

$(function () {

});