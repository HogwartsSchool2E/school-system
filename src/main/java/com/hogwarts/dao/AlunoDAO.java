package com.hogwarts.dao;

import com.hogwarts.model.*;
import com.hogwarts.model.banco.*;
import com.hogwarts.utils.Conexao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AlunoDAO {

//    Método de geração de boletim de um aluno
    public List<Boletim> gerarBoletimIndividual(int matricula){
        // Criando atributos
        List<Boletim> boletins = new ArrayList<>();
        String sql = """
                SELECT a.nome as "aluno", a.matricula, d.nome as "disciplina", d.id, n.nota_um, n.nota_dois, o.observacao, o.id as "ob_id", p.nome as "professor", c.nome as "casa_hogwarts"
                FROM aluno a
                JOIN casa_hogwarts c ON a.cod_casa = c.id
                JOIN disciplina d ON d.id IN (SELECT cod_disciplina FROM nota WHERE cod_aluno = a.matricula)
                LEFT JOIN nota n ON n.cod_aluno = a.matricula AND n.cod_disciplina = d.id
                JOIN professor p ON p.cod_disciplina = d.id
                LEFT JOIN observacao o ON o.cod_aluno = a.matricula AND d.id = o.cod_disciplina
                WHERE a.matricula = ?
                ORDER BY a.nome;
                """;

        // Realizando busca no banco de dados-
        try ( Connection conn = Conexao.conectar();
              PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // Inserindo os atributos e recebendo o SELECT
            pstmt.setInt(1, matricula);
            ResultSet rs = pstmt.executeQuery();

            // Inserindo os valores nos objetos
            while (rs.next()){
                Disciplina d = new Disciplina();
                Nota n = new Nota();
                Observacao o = new Observacao();
                Aluno a = new Aluno();
                Professor p = new Professor();
                CasaHogwarts c = new CasaHogwarts();

                // Capturando valores da seleção
                d.setNome(rs.getString("disciplina"));
                d.setId(rs.getInt("id"));

                n.setNotaUm(rs.getDouble("nota_um"));
                n.setNotaDois(rs.getDouble("nota_dois"));

                o.setObservacao(rs.getString("observacao"));
                o.setId(rs.getInt("ob_id"));

                a.setNome(rs.getString("aluno"));
                a.setMatricula(rs.getInt("matricula"));

                p.setNome(rs.getString("professor"));

                c.setNome(rs.getString("casa_hogwarts"));

                // Geração do boletim
                boletins.add(new Boletim(a, n.getNotaUm(), n.getNotaDois(), o, d, p, c));
            } return boletins;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } return null;
    }

//    Método de geração de boletim de todos os alunos
    public List<Boletim> gerarBoletimTodos(){
        // Criando atributos
        List<Boletim> boletins = new ArrayList<>();
        String sql = """
                SELECT a.nome as "aluno", a.matricula, d.nome as "disciplina", d.id, n.nota_um, n.nota_dois, o.observacao, o.id as "ob_id", p.nome as "professor", c.nome as "casa_hogwarts"
                FROM aluno a
                JOIN casa_hogwarts c ON a.cod_casa = c.id
                JOIN disciplina d ON d.id IN (SELECT cod_disciplina FROM nota WHERE cod_aluno = a.matricula)
                LEFT JOIN nota n ON n.cod_aluno = a.matricula AND n.cod_disciplina = d.id
                JOIN professor p ON p.cod_disciplina = d.id
                LEFT JOIN observacao o ON o.cod_aluno = a.matricula AND d.id = o.cod_disciplina
                ORDER BY a.nome;""";

        // Realizando busca no banco de dados
        try ( Connection conn = Conexao.conectar();
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql)
                ){

            // Inserindo os valores nos objetos
            while (rs.next()){
                Disciplina d = new Disciplina();
                Nota n = new Nota();
                Observacao o = new Observacao();
                Aluno a = new Aluno();
                Professor p = new Professor();
                CasaHogwarts c = new CasaHogwarts();

                // Capturando valores da seleção
                d.setNome(rs.getString("disciplina"));
                d.setId(rs.getInt("id"));

                n.setNotaUm(rs.getDouble("nota_um"));
                n.setNotaDois(rs.getDouble("nota_dois"));

                o.setObservacao(rs.getString("observacao"));
                o.setId(rs.getInt("ob_id"));

                a.setNome(rs.getString("aluno"));
                a.setMatricula(rs.getInt("matricula"));

                p.setNome(rs.getString("professor"));

                c.setNome(rs.getString("casa_hogwarts"));

                // Geração do boletim
                boletins.add(new Boletim(a, n.getNotaUm(), n.getNotaDois(), o, d, p, c));
            } return boletins;
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } return null;
    }

    // Método de login
    public boolean login(String nome, String senha) throws ClassNotFoundException, SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        boolean retorno = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT 1 FROM ALUNO WHERE NOME = ? AND SENHA = ?");
            pstmt.setString(1, nome);
            pstmt.setString(2, senha);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                retorno = true; // é aluno
                // false = não é aluno
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            conexao.desconectar(conn);
        }
        return retorno;
    }

    // Validar cpf
    public static boolean validarCpf(String cpf){
        String caminho = "src/main/java/com/hogwarts/dao/cpfs.txt";
        File arquivo = new File(caminho);

        List<String> texto = new ArrayList<>();
        int contador = 0;

        try{
            Scanner inFile = new Scanner(arquivo);

            while(inFile.hasNextLine()){
                String linha = inFile.nextLine();
                texto.add(linha);
            }

            inFile.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo));

            if(texto.contains(cpf)){
                texto.remove(cpf);
                while(contador < texto.size()){
                    bw.write(texto.get(contador));
                    bw.newLine();
                    contador ++;
                }
                return true;
            }

            bw.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    // Método cadastrar alunos (admin)

    public boolean cadastrar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        int retorno = 0;
        if (validarCpf(aluno.getCpf())){
            try {
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ALUNO(NOME, CPF, EMAIL, SENHA, COD_CASA) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, aluno.getNome());
                pstmt.setString(2, aluno.getCpf());
                pstmt.setString(3, aluno.getEmail());
                pstmt.setString(4, aluno.getSenha());
                pstmt.setInt(5, aluno.getCasaHogwarts().getId());
                retorno = pstmt.executeUpdate();
                if (retorno > 0) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int matricula = rs.getInt(1);
                        aluno.setMatricula(matricula);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexao.desconectar(conn);
            }
        }
        return retorno > 0;
    }
}
