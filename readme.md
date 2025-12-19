# Dealership API

API REST para gerenciamento de concessionárias e veículos usando **Spring Boot**, **JPA/Hibernate** e **PostgreSQL**.

## ✨ Funcionalidades

- **Autenticação**: Login com JWT
- **Concessionárias**: CRUD completo
- **Veículos**: Criação única por placa/concessionária, listagem por concessionária, detalhes por placa
- **Validações**: Placa única por concessionária, concessionária existente
- **Paginação**: Listagem de veículos otimizada

## 🛠️ Tecnologias
- Spring Boot 4.0.0
- Spring Data JPA
- Hibernate 6.x
- PostgreSQL
- Java 21
- Maven/Gradle
- Lombok
- Validation API
- Flyway
- JWT
- Spring Security

## 🚀 Instalação

1. **Clone o projeto**
- git clone <seu-repo>
- cd dealership-api

2. **Configure o banco** (PostgreSQL)
- spring.datasource.url=jdbc:postgresql://localhost:5432/dealership
- spring.datasource.username=postgres
- spring.datasource.password=123456
- spring.jpa.hibernate.ddl-auto=validate

3. **Execute as migrations** (Flyway)

4. **Inicie a aplicação**

## 🏗️ Estrutura do Projeto
```bash
src/main/java/com/wellington/dealership/
├── DealershipApplication.java
├── controllers/
│ ├── DealershipController.java
│ └── VehicleController.java
├── services/
│ ├── DealershipService.java
│ └── VehicleService.java
├── repositories/
│ ├── DealershipRepository.java
│ └── VehicleRepository.java
├── entities/
│ ├── Dealership.java
│ └── Vehicle.java
├── DTOs/
│ ├──  CreateVehicleDTO.java
│ ├── LoginRequestDTO.java
│ ├── RegisterRequestDTO.java
│ ├── ResponseDTO.java
│ └── UpdateRequestDTO.java
└── infra/
│ ├── security/
│ │ ├── CustomUserDetailsService.java
│ │ ├── SecurityConfig.java
│ │ ├── SecurityFilter.java
│ │ └── TokenService.java
```

## 🛡️ Validações Implementadas

- ✅ Placa única por concessionária
- ✅ Concessionária existe antes de associar veículo
- ✅ Injeção de dependência por construtor
- ✅ Tratamento de exceções customizadas
- ✅ Paginação para listagens grandes

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch `feature/nova-funcionalidade`
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

**Desenvolvido por Wellington**  
**Stack: Spring Boot | JPA | PostgreSQL**
