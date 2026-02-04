<%@ page import="com.hogwarts.model.Boletim" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hogwarts.utils.Formatador" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 04/02/2026
  Time: 02:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Capturando valores do servlet--%>
<%
    List<Boletim> boletim = (List<Boletim>) request.getAttribute("boletim");
%>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <main>
            <h1><%=boletim.getFirst().getAluno().getNome()%></h1>
            <h2><%=boletim.getFirst().getCasaHogwarts().getNome()%></h2>

            <%for (Boletim b: boletim) {%>
            <strong>Professor: </strong> <%=b.getProfessor().getNome()%> <br>
            <strong>Disciplina: </strong> <%=b.getDisciplina().getNome()%> <br>
            <strong>Nota 1: </strong> <%=b.getNota1()%> <br>
            <strong>Nota 2: </strong> <%=b.getNota2()%> <br>
            <strong>Média: </strong> <%=b.getMedia()%> <br>
            <strong>Observação: </strong> <%=Formatador.mostrar(b.getObservacao().getObservacao())%> <br>
            <strong>Situação: </strong> <%=b.getSituacao()%> <br> <br>
            <%}%>
        </main>
    </body>
</html>
