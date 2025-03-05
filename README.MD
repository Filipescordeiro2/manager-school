# School Manager

## Descrição
Gerenciador Escolar é uma aplicação Spring Boot projetada para gerenciar dados relacionados à escola, incluindo professores, disciplinas e instituições.

## Tecnologias
- Java
- Spring Boot
- Maven
- PostgreSQL

## Arquitetura
A aplicação segue uma arquitetura em camadas, que inclui as seguintes camadas:
- **Controller** 🕹️: Lida com requisições e respostas HTTP.
- **Service** 🛠️: Contém a lógica de negócios da aplicação.
- **Repository** 🗄️: Gerencia o acesso e a persistência dos dados.
- **Domain** 📦: Contém as entidades principais e a lógica de domínio.
- **Filter** 🔍: Registra logs de requisições HTTP.
- **Exception** ⚠️: Gerencia o tratamento de exceções.
- **Utilities** 🧰: Contém classes utilitárias e funções auxiliares.
- **Enums** 📑: Define enums utilizados na aplicação.
- **Configuration** ⚙️: Contém classes de configuração da aplicação.
- **DTO** 📄: Define objetos de transferência de dados utilizados na aplicação.

## Funcionalidades do Projeto
- Gerenciamento de professores
- Gerenciamento de disciplinas
- Gerenciamento de instituições
- Gerenciamento de estudantes
- Gerenciamento de turmas
- Registro e consulta de logs de requisições HTTP
- Tratamento centralizado de exceções
- Etc...

## Começando

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6.0 ou superior
- PostgreSQL

### Instalação
1. Clone o repositório:
    ```sh
    git clone https://github.com/Filipescordeiro2/school-manager.git
    cd school-manager
    ```

2. Configure o banco de dados no `application.properties`:
    ```ini
    spring.datasource.url=jdbc:postgresql://localhost:5439/meubanco
    spring.datasource.username=admin
    spring.datasource.password=admin
    ```

3. Construa o projeto:
    ```sh
    mvn clean install
    ```

4. Execute a aplicação:
    ```sh
    mvn spring-boot:run
    ```

### Uso
Acesse a aplicação em `http://localhost:8080/schoolManager/`.

## Creator

**Filipe Santana Cordeiro**  
**Backend Developer**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/filipesantanacordeiro/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Filipescordeiro2)