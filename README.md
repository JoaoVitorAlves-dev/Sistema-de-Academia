# Sistema de Academia — Relacionamentos JPA + Autenticação JWT

Projeto de estudos desenvolvido com **Spring Boot**.

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring Security + JWT (jjwt)
- Banco de dados PostgreSQL
- Lombok

## Modelo de entidades

O sistema representa uma academia com quatro entidades principais:

- `Aluno`
- `Matricula`
- `Turma`
- `Instrutor`

### Relacionamentos

```text
Aluno     1 ──── 0..* Matricula
Turma     1 ──── 0..* Matricula
Instrutor 1 ──── 0..* Turma
```

- Um aluno pode possuir várias matrículas;
- Cada matrícula pertence a um único aluno;
- Uma turma pode possuir várias matrículas;
- Cada matrícula referencia uma única turma;
- Um instrutor pode ministrar várias turmas;
- Cada turma possui um único instrutor.

Além disso, `Aluno` também representa o usuário autenticável do sistema (`implements UserDetails`), com um relacionamento `ManyToMany` para `Roles` (papéis de acesso, ex: `ALUNO`, `ADMIN`).

## Mapeamento JPA

O lado que possui as chaves estrangeiras é `Matricula`. Por isso, ele usa `@ManyToOne` para `Aluno` e `Turma`:

```java
@ManyToOne
@JoinColumn(name = "aluno_id")
private Aluno aluno;

@ManyToOne
@JoinColumn(name = "turma_id")
private Turma turma;
```

No outro lado, `Aluno` usa `@OneToMany(mappedBy = "aluno")` para representar a coleção de matrículas.

O relacionamento entre turma e instrutor segue a mesma ideia: `Turma` possui a FK `instrutor_id` e usa `@ManyToOne`.

## Estrutura do projeto

```
src/main/java/
├── config/        # Segurança, filtro JWT e geração/validação de token
├── controller/    # Endpoints REST
├── dto/           # Objetos de request e response
├── entity/        # Entidades JPA
├── enums/         # Enumerações (ex: papéis de usuário)
├── exceptions/    # Exceções da aplicação
├── mapper/        # Conversão entre entity e DTO
├── repository/    # Acesso aos dados com JpaRepository
└── service/       # Regras de negócio
```

## Autenticação

A API usa **JWT** com Spring Security. As rotas de autenticação são públicas; todas as demais exigem um token válido.

| Método | Rota              | Descrição                          | Autenticação |
| ------ | ----------------- | ----------------------------------- | ------------ |
| POST   | `/v1/auth/register` | Cadastra um novo aluno              | Não          |
| POST   | `/v1/auth/login`    | Autentica e retorna um token JWT    | Não          |

### Exemplo: registrar

```http
POST /v1/auth/register
Content-Type: application/json

{
  "nome": "João Vitor",
  "email": "joao@email.com",
  "senha": "senhaSegura123"
}
```

### Exemplo: login

```http
POST /v1/auth/login
Content-Type: application/json

{
  "email": "joao@email.com",
  "senha": "senhaSegura123"
}
```

**Resposta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": 3600000
}
```

Para chamar as rotas protegidas, envie o token no header:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

## Endpoints principais

| Recurso     | Base URL       |
| ----------- | -------------- |
| Alunos      | `/alunos`      |
| Matrículas  | `/matriculas`  |
| Turmas      | `/turmas`      |
| Instrutores | `/instrutores` |

Cada recurso possui operações de listar, buscar por ID, criar, atualizar e excluir.

### Exemplo: criar um instrutor

```http
POST /instrutores
Content-Type: application/json

{
  "nome": "Ana Souza",
  "especialidade": "Musculação"
}
```

### Exemplo: criar uma turma

```http
POST /turmas
Content-Type: application/json

{
  "nome": "Treino Funcional",
  "horario": "18:00",
  "capacidade": 20,
  "instrutorId": 1
}
```

### Exemplo: criar uma matrícula

```http
POST /matriculas
Content-Type: application/json

{
  "alunoId": 1,
  "turmaId": 1,
  "dataMatricula": "2026-09-07",
  "status": "ATIVA"
}
```

## Configuração e execução

O `application.properties` **não é versionado** (contém credenciais e chave JWT). Para rodar o projeto localmente:

1. Crie, no pgAdmin, um banco de dados chamado `gymsystem`;
2. Crie o arquivo `src/main/resources/application.properties` com o seguinte conteúdo, ajustando usuário/senha do seu PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gymsystem
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

jwt.secret=${JWT_SECRET:GERE_UMA_CHAVE_COM_openssl_rand_-base64_32}
jwt.expiration=3600000
jwt.refresh-expiration=604800000
```

3. Gere uma chave JWT segura:

```bash
openssl rand -base64 32
```

4. Execute a aplicação Spring Boot.

---

Feito para fins de estudo com Spring Boot, JPA e Spring Security.