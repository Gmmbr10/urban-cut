<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.ClienteService"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Cliente</title>
    <link rel="stylesheet" href="Cliente.css">
</head>

<body>


     <header>
        <nav class="nav-bar">
            <div class="nav-list">
                <ul>
                    <li><a href="home.html" class=nav-link>Home</a></li>
                    <li><a href="Cliente.jsp" class="nav-link">Cliente</a></li>
                    <li><a href="Barbeiro.jsp" class="nav-link">Barbeiro</a></li>
                    <li><a href="ConfigurandoHorarios.jsp" class="nav-link">Horários</a></li>
                    <li><a href="CadastroEstabelecimento.html" class="nav-link">Estabelecimento</a></li>
                    <li><a href="ListagemdoEstabelecimento.html" class="nav-link">Estabelecimento</a></li>
                    <li><a href="PaginaAgendamento.html" class="nav-link">Agendamento</a></li>
                </ul>
            </div>
        </nav>
    </header>

    <div class="container">
        <div class="formu1">
            <form name="cadastrar_cliente" method="post" action="#">

                <h2 class="titulo">Cadastro</h2>

                <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
                <input value="<%= request.getParameter("nome") == null || request.getParameter("nome").isBlank() ? "" : request.getParameter("nome")%>" type="text" name="nome" placeholder="Digite o seu nome:"><br><br>
                <input value="<%= request.getParameter("email") == null || request.getParameter("email").isBlank() ? "" : request.getParameter("email")%>" type="email" name="email" placeholder="Digite o seu e-mail:"><br><br>
                <input value="<%= request.getParameter("senha") == null || request.getParameter("senha").isBlank() ? "" : request.getParameter("senha")%>" type="password" name="senha" placeholder="Digite a sua senha:"><br><br>

                <button type="submit" name="cadastrar" class="btn1">Cadastrar</button><br><br>

                <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
                <%

                    if (request.getParameter("cadastrar") != null){
                        ClienteService service = new ClienteService();
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

        <div class="formu1">
            <form name="login_cliente" method="post" action="#">

                <h2 class="titulo">Login</h2>

                <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
                <input value="<%= request.getParameter("email") == null || request.getParameter("email").isBlank() ? "" : request.getParameter("email")%>" type="email" name="email" placeholder="Digite o seu e-mail:"><br><br>
                <input value="<%= request.getParameter("senha") == null || request.getParameter("senha").isBlank() ? "" : request.getParameter("senha")%>" type="password" name="senha" placeholder="Digite a sua senha:"><br><br>
                <input type="hidden" name="tipo" value="cliente">

                <button type="submit" name="entrar" class="btn1">Entrar</button><br><br>

                <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
                <%

                    if (request.getParameter("entrar") != null){
                        AuthService service = new AuthService();
	                    Response<Boolean> resposta = service.login(request,session);

                        if (resposta.getStatusCode() == 204) {
                            response.sendRedirect("HomeCliente.jsp");
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