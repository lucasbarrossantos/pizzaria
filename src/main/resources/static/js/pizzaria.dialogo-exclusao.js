Pizzaria = Pizzaria || {};

Pizzaria.DialogoExclusao = (function () {

    function DialogoExclusao() {
        this.exclusaoBtn = $('.js-exclusao-btn');
    }

    DialogoExclusao.prototype.iniciar = function () {
        this.exclusaoBtn.on('click', onExcluirClicado.bind(this));

        if (window.location.search.indexOf('excluido') > -1) {
            swal({
                title: "Pronto!",
                text: "Excluído com sucesso.",
                timer: 1000,
                type: "success",
                showConfirmButton: false
            }, function () {
                setTimeout(function () {
                    window.location = document.URL.replace('excluido', '');
                }, 1000)
            })
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
            confirmButtonText: 'Excluir',
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