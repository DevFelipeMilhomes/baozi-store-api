# Baozi Store API 🥟

Este é um projeto acadêmico desenvolvido para a disciplina de **Desenvolvimento Web - Back End**. A aplicação consiste numa API REST para a gestão de uma loja fictícia de pães chineses.

## 🎓 Observações
* **Escopo:** API funcional com operações de CRUD para as entidades principais.
* **Limitações:** Por ser um projeto focado exclusivamente em Back-End, a aplicação **não possui**:
    * Interface de utilizador (Frontend).
    * Sistemas de Login ou Autenticação.
    * Relacionamentos complexos entre as entidades (Foreign Keys) via JPA.
    * Tratamento avançado de segurança.

## 🚀 Tecnologias
- Java
- Spring Boot
- Spring Data JPA
- MariaDB (Base de Dados)

## 🏗️ Endpoints Implementados

### Cliente
- `GET /clientes` -> Lista todos os clientes cadastrados.
- `GET /clientes/{id}` -> Busca um cliente específico pelo identificador.
- `POST /clientes` -> Cadastra novos clientes.
- `PUT /clientes/{id}` -> Atualiza os dados de um cliente existente.
- `DELETE /clientes/{id}` -> Remove um cliente do sistema.

### Produto
- `GET /produtos` -> Lista todos os produtos cadastrados.
- `GET /produtos/{id}` -> Busca um produto específico pelo identificador.
- `POST /produtos` -> Cadastra novos produtos.
- `PUT /produtos/{id}` -> Atualiza os dados de um produto existente.
- `DELETE /produtos/{id}` -> Remove um produto do sistema.

### Pedido
- `GET /pedidos` -> Lista todos os pedidos cadastrados.
- `GET /pedidos/{id}` -> Busca um pedido específico pelo identificador.
- `POST /pedidos` -> Cadastra novos pedidos.
- `PUT /pedidos/{id}` -> Atualiza os dados de um pedido existente.
- `DELETE /pedidos/{id}` -> Remove um pedido do sistema.

## 🛠️ Como executar
1.  Configure a ligação com o MariaDB no ficheiro `application.properties`.
2.  Execute o projeto através da sua IDE ou via terminal com `mvn spring-boot:run`.
3.  Utilize o **Postman** ou ferramenta similar para testar os endpoints em `localhost:8080`.

---
**Desenvolvido por:** Felipe Barbosa