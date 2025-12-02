<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Cliente</title>
    <link rel="stylesheet" href="../HomeCliente.css">
</head>
<body>
    
    <header>
        <nav class="nav-bar">
            <div class="nav-list">
                <ul>
                    <li><a href="HomeCliente.html" class=nav-link>Home</a></li>
                    <li><a href="ListagemdoEstabelecimento.html" class="nav-link">Listagem de Estabelecimento</a></li>
                    <li><a href="logout.jsp" class="nav-link">Sair</a></li>
                </ul>
            </div>
        </nav>
    </header>

    <section class="Conjunto">

        <h1 class="tituloBarber">Barbearias disponíveis</h1>

        <section>
        
            <section>
                <div class="barbearia1">
                    <h2>Nome da barbearia</h2>
                    <!-- pode deixar o tamanho das letras das listas pequenas -->
                    <ul>
                        <h3>Atendemos nos seguintes dias:</h3>
                        <li>Terça-feira: 00:00 às 00:00</li>
                        <li>Quarta-feira: 00:00 às 00:00</li>
                        <li>Quinta-feira: 00:00 às 00:00</li>
                        <li>Sexta-feira: 00:00 às 00:00</li>
                        <li>Sabado: 00:00 às 00:00</li>
                        <li>Domingo: 00:00 às 00:00</li>
                    </ul>
                    
                    <ul>
                        <h3 class="subtitulo">Endereço</h3>
                        <li>Estado - Cidade</li>
                        <li>Logradouro, Número, Complemento</li>
                    </ul>
                </div>
            </section>

            <section>
                <div class="barbearia1">
                    <h2 class="titulo2">Nome da barbearia</h2>
                    <ul>
                        <h3>Atendemos nos seguintes dias:</h3>
                        <li>Terça-feira: 00:00 às 00:00</li>
                        <li>Quarta-feira: 00:00 às 00:00</li>
                        <li>Quinta-feira: 00:00 às 00:00</li>
                        <li>Sexta-feira: 00:00 às 00:00</li>
                        <li>Sabado: 00:00 às 00:00</li>
                        <li>Domingo: 00:00 às 00:00</li>
                    </ul>
                    <ul>
                        <h3 class="subtitulo">Endereço</h3>
                        <li>Estado - Cidade</li>
                        <li>Logradouro, Número, Complemento</li>
                        </ul>
                </div>
            </section>

            <section>
                <div class="barbearia1">
                <h2 class="titulo3">Nome da barbearia</h2>
                    <ul>
                        <h3>Atendemos nos seguintes dias:</h3>
                        <li>Terça-feira: 00:00 às 00:00</li>
                        <li>Quarta-feira: 00:00 às 00:00</li>
                        <li>Quinta-feira: 00:00 às 00:00</li>
                        <li>Sexta-feira: 00:00 às 00:00</li>
                        <li>Sabado: 00:00 às 00:00</li>
                        <li>Domingo: 00:00 às 00:00</li>
                    </ul>
                    <ul>
                        <h3 class="subtitulo">Endereço</h3>
                        <li>Estado - Cidade</li>
                        <li>Logradouro, Número, Complemento</li>
                    </ul>
                </div>
            </section>

        </section>

    </section>

</body>
</html>