<%@ page import="com.hogwarts.utils.Formatador" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 10/02/2026
  Time: 01:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
    String nomeProf = (String) session.getAttribute("nomeProf");
    String disciplina = (String) session.getAttribute("disciplina");

    if (nomeProf == null) nomeProf = "Jones";
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<main>
    <h1>Olá, <%=nomeProf%>. Bem-vindo!</h1>
    <h2>Professor de <%=Formatador.mostrar(disciplina)%></h2>
    <p>O que você quer fazer hoje?</p>


    <form method="get" action="boletim-servlet">
        <button type="submit" name="tipo" value="todos">Boletim de todos os alunos</button>
        <button type="submit" name="tipo" value="observacao">Cadastrar observação</button>

        <input type="hidden" name="disciplina" value="<%=disciplina%>">
    </form>

    <div class="modal">
        <button type="button" name="tipo" class="abre-modal" data-modal="modal-1">
            Boletim individual
        </button>

        <dialog id="modal-1">
            <button type="button" class="fecha-modal" data-modal="modal-1">x</button>

            <form action="boletim-servlet" method="get">
                <label for="matricula">Digite a matrícula do aluno:</label>
                <input type="number" name="matricula" id="matricula" autocomplete="on" min="1000" required>

                <input type="hidden" name="disciplina" value="<%=disciplina%>">

                <button type="submit" name="tipo" value="individual">Enviar dados</button>
            </form>
        </dialog>
    </div>
</main>
<script src="<%=request.getContextPath()%>/js/script.js"></script>

</body>
</html>
