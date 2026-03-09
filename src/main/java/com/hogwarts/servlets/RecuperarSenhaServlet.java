package com.hogwarts.servlets;

import com.hogwarts.dao.AlunoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name="/recuperar-senha", value = "/recupera-senha")
public class RecuperarSenhaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String novaSenha = req.getParameter("senha");

        try {

            AlunoDAO dao = new AlunoDAO();

            boolean atualizado = dao.atualizarSenhaPorEmail(email, novaSenha);

            if (atualizado) {

                req.setAttribute("mensagem",
                        "Senha alterada com sucesso. Faça login novamente.");

                req.getRequestDispatcher("index.jsp")
                        .forward(req, resp);

            } else {

                req.setAttribute("mensagemErro",
                        "Não foi possível atualizar a senha.");

                req.getRequestDispatcher("WEB-INF/pagina-erro.jsp")
                        .forward(req, resp);
            }

        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {

            e.printStackTrace();

            req.setAttribute("mensagemErro",
                    "Erro ao atualizar senha.");

            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp")
                    .forward(req, resp);
        }
    }
}