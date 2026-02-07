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
//        Captura tipo dos buttons do formulário
        String tipoBoletim = req.getParameter("tipo");

//        Insere valores nos atributos e envia para o JSP
        if ("todos".equals(tipoBoletim)){
            req.setAttribute("boletins", new AlunoDAO().gerarBoletimTodos());
            req.getRequestDispatcher("WEB-INF/boletim-todos.jsp").forward(req, resp);
        } else if ("individual".equals(tipoBoletim)){
//            Captura input da matrícula no modal
            try {
                int matricula = Integer.parseInt(req.getParameter("matricula"));
                req.setAttribute("boletim", new AlunoDAO().gerarBoletimIndividual(matricula));
                req.getRequestDispatcher("WEB-INF/boletim-individual.jsp").forward(req, resp);
            } catch (NumberFormatException nfe) {
//                Envia mensagem de erro para a página de erro
                nfe.printStackTrace();
                req.setAttribute("mensagemErro", "Digite apenas números.");
                req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("boletins", new AlunoDAO().gerarBoletimTodos());
            req.getRequestDispatcher("WEB-INF/cadastrar-observacao.jsp").forward(req, resp);
        }
    }
}
