<%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 07/02/2026
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String nomeAdmin = (String) session.getAttribute("nomeAdmin");
    if (nomeAdmin == null) nomeAdmin = "Jones";
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<main>
    <h1>Olá, <%=nomeAdmin%>. Bem-vindo!</h1>
    <p>O que você quer fazer hoje?</p>

    <form action="admin-servlet" id="f" method="get"></form>

    <section>
        <h3>Casas de Hogwarts</h3>
        <button type="submit" form="f" name="tipo" value="casas">Ver casas</button>
    </section>

    <section>
        <h3>Disciplinas</h3>
        <button type="submit" form="f" name="tipo" value="disciplinas">Ver disciplinas</button>
    </section>

    <section>
        <h3>Alunos</h3>
        <button type="submit" form="f" name="tipo" value="alunos">Ver alunos</button>
    </section>
</main>
</body>
</html>
