document.addEventListener('DOMContentLoaded', function() {

    let todasMovimentacoes = [];
    const tbody = document.getElementById('tabelaMovimentacoes');

    const filtroEntrada = document.getElementById('filtroEntrada');
    const filtroSaida = document.getElementById('filtroSaida');
    const filtroAjuste = document.getElementById('filtroAjuste');
    const filtroId = document.getElementById('filtroId');
    const filtroNome = document.getElementById('filtroNome');
    const filtroFornecedor = document.getElementById('filtroFornecedor');
    const btnLimpar = document.getElementById('btnLimpar');

    fetch('/api/movimentacoes')
        .then(response => response.json())
        .then(data => {
            todasMovimentacoes = data;
            renderizarTabela(todasMovimentacoes);
        })
        .catch(error => {
            console.error("Erro na API:", error);
            tbody.innerHTML = '<tr><td colspan="6" style="text-align:center; padding: 2rem; color:#c62828;">Erro ao carregar os dados.</td></tr>';
        });

    function renderizarTabela(lista) {
        tbody.innerHTML = '';

        if (lista.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6" style="text-align:center; padding: 2rem; color:#666;">Nenhuma movimentação encontrada.</td></tr>';
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

    function aplicarFiltros() {
        const termoId = filtroId.value.trim();
        const termoNome = filtroNome.value.trim().toLowerCase();
        const termoFornecedor = filtroFornecedor.value.trim().toLowerCase();

        const querEntrada = filtroEntrada.checked;
        const querSaida = filtroSaida.checked;
        const querAjuste = filtroAjuste.checked;

        const filtrarPorTipo = querEntrada || querSaida || querAjuste;

        const listaFiltrada = todasMovimentacoes.filter(mov => {

            let passaTipo = true;
            if (filtrarPorTipo) {
                passaTipo = (querEntrada && mov.tipoMovimentacao === 'ENTRADA') ||
                            (querSaida && mov.tipoMovimentacao === 'SAIDA') ||
                            (querAjuste && mov.tipoMovimentacao === 'AJUSTE');
            }

            let passaId = true;
            if (termoId !== '') {
                passaId = (mov.id.toString() === termoId);
            }

            let passaNome = true;
            if (termoNome !== '') {
                const nomeProd = mov.produto ? mov.produto.nome.toLowerCase() : "";
                passaNome = nomeProd.includes(termoNome);
            }

            let passaFornecedor = true;
            if (termoFornecedor !== '') {
                const nomeForn = (mov.produto && mov.produto.fornecedor) ? mov.produto.fornecedor.razaoSocial.toLowerCase() : "";
                passaFornecedor = nomeForn.includes(termoFornecedor);
            }

            return passaTipo && passaId && passaNome && passaFornecedor;
        });

        renderizarTabela(listaFiltrada);
    }

    filtroEntrada.addEventListener('change', aplicarFiltros);
    filtroSaida.addEventListener('change', aplicarFiltros);
    filtroAjuste.addEventListener('change', aplicarFiltros);

    filtroId.addEventListener('input', aplicarFiltros);
    filtroNome.addEventListener('input', aplicarFiltros);
    filtroFornecedor.addEventListener('input', aplicarFiltros);

    btnLimpar.addEventListener('click', function() {
        filtroEntrada.checked = false;
        filtroSaida.checked = false;
        filtroAjuste.checked = false;

        filtroId.value = '';
        filtroNome.value = '';
        filtroFornecedor.value = '';

        renderizarTabela(todasMovimentacoes);
    });
});