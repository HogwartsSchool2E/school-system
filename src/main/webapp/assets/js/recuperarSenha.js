document.addEventListener("DOMContentLoaded", function () {

    emailjs.init("fcqEfpMxrswSj69t1");

    let senhaGerada = "";

    const btnRecuperar = document.getElementById("botao-recuperar");
    const form = document.getElementById("formulario-recuperacao");

    btnRecuperar.addEventListener("click", function () {

        const email =
            document.getElementById("email-recuperacao").value.trim();

        if (!email) {

            alert("Digite o email");

            return;

        }

        const caracteres =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        senhaGerada = "";

        for (let i = 0; i < 5; i++) {

            senhaGerada += caracteres.charAt(
                Math.floor(Math.random() * caracteres.length)
            );

        }

        const parametros = {

            email_destino: email,
            mensagem: "Sua senha temporária é: " + senhaGerada

        };

        emailjs
            .send("service_8jvwrn4", "template_ckeh54r", parametros)

            .then(function () {

                document.getElementById("mensagem").innerText =
                    "Senha enviada para o email.";

            })

            .catch(function (error) {

                console.error(error);

            });

    });

    form.addEventListener("submit", function (e) {

        const digitada =
            document.getElementById("senha_digitada").value.trim();

        if (digitada !== senhaGerada) {

            e.preventDefault();

            document.getElementById("mensagem").innerText =
                "Senha incorreta.";

            return;

        }

        document.getElementById("senha_final").value = senhaGerada;

    });

});