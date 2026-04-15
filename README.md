# 🛒 Stellar Inventory — Product Management REST API

![Java](https://img.shields.io/badge/Java-17%2B-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=flat-square&logo=postgresql)
![Vanilla JS](https://img.shields.io/badge/Frontend-Vanilla%20JS-yellow?style=flat-square&logo=javascript)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

A full-stack **product inventory management** application built with **Spring Boot** (backend REST API) and a clean **Vanilla JS** frontend. Supports full CRUD operations with soft-delete, PostgreSQL persistence, and a responsive dark/light UI.

---

## ✨ Features

- ✅ **Full CRUD** — Create, Read, Update, Delete products
- 🗂️ **Soft Delete** — Products are deactivated, not permanently removed (`active = true` filter via `@SQLRestriction`)
- 🗄️ **PostgreSQL** with Spring Data JPA / Hibernate
- 🔒 **Bean Validation** — Request DTOs validated with `@NotBlank`
- 🌗 **Dark / Light Theme** — Persisted in `localStorage`
- 📊 **Live Metrics** — Real-time total product count & net inventory value
- 🔄 **DTO Pattern** — Clean separation: `ProductCreateRequest` → `Products` entity → `ProductResponse`
- 🌐 **CORS enabled** for local frontend integration

---

## 🏗️ Tech Stack

| Layer      | Technology                        |
|------------|-----------------------------------|
| Backend    | Java 17+, Spring Boot 3, Spring Data JPA |
| Database   | PostgreSQL 15                     |
| ORM        | Hibernate (`ddl-auto: update`)    |
| Validation | Jakarta Bean Validation           |
| Boilerplate| Lombok                            |
| Frontend   | HTML5, CSS3, Vanilla JavaScript   |
| Build Tool | Maven                             |

---

## 📁 Project Structure

```
product_boot/
├── src/main/java/com/example/product_boot/
│   ├── ProductBootApplication.java       # Entry point
│   ├── controller/
│   │   └── ProductController.java        # REST endpoints
│   ├── entity/
│   │   └── Products.java                 # JPA entity (soft-delete)
│   ├── repository/
│   │   └── ProductRepository.java        # JpaRepository
│   ├── request/
│   │   └── ProductCreateRequest.java     # Validated request DTO
│   └── response/
│       └── ProductResponse.java          # Response DTO
├── src/main/resources/
│   └── application.yaml                  # DB + JPA config
└── frontend/
    ├── index.html
    ├── style.css
    └── script.js
```

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL running locally

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/stellar-inventory.git
cd stellar-inventory
```

### 2. Configure the database

Edit `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mydb
    username: your_username
    password: your_password
```

> Make sure the database `mydb` exists in your PostgreSQL instance.  
> You can create it with: `CREATE DATABASE mydb;`

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

### 4. Open the frontend

Open `frontend/index.html` in your browser (or serve it with any static server).

---

## 📡 API Endpoints

| Method   | Endpoint          | Description              |
|----------|-------------------|--------------------------|
| `GET`    | `/products`       | Get all active products  |
| `GET`    | `/products/{id}`  | Get product by ID        |
| `POST`   | `/products`       | Create a new product     |
| `PUT`    | `/products/{id}`  | Update a product         |
| `DELETE` | `/products/{id}`  | Delete a product         |

### Example Request — Create Product

```bash
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{"name": "Laptop", "price": 999.99}'
```

### Example Response

```json
{
  "id": 1,
  "name": "Laptop",
  "price": 999.99
}
```

---

## 🐛 Known Issues / Future Improvements

- [ ] Add `quantity` field to entity
- [ ] Implement pagination for product listing
- [ ] Add search/filter by name
- [ ] Write integration tests with `@SpringBootTest`
- [ ] Dockerize the application with `docker-compose`
- [ ] Add Swagger/OpenAPI documentation

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

> Built with ☕ Java & passion by [Your Name](https://github.com/YOUR_USERNAME)
