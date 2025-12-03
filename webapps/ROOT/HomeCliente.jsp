<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.repositories.BarbeariaRepository"%>
<%@page import="com.urbancut.repositories.DiaFuncionamentoRepository"%>
<%@page import="com.urbancut.repositories.EnderecoRepository"%>
<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.models.Barbearia"%>
<%@page import="com.urbancut.models.DiaFuncionamento"%>
<%@page import="com.urbancut.models.Endereco"%>
<%@page import="java.util.List"%>
<%
	AuthService service = new AuthService();
	boolean isLogged = service.isLogged(session);

    if (!isLogged) {
        response.sendRedirect("Cliente.jsp");
        return;
    }

    boolean isThatRule = service.isThatRule(session,"cliente");

    if (!isThatRule) {
        service.logout(session);
        response.sendRedirect("Cliente.jsp");
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
				<h1 class="tituloBarber">Barbearias disponíveis</h1>

				<%
                
                    BarbeariaRepository barbeariaRepository = new BarbeariaRepository();
                    DiaFuncionamentoRepository diaRepository = new DiaFuncionamentoRepository();

                    List<Barbearia> barbearias = barbeariaRepository.all();

                    for (Barbearia b : barbearias) {

                        List<DiaFuncionamento> dias = diaRepository.searchByBarbearia(b.getIdBarbearia());
                        Endereco endereco = new EnderecoRepository().searchById(b.getIdEndereco());

                        b.setDiasFuncionamento(dias.toArray(new DiaFuncionamento[0]));

                %>

                    <div class="card">
                        <h2><%=b.getNome()%></h2>

                        <h3>Atendemos nos seguintes dias:</h3>
                        <ul>
                            <%
                            
                                for (DiaFuncionamento d : b.getDiasFuncionamento()){
                                    %>

                                        <li>
                                            <%=d.getDiaSemana()%>:
                                            <%=d.getHoraAbertura()%> às
                                            <%=d.getHoraFechamento()%>
                                        </li>

                                    <%
                                }
                            
                            %>
                        </ul>

                        <h3>Endereço</h3>
                        <ul>
                            <li><%=endereco.getCep()%> - <%=endereco.getEstado()%> - <%=endereco.getCidade()%> - <%=endereco.getBairro()%> - <%=endereco.getLogradouro()%></li>
                            <li>Número: <%=endereco.getNumero()%></li>
                            <%=endereco.getComplemento() == null || endereco.getComplemento().isBlank() ? "" : "<li>Complemento: " + endereco.getComplemento() + "</li>"%>
                        </ul>
                    </div>

                <%
                    }

                %>
			</section>
		</section>
	</body>
</html>