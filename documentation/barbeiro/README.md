[Voltar](../README.md)

# Documentação Barbeiro

- [Criar barbeiro](#create)
- [Buscar barbeiro](#search)
- [Atualizar barbeiro](#update)
- [Deletar barbeiro](#delete)

<h2 id="create">Criar barbeiro</h2>
Para salvar um barbeiro, será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
> 
> - Dado: nome do parâmetro durante o envio

- Nome do barbeiro: `nome`
- Email do barbeiro: `email`
- Senha do barbeiro: `senha`

Para registrar o barbeiro, será necessário usar o método `register()` da classe `BarbeiroService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre o retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeiroService"%>
<%
	BarbeiroService service = new BarbeiroService();
	Response<Boolean> resposta = service.register(request);
%>
```

<h2 id="search">Buscar barbeiro</h2>
Para buscar por um barbeiro, será necessário enviar o seguinte dado:

- Id do barbeiro: `idBarbeiro`

ou

- Email do cliente: `email`

Para buscar o barbeiro usando o id, será necessário usar o método `searchById()` da classe `BarbeiroService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Para buscar o barbeiro usando o email, será necessário usar o método `searchByEmail()` da classe `BarbeiroService`.

Como resposta, o método retornará um objeto do tipo `Response<Barbeiro>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.services.BarbeiroService"%>
<%
	BarbeiroService service = new BarbeiroService();
	Response<Barbeiro> resposta = service.searchById(request);
	// ou
	Response<Barbeiro> resposta = service.searchByEmail(request);
%>
```

<h2 id="update">Atualizar barbeiro</h2>
Para atualizar um barbeiro, será necessário enviar os seguintes dados:

- Id do barbeiro: `idBarbeiro`
- Id da barbearia: `idBarbearia`
- Nome do barbeiro: `nome`
- Email do barbeiro: `email`
- Senha do barbeiro: `senha`

Para atualizar o barbeiro, será necessário usar o método `update()` da classe `BarbeiroService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeiroService"%>
<%
	BarbeiroService service = new BarbeiroService();
	Response<Boolean> resposta = service.update(request);
%>
```

<h2 id="delete">Deletar barbeiro</h2>
Para excluir um barbeiro, será necessário enviar o seguinte dado:

- Id do barbeiro: `idBarbeiro`

Para deletar o barbeiro, será necessário usar o método `delete()` da classe `BarbeiroService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.BarbeiroService"%>
<%
	BarbeiroService service = new BarbeiroService();
	Response<Boolean> resposta = service.delete(request);
%>
```
