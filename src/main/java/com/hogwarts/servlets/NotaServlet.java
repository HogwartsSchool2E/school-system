package com.hogwarts.servlets;

import com.hogwarts.dao.NotaDAO;
import com.hogwarts.model.banco.Aluno;
import com.hogwarts.model.banco.Disciplina;
import com.hogwarts.model.banco.Nota;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet(name = "NotaServlet", value = "/nota-servlet")
public class NotaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String tipo = req.getParameter("tipo");
        String discEncaminhar = URLEncoder.encode(req.getParameter("disciplina"), StandardCharsets.UTF_8);

        try {
            switch (req.getParameter("acao")){
                case "inserir":
                    inserir(req); break;

                case "atualizar":
                    atualizar(req); break;

                case "excluir":
                    excluir(req); break;

                default:
                    req.setAttribute("mensagemErro", "Não foi possível concluir sua solicitação.");
                    req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
            }

            resp.sendRedirect(req.getContextPath() + "/boletim-servlet?tipo=" + "todos" + "&disciplina=" + discEncaminhar);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void inserir(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        NotaDAO nd = new NotaDAO();
        nd.inserirNota(lerNota(req, "nota"), montarNota(req), lerEhN1(req));
    }

    private void atualizar(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        NotaDAO nd = new NotaDAO();
        nd.atualizarNota(lerNota(req, "nota-edita"), montarNota(req), lerEhN1(req));
    }

    private void excluir(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        NotaDAO nd = new NotaDAO();
        nd.excluirNota(montarNota(req), lerEhN1(req));
    }

    private Nota montarNota(HttpServletRequest req){
        Nota n = new Nota();
        Aluno a = new Aluno();
        Disciplina d = new Disciplina();

        int matricula = Integer.parseInt(req.getParameter("matricula-aluno"));
        int idDisc = Integer.parseInt(req.getParameter("id-disciplina"));

        a.setMatricula(matricula);
        d.setId(idDisc);

        n.setAluno(a);
        n.setDisciplina(d);

        return n;
    }

    private double lerNota(HttpServletRequest req, String nome){
        return Double.parseDouble(req.getParameter(nome));
    }

    private boolean lerEhN1(HttpServletRequest req){
        return Boolean.parseBoolean(req.getParameter("eh-n1"));
    }
}
