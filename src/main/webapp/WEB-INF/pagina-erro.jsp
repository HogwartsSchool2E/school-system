<%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 04/02/2026
  Time: 02:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<%--Capturando valores do servlet--%>
<%
    String mensagemErro = (String) request.getAttribute("mensagemErro");
%>

<html>
    <head>
        <title>Erro</title>
    </head>
    <body>
        <main>
            <h1>Ops! Ocorreu um erro...</h1>
            <strong><%=mensagemErro%></strong>
        </main>
    </body>
</html>
