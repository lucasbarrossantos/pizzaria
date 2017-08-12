var Pizzaria = Pizzaria || {};

Pizzaria.AutoComplete = (function () {

    function AutoComplete() {
        this.skuOuNomeInput = $('.js-sku-nome-produto-input');
        var htmlTemplateAutoComplete = $('#template-autocomplete-produto').html();
        this.template = Handlebars.compile(htmlTemplateAutoComplete);

        // Emitir eventos
        this.emitter = $({});
        this.on = this.emitter.on.bind(this.emitter);
    }

    AutoComplete.prototype.iniciar = function () {
        var opcoes = {
            url: function (skuOuNome) {
                return this.skuOuNomeInput.data('url') + '?skuOuNome=' + skuOuNome
            }.bind(this),
            getValue: 'descricao',
            minCharNumber: 3,
            requestDelay: 500,
            ajaxSettings: {
                contentType: 'application/json'
            },
            template: {
                type: 'custom',
                method: template.bind(this)
            },
            list: {
                onChooseEvent: onItemSelecionado.bind(this)
            }
        };        
        this.skuOuNomeInput.easyAutocomplete(opcoes);
    };
    
    function onItemSelecionado() {
        //console.log('selecionou um item!')
        /**
         * item-selecionado: Nome do evento
         */
        this.emitter.trigger('item-selecionado', this.skuOuNomeInput.getSelectedItemData());
        this.skuOuNomeInput.val('');
        this.skuOuNomeInput.focus();
        //console.log('Selecionou o item:', this.skuOuNomeInput.getSelectedItemData());
    }
    
    function template(descricao, produto) {
        //console.log(arguments);
        produto.valorFormatado = Pizzaria.formatarMoeda(produto.valorUnitario);
        return this.template(produto);
    }
    
    return AutoComplete;

}());