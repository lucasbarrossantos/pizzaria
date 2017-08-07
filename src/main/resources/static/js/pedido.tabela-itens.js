Pizzaria.TabelaItens = (function () {

    function TabelaItens(autocomplete) {
        this.autocomplete = autocomplete;
    }

    TabelaItens.prototype.iniciar = function () {
        this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
    };

    function onItemSelecionado(evento, item) {
        var response = $.ajax({
            url: 'item',
            method: 'POST',
            data: {
                codigoProduto: item.id
            }
        });

        response.done(function (data) {
            console.log('retorno', data)
        })
    }

    return TabelaItens;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoComplete();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItens(autocomplete);
    tabelaItens.iniciar();

});