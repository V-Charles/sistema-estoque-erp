document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('formEdicao');
    const btnSalvar = document.getElementById('btnSalvar');

    if (!form || !btnSalvar) return;

    const editIcons = document.querySelectorAll('.edit-icon');
    const formControls = form.querySelectorAll('.form-input:not([readonly]), input[type="radio"]');

    formControls.forEach(control => {
        if (control.type === 'radio') {
            control.dataset.initial = control.checked;
        } else {
            control.dataset.initial = control.value;
        }
    });

    editIcons.forEach(icon => {
        icon.addEventListener('click', function(e) {
            e.preventDefault();
            const wrapper = this.closest('.editable-field');
            const input = wrapper.querySelector('.form-input');

            if(input) {
                input.removeAttribute('disabled');
                input.focus();
            }
        });
    });

    function verificarAlteracoes() {
        let mudou = false;

        formControls.forEach(control => {
            if (control.type === 'radio') {
                if (control.checked.toString() !== control.dataset.initial) {
                    mudou = true;
                }
            } else {
                if (control.value !== control.dataset.initial) {
                    mudou = true;
                }
            }
        });

        if (mudou) {
            btnSalvar.removeAttribute('disabled');
        } else {
            btnSalvar.setAttribute('disabled', 'true');
        }
    }

    form.addEventListener('input', verificarAlteracoes);
    form.addEventListener('change', verificarAlteracoes);

    form.addEventListener('submit', function() {
        formControls.forEach(control => {
            control.removeAttribute('disabled');
        });

        const statusVisualEl = document.querySelector('input[name="statusVisual"]:checked');
        const statusRealEl = document.getElementById('statusReal');

        if (statusVisualEl && statusRealEl) {
            statusRealEl.value = (statusVisualEl.value === 'ativo');
        }
    });
});