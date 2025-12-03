<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.DiaFuncionamentoService"%>
<%@page import="com.urbancut.models.DiaFuncionamento"%>
<%@page import="com.urbancut.repositories.DiaFuncionamentoRepository"%>
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

    if (!((boolean) session.getAttribute("isDono"))) {
        response.sendRedirect("HomeBarbeiro.jsp");
    }

    DiaFuncionamento[] dias = new DiaFuncionamentoRepository().searchByBarbearia((int) session.getAttribute("idBarbearia")).toArray(new DiaFuncionamento[0]);

    if (dias.length >= 1) {
        response.sendRedirect("HomeBarbeiro.jsp");
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Horarios</title>
    <link rel="stylesheet" href="ConfigurandoHorarios.css">
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

    <form name="configurar_horarios" method="post" action="#">

        <h2>Configure o horário do seu estabelecimento!</h2>
        <p>
            Selecione os dias que seu estabelecimento abre e indique o horário.
        </p>

        <label>
            <input type="checkbox" name="diaSemana" value="Domingo">
            Domingo
        </label>
        <label>
            <input type="checkbox" name="diaSemana" value="Segunda-Feira">
            Segunda-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana" value="Terca-Feira">
            Terça-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana" value="Quarta-Feira">
            Quarta-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana" value="Quinta-Feira">
            Quinta-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana" value="Sexta-Feira">
            Sexta-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana" value="Sabado">
            Sabado
        </label>

        <label for="horaAbertura">Hora que abre:</label>
        <input type="time" name="horaAbertura">

        <label for="horaFechamento">Hora que fecha:</label>
        <input type="time" name="horaFechamento">

        <button type="submit" name="cadastrar" style="display:block; margin: 1.5rem auto">Cadastrar</button>

        <%

            if (request.getParameter("cadastrar") != null) {
                DiaFuncionamentoService service = new DiaFuncionamentoService();
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
    
</body>
</html>