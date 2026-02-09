package com.hogwarts.servlets.admin;

import com.hogwarts.dao.admin.CasaDAO;
import com.hogwarts.dao.admin.DisciplinaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CasaServlet", value = "/casa-servlet")
public class CasaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CasaDAO c = new CasaDAO();

            int idNovo = Integer.parseInt(req.getParameter("professor-novo-id"));
            int idAntigo = Integer.parseInt(req.getParameter("professor-antigo-id"));
            int idCasa = Integer.parseInt(req.getParameter("id-casa"));

            c.atualizarCasa(idAntigo, idNovo, idCasa);

            req.setAttribute("casasHogwarts", new CasaDAO().buscarCasaHogwarts());
            req.setAttribute("professores", new DisciplinaDAO().buscarProfessores());
            req.getRequestDispatcher("WEB-INF/admin/casas.jsp").forward(req, resp);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um problema com o banco de dados, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            req.setAttribute("mensagemErro", "O sistema não conseguiu acessar um componente necessário, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um erro ao processar as informações, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }
}
