Pizzaria.TabelaItens = (function () {

    function TabelaItens(autocomplete) {
        this.autocomplete = autocomplete;
        this.tabelaProdutosContainer = $('.js-tabela-produtos-container');
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

        response.done(onItemAtualizadoNoServidor.bind(this))
    }

    function onItemAtualizadoNoServidor(html) {
        this.tabelaProdutosContainer.html(html);
        $('.js-tabela-produto-quantidade-item').on('change', onQuantidadeItemProdutoAlterada.bind(this));
        $('.js-tabela-itens-produtos').on('dblclick', onDoubleClick);
        $('.js-exluir-item-produto-btn').on('click', onExcluirItemProdutoClick.bind(this));
    }

    function onQuantidadeItemProdutoAlterada(evento) {
        var input = $(evento.target);
        var quantidade = input.val();
        var codigoProduto = input.data('codigo-produto');

        console.log('Quantidade', quantidade);

        var response = $.ajax({
            url: 'itemProduto/' + codigoProduto,
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
    
    function onExcluirItemProdutoClick(evento) {
        var codigoProduto = $(evento.target).data('codigo-produto');

        var response = $.ajax({
            url: 'itemProduto/' + codigoProduto,
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