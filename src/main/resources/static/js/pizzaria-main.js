var Pizzaria = Pizzaria || {};


Pizzaria.formatarMoeda = function (valor) {
    numeral.locale('pt-br');
    return numeral(valor).format('0,0.00');
};

$(function () {

});