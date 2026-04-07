document.addEventListener('DOMContentLoaded', function() {

    let todasMovimentacoes = [];
    const tbody = document.getElementById('tabelaMovimentacoes');

    fetch('/api/movimentacoes')
        .then(response => response.json())
        .then(data => {
            todasMovimentacoes = data;
            renderizarTabela(todasMovimentacoes);
        })
        .catch(error => {
            console.error("Erro na API de Movimentações:", error);
            tbody.innerHTML = '<tr><td colspan="6" style="text-align:center; padding: 2rem; color:red;">Erro ao carregar os dados.</td></tr>';
        });

    function renderizarTabela(lista) {
        tbody.innerHTML = '';

        if (lista.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6" style="text-align:center; padding: 2rem; color:#666;">Nenhuma movimentação registrada.</td></tr>';
            return;
        }

        lista.forEach(mov => {
            const nomeProduto = mov.produto ? mov.produto.nome : "Produto Indisponível";
            const nomeFornecedor = (mov.produto && mov.produto.fornecedor) ? mov.produto.fornecedor.razaoSocial : "Não Cadastrado";

            let corTipo = '#333';
            if(mov.tipoMovimentacao === 'ENTRADA') corTipo = '#2e7d32';
            if(mov.tipoMovimentacao === 'SAIDA') corTipo = '#c62828';
            if(mov.tipoMovimentacao === 'AJUSTE') corTipo = '#f57f17';

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${mov.id}</td>
                <td style="font-weight: 500; color: ${corTipo};">${mov.tipoMovimentacao}</td>
                <td>${nomeProduto}</td>
                <td>${nomeFornecedor}</td>
                <td>${mov.quantidade}</td>
                <td class="actions">
                    <a href="/visualizar-movimentacao?id=${mov.id}" class="btn-action btn-view" title="Visualizar Detalhes">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                    </a>
                </td>
            `;
            tbody.appendChild(tr);
        });
    }
});