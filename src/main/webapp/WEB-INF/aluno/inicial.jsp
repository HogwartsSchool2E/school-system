<%@ page import="com.hogwarts.model.banco.Aluno" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 16/02/2026
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Aluno aluno = (Aluno) session.getAttribute("aluno");%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="aluno-servlet" method="post" id="f">
    <input type="hidden" name="matricula" value="<%=aluno.getMatricula()%>">
</form>

<form action="download-servlet" method="post" id="d">
    <input type="hidden" name="matricula" value="<%=aluno.getMatricula()%>">
</form>

<header>
    <h1>Portal do Aluno - <%=aluno.getCasaHogwarts().getNome()%></h1>

    <h3>Olá, <%=aluno.getNome()%>! Seja muito bem-vindo.</h3>
    <p><em>"Enquanto este castelo se mantiver erguido, manter-se-á também o compromisso solene de instruir, proteger e iluminar todos aqueles que escolhem trilhar o caminho do saber."</em></p>
</header>

<main>
    <section>
        <img src="" alt="">

        <div>
            <h5>Visualizar Notas e Observações</h5>
            <hr>
            <strong>Seção reservada para visualização de notas e observações registradas pelos professores.</strong>

            <button form="f" name="acao" value="boletim">Acessar</button>
        </div>
    </section>

    <section>
        <img src="" alt="">

        <div>
            <h5>Emitir boletim</h5>
            <hr>
            <strong>Clique aqui para fazer download do boletim.</strong>

            <button form="d" name="acao" value="download">Acessar</button>
        </div>
    </section>

    <section>
        <img src="" alt="">

        <div>
            <h5>Visualizar perfil</h5>
            <hr>
            <strong>Visualize seu perfil com seus dados.</strong>

            <button form="f" name="acao" value="perfil">Acessar</button>
        </div>
    </section>
</main>
</body>
</html>
