<%@ page import="com.hogwarts.model.banco.Aluno" %><%--
  Created by IntelliJ IDEA.
  User: daviramos-ieg
  Date: 16/02/2026
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%Aluno aluno = (Aluno) session.getAttribute("aluno");%>

<html>
<head>
    <title>Title</title>
</head>
<style>@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap');

body {
    margin: 0;
    padding: 0;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    font-family: 'Poppins', sans-serif;
    background-image: url('img/fundoTela.png');
    background-size: cover;
}

main {

    background: rgba(255, 255, 255, 0.2); 
    

    width: 500px; 
    
    padding: 40px;
    border-radius: 40px; 
    box-shadow: 0 20px 40px rgba(0,0,0,0.1);
    

    color: white; 
    
    
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    
    border: 1px solid rgba(255, 255, 255, 0.3);
    
    display: flex;
    flex-direction: column;
}

header {
    text-align: center;
    border-bottom: 1px solid rgba(255, 255, 255, 0.2);
    padding-bottom: 20px;
    margin-bottom: 25px;
    order: -1;
}

header h1 {
    font-size: 0.8rem;
    text-transform: uppercase;
    letter-spacing: 2px;
    color: white;
    margin: 0;
    opacity: 0.8;
}

h3 {
    font-size: 1.8rem;
    margin: 0 0 30px 0;
    font-weight: 600;
    text-align: center;
    color: white;
}

main p {
    margin: 0;
    font-size: 0.7rem;
    text-transform: uppercase;
    color: white; 
    letter-spacing: 1px;
    opacity: 0.7;
}

main p strong {
    display: block;
    font-size: 1rem;
    color: white; 
    text-transform: none;
    margin-bottom: 15px;
    font-weight: 400;
    margin-top: 2px;
}</style>
<body>
<form action="aluno-servlet" method="post" id="f">
    <input type="hidden" name="matricula" value="<%=aluno.getMatricula()%>">
</form>

<header>
    <h1>Portal do Aluno - <%=aluno.getCasaHogwarts().getNome()%></h1>
</header>

<main>
    <h3><%=aluno.getNome()%></h3>

    <p>Período:</p>
    <p><strong>Integral</strong></p>

    <p>Turma:</p>
    <p><strong>1ª Série <%=aluno.getCasaHogwarts().getNome()%></strong></p>

    <p>Email:</p>
    <p><strong><%=aluno.getEmail()%></strong></p>

    <p>CPF:</p>
    <p><strong><%=aluno.getCpf()%></strong></p>
</main>
</body>
</html>
