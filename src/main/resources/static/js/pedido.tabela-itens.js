Pizzaria.TabelaItens = (function () {

    function TabelaItens(autocomplete) {
        this.autocomplete = autocomplete;
        this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
        this.uuid = $('#uuid').val();
    }

    TabelaItens.prototype.iniciar = function () {
        this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
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
        var quantidadeItemInput = $('.js-tabela-produto-quantidade-item');
        quantidadeItemInput.on('change', onQuantidadeItemProdutoAlterada.bind(this));
        quantidadeItemInput.maskMoney({precision: 0, thousands: ''});

        var valorProdutos = $('.js-tabela-itens-produtos').data('valor-total-produtos');
        $('.js-excluir-item-produto-btn').on('click', onExcluirItemProdutoClick.bind(this));

        console.log('valor depois', valorProdutos);

        // Itens
        $('.js-valor-itens-pedido').html(Pizzaria.formatarMoeda(parseFloat(valorProdutos)));
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

    function onDoubleClick(evento) {
        $(this).toggleClass('solicitando-exclusao');
    }

    function onExcluirItemProdutoClick(evento) {
        var codigoProduto = $(evento.target).data('codigo-produto');

        var response = $.ajax({
            url: 'itemProduto/' + this.uuid + '/' + codigoProduto,
            method: 'DELETE'
        });

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    return TabelaItens;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoComplete();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItens(autocomplete);
    tabelaItens.iniciar();

});