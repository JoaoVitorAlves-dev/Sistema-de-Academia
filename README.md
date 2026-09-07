# Sistema de Biblioteca — Relacionamentos JPA

Projeto de estudos desenvolvido com **Spring Boot** para praticar relacionamentos entre entidades usando **JPA/Hibernate**.

## Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados POSTRESQL
- Lombok

## Modelo de entidades

O sistema representa uma biblioteca com quatro entidades principais:

- `Usuario`
- `Emprestimo`
- `Livro`
- `Autor`

### Relacionamentos

```text
Usuario 1 ──── 0..* Emprestimo
Livro   1 ──── 0..* Emprestimo
Autor   1 ──── 0..* Livro
```

## Mapeamento JPA

O lado que possui a chave estrangeira é `Emprestimo`. Por isso, ele usa `@ManyToOne` para `Usuario` e `Livro`:

```java
@ManyToOne
@JoinColumn(name = "usuario_id")
private Usuario usuario;

@ManyToOne
@JoinColumn(name = "livro_id")
private Livro livro;
```

No outro lado, `Usuario` usa `@OneToMany(mappedBy = "usuario")` para representar a coleção de empréstimos.

O relacionamento entre livro e autor segue a mesma ideia: `Livro` possui a FK `autor_id` e usa `@ManyToOne`.

## Estrutura do projeto

```text
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

| Recurso | Base URL |
| --- | --- |
| Usuários | `/usuarios` |
| Autores | `/autores` |
| Livros | `/livros` |
| Empréstimos | `/emprestimos` |

Cada recurso possui operações de listar, buscar por ID, criar, atualizar e excluir.

### Exemplo: criar um autor

```http
POST /autores
Content-Type: application/json

{
  "nome": "Machado de Assis",
  "nacionalidade": "Brasileira"
}
```

### Exemplo: criar um livro

```http
POST /livros
Content-Type: application/json

{
  "titulo": "Dom Casmurro",
  "isbn": "978-85-359-0277-5",
  "anoPublicacao": "1899-01-01",
  "autorId": 1
}
```

### Exemplo: criar um empréstimo

```http
POST /emprestimos
Content-Type: application/json

{
  "usuarioId": 1,
  "livroId": 1,
  "dataEmprestimo": "2026-09-07",
  "dataDevolucao": "2026-09-21"
}
```

## Banco de dados

O projeto utiliza o banco POSTGRESQL.

---

Feito para fins de estudo com Spring Boot e JPA.
