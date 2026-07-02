# Sicredi QE Desafio - Testes Automatizados de API

Projeto de testes automatizados para a API publica [DummyJSON](https://dummyjson.com), desenvolvido em Java 17 com Maven, JUnit 5, Rest Assured e Allure.

O objetivo e demonstrar uma estrategia objetiva de automacao de API, cobrindo fluxos positivos, fluxos de excecao, autenticacao e validacoes de contrato basicas.

## Tecnologias

- Java 17
- Maven
- JUnit 5
- Rest Assured
- Hamcrest
- Allure Reports
- GitHub Actions

## Estrutura

```text
.
|-- .github/workflows/tests.yml
|-- pom.xml
|-- README.md
`-- src/test/java/com/sicredi/api
    |-- base/BaseTest.java
    |-- utils/AuthUtils.java
    `-- tests
        |-- AuthTest.java
        |-- AuthProductsTest.java
        |-- HealthCheckTest.java
        |-- ProductByIdTest.java
        |-- ProductsAddTest.java
        |-- ProductsTest.java
        |-- UserTest.java
        `-- UsersTest.java
```

## Pre-requisitos

- Java 17+
- Maven 3.9+
- Acesso a internet para consumir `https://dummyjson.com`

## Como executar

Executar todos os testes:

```bash
mvn clean test
```

Executar apontando para outra URL base:

```bash
mvn clean test -Dbase.url=https://dummyjson.com
```

Executar com outro usuario da API:

```bash
mvn clean test -Dapi.username=emilys -Dapi.password=emilyspass
```

Gerar/abrir relatorio Allure:

```bash
mvn allure:serve
```

Os resultados de execucao sao gerados em `target/allure-results` e nao sao versionados.

## Cobertura automatizada

| Endpoint | Cenarios |
| --- | --- |
| `GET /test` | Verifica disponibilidade da API |
| `GET /users` | Lista usuarios disponiveis para autenticacao |
| `POST /auth/login` | Login com credenciais validas e rejeicao de credenciais invalidas |
| `GET /auth/me` | Consulta perfil com token valido |
| `GET /auth/products` | Lista produtos autenticados, rejeita acesso sem token e com token invalido |
| `GET /products` | Lista produtos e valida estrutura basica |
| `GET /products/{id}` | Consulta produto existente e valida erro para produto inexistente |
| `POST /products/add` | Cria produto e valida campos retornados |

## Boas praticas aplicadas

- Reuso de configuracao comum em `BaseTest`.
- Centralizacao de autenticacao em `AuthUtils`.
- Base URL e credenciais configuraveis por propriedades Maven.
- Validacoes objetivas de status code e campos relevantes do body.
- Testes negativos para autenticacao e acesso protegido.
- Sem token, resultados de teste ou arquivos de IDE versionados.
- Pipeline de CI executando `mvn -B clean test` a cada push ou pull request na branch `main`.

## Observacoes sobre a API

A DummyJSON e uma API publica de apoio a testes. Alguns comportamentos podem diferir de APIs produtivas, por exemplo aceitar `200` em criacoes simuladas ou retornar codigos diferentes conforme a regra interna do servico. Os testes foram escritos para validar o comportamento atual documentado/observado sem mascarar falhas de autenticacao.

## Autora

Monique Fernandes Ribeiro C. S.
