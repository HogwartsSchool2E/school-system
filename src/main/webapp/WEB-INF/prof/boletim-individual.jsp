<%@ page import="com.hogwarts.model.Boletim" %>
<%@ page import="com.hogwarts.utils.Formatador" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 04/02/2026
  Time: 02:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%--Capturando valores do servlet--%>
<%
    Boletim boletim = (Boletim) request.getAttribute("boletim");
    String disciplina = (String) request.getAttribute("disciplina");
%>

<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/modal.css">
    </head>
    <body>
        <main>
            <h1><%=boletim.getAluno().getNome()%></h1>
            <h2><%=boletim.getCasaHogwarts().getNome()%></h2>
            <p><%=disciplina%></p>

            <%String id = String.valueOf(1);
                if (boletim.getDisciplina().getNome().equals(disciplina)){%>
            <strong>Professor: </strong> <%=boletim.getProfessor().getNome()%> <br>
            <strong>Disciplina: </strong> <%=boletim.getDisciplina().getNome()%> <br>
            <strong>Nota 1: </strong> <%=boletim.getNota1() == 0 ? "--" : ("<strong>" + boletim.getNota1() + "</strong>") %> <br>
            <strong>Nota 2: </strong> <%=boletim.getNota2() == 0 ? "--" : ("<strong>" + boletim.getNota2() + "</strong>") %> <br>
            <strong>Média: </strong> <%=boletim.getMedia() == 0 ? "--" : ("<strong>" + boletim.getMedia() + "</strong>")%> <br>
            <strong>Observação: </strong> <%=Formatador.mostrar(boletim.getObservacao().getObservacao())%> <br>
            <strong>Situação: </strong> <%=boletim.getSituacao().getNome()%> <br> <br>
            <%}%>

            <section>
                <h3>NOTAS</h3>

                <%if (boletim.getNota1() == 0) {%>
                <div>
                    <button type="button" name="" class="abre-modal" data-modal="modal-add-semnotas-<%=id%>">Adicionar Nota</button>

                    <dialog id="modal-add-semnotas-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-add-semnotas-<%=id%>">x</button>
                        <form action="nota-servlet" method="post">
                            <label for="nota1-semnotas">Digite a nota:</label>
                            <input type="number" name="nota" id="nota1-semnotas" step="0.01" min="0.01" max="10" required>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="eh-n1" value="true">
                            <input type="hidden" name="tipo" value="individual">
                            <input type="hidden" name="matricula" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="inserir">Enviar dados</button>
                        </form>
                    </dialog>
                </div>

                <%} else if (boletim.getNota2() == 0) {%>
                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-add-umanota-<%=id%>">Adicionar Nota</button>

                    <dialog id="modal-add-umanota-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-add-umanota-<%=id%>">x</button>
                        <form action="nota-servlet" method="post">
                            <label for="nota2-semnotas">Digite a nota:</label>
                            <input type="number" name="nota" id="nota2-semnotas" step="0.01" min="0.01" max="10" required>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="eh-n1" value="false">
                            <input type="hidden" name="tipo" value="individual">
                            <input type="hidden" name="matricula" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="inserir">Enviar dados</button>
                        </form>
                    </dialog>
                </div>

                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-edita-umanota-<%=id%>">Editar Nota</button>

                    <dialog id="modal-edita-umanota-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-edita-umanota-<%=id%>">x</button>

                        <p>Antiga nota: <em><%=boletim.getNota1()%></em></p>

                        <form action="nota-servlet" method="post">
                            <label for="nota-edita">Digite a nova nota:</label>
                            <input type="number" name="nota-edita" id="nota-umanota" step="0.01" min="0.01" max="10" required>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="eh-n1" value="true">
                            <input type="hidden" name="tipo" value="individual">
                            <input type="hidden" name="matricula" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="atualizar">Enviar dados</button>
                        </form>
                    </dialog>
                </div>

                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-exclui-umanota-<%=id%>">Excluir Nota</button>

                    <dialog id="modal-exclui-umanota-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-exclui-umanota-<%=id%>">x</button>
                        <form action="nota-servlet" method="post">
                            <p>Nota a ser excluída: <em><%=boletim.getNota1()%></em></p>
                            <label for="excluir">Você tem certeza que quer excluir essa nota?</label>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="eh-n1" value="true">
                            <input type="hidden" name="tipo" value="individual">
                            <input type="hidden" name="matricula" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="excluir">Sim</button>
                            <button type="button" class="fecha-modal" data-modal="modal-exclui-umanota-<%=id%>">Não</button>
                        </form>
                    </dialog>
                </div>

                <%} else {%>
                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-edita-duasnotas-<%=id%>">Editar Nota</button>

                    <dialog id="modal-edita-duasnotas-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-edita-duasnotas-<%=id%>">x</button>

                        <p>Antiga nota: <em class="antiga-nota"></em></p>

                        <form action="nota-servlet" method="post">
                            <label for="nota">Selecione uma nota para editar:</label>
                            <select name="nota" class="nota-select" required>
                                <option value="">Selecione</option>
                                <option value="<%=boletim.getNota1()%>" data-n1="true">Nota 1</option>
                                <option value="<%=boletim.getNota2()%>" data-n1="false">Nota 2</option>
                            </select>

                            <label for="nota-edita">Digite a nova nota:</label>
                            <input type="number" name="nota-edita" id="nota-edita" step="0.01" min="0.01" max="10" required>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="eh-n1" class="eh-n1">
                            <input type="hidden" name="tipo" value="individual">
                            <input type="hidden" name="matricula" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="atualizar">Enviar dados</button>
                        </form>
                    </dialog>
                </div>

                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-exclui-duasnotas-<%=id%>">Excluir Nota</button>

                    <dialog id="modal-exclui-duasnotas-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-exclui-duasnotas-<%=id%>">x</button>

                        <p>Nota a ser excluída: <em class="antiga-nota"></em></p>

                        <form action="nota-servlet" method="post">
                            <label for="nota">Selecione uma nota para excluir:</label>
                            <select name="nota" class="nota-select" required>
                                <option value="">Selecione</option>
                                <option value="<%=boletim.getNota1()%>" data-n1="true">Nota 1</option>
                                <option value="<%=boletim.getNota2()%>" data-n1="false">Nota 2</option>
                            </select>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="eh-n1" class="eh-n1">
                            <input type="hidden" name="tipo" value="individual">
                            <input type="hidden" name="matricula" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <label for="excluir">Você tem certeza que quer excluir essa nota?</label>

                            <button type="submit" name="acao" value="excluir">Sim</button>
                            <button type="button" class="fecha-modal" data-modal="modal-exclui-duasnotas-<%=id%>">Não</button>
                        </form>
                    </dialog>
                </div>
                <%}%>
            </section>

            <hr>

            <section>
                <h3>OBSERVAÇÕES</h3>

                <%if (boletim.getObservacao().getObservacao() == null){%>

                <div class="modal" >
                    <button type="button" name="" class="abre-modal" data-modal="modal-add-<%=id%>">Adicionar Observação</button>

                    <dialog id="modal-add-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-add-<%=id%>">x</button>
                        <form action="observacoes-servlet" method="get">
                            <label for="adicionar">Digite a observação:</label>
                            <input type="text" name="adicionar" id="adicionar" autocomplete="off" required>

                            <input type="hidden" name="matricula-aluno" value="<%=boletim.getAluno().getMatricula()%>">
                            <input type="hidden" name="id-disciplina" value="<%=boletim.getDisciplina().getId()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="adicionar">Enviar dados</button>
                        </form>
                    </dialog>
                </div>

                <%} else {%>
                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-edita-<%=id%>">Editar Observação</button>

                    <dialog id="modal-edita-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-edita-<%=id%>">x</button>

                        <p>Antiga observação: <em><%=boletim.getObservacao().getObservacao()%></em></p>

                        <form action="observacoes-servlet" method="get">
                            <label for="editar">Digite a nova observação:</label>
                            <input type="text" name="editar" id="editar" autocomplete="off" required>

                            <input type="hidden" name="id-observacao" value="<%=boletim.getObservacao().getId()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="editar">Enviar dados</button>
                        </form>
                    </dialog>
                </div>

                <div class="modal">
                    <button type="button" name="" class="abre-modal" data-modal="modal-exclui-<%=id%>">Excluir Observação</button>

                    <dialog id="modal-exclui-<%=id%>">
                        <button type="button" class="fecha-modal" data-modal="modal-exclui-<%=id%>">x</button>
                        <form action="observacoes-servlet" method="get">
                            <label for="excluir">Você tem certeza que quer excluir essa observação?</label>

                            <input type="hidden" name="nome-aluno" value="<%=boletim.getAluno().getNome()%>">
                            <input type="hidden" name="nome-disciplina" value="<%=boletim.getDisciplina().getNome()%>">
                            <input type="hidden" name="disciplina" value="<%=disciplina%>">

                            <button type="submit" name="acao" value="excluir">Sim</button>
                            <button type="button" class="fecha-modal" data-modal="modal-exclui-<%=id%>">Não</button>
                        </form>
                    </dialog>
                </div>
                <%}%>
            </section>

        </main>

        <script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
    </body>
</html>
