# Urban Cut
Urban Cut é um sistema que facilita o processo de agendamento de cortes pelo lado do cliente e do barbeiro.

[Documentação do backend](./documentation/README.md) •
[Funcionalidades do projeto](#func) •
[Tecnologias](#tec) •
[Executar o app](#exec) •
[Autores](#autors)

<h2 id="func">:rocket: Funcionalidades do projeto</h2>
O sistema foi desenvolvido

<h2 id="tec">:computer: Tecnologias</h2>
Este projeto foi desenvolvido com as seguintes tecnologias:

- Git e Github
- HTML e CSS
- JavaScript
- Java
- JSP
- JDBC
- Maven
- MySQL
- Docker

<h2 id="exec">:runner: Executar o app</h2>
1. Clone o repositório
```bash
git clone https://github.com/Gmmbr10/urban-cut
```

2. Acesse a pasta raiz do projeto

### Usando o tomcat + mysql (local)
> Fique atento
> 
> Provavelmente será necessário alterar os dados da conexão com o banco

3. Copie o conteúdo da pasta `webapps/ROOT` para a pasta no seu servidor

4. Copie o conteúdo da pasta `lib` para a pasta `lib` do seu servidor

5. Crie o banco com o conteúdo do arquivo `backend/database/db.sql`

### Usando o Docker
> Fique atento
> 
> Provavelmente será necessário alterar os dados da conexão com o banco

3. Execute:
```bash
docker compose up
# ou
docker-compose up
```

4. Acesse o container do mysql:
```bash
docker container exec -ti <id> mysql -u root -p
```
> A senha para acessar é `root`

5. Copie e cole o conteúdo do arquivo `backend/database/db.sql` no terminal

> ### Atenção ao executar
> Caso queira alterar algo no código, por exemplo os dados da conexão, use o maven para compilar.
> 
> Na pasta raiz do sistema execute:
> ```bash
> mvn clean package
> ```

<h2 id="autors">:busts_in_silhouette: Autores</h2>

- [Arthur Pessoa da Silva](https://github.com/ARTps2006)
- [Gabriel de Lima Silva](https://github.com/GLima-077)
- [Giovanne Monteiro de Melo](https://github.com/gmmbr10)