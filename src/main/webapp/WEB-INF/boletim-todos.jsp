<%@ page import="com.hogwarts.model.Boletim" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hogwarts.utils.Formatador" %>
<%@ page import="static com.hogwarts.utils.Formatador.mostrar" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 04/02/2026
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Capturando valores do servlet--%>
<%
    List<Boletim> boletins = (List<Boletim>) request.getAttribute("boletins");
%>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <main>
        <%if (boletins != null && !boletins.isEmpty()){%>
            <table border="1">
                <thead>
                    <tr>
                        <th>Aluno</th>
                        <th>Casa</th>
                        <th>Professor</th>
                        <th>Disciplina</th>
                        <th>Nota 1</th>
                        <th>Nota 2</th>
                        <th>Média</th>
                        <th>Observação</th>
                        <th>Situação</th>
                    </tr>
                </thead>

                <%for (Boletim b : boletins){%>
                <tbody>
                    <tr>
                        <td><%=b.getAluno().getNome()%></td>
                        <td><%=b.getCasaHogwarts().getNome()%></td>
                        <td><%=b.getProfessor().getNome()%></td>
                        <td><%=b.getDisciplina().getNome()%></td>
                        <td><%=b.getNota1()%></td>
                        <td><%=b.getNota2()%></td>
                        <td><%=b.getMedia()%></td>
                        <td><%=mostrar(b.getObservacao().getObservacao())%></td>
                        <td><%=b.getSituacao()%></td>
                    </tr>
                </tbody>
                <%}%>
            </table>
            <%} else {%> <p>Nenhum boletim encontrado.</p> <%}%>
        </main>
    </body>
</html>
