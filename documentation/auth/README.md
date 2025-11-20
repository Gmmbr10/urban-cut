[Voltar](../README.md)

# Documentação Auth

- [Criando a sessão](#make)
- [Encerrar a sessão](#destroy)
- [Verificar a sessão](#verify)
- [Verificar a regra da sessão](#verify-rule)

<h2 id="make">Criando a sessão</h2>
Para criar uma sessão será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
>
> - Dado: nome do parâmetro durante o envio

- Email: `email`
- Senha: `senha`
- Tipo: `tipo`

Para criar a sessão, o tipo de ter um dos seguintes valores: `barbeiro` ou `cliente`.
Além disso, será necessário usar o método `login()` da classe `AuthService`,
que recebe como parâmetro um objeto do tipo `HttpServletRequest` e `HttpSession`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre o retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%
	AuthService service = new AuthService();
	Response<Boolean> resposta = service.login(request,session);
%>
```

<h2 id="destroy">Encerrar a sessão</h2>
Para encerrar uma sessão, será necessário usar o método `logout()` da classe `AuthService`,
que recebe como parâmetro um objeto do tipo `HttpSession`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%
	AuthService service = new AuthService();
	Response<Boolean> resposta = service.logout(session);
%>
```

<h2 id="verify">Verificar a sessão</h2>
Para verificar uma sessão, será necessário usar o método `isLogged()` da classe `AuthService`,
que recebe como parâmetro um objeto do tipo `HttpSession`.

Como resposta, o método retornará um objeto do tipo `boolean`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%
	AuthService service = new AuthService();
	boolean resposta = service.isLogged(session);
%>
```

<h2 id="verify-rule">Verificar a regra da sessão</h2>
Para verificar a regra de uma sessão, será necessário usar o método `isThatRule()` da classe `AuthService`,
que recebe como parâmetro um objeto do tipo `HttpSession` e uma *String* informando a regra.

> A regra seria o tipo de usuário da sessão, se é, por exemplo, cliente ou barbeiro.

Como resposta, o método retornará um objeto do tipo `boolean`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.AuthService"%>
<%
	AuthService service = new AuthService();
	boolean resposta = service.isThatRule(session,"Cliente");
%>
```