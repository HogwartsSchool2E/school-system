package com.hogwarts.servlets;

import com.hogwarts.dao.AlunoDAO;
import com.hogwarts.model.Boletim;
import com.hogwarts.model.banco.Aluno;
import com.hogwarts.utils.Formatador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.openpdf.text.*;
import org.openpdf.text.Font;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DownloadServlet", value = "/download-servlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int matricula = Integer.parseInt(req.getParameter("matricula"));
            Aluno aluno = new AlunoDAO().buscarAluno(matricula);
            List<Boletim> boletins = new AlunoDAO().gerarBoletimIndividual(matricula);

            gerarPdf(resp, aluno, boletins);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            req.setAttribute("mensagemErro", "Houve um problema com o banco de dados, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
            req.setAttribute("mensagemErro", "O sistema não conseguiu acessar um componente necessário, não se preocupe, tente novamente em alguns minutos.");
            req.getRequestDispatcher("WEB-INF/pagina-erro.jsp").forward(req, resp);
        }
    }

    private void gerarPdf(HttpServletResponse resp, Aluno aluno, List<Boletim> boletins) throws IOException {
//        Criando atributos
        String nomeArquivo = "boletim-" + aluno.getMatricula() + ".pdf";

        resp.setContentType("application/pdf"); // seta resposta como pdf
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + "\""); // diz para o navegador abrir conteúdo como arquivo, não página

//        Criando documento pdf
        Document d = new Document(PageSize.A4, 20, 20, 20, 20); // seta tamanho e margins
        PdfWriter.getInstance(d, resp.getOutputStream()); // liga documento ao servlet

        d.open();

//        Fontes
        Font titulo = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font subtitulo = new Font(Font.HELVETICA, 12, Font.NORMAL);
        Font tabelaNegrito = new Font(Font.HELVETICA, 10, Font.BOLD);
        Font tabelaCelula = new Font(Font.HELVETICA, 10, Font.NORMAL);
        Font tabelaItalico = new Font(Font.HELVETICA, 10, Font.ITALIC);

//        Cabeçalho do PDF
        Paragraph p1 = new Paragraph("Escola de Magia e Bruxaria de Hogwarts", titulo);
        p1.setAlignment(Element.ALIGN_CENTER);
        p1.setSpacingAfter(5);
        d.add(p1);

        Paragraph p2 = new Paragraph((aluno.getNome() + " - " + aluno.getCasaHogwarts().getNome()), subtitulo);
        p2.setAlignment(Element.ALIGN_CENTER);
        p2.setSpacingAfter(15);
        d.add(p2);

//        Tabela
        PdfPTable tabela = new PdfPTable(7); // tabela com 7 colunas
        tabela.setWidthPercentage(100); // ocupa 100% do espaço disponível
        tabela.setSpacingBefore(5); // espaço antes da tabela

        tabela.setWidths(new float[]{2.2f, 2.0f, 0.9f, 0.9f, 0.9f, 2.6f, 2.2f}); // define larguras das colunas

//        Cabeçalho da tabela
        inserirCelulaCabecalho(tabela, "Professor", tabelaNegrito);
        inserirCelulaCabecalho(tabela, "Disciplina", tabelaNegrito);
        inserirCelulaCabecalho(tabela, "Nota 1", tabelaNegrito);
        inserirCelulaCabecalho(tabela, "Nota 2", tabelaNegrito);
        inserirCelulaCabecalho(tabela, "Média", tabelaNegrito);
        inserirCelulaCabecalho(tabela, "Observação", tabelaNegrito);
        inserirCelulaCabecalho(tabela, "Situação", tabelaNegrito);

//        Para cada boletim inserir uma linha
        for (Boletim b : boletins){
            inserirCelulaCorpo(tabela, Formatador.mostrar(b.getProfessor().getNome()), tabelaCelula);
            inserirCelulaCorpo(tabela, Formatador.mostrar(b.getDisciplina().getNome()), tabelaCelula);

            inserirCelulaNota(tabela, b.getNota1(), tabelaCelula, tabelaNegrito);
            inserirCelulaNota(tabela, b.getNota2(),tabelaCelula, tabelaNegrito);
            inserirCelulaNota(tabela, b.getMedia(),tabelaCelula, tabelaNegrito);

            inserirCelulaObservacao(tabela, b.getObservacao().getObservacao(), tabelaCelula, tabelaItalico);
            inserirCelulaCorpo(tabela, b.getSituacao().getNome(), tabelaCelula);
        }

        d.add(tabela);
        d.close();
    }

    private void inserirCelulaCabecalho(PdfPTable t, String texto, Font fonte){
        PdfPCell c = new PdfPCell(new Phrase(texto, fonte)); // trecho de texto no pdf

        c.setPadding(5);
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
        c.setBackgroundColor(new Color(230, 230, 230));

        t.addCell(c);
    }

    private void inserirCelulaCorpo(PdfPTable t, String texto, Font fonte){
        PdfPCell c = new PdfPCell(new Phrase(texto, fonte));

        c.setPadding(5);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);
        t.addCell(c);
    }

    private void inserirCelulaNota(PdfPTable t, double valor, Font fonteNormal, Font fonteNegrito){
        String s = (valor == 0.0) ? "--" : String.valueOf(valor);
        Font f = (valor == 0.0) ? fonteNormal : fonteNegrito;

        PdfPCell c = new PdfPCell(new Phrase(s, f));
        c.setPadding(5);

        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c);
    }

    private void inserirCelulaObservacao(PdfPTable t, String observacao, Font fonteNormal, Font fonteItalico){
        String s = (observacao == null) ? "Não possui" : observacao;
        Font f = (observacao == null) ? fonteNormal : fonteItalico;

        PdfPCell c = new PdfPCell(new Phrase(s, f));
        c.setPadding(5);
        t.addCell(c);
    }
}
