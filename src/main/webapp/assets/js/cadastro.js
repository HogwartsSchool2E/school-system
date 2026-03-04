 // parte de verificação
   document.addEventListener("DOMContentLoaded", function () {

    const btnConcluirCadastro = document.getElementById("botao-principal");

    btnConcluirCadastro.addEventListener("click", function (event) {

        event.preventDefault(); // impede envio automático do form

        const user_email = document.getElementById("user_email").value.trim();
        const senha = document.getElementById("senha").value.trim();
        const confSenha = document.getElementById("confirmar-senha").value.trim();

        if (!user_email || !senha || !confSenha) {
            alert("Todos os campos devem ser preenchidos!");
            return;
        }

        if (senha !== confSenha) {
            alert("O campo 'confirmar senha' esta diferente da senha!");
            return;
        }

        alert("Cadastro realizado com sucesso!");
    });

    const btnRecuperar = document.getElementById("botao-recuperar");

    btnRecuperar.addEventListener("click", function (event) {
         // gera senha.
        const caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        let senha = "";
        for (let i = 0; i < 5; i++) {
            senha += caracteres.charAt(Math.floor(Math.random() * caracteres.length));
        }
        alert(`Sua nova senha é: ${senha}`);
    });

});
