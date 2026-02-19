<%@ page import="com.hogwarts.model.banco.Disciplina" %>
<%@ page import="com.hogwarts.utils.Formatador" %>
<%@ page import="java.text.Normalizer" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 07/02/2026
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Disciplina> disciplinas = (List<Disciplina>) request.getAttribute("disciplinas");
    HashMap<Integer, String> profJaMostrados = new HashMap<>();
%>

<html>
<head>
    <title>Gerenciamento de Disciplinas - Hogwarts</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/modal.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/disciplinas.css">
</head>
<body>
<main>
    <h1>Gerenciamento de Disciplinas e Professores por Disciplina</h1>

    <%if (disciplinas != null && !disciplinas.isEmpty()){%>
    <table>
        <thead>
        <tr>
            <th>Matéria</th>
            <th>Professor</th>
            <th colspan="2">Ações</th>
        </tr>
        </thead>
        <tbody>
        <%int i = 0;
            for (Disciplina d : disciplinas){
                String id = String.valueOf(i++);%>
        <tr>
            <td><%=Formatador.mostrar(d.getNome())%></td>
            <td><%=Formatador.mostrar(d.getProfessor().getNome())%></td>
            <%
                String professorAtual = d.getProfessor().getNome();
                if (d.getNome() != null){%>
            <td class="modal">
                <button type="button" class="abre-modal" data-modal="modal-edita-<%=id%>">Alterar Professor</button>

                <dialog id="modal-edita-<%=id%>">
                    <button class="fecha-modal" data-modal="modal-edita-<%=id%>">x</button>

                    <p>
                        Disciplina: <em><%=Formatador.mostrar(d.getNome())%></em><br>
                        Antigo professor: <em><%=Formatador.mostrar(professorAtual)%></em>
                    </p>

                    <form method="post" action="disciplina-servlet">
                        <label for="id-prof-<%=id%>">Selecione o novo professor:</label>
                        <select name="id-prof" id="id-prof-<%=id%>" required> <%-- ID único para cada select --%>
                            <option value="">Selecione</option>
                            <%for (Disciplina di : disciplinas) {
                                String nome = (di.getProfessor() != null) ? di.getProfessor().getNome() : null;
                                Integer idProf = (di.getProfessor() != null) ? di.getProfessor().getId() : null;

                                if (idProf != null && nome != null && !Objects.equals(nome, professorAtual)){
                                    // Verifica se o professor já foi adicionado ao mapa para evitar duplicatas visuais
                                    if (!profJaMostrados.containsKey(idProf)) {
                                        profJaMostrados.put(idProf, nome);
                            %>
                            <option value="<%=di.getProfessor().getId()%>"><%=di.getProfessor().getNome()%></option>
                            <%      }
                            }
                            }
                                profJaMostrados.clear(); // Limpa para a próxima iteração
                            %>
                        </select>

                        <input type="hidden" name="id-disc" value="<%=d.getId()%>">

                        <button type="submit" name="acao" value="atualizarDisc">Enviar dados</button>
                    </form>
                </dialog>
            </td>
            <%}%>

            <%if (d.getProfessor().getNome() != null){%>
            <td class="modal">
                <button type="button" class="abre-modal" data-modal="modal-exclui-<%=id%>">Excluir professor</button>

                <dialog id="modal-exclui-<%=id%>">
                    <button class="fecha-modal" data-modal="modal-exclui-<%=id%>">x</button>

                    <p>
                        Disciplina: <em><%=Formatador.mostrar(d.getNome())%></em><br>
                        Professor atual: <em><%=professorAtual%></em>

                        <strong>Atenção! Essa ação é irreversível. Você talvez precise alocar um novo professor para esta matéria.</strong>
                        <strong>Caso o professor seja gestor de uma casa, será preciso alocar um novo professor para essa função também.</strong>
                        <strong>Todas as observações deste professor serão excluídas.</strong>
                    </p>

                    <form action="disciplina-servlet" method="post">
                        <label for="confirma-exclusao-<%=id%>">Você tem certeza que quer excluir esse professor?</label>
                        <%-- Adicionei um ID para a label --%>
                        <input type="hidden" name="idProf" value="<%=d.getProfessor().getId()%>">

                        <button type="submit" name="acao" value="excluirProf">Sim</button>
                        <button type="button" class="fecha-modal" data-modal="modal-exclui-<%=id%>">Não</button>
                    </form>
                </dialog>
            </td>
            <%}%>
        </tr>
        <%}%>
        </tbody>
    </table>

    <%-- Novo contêiner para os botões "Adicionar" --%>
    <div class="modal-buttons-container">
        <div class="modal">
            <button type="button" class="abre-modal" data-modal="modal-add-disc">Adicionar Disciplina</button>

            <dialog id="modal-add-disc">
                <button class="fecha-modal" data-modal="modal-add-disc">x</button>

                <form method="post" action="disciplina-servlet">
                    <label for="disciplina">Digite o nome da disciplina:</label>
                    <input type="text" name="disciplina" id="disciplina" maxlength="50" required>

                    <label for="professor-add-disc">Selecione o professor:</label>
                    <select name="professor" id="professor-add-disc" required>
                        <option value="">Selecione</option>
                        <%
                            // Popula o HashMap de profs para o modal de adicionar disciplina
                            for (Disciplina di : disciplinas){
                                if (di.getProfessor() != null){
                                    int id = di.getProfessor().getId();
                                    String nome = di.getProfessor().getNome();
                                    if (!profJaMostrados.containsKey(id)) profJaMostrados.put(id, nome);
                                }
                            }
                            for (Map.Entry<Integer, String> p : profJaMostrados.entrySet()){%>
                        <option value="<%=p.getKey()%>"><%=p.getValue()%></option>
                        <%}
                            profJaMostrados.clear(); // Limpa após o uso
                        %>
                    </select>

                    <button type="submit" name="acao" value="inserirDisc">Enviar dados</button>
                </form>
            </dialog>
        </div>

        <div class="modal">
            <button type="button" class="abre-modal" data-modal="modal-add-prof">Adicionar Professor</button>

            <dialog id="modal-add-prof">
                <button class="fecha-modal" data-modal="modal-add-prof">x</button>

                <form method="post" action="disciplina-servlet">
                    <label for="professor-nome-add">Digite o nome do professor:</label>
                    <input type="text" name="professor" id="professor-nome-add" maxlength="70" required>

                    <label for="usuario-add-prof">Digite o nome do usuário:</label>
                    <input type="text" name="usuario" id="usuario-add-prof" maxlength="50" pattern="^[a-z]+\.[a-z]+$" required>

                    <label for="senha-add-prof">Digite a senha:</label>
                    <input type="password" name="senha" id="senha-add-prof" required>

                    <button type="submit" name="acao" value="inserirProf">Enviar dados</button>
                </form>
            </dialog>
        </div>
    </div>

    <%} else {%> <p>Nenhuma disciplina encontrada. Que tal adicionar uma?</p> <%}%>
</main>

<script src="<%=request.getContextPath()%>/assets/js/script.js"></script>

</body>
</html>