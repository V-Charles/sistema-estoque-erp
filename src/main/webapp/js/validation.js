document.addEventListener('DOMContentLoaded', () => {

    // Validação de CNPJ
    const isCNPJValido = (cnpj) => {
        cnpj = cnpj.replace(/[^\d]+/g, '');
        if (cnpj === '' || cnpj.length !== 14 || /^(\d)\1+$/.test(cnpj)) return false;

        let tamanho = cnpj.length - 2;
        let numeros = cnpj.substring(0, tamanho);
        let digitos = cnpj.substring(tamanho);
        let soma = 0;
        let pos = tamanho - 7;

        for (let i=tamanho; i>=1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2) pos = 9;
        }
        let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0)) return false;

        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (let i=tamanho; i>=1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2) pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1)) return false;
        return true;
    };

    // Validação de E-mail
    const isEmailValido = (email) => {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    };

    // Validação de senha
    const isSenhaValida = (senha) => {
        const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
        return regex.test(senha);
    }

    // validação de Telefone
    const isTelefoneValido = (telefone) => {
        const num = telefone.replace(/\D/g, '');
        return num.length >= 10 && num.length <= 11;
    };

    // Funções para exibir mensagens de erro
    const mostrarErro = (input, mensagem) => {
        removerErro(input);
        input.classList.add('input-error');
        const erroSpan = document.createElement('span');
        erroSpan.classList.add('error-message');
        erroSpan.innerText = mensagem;
        input.parentElement.appendChild(erroSpan);
    };

    const removerErro = (input) => {
        input.classList.remove('input-error');
        const erroSpan = input.parentElement.querySelector('.error-message');
        if (erroSpan) {
            erroSpan.remove();
        }
    };

    // Aplicar eventos aos campos
    const cnpjInputs = document.querySelectorAll('.valida-cnpj');
    cnpjInputs.forEach(input => {
        input.addEventListener('blur', (e) => {
            const valor = e.target.value;
            if (valor && !isCNPJValido(valor)) {
                mostrarErro(e.target, "CNPJ inválido.");
            } else {
                removerErro(e.target);
            }
        });
    });

    const emailInputs = document.querySelectorAll('.valida-email');
    emailInputs.forEach(input => {
        input.addEventListener('blur', (e) => {
            const valor = e.target.value;
            if (valor && !isEmailValido(valor)) {
                mostrarErro(e.target, "Por favor, insira um e-mail válido.");
            } else {
                removerErro(e.target);
            }
        });
    });

    const senhaInputs = document.querySelectorAll('.valida-senha');
    senhaInputs.forEach(input => {
        input.addEventListener('blur', (e) => {
            const valor = e.target.value;
            if (valor && !isSenhaValida(valor)) {
                mostrarErro(e.target, "A senha deve conter mín. 8 caracteres, com maiúscula, minúscula, número e símbolo.");
            } else {
                removerErro(e.target);
            }
        });
    });

    const telInputs = document.querySelectorAll('.valida-telefone');
    telInputs.forEach(input => {
        input.addEventListener('blur', (e) => {
            const valor = e.target.value;
            if (valor && !isTelefoneValido(valor)) {
                mostrarErro(e.target, "Telefone inválido.");
            } else {
                removerErro(e.target);
            }
        });
    });
});