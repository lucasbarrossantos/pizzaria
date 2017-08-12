var Pizzaria = Pizzaria || {};


Pizzaria.formatarMoeda = function (valor) {
    console.log('valor antes de formatar', valor);
    numeral.locale('pt-br');
    return numeral(valor).format('0,0.00');
};

$(function () {

});