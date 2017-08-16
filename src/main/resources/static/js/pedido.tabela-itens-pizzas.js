Pizzaria.TabelaItensPizzas = (function () {

    function TabelaItensPizzas(autocomplete) {
        this.autocomplete = autocomplete;
        this.tabelaProdutosContainer = $('.js-tabela-pizzas-container');
        this.uuid = $('#uuid').val();
        // Emitir eventos
        this.emitter = $({});
        this.on = this.emitter.on.bind(this.emitter);
    }

    TabelaItensPizzas.prototype.iniciar = function () {
        this.autocomplete.on('pizza-selecionada', onItemSelecionado.bind(this));

        bindQuantidade.call(this);
        bindTabelaItem.call(this);
        onAtualizarValores();
    };

    function onItemSelecionado(evento, item) {
        var response = $.ajax({
            url: 'itemPizza',
            method: 'POST',
            data: {
                codigoPizza: item.id,
                uuid: this.uuid
            }
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function onItemAtualizadoNoServidor(html) {
        this.tabelaProdutosContainer.html(html);

        bindQuantidade.call(this);

        var quantidadeItensPizzas = $('.js-tabela-pizza-quantidade-item');
        quantidadeItensPizzas.on('change', onQuantidadeItemPizzaAlterada.bind(this));
        quantidadeItensPizzas.maskMoney({precision: 0, thousands: ''});

        $('.js-excluir-item-pizza-btn').on('click', onExcluirItemPizzaClick.bind(this));

        onAtualizarValores();
    }

    function onAtualizarValores() {
        var valorProdutos = $('.js-tabela-itens-produtos').data('valor-total-produtos');
        var valorPizzas = $('.js-tabela-itens-pizzas').data('valor-total');
        $('.js-valor-itens-pizzas').html(Pizzaria.formatarMoeda(parseFloat(valorPizzas)));

        var valorPizzasProdutos = 0;
        if (valorProdutos != undefined && valorPizzas != undefined) {
            valorPizzasProdutos = parseFloat(valorProdutos) + parseFloat(valorPizzas);
            $('.js-valor-total-itens').html(Pizzaria.formatarMoeda(parseFloat(valorPizzasProdutos)));
        } else if (valorPizzas == undefined) {
            valorPizzasProdutos = parseFloat(valorProdutos);
            $('.js-valor-total-itens').html(Pizzaria.formatarMoeda(parseFloat(valorPizzasProdutos)));
        } else if (valorPizzas != undefined) {
            valorPizzasProdutos = parseFloat(valorPizzas);
            $('.js-valor-total-itens').html(Pizzaria.formatarMoeda(parseFloat(valorPizzasProdutos)));
        }
    }

    function onQuantidadeItemPizzaAlterada(evento) {
        var input = $(evento.target);
        var quantidade = input.val();

        if (quantidade <= 0) {
            input.val(1);
            quantidade = 1;
        }

        var codigoPizza = input.data('codigo-pizza');

        var response = $.ajax({
            url: 'itemPizza/' + codigoPizza,
            method: 'PUT',
            data: {
                quantidade: quantidade,
                uuid: this.uuid
            }
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function onExcluirItemPizzaClick(evento) {
        var codigoPizza = $(evento.target).data('codigo-pizza');
        console.log('excluir pizza clicado');

        var response = $.ajax({
            url: 'itemPizza/' + this.uuid + '/' + codigoPizza,
            method: 'DELETE'
        });

        response.done(onItemAtualizadoNoServidor.bind(this));
    }

    function bindTabelaItem() {
        var tabelaItem = $('.js-tabela-itens-pizzas');
        $('.js-excluir-item-pizza-btn').on('click', onExcluirItemPizzaClick.bind(this));
        return tabelaItem;
    }

    function bindQuantidade() {
        var quantidadeItemInput = $('.js-tabela-pizza-quantidade-item');
        quantidadeItemInput.on('change', onQuantidadeItemPizzaAlterada.bind(this));
        quantidadeItemInput.maskMoney({ precision: 0, thousands: '' });
    }

    return TabelaItensPizzas;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoCompletePizza();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItensPizzas(autocomplete);
    tabelaItens.iniciar();

});