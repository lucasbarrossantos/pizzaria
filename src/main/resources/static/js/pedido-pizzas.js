Pizzaria.PedidoPizza = (function () {

    function PedidoPizza(tabelaItens) {
        this.tabelaItens = tabelaItens;
    }

    PedidoPizza.prototype.iniciar = function () {
        this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensPizzasAtualizada.bind(this))
    };

    function onTabelaItensPizzasAtualizada(evento, valorTotalItensPizzas) {
        console.log('valor pizzas', valorTotalItensPizzas);
    }

    return PedidoPizza;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoCompletePizza();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItensPizzas(autocomplete);
    tabelaItens.iniciar();

    var pedidoPizza = new Pizzaria.PedidoPizza(tabelaItens);
    pedidoPizza.iniciar();

});