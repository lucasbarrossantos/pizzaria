Pizzaria.PedidoProduto = (function () {

    function PedidoProduto(tabelaItens) {
        this.tabelaItens = tabelaItens;
    }

    PedidoProduto.prototype.iniciar = function () {
        this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensProdutosAtualizada.bind(this));
    };
    
    function onTabelaItensProdutosAtualizada(evento, valorTotalItensProdutos) {
        console.log('valor produtos', valorTotalItensProdutos);
    }

    return PedidoProduto;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoComplete();
    autocomplete.iniciar();

    var tabelaItens = new Pizzaria.TabelaItens(autocomplete);
    tabelaItens.iniciar();

    var pedidoProdutos = new Pizzaria.PedidoProduto(tabelaItens);
    pedidoProdutos.iniciar();

});