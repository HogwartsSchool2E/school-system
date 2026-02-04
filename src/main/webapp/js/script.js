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