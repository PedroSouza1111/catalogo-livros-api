# Catálogo de Livros API

API RESTful para gerenciamento de um catálogo de livros

## Como Executar

1.  Clone este repositório.
2.  Certifique-se de ter o Java 17 (ou superior) e o Maven instalados.
3.  Na raiz do projeto, execute o seguinte comando para iniciar a aplicação:
    ```bash
    mvn spring-boot:run
    ```
4.  A API estará disponível em `http://localhost:8080`.

## Endpoints

-   **Documentação Swagger**: [http://localhost:8080/docs](http://localhost:8080/docs)
-   **Console H2**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    -   **JDBC URL**: `jdbc:h2:mem:catalogodb`
    -   **User Name**: `sa`
    -   **Password**: (deixe em branco)
