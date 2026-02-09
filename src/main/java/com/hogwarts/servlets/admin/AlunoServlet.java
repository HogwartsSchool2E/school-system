package com.hogwarts.servlets.admin;

import com.hogwarts.dao.AlunoDAO;
import com.hogwarts.model.banco.Aluno;
import com.hogwarts.model.banco.CasaHogwarts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "AlunoServlet", value = "/aluno-servlet")
public class AlunoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            switch(req.getParameter("acao")){
                case "inserir":
                    inserirAluno(req); break;

                case "atualizar":
                    atualizarAluno(req); break;

                case "excluir":
                    excluirAluno(req); break;

                default:
                    req.setAttribute("mensagemErro", "Não foi possível concluir sua solicitação.");
                    req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
            }

            resp.sendRedirect(req.getContextPath() + "/admin-servlet?tipo=alunos");
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
        }catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um erro ao processar as informações de criptografia de senha, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }

    private void inserirAluno(HttpServletRequest req) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
        CasaHogwarts c = new CasaHogwarts();
        c.setId(Integer.parseInt(req.getParameter("casa")));

        String nome = req.getParameter("aluno");
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        new AlunoDAO().inserirAluno(new Aluno(nome, cpf, email, senha, c));
    }

    private void atualizarAluno(HttpServletRequest req) throws SQLException, ClassNotFoundException{
        Aluno a = new Aluno();
        a.setEmail(req.getParameter("email"));
        a.setMatricula(Integer.parseInt(req.getParameter("matricula")));

        new AlunoDAO().atualizarAluno(a);
    }

    private void excluirAluno(HttpServletRequest req) throws SQLException, ClassNotFoundException{
        new AlunoDAO().excluirAluno(Integer.parseInt(req.getParameter("matricula")));
    }
}
