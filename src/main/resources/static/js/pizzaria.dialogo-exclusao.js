Pizzaria = Pizzaria || {};

Pizzaria.DialogoExclusao = (function () {

    function DialogoExclusao() {
        this.exclusaoBtn = $('.js-exclusao-btn');
    }

    DialogoExclusao.prototype.iniciar = function () {
        this.exclusaoBtn.on('click', onExcluirClicado.bind(this));

        if (window.location.search.indexOf('excluido') > -1){
            swal('Pronto!', 'Excluído com sucesso.', 'success');
        }
    };

    function onExcluirClicado(evento) {
        evento.preventDefault();
        var botaoClicado = $(evento.currentTarget);
        var url = botaoClicado.data('url');
        var objeto = botaoClicado.data('objeto');

        swal({
            title: 'Tem certeza',
            type: "warning",
            text: 'Excluir "' + objeto + '" ? Você não poderá recuperar depois',
            showCancelButton: true,
            confirmButtonColor: '#DD5B55',
            confirmButtonText: 'Sim, excluir agora',
            closeOnConfirm: true,
            showLoaderOnConfirm: true
        }, onExcluirConfirmado.bind(this, url));
    }

    function onExcluirConfirmado(url) {
        $.ajax({
            url: url,
            method: 'DELETE',
            success: onExcluidoComSucesso.bind(this),
            error: onErroAoExcluir.bind(this)
        });
    }

    function onExcluidoComSucesso() {
        var urlAtual = window.location.href;
        var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
        var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';

        window.location = novaUrl;
    }

    function onErroAoExcluir(e) {
        console.log('erro', e.responseText);
        swal('Algo deu errado :(', e.responseText, 'error');
    }
    
    return DialogoExclusao;

}());

$(function () {

    var dialogoExclusao = new Pizzaria.DialogoExclusao();
    dialogoExclusao.iniciar();

});