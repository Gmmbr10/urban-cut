<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.models.DiaFuncionamento"%>
<%@page import="com.urbancut.models.ResumoDia"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.repositories.ResumoDiaRepository"%>
<%@page import="com.urbancut.repositories.BarbeiroRepository"%>
<%@page import="com.urbancut.repositories.DiaFuncionamentoRepository"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.time.LocalDate"%>
<%
	AuthService service = new AuthService();
	boolean isLogged = service.isLogged(session);

    if (!isLogged) {
        response.sendRedirect("Barbeiro.jsp");
        return;
    }

    boolean isThatRule = service.isThatRule(session,"barbeiro");

    if (!isThatRule) {
        service.logout(session);
        response.sendRedirect("Barbeiro.jsp");
        return;
    }

    if (!((boolean) session.getAttribute("hasBarbearia"))) {
        response.sendRedirect("Estabelecimento.jsp");
    }

    if (((boolean) session.getAttribute("hasBarbearia")) && session.getAttribute("isDono") != null && ((boolean) session.getAttribute("isDono"))) {
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
		<link rel="stylesheet" href="../HomeBarbeiro.css" />
	</head>
	<body>
		<header>
			<nav class="nav-bar">
				<div class="nav-list">
					<ul>
						<li><a href="HomeBarbeiro.jsp" class="nav-link">Home</a></li>
                        <%
                        
                            if (session.getAttribute("isDono") != null && ((boolean) session.getAttribute("isDono"))) {
                                out.print("<li><a href=\"CadastroFuncionario.jsp\" class=\"nav-link\">Cadastrar Funcionario</a></li>");
                            }

                        %>
						<li>
							<a href="logout.jsp" class="nav-link">
                                Sair
                            </a>
						</li>
					</ul>
				</div>
			</nav>
		</header>

		<section>
			<section class="container-cards">
				<h1 class="tituloBarber">Resumo do dia</h1>

                <%
                
                    ResumoDia[] resumos = new ResumoDiaRepository().summary(LocalDate.now(),(int) session.getAttribute("id")).toArray(new ResumoDia[0]);

                    if (resumos.length <= 0) {
                        %>

                            <div class="card">
                                <p>Não encontramos nenhuma reserva!</p>
                            </div>

                        <%
                    } else {

                        for (ResumoDia r : resumos) {
                            %>

                                <div class="card">
                                    <p><span><%=r.getHorario()%></span> - <%=r.getCliente().getNome()%></p>
                                </div>

                            <%
                        }

                    }
                %>
			</section>
		</section>
	</body>
</html>