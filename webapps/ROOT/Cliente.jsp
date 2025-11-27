<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%@page import="com.urbancut.services.ClienteService"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Cliente</title>
</head>
<body>

    <form name="cadastrar_cliente" method="post" action="#">

        <h2>Cadastrar</h2>

        <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
        <input type="text" name="nome">
        <input type="email" name="email">
        <input type="password" name="senha">

        <button type="submit" name="cadastrar">Cadastrar</button>

        <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
        <%
        
            if (request.getParameter("cadastrar") != null){
                ClienteService service = new ClienteService();
                Response<Boolean> resposta = service.register(request);

                if (resposta.getStatusCode() == 201) {
                    out.print("<p>Cadastro realizado com sucesso!</p>");
                } else {
                    out.print("<p>");
                    out.print(resposta.getMensagem());
                    out.print("</p>");
                }
            }

        %>

    </form>

    <form name="login_cliente" method="post" action="#">

        <h2>Entrar</h2>

        <!-- NÃO ALTERAR O NAME DOS INPUTS ABAIXO -->
        <input type="email" name="email">
        <input type="password" name="senha">
        <input type="hidden" name="tipo" value="cliente">

        <button type="submit" name="entrar">Entrar</button>

        <!-- NÃO ALTERAR A ESTRUTURA ABAIXO -->
        <span>Mensagem de erro</span>

    </form>
    
</body>
</html>