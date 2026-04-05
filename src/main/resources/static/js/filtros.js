document.addEventListener('DOMContentLoaded', function() {
    // 1. Log para termos certeza que o arquivo foi carregado!
    console.log("✅ Arquivo filtros.js carregado com sucesso!");

    const inputNome = document.querySelector('input[name="nomeBusca"]');
    const formBusca = document.querySelector('.search-group');
    let timeoutId;

    if (!inputNome) {
        console.error("❌ O campo de busca não foi encontrado pelo JavaScript.");
        return;
    }

    // 2. A MÁGICA DE UX: Proíbe o formulário de recarregar a página se o usuário apertar Enter!
    if (formBusca) {
        formBusca.addEventListener('submit', function(event) {
            event.preventDefault(); // "Navegador, não faça nada! Deixa que o JS resolve."
            console.log("🛑 Submit padrão bloqueado!");
        });
    }

    // 3. O Evento de Digitação
    inputNome.addEventListener('input', function() {
        console.log("⌨️ Usuário digitou algo...");

        clearTimeout(timeoutId);

        timeoutId = setTimeout(() => {
            const termoBusca = inputNome.value;
            console.log("🔍 Enviando busca para o Java: ", termoBusca);

            // Fazendo a requisição
            fetch(`/produtos?nomeBusca=${encodeURIComponent(termoBusca)}&ajax=true`)
                .then(response => {
                    if(!response.ok) throw new Error("Erro na comunicação com o servidor");
                    return response.text();
                })
                .then(htmlFragmento => {
                    console.log("📦 Resposta do Java recebida! Atualizando tabela...");
                    const tbodyAtual = document.getElementById('tabelaProdutos');

                    if(tbodyAtual) {
                        // Substitui a tabela velha pela nova
                        tbodyAtual.outerHTML = htmlFragmento;
                    } else {
                        console.error("❌ Tag <tbody id='tabelaProdutos'> não encontrada no HTML!");
                    }
                })
                .catch(error => console.error('❌ Erro no Fetch:', error));

        }, 300);
    });
});