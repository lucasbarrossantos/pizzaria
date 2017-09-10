var Pizzaria = Pizzaria || {};

Pizzaria.GraficoPorMes = (function () {

    function GraficoPorMes() {
        this.ctx = $('#graficoPorMes')[0].getContext('2d');
    }

    GraficoPorMes.prototype.iniciar = function () {
        $.ajax({
            url: 'pedidos/totalPorMes',
            method: 'GET',
            success: onDadosRenderizados.bind(this)
        })
    };

    function onDadosRenderizados(data) {
        var meses = [];
        var valores = [];
        var total = 0;

        data.forEach(function (pedido) {
            meses.unshift(pedido.mes); // inseri no início
            valores.unshift(pedido.valor);
            total = total + pedido.valor;
        });

        var options = {
            animation: false,
            scaleLabel:
                function (label) {
                    return '$' + label.value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                },
            responsive: true,
            scales: {
                yAxes: [{
                    ticks: {
                        callback: function (value) {
                            return numeral(value).format('$ 0,0')
                        }
                    }
                }]
            },
            title: {
                display: true,
                text: 'Últimos seis meses'
            }
        };

        var graficoPorMes = new Chart(this.ctx, {
            type: 'line',
            data: {
                labels: meses,
                datasets: [{
                    label: 'R$ ' + Pizzaria.formatarMoeda(total),
                    backgroundColor: "rgba(26, 179, 148, 0.5)",
                    pointBorderColor: "rgba(26, 179, 148, 1)",
                    pointBackgroundColor: "#fff",
                    data: valores
                }]
            },
            options: options
        });
    }

    return GraficoPorMes;

}());

$(function () {

    var graficoPorMes = new Pizzaria.GraficoPorMes();
    graficoPorMes.iniciar();

});