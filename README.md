# API REST para Controle de Empresas, Vagas e Aplicações

Este projeto é uma API REST desenvolvida em **Java** utilizando o **Spring Boot**. O objetivo principal é gerenciar **empresas**, **vagas de emprego** e **aplicações de candidatos** para essas vagas, fornecendo operações CRUD (Create, Read, Update e Delete) e funcionalidades relacionadas a persistência, segurança e autorização.

---

## **Tecnologias Utilizadas**

- **Java**
  - Linguagem base para o desenvolvimento da API.
- **Spring Boot**
  - Framework para facilitar o desenvolvimento de aplicações Java.
- **JPA (Java Persistence API)**
  - Para mapeamento objeto-relacional.
- **Hibernate**
  - Implementação do JPA usada para persistência de dados.
- **Spring Security**
  - Para autenticação e autorização dos usuários.
- **Banco de Dados**
  - Suporte para integração com bancos relacionais (ex.: MySQL ou PostgreSQL).

---

## **Funcionalidades**

- **Gestão de Empresas:**
  - Criar empresa e autenticar.
- **Gestão de Vagas:**
  - Criar vaga 
- **Aplicações de Candidatos:**
  - Criar candidato e autenticar.
- **Segurança:**
  - Autenticação e autorização utilizando **JWT (JSON Web Token)**.

---

## **Arquitetura**

A API segue os princípios de **arquitetura REST**, com endpoints bem definidos para cada recurso (Empresas, Vagas e Aplicações). Utiliza o conceito de **Filter Chain** do Spring Security para implementar autenticação e autorização, garantindo o acesso seguro às operações.

### **Endpoints** (Exemplos)

- **Empresas:**
  - `POST /company/` - Criar uma nova empresa.
  - `POST /company/auth` - Autenticar empresa.

- **Vagas:**
  - `POST /company/jobs` - Criar uma nova vaga.

- **Candidato:**
  - `POST /candidate/` - Criar um novo usuario.
  - `POST /candidate/auth` - Autenticar usuario.
  - `POST /candidate/profile` - Ver perfil do usuario cadastrado.

---

## **Segurança**

A segurança da API é implementada com **Spring Security**, utilizando a abordagem de **Filter Chain** para gerenciar autenticação e autorização. O método `@Bean SecurityFilterChain` garante:

- Requisições autenticadas para acessar os endpoints.
- Configuração de permissões baseadas em papéis de usuários (ex.: COMPANY e CANDIDATE).

### **JWT (JSON Web Token)**

A autenticação é baseada em **JWT**, que permite o envio de tokens seguros nas requisições, garantindo a integridade das sessões do usuário.

---

## **Como Executar o Projeto**

1. Clone o repositório:

   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Configure o banco de dados no arquivo `application.properties` ou `application.yml`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   security.token.secret=chave_token
   ```

3. Configure e execute os serviços com **Docker Compose**:

   Crie um arquivo `docker-compose.yml` com o seguinte conteúdo:

   ```yaml
   version: "3.8"

   services:
     postgree:
       image: postgres
       container_name: nome_container
       ports:
         - "5432:5432"
       environment:
         - POSTGRES_USER=seu_usuario
         - POSTGRES_PASSWORD=sua_senha
         - POSTGRES_DB=nome_banco
   ```

   Em seguida, execute o comando:

   ```bash
   docker-compose up -d
   ```

4. Compile e execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

5. Acesse a API na URL:

   ```
   http://localhost:8080
   ```

---

## **Futuras Melhorias**

- Implementar paginação e ordenação para listagens de dados.
- Adicionar documentação com **Swagger**.
- Testes unitários e de integração.

---

## **Sobre o Desenvolvedor**

Desenvolvido por **Andrey**, um entusiasta em **Desenvolvimento Back-End** e atualmente explorando o universo do **Spring Boot** com **Java**. Este projeto faz parte de um módulo acadêmico com foco em APIs REST. Sempre em busca de aprender e aplicar boas práticas de desenvolvimento.
