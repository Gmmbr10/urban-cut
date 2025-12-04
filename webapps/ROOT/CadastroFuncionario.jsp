<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.models.DiaFuncionamento"%>
<%@page import="com.urbancut.models.ResumoDia"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.BarbeiroService"%>
<%@page import="com.urbancut.repositories.ResumoDiaRepository"%>
<%@page import="com.urbancut.repositories.BarbeiroRepository"%>
<%@page import="com.urbancut.repositories.DiaFuncionamentoRepository"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.time.LocalDate"%>
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

    if (!((boolean) session.getAttribute("hasBarbearia"))) {
        response.sendRedirect("Estabelecimento.jsp");
    }

    if (session.getAttribute("isDono") == null || !((boolean) session.getAttribute("isDono"))) {
        response.sendRedirect("HomeBarbeiro.jsp");
    }

    if (((boolean) session.getAttribute("hasBarbearia")) && ((boolean) session.getAttribute("isDono"))) {
        DiaFuncionamento[] dias = new DiaFuncionamentoRepository().searchByBarbearia((int) session.getAttribute("idBarbearia")).toArray(new DiaFuncionamento[0]);

        if (dias.length <= 0) {
            response.sendRedirect("ConfigurandoHorarios.jsp");
        }
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Urban Cut - Barbeiro</title>
		<link rel="stylesheet" href="../barbeiro.css" />
	</head>
	<body>
		<header>
			<nav class="nav-bar">
				<div class="nav-list">
					<ul>
						<li><a href="HomeBarbeiro.jsp" class="nav-link">Home</a></li>
						<li><a href="CadastroFuncionario.jsp" class="nav-link">Cadastrar Funcionario</a></li>
						<li>
							<a href="logout.jsp" class="nav-link">
                                Sair
                            </a>
						</li>
					</ul>
				</div>
			</nav>
		</header>

		<div class="container">
            <div class="formu1">
                <form name="cadastrar_barbeiro" method="post" action="#">

                    <h2 class="titulo">Cadastrar funcionario</h2>

                    <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
                    <input value="<%= request.getParameter("cadastrar") == null ? "" : request.getParameter("nome") == null || request.getParameter("nome").isBlank() ? "" : request.getParameter("nome")%>" type="text" name="nome" placeholder="Digite o nome do funcionário:"><br><br>
                    <input value="<%= request.getParameter("cadastrar") == null ? "" : request.getParameter("email") == null || request.getParameter("email").isBlank() ? "" : request.getParameter("email")%>" type="email" name="email" placeholder="Digite o e-mail do funcionário:"><br><br>
                    <input value="<%= request.getParameter("cadastrar") == null ? "" : request.getParameter("senha") == null || request.getParameter("senha").isBlank() ? "" : request.getParameter("senha")%>" type="password" name="senha" placeholder="Digite uma senha para ele:"><br><br>
                    <input type="hidden" name="idBarbearia" value="<%=(int) session.getAttribute("idBarbearia")%>">

                    <button type="submit" name="cadastrar" class="btn1">Cadastrar</button><br><br>

                    <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
                    <%

                        if (request.getParameter("cadastrar") != null){
                            if (session.getAttribute("idBarbearia").equals(request.getParameter("idBarbearia"))) {
                                request.getRequestDispatcher("CadastroFuncionario.jsp").forward(request, response);
                            }

                            BarbeiroService service = new BarbeiroService();
                            Response<Boolean> resposta = service.register(request);

                            if (resposta.getStatusCode() == 201) {
                                out.print("<span class=\"sucesso\">Cadastro realizado com sucesso!</span>");
                            } else {
                                out.print("<span class=\"error\">");
                                out.print(resposta.getMensagem());
                                out.print("</span>");
                            }
                        }

                    %>

                </form>
            </div>
        </div>
	</body>
</html>