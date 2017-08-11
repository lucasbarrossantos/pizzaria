$(function () {
    //Textare auto growth
    //autosize($('textarea.auto-growth'));

    $('#dataDoPagamento').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        weekStart: 0,
        time: false,
        lang: 'br',
        cancelText : 'Cancelar'
    });
    $('#dataDeValidade').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        weekStart: 0,
        time: false,
        lang: 'br',
        cancelText : 'Cancelar'
    });


    $('#dataDeNascimento').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        weekStart: 0,
        time: false,
        lang: 'br',
        cancelText : 'Cancelar'
    });

});