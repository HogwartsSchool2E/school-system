package com.hogwarts.servlets;

import com.hogwarts.dao.AlunoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BoletimServlet", value = "/boletim-servlet")
public class BoletimServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("disciplina", req.getParameter("disciplina"));

            switch (req.getParameter("tipo")){
                case "todos":
                    boletimTodos(req, resp); break;

                case "individual":
                    boletimIndividual(req, resp); break;

                case "observacao":
                    observacoes(req, resp); break;

                default:
                    req.setAttribute("mensagemErro", "Não foi possível concluir sua solicitação.");
                    req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            req.setAttribute("mensagemErro", "Selecione uma opção válida.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }

    private void boletimTodos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setAttribute("boletins", new AlunoDAO().gerarBoletimTodos());
        req.setAttribute("tipo", "todos");

        req.getRequestDispatcher("WEB-INF/prof/boletim-todos.jsp").forward(req, resp);
    }

    private void boletimIndividual(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException{
        int matricula = Integer.parseInt(req.getParameter("matricula"));
        String disc = req.getParameter("disciplina");

        req.setAttribute("boletim", new AlunoDAO().gerarBoletimIndividual(matricula, disc));
        req.setAttribute("tipo", "individual");

        req.getRequestDispatcher("WEB-INF/prof/boletim-individual.jsp").forward(req, resp);
    }

    private void observacoes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setAttribute("boletins", new AlunoDAO().gerarBoletimTodos());
        req.getRequestDispatcher("WEB-INF/prof/cadastrar-observacao.jsp").forward(req, resp);
    }
}
