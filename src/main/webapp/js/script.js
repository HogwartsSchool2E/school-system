// OPÇÕES DE MODAL
const abrirButtons = document.querySelectorAll('.abre-modal')
abrirButtons.forEach(button => {
    button.addEventListener('click', () => {
        const idModal = button.getAttribute('data-modal')
        const modal = document.getElementById(idModal)

        modal.showModal()
    })
})

const fechaButtons = document.querySelectorAll('.fecha-modal')
fechaButtons.forEach(button => {
    button.addEventListener('click', () => {
        const idModal = button.getAttribute('data-modal')
        const modal = document.getElementById(idModal)

        modal.close()
    })
})

// OPÇÕES DE MUDANÇA DE NOTA DINÂMICA
document.querySelectorAll('.nota-select').forEach((select) => {
    select.addEventListener('change', (x) => {
        const dialog = x.target.closest('dialog')

        const antiga = dialog?.querySelector('.antiga-nota')
        if (antiga) antiga.textContent = x.target.value

        const hidden = dialog?.querySelector('.eh-n1')
        if (hidden) hidden.value = x.target.selectedOptions[0].dataset.n1
    })
})

// MUDANÇA AUTOMÁTICA NO INPUT RADIO
const radioDigitar = document.getElementById('digitar')
const radioEscolher = document.getElementById('escolher')

const sectionDigitar = document.getElementById('digitar-section')
const sectionEscolher = document.getElementById('escolher-section')

const digitarMatricula = document.getElementById('matricula-digitar')
const selectMatricula = document.getElementById('matricula-select')

function atualizar(){
    const modo = document.querySelector('input[name="modo"]:checked').value

    if (modo === 'digitar'){
        sectionDigitar.style.display = 'block'
        sectionEscolher.style.display = 'none'

        digitarMatricula.disabled = false
        digitarMatricula.required = true

        selectMatricula.disabled = true
        selectMatricula.required = false
        selectMatricula.value = ''
    } else {
        sectionDigitar.style.display = 'none'
        sectionEscolher.style.display = 'block'

        digitarMatricula.disabled = true
        digitarMatricula.required = false
        digitarMatricula.value = ''

        selectMatricula.disabled = false
        selectMatricula.required = true
    }
}

radioDigitar.addEventListener('change', atualizar)
radioEscolher.addEventListener('change', atualizar)
atualizar()