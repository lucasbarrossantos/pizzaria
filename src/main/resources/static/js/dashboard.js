var Pizzaria = Pizzaria || {};

Pizzaria.GraficoPorMes = (function () {

    function GraficoPorMes() {
        this.ctx = $('#graficoPorMes')[0].getContext('2d');
    }

    GraficoPorMes.prototype.iniciar = function () {
        var graficoPorMes = new Chart(this.ctx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Jun'],
                datasets: [{
                    label: 'Vendas por mês',
                    backgroundColor:"rgba(26, 179, 148, 0.5)",
                    pointBorderColor: "rgba(26, 179, 148, 1)",
                    pointBackgroundColor: "#fff",
                    data: [10, 5, 7, 2, 9]
                }]
            }
        });
    };

    return GraficoPorMes;

}());

$(function () {

    var graficoPorMes = new Pizzaria.GraficoPorMes();
    graficoPorMes.iniciar();

});