[Voltar](../README.md)

# Documentação Cliente

- [Criar cliente](#create)
- [Buscar cliente](#search)
- [Atualizar cliente](#update)
- [Deletar cliente](#delete)

<h2 id="create">Criar cliente</h2>
Para salvar um cliente, será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
> 
> - Dado: nome do parâmetro durante o envio

- Nome do cliente: `nome`
- Email do cliente: `email`
- Senha do cliente: `senha`

Para registrar o cliente, será necessário usar o método `register()` da classe `ClienteService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre o retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.ClienteService"%>
<%
	ClienteService service = new ClienteService();
	Response<Boolean> resposta = service.register(request);
%>
```

<h2 id="search">Buscar cliente</h2>
Para buscar por um cliente, será necessário enviar o seguinte dado:

- Id do cliente: `idCliente`

ou

- Email do cliente: `email`

Para buscar o cliente usando o id, será necessário usar o método `searchById()` da classe `ClienteService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Para buscar o cliente usando o email, será necessário usar o método `searchByEmail()` da classe `ClienteService`.

Como resposta, o método retornará um objeto do tipo `Response<Cliente>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Cliente"%>
<%@page import="com.urbancut.services.ClienteService"%>
<%
	ClienteService service = new ClienteService();
	Response<Cliente> resposta = service.searchById(request);
	// ou
	Response<Cliente> resposta = service.searchByEmail(request);
%>
```

<h2 id="update">Atualizar cliente</h2>
Para atualizar um cliente, será necessário enviar os seguintes dados:

- Id do cliente: `idCliente`
- Nome do cliente: `nome`
- Email do cliente: `email`
- Senha do cliente: `senha`

Para atualizar o cliente, será necessário usar o método `update()` da classe `ClienteService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.ClienteService"%>
<%
	ClienteService service = new ClienteService();
	Response<Boolean> resposta = service.update(request);
%>
```

<h2 id="delete">Deletar cliente</h2>
Para excluir um cliente, será necessário enviar o seguinte dado:

- Id do cliente: `idCliente`

Para deletar o cliente, será necessário usar o método `delete()` da classe `ClienteService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.ClienteService"%>
<%
	ClienteService service = new ClienteService();
	Response<Boolean> resposta = service.delete(request);
%>
```
