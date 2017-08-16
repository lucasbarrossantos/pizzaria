Pizzaria.TabelaItens = (function () {

    function TabelaItens(autocomplete) {
        this.autocomplete = autocomplete;
        this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
        this.uuid = $('#uuid').val();
    }

    TabelaItens.prototype.iniciar = function () {
        this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));

        bindQuantidade.call(this);
        bindTabelaItem.call(this);
        onAtualizarValores();
    };

    function onItemSelecionado(evento, item) {
        var response = $.ajax({
            url: 'item',
            method: 'POST',
            data: {
                codigoProduto: item.id,
                uuid: this.uuid
            }
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function onItemAtualizadoNoServidor(html) {
        this.tabelaProdutosContainer.html(html);

        bindQuantidade.call(this);

        var quantidadeItemInput = $('.js-tabela-produto-quantidade-item');
        quantidadeItemInput.on('change', onQuantidadeItemProdutoAlterada.bind(this));
        quantidadeItemInput.maskMoney({precision: 0, thousands: ''});

        $('.js-excluir-item-produto-btn').on('click', onExcluirItemProdutoClick.bind(this));

        onAtualizarValores();
    }

    function onAtualizarValores() {
        var valorProdutos = $('.js-tabela-itens-produtos').data('valor-total-produtos');
        var valorPizzas = $('.js-tabela-itens-pizzas').data('valor-total');
        // Itens
        $('.js-valor-itens-pedido').html(Pizzaria.formatarMoeda(parseFloat(valorProdutos)));

        var valorPizzasProdutos = 0;
        if (valorProdutos != undefined && valorPizzas != undefined) {
            valorPizzasProdutos = parseFloat(valorProdutos) + parseFloat(valorPizzas);
            $('.js-valor-total-itens').html(Pizzaria.formatarMoeda(parseFloat(valorPizzasProdutos)));
        } else if (valorProdutos == undefined) {
            valorPizzasProdutos = parseFloat(valorPizzas);
            $('.js-valor-total-itens').html(Pizzaria.formatarMoeda(parseFloat(valorPizzasProdutos)));
        } else if (valorProdutos != undefined) {
            valorPizzasProdutos = parseFloat(valorProdutos);
            $('.js-valor-total-itens').html(Pizzaria.formatarMoeda(parseFloat(valorPizzasProdutos)));
        }
    }

    function onQuantidadeItemProdutoAlterada(evento) {
        var input = $(evento.target);
        var quantidade = input.val();

        if (quantidade <= 0) {
            input.val(1);
            quantidade = 1;
        }

        var codigoProduto = input.data('codigo-produto');

        var response = $.ajax({
            url: 'itemProduto/' + codigoProduto,
            method: 'PUT',
            data: {
                quantidade: quantidade,
                uuid: this.uuid
            }
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function onExcluirItemProdutoClick(evento) {
        var codigoProduto = $(evento.target).data('codigo-produto');
        console.log('excluir clicado');

        var response = $.ajax({
            url: 'itemProduto/' + this.uuid + '/' + codigoProduto,
            method: 'DELETE'
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function bindTabelaItem() {
        var tabelaItem = $('.js-tabela-itens-produtos');
        $('.js-excluir-item-produto-btn').on('click', onExcluirItemProdutoClick.bind(this));
        return tabelaItem;
    }

    function bindQuantidade() {
        var quantidadeItemInput = $('.js-tabela-produto-quantidade-item');
        quantidadeItemInput.on('change', onQuantidadeItemProdutoAlterada.bind(this));
        quantidadeItemInput.maskMoney({precision: 0, thousands: ''});
    }

    return TabelaItens;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoComplete();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItens(autocomplete);
    tabelaItens.iniciar();

});