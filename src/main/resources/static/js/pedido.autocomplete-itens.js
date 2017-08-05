var Pizzaria = Pizzaria || {};

Pizzaria.AutoComplete = (function () {

    function AutoComplete() {
        this.skuOuNomeInput = $('.js-sku-nome-produto-input');
        var htmlTemplateAutoComplete = $('#template-autocomplete-produto').html();
        this.template = Handlebars.compile(htmlTemplateAutoComplete);
    }

    AutoComplete.prototype.iniciar = function () {
        var opcoes = {
            url: function (skuOuNome) {
                return '/produtos?skuOuNome=' + skuOuNome
            },
            getValue: 'descricao',
            minCharNumber: 3,
            requestDelay: 500,
            ajaxSettings: {
                contentType: 'application/json'
            },
            template: {
                type: 'custom',
                method: function (descricao, produto) {
                    //console.log(arguments);
                    produto.valorFormatado = Pizzaria.formatarMoeda(produto.valorUnitario);
                    return this.template(produto);
                }.bind(this)
            }
        };        
        this.skuOuNomeInput.easyAutocomplete(opcoes);
    };
    
    return AutoComplete;

}());

$(function () {

    var autocomplete = new Pizzaria.AutoComplete();
    autocomplete.iniciar();

});