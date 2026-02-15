package com.hogwarts.servlets.admin;

import com.hogwarts.dao.admin.DisciplinaDAO;
import com.hogwarts.model.banco.Disciplina;
import com.hogwarts.model.banco.Professor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "DisciplinaServlet", value = "/disciplina-servlet")
public class DisciplinaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            switch(req.getParameter("acao")){
                case "inserirProf":
                    inserirProf(req); break;

                case "inserirDisc":
                    inserirDisc(req); break;

                case "atualizarDisc":
                    atualizarDisc(req); break;

                case "excluirProf":
                    excluirProf(req); break;

                default:
                    req.setAttribute("mensagemErro", "Não foi possível concluir sua solicitação.");
                    req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
            }

            resp.sendRedirect(req.getContextPath() + "/admin-servlet?tipo=disciplinas");
        } catch (SQLException sqle){
            sqle.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um problema com o banco de dados, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            req.setAttribute("mensagemErro", "O sistema não conseguiu acessar um componente necessário, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp");
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            req.setAttribute("mensagemErro", "Selecione uma opção válida.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um erro ao processar as informações de criptografia de senha, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }

    private void inserirProf(HttpServletRequest req) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        Professor p = new Professor();

        p.setNome(req.getParameter("professor"));
        p.setUsuario(req.getParameter("usuario"));
        p.setSenha(req.getParameter("senha"));

        new DisciplinaDAO().inserirProfessor(p);
    }

    private void inserirDisc(HttpServletRequest req) throws SQLException, ClassNotFoundException{
        Professor p = new Professor();

        String nome = req.getParameter("disciplina");
        p.setId(Integer.parseInt(req.getParameter("professor")));

        new DisciplinaDAO().inserirDisciplina(new Disciplina(nome, p));
    }

    private void atualizarDisc(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        int idDisciplina = Integer.parseInt(req.getParameter("id-disc"));
        int idProfNovo = Integer.parseInt(req.getParameter("id-prof"));

        new DisciplinaDAO().atualizarDisciplina(idDisciplina, idProfNovo);
    }

    private void excluirProf(HttpServletRequest req) throws SQLException, ClassNotFoundException{
        new DisciplinaDAO().excluirProfessor(Integer.parseInt(req.getParameter("idProf")));
    }
}
