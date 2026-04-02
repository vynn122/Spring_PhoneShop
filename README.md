# 📱 Phone Shop API

A RESTful API built with **Spring Boot** for managing an online phone store.
This project provides backend services for managing **products, brands, categories, variants, and product images**.

---

# 🚀 Features

* Product management
* Brand management
* Category management
* Product variants
* Product image upload
* Pagination and filtering
* RESTful API design
* Swagger API documentation

---

# 🛠 Tech Stack

| Technology      | Description               |
| --------------- | ------------------------- |
| Java            | Programming language      |
| Spring Boot     | Backend framework         |
| Spring Data JPA | Database ORM              |
| PostgreSQL      | Relational database       |
| Redis           | Caching / session storage |
| Maven           | Dependency management     |
| Swagger OpenAPI | API documentation         |

---

# 📂 Project Structure

```
phoneshop-api
│
├── src/main/java/com/example/phoneshop
│
│   ├── controller        # REST API controllers
│   ├── service           # Service interfaces
│   ├── serviceImpl       # Business logic implementation
│   ├── repository        # JPA repositories
│   ├── entity            # Database entities
│   ├── dto               # Request & response DTOs
│   ├── exception         # Custom exceptions
│   ├── config            # Configuration classes
│   └── utils             # Utility classes
│
├── src/main/resources
│   ├── application.properties
│   └── static
│
└── pom.xml
```

---

# ⚙️ Installation

## 1 Clone the repository

```
git clone https://github.com/yourusername/phoneshop-api.git
```

---

## 2 Navigate to the project

```
cd phoneshop-api
```

---

## 3 Configure Database

Edit **application.properties**

```
spring.datasource.url=jdbc:postgresql://localhost:5432/phoneshop
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4 Run the application

Using Maven:

```
mvn spring-boot:run
```

Or run the main class:

```
PhoneShopApplication.java
```

Server will start at:

```
http://localhost:8080
```

---

# 📑 API Documentation

Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

This interface allows you to test all API endpoints.

---

# 📌 API Endpoints

## Product

| Method | Endpoint           | Description        |
| ------ | ------------------ | ------------------ |
| GET    | /api/products/list | Get all products   |
| GET    | /api/products/{id} | Get product detail |
| POST   | /api/products      | Create product     |
| PUT    | /api/products/{id} | Update product     |
| DELETE | /api/products/{id} | Delete product     |

---

## Brand

| Method | Endpoint         |
| ------ | ---------------- |
| GET    | /api/brands      |
| POST   | /api/brands      |
| PUT    | /api/brands/{id} |
| DELETE | /api/brands/{id} |

---

## Category

| Method | Endpoint             |
| ------ | -------------------- |
| GET    | /api/categories      |
| POST   | /api/categories      |
| PUT    | /api/categories/{id} |
| DELETE | /api/categories/{id} |

---

# 📦 Example Response

Example API response for product detail:

```json
{
  "success": true,
  "message": "Product retrieved successfully",
  "data": {
    "id": 2,
    "name": "iPhone 14 Pro Max",
    "brand": "Apple",
    "category": "Phone",
    "description": "Apple iPhone",
    "images": [
      "product/bcb191e6-52f4-465b-a98a-0cba80ef0a1f_iphone.jpg"
    ]
  }
}
```

---

# 🔎 Pagination Example

```
GET /api/products?page=0&size=10
```

Response:

```json
{
  "content": [],
  "page": 0,
  "size": 10,
  "totalElements": 100
}
```

---

# 🧪 Testing

You can test APIs using:

* Swagger UI
* Postman
* Curl

Example:

```
GET http://localhost:8080/api/products/1
```

---

# 🛡 Error Handling

Example error response:

```json
{
  "success": false,
  "message": "Product not found"
}
```

---

# 📈 Future Improvements

* Authentication with JWT
* Role-based access control
* Product reviews
* Order management
* Payment integration

---

# 👨‍💻 Author

Developed by **Vin**

---

# 📜 License

This project is licensed under the MIT License.
