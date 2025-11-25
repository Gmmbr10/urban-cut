[Voltar](../README.md)

# Documentação Dia de Funcionamento

- [Cadastrar dia de funcionamento](#create)
- [Buscar dia de funcionamento](#search)
- [Atualizar dia de funcionamento](#update)
- [Deletar dia de funcionamento](#delete)

<h2 id="create">Cadastrar dia de funcionamento</h2>

Para registrar um dia de funcionamento de uma barbearia, será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
>
> - Dado: nome do parâmetro durante o envio

- Id da barbearia: `idBarbearia`

- Dia da semana (Domingo, Segunda-Feira, Terça-Feira, Quarta-Feira, Quinta-Feira, Sexta-Feira, Sábado): `diaSemana`

- Hora de abertura (formato HH:mm): `horaAbertura`

- Hora de fechamento (formato HH:mm): `horaFechamento`

Para registrar o dia de funcionamento, será necessário usar o método `register()` da classe `DiaFuncionamentoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.DiaFuncionamentoService"%>
<%
    DiaFuncionamentoService service = new DiaFuncionamentoService();
    Response<Boolean> resposta = service.register(request);
%>
```

<h2 id="search">Buscar dia de funcionamento</h2>

Para buscar por um dia específico de funcionamento, será necessário enviar o seguinte dado:

- Id do dia de funcionamento: `idDiaFuncionamento`

Para buscar o dia, será necessário usar o método `searchById()` da classe `DiaFuncionamentoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<DiaFuncionamento>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.DiaFuncionamento"%>
<%@page import="com.urbancut.services.DiaFuncionamentoService"%>
<%
    DiaFuncionamentoService service = new DiaFuncionamentoService();
    Response<DiaFuncionamento> resposta = service.searchById(request);
%>
```

<h2 id="update">Atualizar dia de funcionamento</h2>

Para atualizar um dia de funcionamento, será necessário enviar os seguintes dados:

- Id do dia de funcionamento: `idDiaFuncionamento`

- Id da barbearia: `idBarbearia`

- Dia da semana (Domingo, Segunda-Feira, Terça-Feira, Quarta-Feira, Quinta-Feira, Sexta-Feira, Sábado): `diaSemana`

- Hora de abertura (formato HH:mm): `horaAbertura`

- Hora de fechamento (formato HH:mm): `horaFechamento`

Para atualizar o dia de funcionamento, será necessário usar o método `update()` da classe `DiaFuncionamentoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.DiaFuncionamentoService"%>
<%
    DiaFuncionamentoService service = new DiaFuncionamentoService();
    Response<Boolean> resposta = service.update(request);
%>
```

<h2 id="delete">Deletar dia de funcionamento</h2>

Para excluir um dia de funcionamento, será necessário enviar o seguinte dado:

- Id do dia de funcionamento: `idDiaFuncionamento`

Para deletar o dia de funcionamento, será necessário usar o método `delete()` da classe `DiaFuncionamentoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.DiaFuncionamentoService"%>
<%
    DiaFuncionamentoService service = new DiaFuncionamentoService();
    Response<Boolean> resposta = service.delete(request);
%>
```
