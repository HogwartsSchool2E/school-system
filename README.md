# Escola de Magia e Bruxaria de Hogwarts

## Resumo
Sistema para a gestão da Escola de Magia e Bruxaria de Hogwarts. O sistema foi feito principalmente com Java. O sistema é separado em três áreas principais: aluno, professor e administrador.

## Funcionalidades

### Funcionalidades Principais
- **CRUD de alunos, professores e disciplinas**: Na área do administrador, possível alteração mais próxima as tabelas do banco
- **Gerenciamento das casas de Hogwarts**: Edição dos gestores e da pontuação por parte do admin
- **Lançamento de notas**: Lançamento das duas notas e edição/inserção/exclusão dinâmica na UI para facilitar as operações
- **Boletim dinâmico**: Atualizado no banco automáticamente de forma a atualização ser rápida
- **Buscas de alunos**: Busca por select-option e por input:text direto 

### Funcionalidades Secundárias
- **Login**: Login dinâmico com regex para identificar se usuário é aluno ou professor
- **Recuperação de senha**: Usando API com JavaScript para nova senha
- **Dashboards**: Alunos ativos, média das casas, ranking de alunos e quadro de observações
- **Segurança de dados**: Com hash e pgcrypto
- **Mensagens e tratamento de erros**: Todos centralizados em `pagina-erro.jsp` com mensagens que ajudam o usuário a identificar os problemas
- **Exportar boletim para PDF**: Usando a OpenPDF para escrever o boletim atualizado com o banco

## Exceções personalizadas

Exceção `RegexMatchException`, utilizada no projeto para indicar erros relacionados à validação por regex

Exemplo: (`servlets/admin/AlunoServlet.java`: `inserirAluno()`)
```java
if (!Regex.checarCpf(cpf)) throw new RegexMatchException("Digite um CPF válido.");
if (!Regex.checarEmail(email)) throw new RegexMatchException("O email digitado é inválido e não segue os padrões da escola.");
```

## Utils

### `Conexao.java`
- Captura variáveis do `.env`
- Método `conectar()` que retorna `Connection` com o banco de dados
- Método `desconectar()` método depreciado de desconexão do banco

### `Formatador.java`
- Único método `mostrar()` para mais fácil visualização de dados do usuário

### `Hash.java`
- Método de hash para senhas
  - Geração de hash (SHA-256) para anonimização de senhas
  - Criando hash em bytes com `MessageDigest` e convertendo para hexadecimal
- Método de comparação de senhas

### `Regex.java`
- Diversos métodos de verificação de dados com regex
- Método para transformar Strings formatados em números limpos
- Formatação de CPF para banco de dados

## Tecnologias

### Backend
- **Java 21**: Linguagem principal da aplicação
- **JSP**: Rederização das páginas do sistema

### Frontend
- **HTML**: Estrutura das páginas de aplicação
- **CSS**: Estilização das páginas, incluindo estilos específicos para cada área do sistema
- **JavaScript**: Utilizado para validações, interações na interface e manipulação de formulários

### Banco de Dados
- **PostgreSQL**: Gerenciador de banco para armazenamnto de informações do sistema

### Dependências do `pom.xml`
- **JUnit**: Testes de aplicação
- **java-dotenv**: Carregar variáveis de ambiente a partir de `.env`
- **OpenPDF**: Geração de arquivos PDF dinamicamente
- **Jakarta Servlet API**: Classes e interfaces para aplicações web em Java
- **Hibernate ORM**: Mapear classes Java para tabelas do banco de dados
- **JAXB Runtime**: Processamento do XML para o Hibernate
- **Hibernate Validator**: Validação de dados diretamente nas entidades com anotações
- **PostgreSQL Driver**: Driver JDBC do PostgreSQL


## Estrutura do projeto
```
├── 📁 .github
│   └── 📁 appmod
│       └── 📁 appcat
├── 📁 src
│   └── 📁 main
│       ├── 📁 java
│       │   ├── 📁 com
│       │   │   └── 📁 hogwarts
│       │   │       ├── 📁 dao
│       │   │       │   ├── 📁 admin
│       │   │       │   │   ├── ☕ AdminDAO.java
│       │   │       │   │   ├── ☕ CasaDAO.java
│       │   │       │   │   └── ☕ DisciplinaDAO.java
│       │   │       │   ├── ☕ AlunoDAO.java
│       │   │       │   ├── ☕ DashboardDAO.java
│       │   │       │   ├── ☕ NotaDAO.java
│       │   │       │   └── ☕ ObservacaoDAO.java
│       │   │       ├── 📁 exceptions
│       │   │       │   └── ☕ RegexMatchException.java
│       │   │       ├── 📁 model
│       │   │       │   ├── 📁 banco
│       │   │       │   │   ├── ☕ Aluno.java
│       │   │       │   │   ├── ☕ CasaHogwarts.java
│       │   │       │   │   ├── ☕ Disciplina.java
│       │   │       │   │   ├── ☕ Nota.java
│       │   │       │   │   ├── ☕ Observacao.java
│       │   │       │   │   ├── ☕ Professor.java
│       │   │       │   │   └── ☕ Usuario.java
│       │   │       │   ├── 📁 enums
│       │   │       │   │   └── ☕ Situacao.java
│       │   │       │   ├── ☕ Boletim.java
│       │   │       │   ├── ☕ Dashboard.java
│       │   │       │   └── ☕ QuadroObservacoes.java
│       │   │       ├── 📁 servlets
│       │   │       │   ├── 📁 admin
│       │   │       │   │   ├── ☕ AdminServlet.java
│       │   │       │   │   ├── ☕ AlunoServlet.java
│       │   │       │   │   ├── ☕ CasaServlet.java
│       │   │       │   │   └── ☕ DisciplinaServlet.java
│       │   │       │   ├── ☕ BoletimServlet.java
│       │   │       │   ├── ☕ DashboardServlet.java
│       │   │       │   ├── ☕ DownloadServlet.java
│       │   │       │   ├── ☕ LoginServlet.java
│       │   │       │   ├── ☕ NotaServlet.java
│       │   │       │   ├── ☕ ObservacoesServlet.java
│       │   │       │   ├── ☕ ProfessorServlet.java
│       │   │       │   └── ☕ RecuperarSenhaServlet.java
│       │   │       └── 📁 utils
│       │   │           ├── ☕ Conexao.java
│       │   │           ├── ☕ Formatador.java
│       │   │           ├── ☕ Hash.java
│       │   │           ├── ☕ Regex.java
│       │   │           └── 📄 cpfs.txt
│       │   └── 📁 test
│       │       └── ☕ Teste.java
│       ├── 📁 resources
│       └── 📁 webapp
│           ├── 📁 WEB-INF
│           │   ├── 📁 admin
│           │   │   ├── 📄 alunos.jsp
│           │   │   ├── 📄 casas.jsp
│           │   │   ├── 📄 disciplinas.jsp
│           │   │   └── 📄 inicial.jsp
│           │   ├── 📁 aluno
│           │   │   ├── 📄 boletim.jsp
│           │   │   ├── 📄 inicial.jsp
│           │   │   └── 📄 perfil.jsp
│           │   ├── 📁 prof
│           │   │   ├── 📄 boletim-individual.jsp
│           │   │   ├── 📄 boletim-todos.jsp
│           │   │   ├── 📄 cadastrar-observacao.jsp
│           │   │   └── 📄 inicial.jsp
│           │   ├── 📄 dashboard.jsp
│           │   ├── 📄 pagina-erro.jsp
│           │   ├── 📄 recuperarSenha.jsp
│           │   └── ⚙️ web.xml
│           ├── 📁 assets
│           │   ├── 📁 css
│           │   │   ├── 📁 boletins/...
│           │   │   ├── 📁 cadastro/...
│           │   │   ├── 📁 casas/...
│           │   │   ├── 📁 iniciais/...
│           │   │   ├── 📁 servlet-pages/...
│           │   │   ├── 🎨 fontes.css
│           │   │   ├── 🎨 modal.css
│           │   │   └── 🎨 pagina-erro.css
│           │   ├── 📁 fonts
│           │   │   ├── 📄 harry-p.ttf
│           │   │   └── 📄 magic-school-one.ttf
│           │   ├── 📁 icons
│           │   │   ├── 📄 favicon.ico
│           │   ├── 📁 images
│           │   │   ├── 📁 landing-page/...
│           │   │   ├── 📁 pags-alunos/...
│           │   ├── 📁 js
│           │   │   ├── 📄 cadastro.js
│           │   │   ├── 📄 recuperarSenha.js
│           │   │   ├── 📄 regex.js
│           │   │   └── 📄 script.js
│           │   └── 📁 video
│           │       └── 🎬 hogwarts.mp4
│           ├── 📁 pages
│           │   ├── 🌐 login.html
│           │   └── 🌐 recuperarSenha.html
│           └── 🌐 index.html
├── ⚙️ .gitignore
├── 📄 LICENSE
├── 📝 README.md
├── ⚙️ pom.xml
└── 📄 postgresql-42.7.7.jar
```