[Voltar](../README.md)

# Documentação Horário Bloqueado

- [Criar horário bloqueado](#create)
- [Buscar horário bloqueado](#search)
- [Atualizar horário bloqueado](#update)
- [Deletar horário bloqueado](#delete)

<h2 id="create">Criar horário bloqueado</h2>
Para salvar um horário bloqueado, será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
> 
> - Dado: nome do parâmetro durante o envio

- Id do barbeiro: `idBarbeiro`
- Início do bloqueio: `inicio`
- Fim do bloqueio: `fim`

Para registrar o horário bloqueado, será necessário usar o método `register()` da classe `HorarioBloqueioService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre o retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.HorarioBloqueioService"%>
<%
	HorarioBloqueioService service = new HorarioBloqueioService();
	Response<Boolean> resposta = service.register(request);
%>
```

<h2 id="search">Buscar horário bloqueado</h2>
Para buscar por um horário bloqueado, será necessário enviar o seguinte dado:

- Id do barbeiro: `idBarbeiro`

Para buscar o horário bloqueado, será necessário usar o método `searchById()` da classe `HorarioBloqueioService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<HorarioBloqueio>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.HorarioBloqueio"%>
<%@page import="com.urbancut.services.HorarioBloqueioService"%>
<%
	HorarioBloqueioService service = new HorarioBloqueioService();
	Response<HorarioBloqueio> resposta = service.searchById(request);
	// ou
	Response<HorarioBloqueio> resposta = service.searchByEmail(request);
%>
```

<h2 id="update">Atualizar horário bloqueado</h2>
Para atualizar um horário bloqueado, será necessário enviar os seguintes dados:

- Id do horário bloqueado: `idHorarioBloqueado`
- Id do barbeiro: `idBarbeiro`
- Início do bloqueio: `inicio`
- Fim do bloqueio: `fim`

Para atualizar o horário bloqueado, será necessário usar o método `update()` da classe `ClienteService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.HorarioBloqueioService"%>
<%
	HorarioBloqueioService service = new HorarioBloqueioService();
	Response<Boolean> resposta = service.update(request);
%>
```

<h2 id="delete">Deletar horário bloqueado</h2>
Para excluir um horário bloqueado, será necessário enviar o seguinte dado:

- Id do horário bloqueio: `idHorarioBloqueio`

Para deletar o horário bloqueado, será necessário usar o método `delete()` da classe `HorarioBloqueioService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.HorarioBloqueioService"%>
<%
	HorarioBloqueioService service = new HorarioBloqueioService();
	Response<Boolean> resposta = service.delete(request);
%>
```
