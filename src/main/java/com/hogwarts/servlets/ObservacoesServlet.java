package com.hogwarts.servlets;

import com.hogwarts.dao.ObservacaoDAO;
import com.hogwarts.model.banco.Aluno;
import com.hogwarts.model.banco.Disciplina;
import com.hogwarts.model.banco.Observacao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "observacoesServlet", value = "/observacoes-servlet")
public class ObservacoesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            Criando atributos
            String acao = req.getParameter("acao");
            ObservacaoDAO o = new ObservacaoDAO();

//            Verifica tipo de ação
            if ("adicionar".equals(acao)){
                Aluno a = new Aluno();
                Disciplina d = new Disciplina();

                String observacao = req.getParameter("adicionar");
                a.setMatricula(Integer.parseInt(req.getParameter("matricula-aluno")));
                d.setId(Integer.parseInt(req.getParameter("id-disciplina")));

                o.inserirObs(new Observacao(observacao, a, d));
            } else if ("editar".equals(acao)){
                int idObservacao = Integer.parseInt(req.getParameter("id-observacao"));
                String novaObservacao = req.getParameter("editar");

                o.alterarObs(idObservacao, novaObservacao);
            } else {
                String aluno = req.getParameter("nome-aluno");
                String disciplina = req.getParameter("nome-disciplina");

                o.deletarObs(aluno, disciplina);
            }

            resp.sendRedirect( req.getContextPath() + "/boletim-servlet?tipo=observacao");
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
            req.setAttribute("mensagemErro", "Houve um erro ao processar as informações, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }
}
