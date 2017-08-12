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
        var quantidadeItensPizzas = $('.js-tabela-pizza-quantidade-item');
        quantidadeItensPizzas.on('change', onQuantidadeItemPizzaAlterada.bind(this));
        quantidadeItensPizzas.maskMoney({ precision: 0, thousands: '' });

        var tabelaItemPizza = $('.js-tabela-itens-pizzas');
        $('.js-excluir-item-pizza-btn').on('click', onExcluirItemPizzaClick.bind(this));

        this.emitter.trigger('tabela-itens-atualizada', tabelaItemPizza.data('valor-total'));
    }

    function onQuantidadeItemPizzaAlterada(evento) {
        var input = $(evento.target);
        var quantidade = input.val();

        if(quantidade <= 0){
            input.val(1);
            quantidade = 1;
        }

        var codigoPizza = input.data('codigo-pizza');

        var response = $.ajax({
            url: 'itemPizza/'+codigoPizza,
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

        var response = $.ajax({
            url: 'itemPizza/' + this.uuid + '/' + codigoPizza,
            method: 'DELETE'
        });

        response.done(onItemAtualizadoNoServidor.bind(this));
    }

    return TabelaItensPizzas;

}());