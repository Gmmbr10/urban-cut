<%

    if (request.getParameter("cadastrar") != null) {
        String[] diaSemana = request.getParameterValues("diaSemana[]");
        for (String dia : diaSemana){
            out.print(dia + "<br>");
        }
    }

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Urban Cut - Barbearia</title>
    <link rel="stylesheet" href="ConfigurandoHorarios.css">
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
            <input type="checkbox" name="diaSemana[]" value="Domingo">
            Domingo
        </label>
        <label>
            <input type="checkbox" name="diaSemana[]" value="Segunda-feira">
            Segunda-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana[]" value="Terça-feira">
            Terça-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana[]" value="Quarta-feira">
            Quarta-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana[]" value="Quinta-feira">
            Quinta-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana[]" value="Sexta-feira">
            Sexta-feira
        </label>
        <label>
            <input type="checkbox" name="diaSemana[]" value="Sabado">
            Sabado
        </label>

        <label for="horaAbertura">Hora que abre:</label>
        <input type="time" name="horaAbertura">

        <label for="horaAbertura">Hora que fecha:</label>
        <input type="time" name="horaFechamento">

        <button type="submit" name="cadastrar">Cadastrar</button>

    </form>
    
</body>
</html>