# Kyrosb-01

Este projeto é responsável por gerenciar os clientes de um app ficticio com propósito de avaliação.

- [Recursos](#recursos)
  - [Documentação APIs](#documentação-apis)
- [Desenvolvimento](#desenvolvimento)
  - [Requisitos](#requisitos)
  - [Instalação](#instalação)
    - [Docker](#docker-compose)
  - [Configuração](#configuração)
  - [Testes](#Testes)
  
### Documentação APIs

Para a documentação é utilizado o [Swagger](https://swagger.io/). Ferramenta que provê interface para testes.

![swagger](./docs/images/swagger.png)

Por padrão a documentação está disponível no endpoint `/swagger-ui.html#/`.

### Catálogo de erros

| Erro | Descrição           | Ocorre quando                                                  |
| ---- | ------------------- | -------------------------------------------------------------- |
|  400 | Bad Request         | Os dados enviados no request estão inválidos                   |
|  404 | Not Found           | O recurso não foi encontrado                                   |
|  500 | Internal Error      | Acontece um erro interno no módulo                             |

## Desenvolvimento

### Requisitos

```

* Docker
* Docker Compose
* Mysql

```

### Instalação

#### Docker compose:

Acessar a pasta raiz do projeto e executar:

```

https://docs.docker.com/compose/install/
docker-compose up -d

```

### Configuração

Lista de variáveis de ambiente necessárias para a execução da aplicação

| Variável               | Descrição                             |   Tipo   | Obrigatório |  Valor Padrão   |
| ---------------------- | ------------------------------------- | :------: | :---------: | :-------------: |
| DATABASE_NAME          | Nome do banco de dados                |  Texto   |     Não     |    kyros-api    |
| DATABASE_USERNAME      | Usuário para conexão de dados         |  Texto   |     Não     |    kyros-api    |
| DATABASE_PASSWORD      | Senha do usuário para acesso ao banco |  Texto   |     Não     |    kyros-api    |
| DATABASE_HOST          | Host para acesso ao Banco             |  Texto   |     Não     |    localhost    |
| DATABASE_PORT          | Porta para acesso ao Banco            | Numérico |     Não     |      3306       |


### Testes

```bash
# unit tests
$ mvn clean verify

```