<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	AuthService authService = new AuthService();
	boolean isLogged = authService.isLogged(session);

    if (!isLogged) {
        response.sendRedirect("Barbeiro.jsp");
        return;
    }

    boolean isThatRule = authService.isThatRule(session,"barbeiro");

    if (!isThatRule) {
        authService.logout(session);
        response.sendRedirect("Barbeiro.jsp");
        return;
    }

    if ((boolean) session.getAttribute("hasBarbearia")) {
        response.sendRedirect("HomeBarbeiro.jsp");
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Barbeiro</title>
    <link rel="stylesheet" href="../Estabelecimento.css">
</head>
<body>

    <header>
        <nav class="nav-bar">
            <div class="nav-list">
                <ul>
                    <li><a href="../HomeBarbeiro.jsp" class=nav-link>Home</a></li>
                    <li><a href="../logout.jsp" class="nav-link">Sair</a></li>
                </ul>
            </div>
        </nav>
    </header>


    <main>
        <h1>Listagem de Estabelecimentos</h1>
        <div class="container">
            <form name="cadastro_estabelecimento" method="post" action="#">

                <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
                <input 
                    value="<%= request.getParameter("nome") == null|| request.getParameter("nome").isBlank() ? "" : request.getParameter("nome") %>"
                    type="text"
                    name="nome" 
                    placeholder="Nome do estabelecimento"
                ></br>
                <input 
                    value="<%= request.getParameter("tempoMedioAtendimento") == null|| request.getParameter("tempoMedioAtendimento").isBlank() ? "" : request.getParameter("tempoMedioAtendimento") %>"
                    type="number"
                    name="tempoMedioAtendimento" 
                    placeholder="Tempo médio de atendimento"
                ></br>
                <input 
                    value="<%= request.getParameter("cep") == null|| request.getParameter("cep").isBlank() ? "" : request.getParameter("cep") %>"
                    type="text"
                    name="cep" 
                    placeholder="CEP"
                ></br>
                <input 
                    value="<%= request.getParameter("estado") == null|| request.getParameter("estado").isBlank() ? "" : request.getParameter("estado") %>"
                    type="text"
                    name="estado" 
                    placeholder="Estado"
                ></br>
                <input 
                    value="<%= request.getParameter("cidade") == null|| request.getParameter("cidade").isBlank() ? "" : request.getParameter("cidade") %>"
                    type="text"
                    name="cidade" 
                    placeholder="Cidade"
                ></br>
                <input 
                    value="<%= request.getParameter("bairro") == null|| request.getParameter("bairro").isBlank() ? "" : request.getParameter("bairro") %>"
                    type="text"
                    name="bairro" 
                    placeholder="Bairro"
                ></br>
                <input 
                    value="<%= request.getParameter("logradouro") == null|| request.getParameter("logradouro").isBlank() ? "" : request.getParameter("logradouro") %>"
                    type="text"
                    name="logradouro" 
                    placeholder="Logradouro"
                ></br>
                <input 
                    value="<%= request.getParameter("numero") == null|| request.getParameter("numero").isBlank() ? "" : request.getParameter("numero") %>"
                    type="text"
                    name="numero" 
                    placeholder="Número"
                ></br>
                <input 
                    value="<%= request.getParameter("complemento") == null|| request.getParameter("complemento").isBlank() ? "" : request.getParameter("complemento") %>"
                    type="text"
                    name="complemento" 
                    placeholder="Complemento"
                ></br>
                
                <button name="cadastrar" type="submit">Cadastrar</button>

                <%

                    if (request.getParameter("cadastrar") != null) {
                        BarbeariaService service = new BarbeariaService();
                        Response<Boolean> resposta = service.register(request,session);

                        if (resposta.getStatusCode() == 201) {
                            response.sendRedirect("HomeBarbeiro.jsp");
                        } else {
                            out.print("<span class=\"error\">");
                            out.print(resposta.getMensagem());
                            out.print("</span>");
                        }
                    }

                %>

            </form>
        </div>
    </main>   
</body>
</html>