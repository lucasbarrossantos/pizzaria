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

        response.done(onItemAtualizadoNoServidor.bind(this))
    }
    
    function onItemAtualizadoNoServidor(html) {
        this.tabelaProdutosContainer.html(html);
        $('.js-tabela-pizza-quantidade-item').on('change', onQuantidadeItemPizzaAlterada.bind(this));
        $('.js-excluir-item-pizza-btn').on('click', onExcluirItemPizzaClick.bind(this));
    }

    function onQuantidadeItemPizzaAlterada(evento) {
        var input = $(evento.target);
        var quantidade = input.val();
        var codigoPizza = input.data('codigo-pizza');

        var response = $.ajax({
            url: 'itemPizza/'+codigoPizza,
            method: 'PUT',
            data: {
                quantidade: quantidade
            }
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function onDoubleClick(evento) {
        $(this).toggleClass('solicitando-exclusao');
    }

    function onExcluirItemPizzaClick(evento) {
        var codigoPizza = $(evento.target).data('codigo-pizza');

        var response = $.ajax({
            url: 'itemPizza/' + codigoPizza,
            method: 'DELETE'
        });

        response.done(onItemAtualizadoNoServidor.bind(this));
    }

    return TabelaItensPizzas;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoCompletePizza();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItensPizzas(autocomplete);
    tabelaItens.iniciar();

});