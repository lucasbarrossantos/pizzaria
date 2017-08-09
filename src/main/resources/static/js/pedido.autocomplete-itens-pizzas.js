var Pizzaria = Pizzaria || {};

Pizzaria.AutoCompletePizza = (function () {

    function AutoCompletePizza() {
        this.saborOuTamanhoInput = $('.js-sabor-tamanho-input');
        var htmlTemplateAutoCompletePizza = $('#template-autocomplete-pizza').html();
        this.template = Handlebars.compile(htmlTemplateAutoCompletePizza);

        // Emitir eventos
        this.emitter = $({});
        this.on = this.emitter.on.bind(this.emitter);
    }

    AutoCompletePizza.prototype.iniciar = function () {
        console.log('pizza iniciada');
        var opcoes = {
            url: function (saborOuTamanho) {
                return this.saborOuTamanhoInput.data('url') + '?saborOuTamanho=' + saborOuTamanho
            }.bind(this),
            getValue: 'saborPizza',
            minCharNumber: 3,
            requestDelay: 500,
            ajaxSettings: {
                contentType: 'application/json'
            },
            template: {
                type: 'custom',
                method: templatePizza.bind(this)
            },
            list: {
                onChooseEvent: onItemPizzaSelecionada.bind(this)
            }
        };
        this.saborOuTamanhoInput.easyAutocomplete(opcoes);
    };

    function templatePizza(saborPizza, pizza) {
        //console.log(arguments);
        pizza.valorFormatado = Pizzaria.formatarMoeda(pizza.valorUnitario);
        return this.template(pizza);
    }

    function onItemPizzaSelecionada() {
        this.emitter.trigger('pizza-selecionada', this.saborOuTamanhoInput.getSelectedItemData());
        this.saborOuTamanhoInput.val('');
        this.saborOuTamanhoInput.focus();
    }
    
    return AutoCompletePizza;

}());