<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AtendimentoService"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.HorariosDisponiveisService"%>
<%@page import="com.urbancut.repositories.BarbeariaRepository"%>
<%@page import="com.urbancut.repositories.BarbeiroRepository"%>
<%@page import="com.urbancut.repositories.DiaFuncionamentoRepository"%>
<%@page import="com.urbancut.repositories.EnderecoRepository"%>
<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.models.Barbearia"%>
<%@page import="com.urbancut.models.DiaFuncionamento"%>
<%@page import="com.urbancut.models.Endereco"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.util.List"%>
<%
	AuthService authService = new AuthService();
	boolean isLogged = authService.isLogged(session);

    if (!isLogged) {
        response.sendRedirect("Cliente.jsp");
        return;
    }

    boolean isThatRule = authService.isThatRule(session,"cliente");

    if (!isThatRule) {
        authService.logout(session);
        response.sendRedirect("Cliente.jsp");
        return;
    }

	if ((request.getParameter("idBarbearia") == null || request.getParameter("idBarbearia").isBlank()) && session.getAttribute("idBarbeiro") == null) {
		response.sendRedirect("HomeCliente.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Urban Cut - Cliente</title>
		<link rel="stylesheet" href="../HomeCliente.css" />
		<link rel="stylesheet" href="../PaginaAgendamento.css" />
	</head>
	<body>
		<header>
			<nav class="nav-bar">
				<div class="nav-list">
					<ul>
						<li><a href="HomeCliente.jsp" class="nav-link">Home</a></li>
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

				<%

					if (request.getParameter("idBarbearia") != null && request.getParameter("idBarbeiro") == null) {

						Integer idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));

						Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);
						
						if (barbearia == null) {
							response.sendRedirect("HomeCliente.jsp");
							return;
						}

						barbearia.setBarbeiros(new BarbeariaRepository().searchBarbeiros(idBarbearia));

						for (Barbeiro b : barbearia.getBarbeiros()) {

							%>

								<div class="card" style="display:flex; justify-content: space-between; align-items: center;">
								
									<h2 style="margin: 0;"><%=b.getNome()%></h2>

									<a href="../Agendamento.jsp?idBarbearia=<%=b.getIdBarbearia()%>&idBarbeiro=<%=b.getIdBarbearia()%>" class="btn1">Ver disponibilidade</a>

								</div>

							<%

						}

					} else if (request.getParameter("idBarbearia") != null && request.getParameter("idBarbeiro") != null && session.getAttribute("idBarbearia") == null) {

						Integer idBarbearia = Integer.parseInt(request.getParameter("idBarbearia"));
						Integer idBarbeiro = Integer.parseInt(request.getParameter("idBarbeiro"));

						Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);
						
						if (barbearia == null) {
							response.sendRedirect("HomeCliente.jsp");
							return;
						}

						Barbeiro barbeiro = new BarbeiroRepository().searchById(idBarbeiro);
						
						if (barbeiro == null || barbeiro.getIdBarbearia() != barbearia.getIdBarbearia()) {
							response.sendRedirect("Agendamento.jsp?idBarbearia=" + barbearia.getIdBarbearia());
							return;
						}

						%>
						
							<form class="card" name="selecionar_dia" method="post" action="#">

								<h2 class="titulo">Escolha o dia que você deseja agendar</h2>

								<input type="hidden" name="idBarbearia" value="<%=request.getParameter("idBarbearia")%>">
								<input type="hidden" name="idBarbeiro" value="<%=request.getParameter("idBarbeiro")%>">

								<label>
									Data:
									<input type="date" name="diaEscolhido" required class="date-input" value="<%=request.getParameter("diaEscolhido")%>" />
								</label>

								<button type="submit" name="verificar" class="btn1">Verificar disponibilidade</button><br><br>

								<%
								
									if (request.getParameter("verificar") != null) {

										HorariosDisponiveisService horariosService = new HorariosDisponiveisService();
										Response<List<LocalTime>> resposta = horariosService.list(request);

										if (resposta.getStatusCode() == 204) {
											session.setAttribute("idBarbearia", request.getParameter("idBarbearia"));
											session.setAttribute("idBarbeiro", request.getParameter("idBarbeiro"));
											session.setAttribute("data", request.getParameter("diaEscolhido"));
											session.setAttribute("horarios", resposta.getDado());
											response.sendRedirect("Agendamento.jsp");
										} else {
											out.print("<span class=\"error\">");
											out.print(resposta.getMensagem());
											out.print("</span>");
										}

									}

								%>
							</form>
						
						<%

					} else if ( (session.getAttribute("idBarbeiro") != null && session.getAttribute("idBarbeiro") != null && session.getAttribute("data") != null) || request.getParameter("agendar") != null) {

						Integer idBarbearia = Integer.parseInt(session.getAttribute("idBarbearia").toString());
						Integer idBarbeiro = Integer.parseInt(session.getAttribute("idBarbeiro").toString());

						Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);
						
						if (barbearia == null) {
							session.removeAttribute("idBarbearia");
							session.removeAttribute("idBarbeiro");
							session.removeAttribute("data");
							session.removeAttribute("horarios");
							response.sendRedirect("HomeCliente.jsp");
							return;
						}

						Barbeiro barbeiro = new BarbeiroRepository().searchById(idBarbeiro);
						
						if (barbeiro == null || barbeiro.getIdBarbearia() != barbearia.getIdBarbearia()) {
							session.removeAttribute("idBarbearia");
							session.removeAttribute("idBarbeiro");
							session.removeAttribute("data");
							session.removeAttribute("horarios");
							response.sendRedirect("Agendamento.jsp?idBarbearia=" + barbearia.getIdBarbearia());
							return;
						}

						if (session.getAttribute("horarios") == null) {
							session.removeAttribute("idBarbearia");
							session.removeAttribute("idBarbeiro");
							session.removeAttribute("data");
							session.removeAttribute("horarios");
							response.sendRedirect("Agendamento.jsp?idBarbearia=" + barbearia.getIdBarbearia());
						}

						List<LocalTime> horarios = (List<LocalTime>) session.getAttribute("horarios");

						%>
						
							<form class="card" name="selecionar_dia" method="post" action="#">

								<h2 class="titulo">Escolha o dia que você deseja agendar</h2>

								<input type="hidden" name="idBarbearia" value="<%=session.getAttribute("idBarbearia")%>">
								<input type="hidden" name="idBarbeiro" value="<%=session.getAttribute("idBarbeiro")%>">
								<input type="hidden" name="idCliente" value="<%=session.getAttribute("id")%>">
								<input type="hidden" name="data" value="<%=session.getAttribute("data")%>" />

								<label>
									Horários disponíveis:
									<select name="horario">
										<%
										
											for (LocalTime time : horarios) {
												%>

													<option value="<%=time%>"><%=time%></option>

												<%
											}

										%>
									</select>
								</label>

								<button type="submit" name="agendar" class="btn1">Agendar</button><br><br>

								<%
								
									if (request.getParameter("agendar") != null) {

										AtendimentoService atendimentoService = new AtendimentoService();
										Response<Boolean> resposta = atendimentoService.register(request);

										if (resposta.getStatusCode() == 201) {
											session.removeAttribute("idBarbearia");
											session.removeAttribute("idBarbeiro");
											session.removeAttribute("data");
											session.removeAttribute("horarios");
											%>

												<script>

													alert("Agendamento realizado com sucesso!");

													window.location.href = "HomeCliente.jsp";

												</script>

											<%
										} else {
											out.print("<span class=\"error\">");
											out.print(resposta.getMensagem());
											out.print("</span>");
										}

									}

								%>

							</form>
						
						<%

					} else {
						response.sendRedirect("HomeCliente.jsp");
						return;
					}

				%>

			</section>
		</section>
	</body>
</html>