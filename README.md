# Arquivos Virtuais

Este projeto é uma aplicação Spring Boot para gerenciar arquivos virtuais. Foi desenvolvido como parte do desafio para desenvolvedor pleno backend java na Essia.

## Funcionalidades

- **Diretorios**
    - Listagem de todos os diretorios.
    - Listagem de sub-diretorios por codigo de diretorio.
    - Inserção de novos diretorios.
    - Atualização de diretorios exitentes (inclusão/removal de subdiretorios, files e relação de parent diretorio).
    - Exclusão de diretorio por codigo diretorio.

- **Arquivos**
    - Inserção de novos arquivos.
    - Atualização de arquivos existentes (inclusão/removal de arquivos em diretorios).
    - Exclusão de arquivos.
    - Listagem de todos os arquivos.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- JUnit & Mockito
- Docker & Docker Compose
- Swagger
- Maven
- Angular
- Bootstrap

## Configuração do Ambiente

### Pré-requisitos

- Git
- Docker e Docker Compose instalados na sua máquina.

### Executando a aplicação

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/Paulocorsi/Essia.git
   cd Essia


### 2. **Instruções para Rodar o Projeto**

1. **Garanta que você clonou o repositório e está no diretório do projeto.**
2. **Construa e inicie os containers com Docker Compose** usando o comando:

   ```bash
   docker-compose up -d --build


## Documentação da API

A documentação da API está disponível via Swagger.

- **Swagger UI:** `http://localhost:8080/swagger-ui/`

## Interface visual

- **Swagger UI:** `http://localhost:4200`
