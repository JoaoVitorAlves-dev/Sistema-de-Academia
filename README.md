# Sistema de Academia — Relacionamentos JPA

Projeto de estudos desenvolvido com **Spring Boot**.

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
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
├── controller/    # Endpoints REST
├── dto/           # Objetos de request e response
├── entity/        # Entidades JPA
├── exceptions/    # Exceções da aplicação
├── mapper/        # Conversão entre entity e DTO
├── repository/    # Acesso aos dados com JpaRepository
└── service/       # Regras de negócio
```

## Endpoints

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

## Banco de dados

O projeto utiliza o banco **PostgreSQL**.

Para executar a aplicação após clonar o repositório:

1. Crie, no pgAdmin, um banco de dados chamado `gymsystem`;
2. Configure sua senha do PostgreSQL no arquivo `application.properties`;
3. Execute a aplicação Spring Boot.

---

Feito para fins de estudo com Spring Boot e JPA.