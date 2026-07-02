# Sicredi QE Desafio - Testes Automatizados de API

Projeto de testes automatizados para a API publica [DummyJSON](https://dummyjson.com), desenvolvido em Java 17 com Maven, JUnit 5, Rest Assured e Allure.

O objetivo e demonstrar uma estrategia objetiva de automacao de API, cobrindo fluxos positivos, fluxos de excecao, autenticacao, validacoes de contrato e uma arquitetura facil de manter.

## Tecnologias

- Java 17
- Maven
- JUnit 5
- Rest Assured
- Hamcrest
- JSON Schema Validator
- Allure Reports
- GitHub Actions

## Estrutura

```text
.
|-- .github/workflows/tests.yml
|-- pom.xml
|-- README.md
`-- src/test
    |-- java/com/sicredi/api
    |   |-- base/BaseTest.java
    |   |-- clients
    |   |-- payloads
    |   |-- specs/ApiSpecs.java
    |   |-- tests
    |   `-- utils/AuthUtils.java
    `-- resources/schemas
```

## Arquitetura da automacao

O projeto foi organizado em camadas para separar responsabilidades:

- `base`: configuracao comum da suite, como `base.url`.
- `specs`: `RequestSpecification` e `ResponseSpecification` reutilizaveis.
- `clients`: encapsulam as chamadas HTTP por dominio da API.
- `payloads`: centralizam a construcao de massas de entrada.
- `tests`: mantem apenas o comportamento esperado e as assercoes.
- `resources/schemas`: contratos JSON Schema usados nas validacoes principais.

## Pre-requisitos

- Java 17+
- Maven 3.9+
- Acesso a internet para consumir `https://dummyjson.com`

## Como executar

Executar todos os testes:

```bash
mvn clean test
```

Executar apenas smoke tests:

```bash
mvn test -Dgroups=smoke
```

Executar apenas testes por dominio:

```bash
mvn test -Dgroups=auth
mvn test -Dgroups=products
mvn test -Dgroups=users
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

## Tags

| Tag | Uso |
| --- | --- |
| `smoke` | Fluxos essenciais para feedback rapido |
| `regression` | Fluxos complementares e negativos |
| `auth` | Autenticacao e recursos protegidos |
| `products` | Endpoints de produtos |
| `users` | Endpoints de usuarios |
| `health` | Disponibilidade da API |

## Boas praticas aplicadas

- Reuso de configuracao comum em `BaseTest`.
- Separacao entre specs, clients, payloads e testes.
- Centralizacao de autenticacao em `AuthUtils`.
- Base URL e credenciais configuraveis por propriedades Maven.
- Validacoes objetivas de status code, campos relevantes e contratos JSON Schema.
- Testes negativos para autenticacao e acesso protegido.
- Tags para execucao seletiva por risco, dominio e rapidez de feedback.
- Sem token, resultados de teste ou arquivos de IDE versionados.
- Pipeline de CI executando smoke e regressao a cada push ou pull request na branch `main`.

## Observacoes sobre a API

A DummyJSON e uma API publica de apoio a testes. Alguns comportamentos podem diferir de APIs produtivas, por exemplo aceitar `200` em criacoes simuladas ou retornar codigos diferentes conforme a regra interna do servico. Os testes foram escritos para validar o comportamento atual documentado/observado sem mascarar falhas de autenticacao.

## Autora

Monique Fernandes Ribeiro C. S.
