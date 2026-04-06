document.addEventListener('DOMContentLoaded', function() {
    let todosFornecedores = [];

    const tbody = document.getElementById('tabelaFornecedores');
    const inputId = document.getElementById('filtroId');
    const inputNome = document.getElementById('filtroNome');
    const inputTelefone = document.getElementById('filtroTelefone');
    const btnLimpar = document.getElementById('btnLimpar');

    fetch('/api/fornecedores')
        .then(response => response.json())
        .then(data => {
            todosFornecedores = data;
            renderizarTabela(todosFornecedores);
        })
        .catch(error => {
            console.error("Erro na API:", error);
            tbody.innerHTML = '<tr><td colspan="7" style="text-align:center; padding: 2rem; color:red;">Erro ao carregar os dados.</td></tr>';
        });

    function formatarCNPJ(cnpj) {
        if (!cnpj) return "";
        cnpj = cnpj.replace(/\D/g, "");
        if (cnpj.length === 14) {
            return cnpj.replace(/^(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})$/, "$1.$2.$3/$4-$5");
        }
        return cnpj;
    }

    function formatarTelefone(tel) {
        if (!tel) return "";
        tel = tel.replace(/\D/g, "");
        if (tel.length === 11) {
            return tel.replace(/^(\d{2})(\d{5})(\d{4})$/, "($1) $2-$3");
        } else if (tel.length === 10) {
            return tel.replace(/^(\d{2})(\d{4})(\d{4})$/, "($1) $2-$3");
        }
        return tel;
    }

    function renderizarTabela(lista) {
        tbody.innerHTML = '';

        if (lista.length === 0) {
            tbody.innerHTML = '<tr><td colspan="7" style="text-align:center; padding: 2rem; color:#666;">Nenhum fornecedor encontrado.</td></tr>';
            return;
        }

        lista.forEach(fornecedor => {
            const mapStatus = {
                'ATIVO': { classe: 'status-ativo', texto: 'Contrato Ativo' },
                'PAUSADO': { classe: 'status-pausado', texto: 'Contrato Pausado' },
                'ENCERRADO': { classe: 'status-descontinuado', texto: 'Contrato Encerrado' }
            };
            const statusVisual = mapStatus[fornecedor.status] || { classe: 'status-descontinuado', texto: 'Desconhecido' };

            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${fornecedor.id}</td>
                <td>${fornecedor.razaoSocial}</td>
                <td>${fornecedor.email}</td>
                <td>${formatarTelefone(fornecedor.telefone)}</td>
                <td>${formatarCNPJ(fornecedor.cnpj)}</td>
                <td>
                    <span class="status-badge ${statusVisual.classe}">${statusVisual.texto}</span>
                </td>
                <td class="actions">
                    <a href="/visualizar-fornecedor?id=${fornecedor.id}" class="btn-action btn-view" title="Visualizar Detalhes">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                    </a>
                </td>
            `;
            tbody.appendChild(tr);
        });
    }

    function aplicarFiltros() {
        const termoId = inputId.value;
        const termoNome = inputNome.value.toLowerCase();
        const termoTelefone = inputTelefone.value.replace(/\D/g, '');

        const filtrados = todosFornecedores.filter(f => {
            const bateuId = termoId === "" || f.id.toString() === termoId;
            const bateuNome = f.razaoSocial && f.razaoSocial.toLowerCase().includes(termoNome);

            const telBanco = f.telefone ? f.telefone.replace(/\D/g, '') : "";
            const bateuTelefone = termoTelefone === "" || telBanco.includes(termoTelefone);

            return bateuId && bateuNome && bateuTelefone;
        });

        renderizarTabela(filtrados);
    }

    inputId.addEventListener('input', aplicarFiltros);
    inputNome.addEventListener('input', aplicarFiltros);
    inputTelefone.addEventListener('input', aplicarFiltros);

    btnLimpar.addEventListener('click', function(e) {
        e.preventDefault();
        inputId.value = '';
        inputNome.value = '';
        inputTelefone.value = '';
        aplicarFiltros();
    });
});