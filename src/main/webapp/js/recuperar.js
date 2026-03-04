document.addEventListener("DOMContentLoaded", () => {
    
    const etapa1 = document.getElementById('etapa-1');
    const etapa2 = document.getElementById('etapa-2');
    const etapa3 = document.getElementById('etapa-3');

    const btnEnviarCodigo = document.getElementById('btn-enviar-codigo');
    const btnValidarCodigo = document.getElementById('btn-validar-codigo');
    const btnVoltarEtapa1 = document.getElementById('btn-voltar-etapa1');
    const btnRedefinirSenha = document.getElementById('btn-redefinir-senha');

    const inputEmail = document.getElementById('email-recuperacao');
    const emailDisplay = document.getElementById('email-display');
    const otpBoxes = document.querySelectorAll('.otp-box');
    const inputNovaSenha = document.getElementById('nova-senha');
    const inputConfirmaSenha = document.getElementById('confirma-senha');

    const mudarEtapa = (etapaOcultar, etapaMostrar) => {
        etapaOcultar.classList.remove('active');
        etapaOcultar.classList.add('hidden');
        
        etapaMostrar.classList.remove('hidden');
        etapaMostrar.classList.add('active');
    };

    btnEnviarCodigo.addEventListener('click', () => {
        const emailValue = inputEmail.value.trim();
        
        if (emailValue && !inputEmail.classList.contains('input-error')) {
            emailDisplay.innerText = emailValue;
            mudarEtapa(etapa1, etapa2);
            setTimeout(() => otpBoxes[0].focus(), 100);
        } else {
            inputEmail.focus();
        }
    });

    btnVoltarEtapa1.addEventListener('click', (e) => {
        e.preventDefault();
        mudarEtapa(etapa2, etapa1);
        otpBoxes.forEach(box => box.value = '');
    });

    otpBoxes.forEach((box, index) => {
        box.addEventListener('input', (e) => {

            if (e.target.value.length === 1 && index < otpBoxes.length - 1) {
                otpBoxes[index + 1].focus();
            }
        });

        box.addEventListener('keydown', (e) => {

            if (e.key === 'Backspace' && e.target.value === '' && index > 0) {
                otpBoxes[index - 1].focus();
            }
        });

        box.addEventListener('paste', (e) => {
            e.preventDefault();
            const colado = e.clipboardData.getData('text').trim();
            const caracteres = colado.substring(0, 6).split('');
            caracteres.forEach((char, i) => {

                if (otpBoxes[i]) {
                    otpBoxes[i].value = char;
                }
            });
            const ultimoIndice = Math.min(caracteres.length - 1, 5);
            otpBoxes[ultimoIndice].focus();
        });
    });

    btnValidarCodigo.addEventListener('click', () => {
        const codigoDigitado = Array.from(otpBoxes).map(box => box.value).join('');

        if (codigoDigitado.length === 6) {
            mudarEtapa(etapa2, etapa3);
        } else {
            alert('Por favor, preencha os 6 dígitos do código.');
        }
    });

    btnRedefinirSenha.addEventListener('click', () => {
        const s1 = inputNovaSenha.value;
        const s2 = inputConfirmaSenha.value;

        if (s1 && !inputNovaSenha.classList.contains('input-error')) {
            if (s1 === s2) {
                alert('Senha redefinida com sucesso! Você será redirecionado para o Login.');
                window.location.href = 'index.html';
            } else {
                alert('As senhas não coincidem. Tente novamente.');
            }
        }
    });
});