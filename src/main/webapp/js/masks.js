document.addEventListener("DOMContentLoaded", () => {

    // Máscara de CNPJ
    const cnpjInputs = document.querySelectorAll('.mask-cnpj');
    cnpjInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let valor = e.target.value.replace(/\D/g, '');
            if (valor.length > 14) valor = valor.substring(0, 14);
            valor = valor.replace(/^(\d{2})(\d)/, '$1.$2');
            valor = valor.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
            valor = valor.replace(/\.(\d{3})(\d)/, '.$1/$2');
            valor = valor.replace(/(\d{4})(\d)/, '$1-$2');

            e.target.value = valor;
        });
    });

    // Máscara de Telefone
    const telInputs = document.querySelectorAll('.mask-telefone');
    telInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let valor = e.target.value.replace(/\D/g, '');
            if (valor.length > 11) valor = valor.substring(0, 11);
            valor = valor.replace(/^(\d{2})(\d)/g, '($1) $2');
            valor = valor.replace(/(\d)(\d{4})$/, '$1-$2');

            e.target.value = valor;
        });
    });

    // Máscara de Dinheiro
    const moneyInputs = document.querySelectorAll('.mask-dinheiro');
    moneyInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let valor = e.target.value.replace(/\D/g, '');
            if (valor === '') {
                e.target.value = '';
                return;
            }

            valor = (parseInt(valor, 10) / 100).toFixed(2) + '';
            valor = valor.replace('.', ',');
            valor = valor.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');

            e.target.value = valor;
        });
    });
});