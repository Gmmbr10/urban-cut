<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Barbearia</title>
</head>
<body>

    <h1>Gerenciar Barbearia</h1>

    <form id="form">
        <input type="number" name="idDono" placeholder="Id do dono"><br><br>
        <input type="number" name="idBarbearia" placeholder="Id da barbearia"><br><br>
        <input type="text" name="nome" placeholder="Nome da barbearia"><br><br>
        <input type="text" name="urlMaps" placeholder="URL do Maps"><br><br>
        <input type="number" name="tempoMedioAtendimento" placeholder="Tempo médio"><br><br>

        <button type="button" onclick="cadastrar()">Cadastrar</button>
        <button type="button" onclick="buscar()">Buscar</button>
        <button type="button" onclick="atualizar()">Atualizar</button>
        <button type="button" onclick="deletar()">Deletar</button>
    </form>

    <pre id="resultado"></pre>

    <%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Boolean> resposta = service.register(request);
%>

<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Barbearia"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Barbearia> resposta = service.searchById(request);
%>

<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Barbearia"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Barbearia> resposta = service.searchById(request);
%>

<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Boolean> resposta = service.delete(request);
%>

    <script src="BarbeariaService.js"></script>
</body>
</html>
