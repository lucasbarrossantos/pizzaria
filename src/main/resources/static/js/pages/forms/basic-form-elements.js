$(function () {
    //Textare auto growth
    //autosize($('textarea.auto-growth'));

    $('#dataDeValidade').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        weekStart: 0,
        time: false,
        lang: 'br',
        cancelText : 'Cancelar'
    });

    $('#dataDeEmissao').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        weekStart: 0,
        time: false,
        lang: 'br',
        cancelText : 'Cancelar'
    });

    $('#dataDoPagamento').bootstrapMaterialDatePicker({
        format: 'DD/MM/YYYY',
        weekStart: 0,
        time: false,
        lang: 'br',
        cancelText : 'Cancelar'
    });



});