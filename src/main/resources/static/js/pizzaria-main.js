var Pizzaria = Pizzaria || {};


Pizzaria.formatarMoeda = function (valor) {
    numeral.locale('pt-br');
    return numeral(valor).format('0,0.00');
};

Pizzaria.Seguranca = (function () {

    function Seguranca() {
        this.token = $('input[name=_csrf]').val();
        this.header = $('input[name=_scrf_header]').val();
    }

    Seguranca.prototype.enable = function () {
        $(document).ajaxSend(function (event, jqxhr, settings) {
            jqxhr.setRequestHeader(this.header, this.token);
        }.bind(this));
    };

    return Seguranca;

}());

$(function () {
    var seguranca = new Pizzaria.Seguranca();
    seguranca.enable();
});