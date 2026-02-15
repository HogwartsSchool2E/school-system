<%@ page import="java.util.List" %>
<%@ page import="com.hogwarts.model.banco.CasaHogwarts" %>
<%@ page import="com.hogwarts.model.banco.Professor" %>
<%@ page import="com.hogwarts.model.banco.Disciplina" %>
<%@ page import="com.hogwarts.utils.Formatador" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 07/02/2026
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CasaHogwarts> casasHogwarts = (List<CasaHogwarts>) request.getAttribute("casasHogwarts");
    List<Disciplina> professores = (List<Disciplina>) request.getAttribute("professores");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<main>
    <%if (casasHogwarts != null && !casasHogwarts.isEmpty()){%>
    <table border="1">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Pontuação</th>
            <th>Gestor</th>
            <th colspan="2">Ações</th>
        </tr>
        </thead>

        <%int i = 0;
            for (CasaHogwarts c : casasHogwarts){
            String id = String.valueOf(i++);%>
        <tbody>
        <tr>
            <td><%=c.getNome()%></td>
            <td><%=c.getPontuacao()%></td>
            <td><%=Formatador.mostrar(c.getProfessor().getNome())%></td>
            <td class="modal">
                <button type="button" class="abre-modal" data-modal="modal-edita-<%=id%>">Editar</button>

                <dialog id="modal-edita-<%=id%>">
                    <button class="fecha-modal" data-modal="modal-edita-<%=id%>">x</button>

                    <p>Antigo professor: <em><%=Formatador.mostrar(c.getProfessor().getNome())%></em></p>

                    <form method="post" action="casa-servlet">
                        <label for="professor-novo-id">Selecione o novo professor:</label>
                        <select name="professor-novo-id" id="professor-novo-id" required>
                            <option value="">Selecione</option>
                            <%for (Disciplina d : professores) {
                            if (!d.getProfessor().getNome().equals(c.getProfessor().getNome())){%>
                            <option value="<%=d.getProfessor().getId()%>"><%=d.getProfessor().getNome()%></option>
                            <%}}%>
                        </select>

                        <input type="hidden" name="professor-antigo-id" value="<%=c.getProfessor().getId()%>">
                        <input type="hidden" name="id-casa" value="<%=c.getId()%>">

                        <button type="submit" name="acao" value="atualizar">Enviar dados</button>
                    </form>
                </dialog>
            </td>
            <td class="modal">
                <button type="button" class="abre-modal" data-modal="modal-ponto-<%=id%>">Editar Pontuação</button>

                <dialog id="modal-ponto-<%=id%>">
                    <button class="fecha-modal" data-modal="modal-ponto-<%=id%>">x</button>

                    <p>Antiga pontuação: <em><%=c.getPontuacao()%></em></p>

                    <form method="post" action="casa-servlet">
                        <label for="pontuacao">Digite a pontuação a ser adicionada:</label>
                        <input type="number" name="pontuacao" id="pontuacao" step="10" required>

                        <input type="hidden" name="id-casa" value="<%=c.getId()%>">
                        <input type="hidden" name="pontuacao-antiga" value="<%=c.getPontuacao()%>">

                        <button type="submit" name="acao" value="atualizar-ponto">Enviar dados</button>
                    </form>
                </dialog>
            </td>
        </tr>
        </tbody>
        <%}%>
    </table>
    <%} else {%> <p>Nenhuma casa encontrada.</p> <%}%>
</main>

<script src="<%=request.getContextPath()%>/js/script.js"></script>

</body>
</html>
