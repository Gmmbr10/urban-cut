[Voltar](../README.md)

# Documentação Endereço

- [Criar endereço](#create)
- [Buscar endereço](#search)
- [Atualizar endereço](#update)
- [Deletar endereço](#delete)

<h2 id="create">Criar endereço</h2>
Para salvar um endereço, será necessário enviar os seguintes dados:

> Exemplo de envio de dado:
>
> - Dado: nome do parâmetro durante o envio

- CEP do endereço: `cep`
- Estado do endereço: `estado`
- Cidade do endereço: `cidade`
- Bairro do endereço: `bairro`
- Logradouro do endereço: `logradouro`
- Número do endereço: `numero`
- Complemento do endereço (opcional): `complemento`

Para registrar o endereço, será necessário usar o método `register()` da classe `EnderecoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

> Veja mais sobre o retorno [aqui](../retorno/README.md).

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.EnderecoService"%>
<%
    EnderecoService service = new EnderecoService();
    Response<Boolean> resposta = service.register(request);
%>
```

<h2 id="search">Buscar endereço</h2>
Para buscar por um endereço, será necessário enviar o seguinte dado:

- Id do endereço: `idEndereco`

Para buscar o endereço, será necessário usar o método `searchById()` da classe `EnderecoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Endereco>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.models.Endereco"%>
<%@page import="com.urbancut.services.EnderecoService"%>
<%
    EnderecoService service = new EnderecoService();
    Response<Endereco> resposta = service.searchById(request);
%>
```

<h2 id="update">Atualizar endereço</h2>
Para atualizar um endereço, será necessário enviar os seguintes dados:

- Id do endereço: `idEndereco`

- CEP do endereço: `cep`

- Estado do endereço: `estado`

- Cidade do endereço: `cidade`

- Bairro do endereço: `bairro`

- Logradouro do endereço: `logradouro`

- Número do endereço: `numero`

- Complemento do endereço (opcional): `complemento`

Para atualizar o endereço, será necessário usar o método `update()` da classe `EnderecoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.EnderecoService"%>
<%
    EnderecoService service = new EnderecoService();
    Response<Boolean> resposta = service.update(request);
%>
```

<h2 id="delete">Deletar endereço</h2>
Para excluir um endereço, será necessário enviar o seguinte dado:

- Id do endereço: `idEndereco`

Para deletar o endereço, será necessário usar o método `delete()` da classe `EnderecoService`, que recebe como parâmetro um objeto do tipo `HttpServletRequest`.

Como resposta, o método retornará um objeto do tipo `Response<Boolean>`.

### Exemplo de uso (JSP)

```jsp
<%@page import="com.urbancut.core.Response"%>
<%@page import="com.urbancut.services.EnderecoService"%>
<%
    EnderecoService service = new EnderecoService();
    Response<Boolean> resposta = service.delete(request);
%>
```