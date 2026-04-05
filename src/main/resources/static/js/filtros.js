document.addEventListener('DOMContentLoaded', function() {
    let todosProdutos = [];

    const tbody = document.getElementById('tabelaProdutos');
    const inputNome = document.getElementById('filtroNome');
    const inputId = document.getElementById('filtroId');
    const selectCategoria = document.getElementById('filtroCategoria');
    const btnLimpar = document.getElementById('btnLimpar');

    fetch('/api/produtos')
        .then(response => response.json())
        .then(data => {
            todosProdutos = data;
            renderizarTabela(todosProdutos);
        })
        .catch(error => {
            console.error("Erro na API:", error);
            tbody.innerHTML = '<tr><td colspan="8" style="text-align:center; padding: 2rem; color:red;">Erro ao carregar os dados.</td></tr>';
        });

    function renderizarTabela(lista) {
        tbody.innerHTML = '';

        if (lista.length === 0) {
            tbody.innerHTML = '<tr><td colspan="8" style="text-align:center; padding: 2rem; color:#666;">Nenhum produto encontrado.</td></tr>';
            return;
        }

        lista.forEach(produto => {
            const precoFormatado = new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(produto.precoVenda);

            const mapStatus = {
                'ATIVO': { classe: 'status-ativo', texto: 'Ativo' },
                'PAUSADO': { classe: 'status-pausado', texto: 'Pausado' },
                'DESCONTINUADO': { classe: 'status-descontinuado', texto: 'Inativo' }
            };
            const statusVisual = mapStatus[produto.status] || { classe: 'status-descontinuado', texto: produto.status };

            let catNome = produto.categoria;
            if(catNome) {
                catNome = catNome.charAt(0) + catNome.slice(1).toLowerCase();
            }

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${produto.id}</td>
                <td>${produto.nome}</td>
                <td>${catNome}</td>
                <td>${produto.fornecedor ? produto.fornecedor.razaoSocial : ''}</td>
                <td>${produto.quantidadeTotalEstoque}</td>
                <td>${precoFormatado}</td>
                <td>
                    <span class="status-badge ${statusVisual.classe}">${statusVisual.texto}</span>
                </td>
                <td class="actions">
                    <a href="/visualizar-produto?id=${produto.id}" class="btn-action btn-view" title="Visualizar Detalhes">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                    </a>
                    <a href="/registra-movimentacao?produtoId=${produto.id}" class="btn-action btn-movi" title="Registrar Movimentação">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2"></path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect><path d="M12 11h4"></path><path d="M12 16h4"></path><path d="M8 11h.01"></path><path d="M8 16h.01"></path></svg>
                    </a>
                </td>
            `;
            tbody.appendChild(tr);
        });
    }

    function aplicarFiltros() {
        const termoNome = inputNome.value.toLowerCase();
        const termoId = inputId.value;
        const termoCategoria = selectCategoria.value;

        const produtosFiltrados = todosProdutos.filter(p => {
            const bateuNome = p.nome.toLowerCase().includes(termoNome);
            const bateuId = termoId === "" || p.id.toString() === termoId;
            const bateuCategoria = termoCategoria === "" || p.categoria === termoCategoria;
            return bateuNome && bateuId && bateuCategoria;
        });

        renderizarTabela(produtosFiltrados);
    }

    inputNome.addEventListener('input', aplicarFiltros);
    inputId.addEventListener('input', aplicarFiltros);
    selectCategoria.addEventListener('change', aplicarFiltros);

    btnLimpar.addEventListener('click', function(e) {
        e.preventDefault();
        inputNome.value = '';
        inputId.value = '';
        selectCategoria.value = '';
        aplicarFiltros();
    });
});