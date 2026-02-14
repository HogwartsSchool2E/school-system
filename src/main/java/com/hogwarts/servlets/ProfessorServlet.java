package com.hogwarts.servlets;

import com.hogwarts.dao.admin.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "ProfessorServlet", value = "/professor-servlet")
public class ProfessorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha-prof");
            String nome = new AdminDAO().ehProfessor(usuario, senha);

            if (nome != null){
                HttpSession sessao = req.getSession();

                sessao.setAttribute("nomeProf", nome);
                sessao.setAttribute("disciplina", new AdminDAO().capturarDisciplina(usuario, nome));

                req.getRequestDispatcher("WEB-INF/prof/inicial.jsp").forward(req, resp);
            } else {
                req.setAttribute("mensagemErro", "Houve um problema com o seu cadastro. Esta página é somente para os professores.");
                req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
            }

        } catch (SQLException sqle){
            sqle.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um problema com o banco de dados, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            req.setAttribute("mensagemErro", "O sistema não conseguiu acessar um componente necessário, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um erro ao processar as informações de criptografia de senha, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }
}
