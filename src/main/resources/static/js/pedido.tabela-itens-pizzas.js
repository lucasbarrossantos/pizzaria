Pizzaria.TabelaItensPizzas = (function () {

    function TabelaItensPizzas(autocomplete) {
        this.autocomplete = autocomplete;
        this.tabelaProdutosContainer = $('.js-tabela-pizzas-container');
    }

    TabelaItensPizzas.prototype.iniciar = function () {
        this.autocomplete.on('pizza-selecionada', onItemSelecionado.bind(this));
    };

    function onItemSelecionado(evento, item) {
        var response = $.ajax({
            url: 'itemPizza',
            method: 'POST',
            data: {
                codigoPizza: item.id
            }
        });

        response.done(onItemAdicionadoNoServidor.bind(this))
    }
    
    function onItemAdicionadoNoServidor(html) {
        this.tabelaProdutosContainer.html(html);
    }

    return TabelaItensPizzas;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoCompletePizza();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItensPizzas(autocomplete);
    tabelaItens.iniciar();

});