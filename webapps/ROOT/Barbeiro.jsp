<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.BarbeiroService"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Barbeiro</title>
    <link rel="stylesheet" href="barbeiro.css">
</head>

<body>


    <header>
        <nav class="nav-bar">
            <div class="nav-list">
                <ul>
                    <li><a href="home.html" class=nav-link>Home</a></li>
                    <li><a href="Cliente.html" class="nav-link">Cliente</a></li>
                    <li><a href="Barbeiro.html" class="nav-link">Barbeiro</a></li>
                </ul>
            </div>
        </nav>
    </header>

    <div class="container">
        <div class="formu1">
            <form name="cadastrar_barbeiro" method="post" action="#">

                <h2 class="titulo">Cadastro</h2>

                <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
                <input value="<%= request.getParameter("nome") == null || request.getParameter("nome").isBlank() ? "" : request.getParameter("nome")%>" type="text" name="nome" placeholder="Digite o seu nome:"><br><br>
                <input value="<%= request.getParameter("email") == null || request.getParameter("email").isBlank() ? "" : request.getParameter("email")%>" type="email" name="email" placeholder="Digite o seu e-mail:"><br><br>
                <input value="<%= request.getParameter("senha") == null || request.getParameter("senha").isBlank() ? "" : request.getParameter("senha")%>" type="password" name="senha" placeholder="Digite a sua senha:"><br><br>

                <button type="submit" name="cadastrar" class="btn1">Cadastrar</button><br><br>

                <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
                <%

                    if (request.getParameter("cadastrar") != null){
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

        <div class="formu1">
            <form name="login_barbeiro" method="post" action="#">

                <h2 class="titulo">Login</h2>

                <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
                <input value="<%= request.getParameter("email") == null || request.getParameter("email").isBlank() ? "" : request.getParameter("email")%>" type="email" name="email" placeholder="Digite o seu e-mail:"><br><br>
                <input value="<%= request.getParameter("senha") == null || request.getParameter("senha").isBlank() ? "" : request.getParameter("senha")%>" type="password" name="senha" placeholder="Digite a sua senha:"><br><br>
                <input type="hidden" name="tipo" value="barbeiro">

                <button type="submit" name="entrar" class="btn1">Entrar</button><br><br>

                <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
                <span class="sucesso">Mensagem de erro</span>
                <span class="error">Mensagem de erro</span>
            </form>
        </div>
    </div>
</body>

</html>
