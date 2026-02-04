<%@ page import="java.util.HashMap" %>
<%@ page import="com.hogwarts.model.QuadroObservacoes" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hogwarts.model.Dashboard" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 03/02/2026
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Capturando valores do servlet--%>
<%
    Dashboard dash = (Dashboard) request.getAttribute("dashboard");
%>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <main>
            <h1>RANKING</h1>
            <p><%=dash.getRanking()%></p>

            <h1>QUANTIDADE DE ALUNOS</h1>
            <p><%=dash.getQtdAlunos()%></p>

            <h1>MÉDIA DAS CASAS</h1>
            <p><%=dash.getMediaCasas()%></p>

            <h1>QUADRO DE OBSERVAÇÕES</h1>
            <p><%=dash.getQuadroObservacoes()%></p>
        </main>
    </body>
</html>
