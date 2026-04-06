document.addEventListener('DOMContentLoaded', function() {

    const dataInput = document.getElementById('data-cadastro');
    const hoje = new Date();
    const ano = hoje.getFullYear();
    const mes = String(hoje.getMonth() + 1).padStart(2, '0');
    const dia = String(hoje.getDate()).padStart(2, '0');
    dataInput.value = `${ano}-${mes}-${dia}`;

    const selectTipo = document.getElementById('tipo-movimentacao');
    const inputValor = document.getElementById('valor-unitario');

    selectTipo.addEventListener('change', function() {
        const tipo = this.value;

        const custo = parseFloat(this.getAttribute('data-custo') || 0);
        const venda = parseFloat(this.getAttribute('data-venda') || 0);

        if (tipo === 'ENTRADA') {
            inputValor.value = custo.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
        } else if (tipo === 'SAIDA') {
            inputValor.value = venda.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
        } else {
            inputValor.value = '0,00';
        }
    });
});