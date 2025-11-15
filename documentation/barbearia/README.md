[Voltar](../README.md)

# Documentação Barbearia

- [Criar barbearia](#create)
- [Buscar barbearia](#search)
- [Atualizar barbearia](#update)
- [Deletar barbearia](#delete)

<h2 id="create">Criar barbearia</h2>
Para salvar um barbearia, será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
> 
> - Dado: nome do parâmetro durante o envio

- Id do dono: `idDono`
- Nome da barbearia: `nome`
- Url do maps da barbearia: `urlMaps`
- Tempo médio do atendimento (em minutos): `tempoMedioAtendimento`

Para registrar a barbearia, será necessário usar o método `register()` da classe `BarbeariaService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre o retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Boolean> resposta = service.register(request);
%>
```

<h2 id="search">Buscar barbearia</h2>
Para buscar por uma barbearia, será necessário enviar o seguinte dado:

- Id da barbearia: `idBarbearia`

Para buscar a barbearia, será necessário usar o método `searchById()` da classe `BarbeariaService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Barbearia>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Barbearia"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Barbearia> resposta = service.searchById(request);
%>
```

<h2 id="update">Atualizar barbearia</h2>
Para atualizar uma barbearia, será necessário enviar os seguintes dados:

- Id do dono: `idDono`
- Id da barbearia: `idBarbearia`
- Nome da barbearia: `nome`
- Email da barbearia: `email`
- Senha da barbearia: `senha`

Para atualizar a barbearia, será necessário usar o método `update()` da classe `BarbeariaService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Boolean> resposta = service.update(request);
%>
```

<h2 id="delete">Deletar barbeiro</h2>
Para excluir uma barbearia, será necessário enviar o seguinte dado:

- Id da barbearia: `idBarbearia`

Para deletar a barbearia, será necessário usar o método `delete()` da classe `BarbeariaService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeariaService"%>
<%
	BarbeariaService service = new BarbeariaService();
	Response<Boolean> resposta = service.delete(request);
%>
```
