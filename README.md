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
- GitLab CI

## Estrutura

```text
.
|-- .github/workflows/tests.yml
|-- .gitlab-ci.yml
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

- Git
- Java 17+
- Maven 3.9+
- Conta no GitLab, caso a entrega siga o formato oficial do desafio
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

## Plano de teste e estrategia

A estrategia prioriza endpoints criticos para o fluxo de negocio: disponibilidade da API, autenticacao, consulta de usuarios para credenciais, acesso autenticado a produtos e operacoes publicas de produtos.

O plano combina:

- Testes smoke para validar rapidamente os fluxos essenciais.
- Testes de regressao para fluxos negativos e regras complementares.
- Validacao de status code conforme comportamento real da API.
- Validacao de campos relevantes para o negocio.
- Validacao de contrato com JSON Schema nos retornos principais.
- Separacao por dominio para facilitar manutencao e leitura tecnica.

## Bugs e inconsistencias identificadas

Durante a automacao foram identificadas diferencas entre a documentacao do desafio e o comportamento atual da API DummyJSON:

- `POST /auth/login`: a documentacao informa `201 OK`, mas a API publica responde `200 OK`.
- `POST /products/add`: a documentacao usa o campo `thumbnail` com a URL contendo `thumnail.jpg`, indicando um erro de digitacao no exemplo.
- `GET /products/{id}`: a documentacao descreve `404 Not found` para id inexistente; a automacao aceita qualquer erro `4xx` para evitar falso negativo caso a API retorne outro codigo de cliente.
- A API DummyJSON e publica e pode evoluir independentemente do desafio, entao as validacoes combinam contrato essencial com tolerancia controlada onde ha divergencia documentada.

## Melhorias sugeridas

- Corrigir exemplos da documentacao que possuem status code ou typo divergente do comportamento real.
- Padronizar estrutura de erro para endpoints protegidos e recursos inexistentes.
- Publicar uma especificacao OpenAPI versionada para reduzir ambiguidades nos contratos.
- Definir massa de dados estavel para testes, evitando dependencia de dados publicos que podem mudar.

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

## Entrega

O enunciado oficial solicita entrega em repositorio privado no GitLab, com codigo e documentacao na branch `main`, alem do convite ao usuario indicado no portal do desafio. O projeto tambem possui GitHub Actions, mas o arquivo `.gitlab-ci.yml` foi incluido para atender diretamente ao formato solicitado.

## Observacoes sobre a API

A DummyJSON e uma API publica de apoio a testes. Alguns comportamentos podem diferir de APIs produtivas, por exemplo aceitar `200` em criacoes simuladas ou retornar codigos diferentes conforme a regra interna do servico. Os testes foram escritos para validar o comportamento atual documentado/observado sem mascarar falhas de autenticacao.

## Autora

Monique Fernandes Ribeiro Coutinho da Silva
