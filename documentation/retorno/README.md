[Voltar](../README.md)

# Documentação Retorno

- [Status Code](#status-code)
- [Mensagem](#message)
- [Dado](#data)

<h2 id="status-code">Status Code</h2>
É um valor inteiro que representa a resposta do evento.

**Neste projeto**, os possíveis valores para ele são:

- `200`: Ocorreu tudo certo
- `201`: Ocorreu tudo certo e o dado foi registrado
- `204`: Ocorreu tudo correto e não há resposta
- `400`: Erro do lado do cliente
- `500`: Erro do lado do servidor

> Caso queira ver mais sobre status code [acesse: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Reference/Status](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Reference/Status)

Você pode acessá-lo usando o método `getStatusCode()`, que retorna um `int`.

<h2 id="message">Mensagem</h2>
Será uma mensagem relacionada ao que aconteceu na execução do método.

**Neste projeto**, as mensagens virão para os seguintes *status code*:

- `400`
- `500`

Você pode acessá-la usando o método `getMensagem()`, que retorna uma `String`.

<h2 id="data">Dado</h2>
Todas as respostas possuem um dado que será do tipo enviado entre os símbolos maior e menor que: `Response<Tipo>`.

Você pode acessá-lo usando o método `getDado()`, que retorna o objeto (dado).