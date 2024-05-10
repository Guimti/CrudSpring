
##  CRUD Spring Boot
Este é um projeto básico de exemplo usando Spring Boot com Java 17, PostgreSQL como banco de dados e Swagger para documentação da API.

## Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

Java JDK 17

Maven

PostgreSQL

Postman (opcional para testar as APIs)


## Documentação da API



  #### POST /products

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| null | `string` | **Criar Produtos**|


  #### GET /products

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| null | `string` | **Listar Produtos**|

#### GET /products/{ID}


| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Busca um produto por ID** |

####  PUT /products/{ID}


| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Edita um produto pelo ID** |


####  DELETE /products/{ID}


| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Deleta um produto pelo ID** |



## Autores

- [@Guimti](https://github.com/Guimti)

## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilherme-macedo-cruz/)

