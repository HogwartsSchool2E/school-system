<%@ page import="java.util.HashMap" %>
<%@ page import="com.hogwarts.model.QuadroObservacoes" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hogwarts.model.Dashboard" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.DecimalFormat" %><%--
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
    DecimalFormat df = new DecimalFormat("0.00");
%>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <main>
            <h1>RANKING</h1>
            <%int i = 0;
            for (Map.Entry<String, Double> entrada : dash.getRanking().entrySet()){
                i++;

                String nome = entrada.getKey();
                Double media = entrada.getValue();%>
            <p><strong><%=i%>º <%=nome%></strong>: <%=media%></p>
            <% }%>


            <h1>QUANTIDADE DE ALUNOS</h1>
            <p><%=dash.getQtdAlunos()%></p>

            <h1>MÉDIA DAS CASAS</h1>
            <%for (Map.Entry<String, Double> entrada : dash.getMediaCasas().entrySet()){
                String nome = entrada.getKey();
                Double media = entrada.getValue();%>
            <p><strong><%=nome%></strong>: <%=df.format(media)%></p>
            <% }%>


            <h1>QUADRO DE OBSERVAÇÕES</h1>
            <p><%for (QuadroObservacoes entrada : dash.getQuadroObservacoes()){%></p>
            <div>
                <em><%=entrada.getObservacao()%></em>
                <p>
                    <h3><%=entrada.getAluno()%> - <%=entrada.getCasa()%></h3>
                    <strong><%=entrada.getProfessor()%></strong>
                </p>
            </div>
            <hr>
            <%}%>
        </main>
    </body>
</html>
