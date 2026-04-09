# ✅ Task Manager - Backend API

A RESTful **Task Manager backend application** built using **Java and Spring Boot**, featuring **JWT-based authentication**, clean layered architecture, and **MySQL database integration**. 

Ideally designed for decoupled frontends, this project demonstrates real-world backend development practices including secure API design, stateless authentication, and scalable project structuring. *(Note: A React frontend is currently in the early stages of development and will be part of a future full-stack update).*

---

## 🚀 Features

- User registration and login
- Secure stateless authentication using **JWT (JSON Web Tokens)**
- Robust API security with **Spring Security**
- Password hashing using **BCrypt**
- Global exception handling with meaningful HTTP status codes
- Clean separation of concerns (Controller, Service, Repository)
- Task Management API:
  - Create tasks
  - Fetch user-specific tasks
  - Mark tasks as completed

---

## 🛠 Tech Stack

| Category | Technology |
|-------|-----------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.x |
| Security | Spring Security, JWT |
| Database | MySQL |
| ORM | JPA / Hibernate |
| Build Tool | Maven |
| API Docs | Springdoc OpenAPI (Swagger) |

---

## 🔐 Authentication Flow (JWT)

1. User logs in using email and password.
2. The backend validates the credentials.
3. A **JWT token** is generated and returned to the client.
4. The client securely stores the JWT.
5. For every protected backend request, the client sends the token in the `Authorization` header:
   ```text
   Authorization: Bearer <JWT_TOKEN>
   ```
6. A custom JWT filter validates the token and the request is processed.
7. Unauthenticated requests are rejected with a `401 Unauthorized`.

---

## 🧱 Project Structure (Backend)

```text
backendWork/
├── src/main/java/com/akshay/taskmanager
│   ├── controller → REST controllers
│   ├── service    → Business logic
│   ├── repository → Database access layer
│   ├── entity     → JPA entities
│   ├── dto        → Request & response objects
│   ├── security   → JWT utilities and filters
│   ├── exception  → Custom exceptions & global handler
│   └── config     → Application and security configuration
└── pom.xml
```

---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone the repository

```bash
git clone https://github.com/Akshayshourya/Project--Task-Manager.git
cd Project--Task-Manager/backendWork
```

### 2️⃣ Configure MySQL Database

1. Create a MySQL database for the project:
   ```sql
   CREATE DATABASE task_manager;
   ```
2. Update `src/main/resources/application.properties` with your credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
   spring.datasource.username=root
   spring.datasource.password=your_password

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

   jwt.secret=your_secret_key
   jwt.expiration=86400000
   ```

### 3️⃣ Run the application

```bash
./mvnw spring-boot:run
```
*The backend server will start at: `http://localhost:8080`*

---

## 🧪 API Testing & Documentation

You can explore and test the backend APIs via:
- **Swagger UI**: Visit `http://localhost:8080/swagger-ui.html` while the server is running.
- API clients like **Postman**, **Insomnia**, or **cURL**.

Remember to include the JWT token in your `Authorization` header when targeting protected endpoints!

---

## 🎯 Project Purpose

This project is built to:
- Strengthen backend fundamentals using Spring Boot.
- Deepen understanding of modern authentication using JSON Web Tokens.
- Practice scalable, clean architecture principles.
- Simulate end-to-end real-world backend workflows.

## 📌 Future Improvements

- [ ] 🔐 Role-based authorization (e.g., ADMIN vs USER)
- [ ] 🔁 Refresh token implementation for seamless sessions
- [ ] ⚠️ Add pagination to user tasks
- [ ] 🐳 Dockerization (Docker Compose for Database + Backend)
- [ ] 🧪 Comprehensive Unit & Integration Testing
- [ ] 🌐 **Frontend Integration (In Progress)**: Connecting the APIs to the WIP React client.

---

## 👤 Author

**Akshay Shourya Jain**  
Java Backend Developer  
- **GitHub**: [Akshayshourya](https://github.com/Akshayshourya)
- **LinkedIn**: [Akshay Shourya Jain](https://www.linkedin.com/in/akshay-shourya-jain-3b158236a/)

### ⭐ If you found this project helpful or inspiring, consider giving it a star on GitHub!