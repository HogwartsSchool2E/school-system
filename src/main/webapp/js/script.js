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
    })
})