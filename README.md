# ✅ Task Manager Backend

A RESTful **Task Manager backend application** built using **Java and Spring Boot**, featuring **JWT-based authentication**, clean layered architecture, and **MySQL database integration**.

This project demonstrates real-world backend development practices including authentication, authorization, secure API design, and scalable project structuring.

---

## 🚀 Features

- User registration and login
- Secure authentication using **JWT (JSON Web Tokens)**
- Stateless API security with **Spring Security**
- Password hashing with **BCrypt**
- Task management:
    - Create tasks
    - Fetch user tasks
    - Mark tasks as completed
- One-to-many relationship between User and Tasks
- Global exception handling with meaningful HTTP status codes
- Clean separation of concerns:
    - Controller
    - Service
    - Repository
- Production-style folder structure

---

## 🛠 Tech Stack

| Category | Technology |
|-------|-----------|
| Language | Java |
| Framework | Spring Boot |
| Security | Spring Security, JWT |
| Database | MySQL |
| ORM | JPA / Hibernate |
| Build Tool | Maven |
| Authentication | JWT |
| Password Encryption | BCrypt |
| Version Control | Git & GitHub |

---

## 🔐 Authentication Flow (JWT)

1. User logs in using **email and password**
2. Backend validates credentials
3. A **JWT token** is generated and returned
4. Client stores the JWT securely
5. For every protected request, client sends:

       Authorization: Bearer <JWT_TOKEN>

6. A custom JWT filter validates the token
7. Requests without a valid token are rejected with: 

       401 Unauthorized
JWT tokens remain valid until expiration, keeping the backend completely **stateless**.

---

## 📌 API Endpoints

### 🔓 Public Endpoints

| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/users` | Register a new user |
| POST | `/users/login` | Login and receive JWT |

---

### 🔐 Protected Endpoints (JWT Required)

| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/users/{userId}/tasks` | Create a task |
| GET | `/users/{userId}/tasks` | Fetch all tasks |
| PATCH | `/users/{userId}/tasks/{taskId}/complete` | Mark task as completed |

---

## 🧱 Project Structure
- src/main/java/com/akshay/taskmanager
- │
- ├── controller → REST controllers
- ├── service → Business logic
- ├── repository → Database access layer
- ├── entity → JPA entities
- ├── dto → Request & response DTOs
- ├── security → JWT utilities and filters
- ├── exception → Custom exceptions & global handler
- └── config → Security and application configuration
---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone the repository

    git clone https://github.com/<your-username>/task-manager-backend.git

### 2️⃣ Configure MySQL

Create a MySQL database:

    CREATE DATABASE task_manager;
Update application.properties:
           
    spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
    spring.datasource.username=root
    spring.datasource.password=your_password

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

    jwt.secret=your_secret_key
    jwt.expiration=86400000
### 3️⃣ Run the application
  
    mvn spring-boot:run
The server will start at:

    http://localhost:8080

## 🧪 API Testing

You can test all APIs using:

- Postman

- Insomnia

- Curl

Make sure to include the JWT token in the Authorization header for protected routes.

## 🎯 Project Purpose

This project was built to:

- Strengthen backend fundamentals

- Understand Spring Security deeply

- Implement JWT-based authentication

- Practice clean architecture

- Simulate production-level backend development

## 📌 Future Improvements

- 🔐 Role-based authorization (ADMIN / USER)

- 🔁 Refresh token implementation

- 🌐 Frontend integration (Full Stack)

- 🐳 Docker support

- 🧪 Unit & integration testing

- 📄 API documentation using Swagger / OpenAPI

## 👤 Author

Akshay Shourya Jain

Java Backend Developer
- GitHub : https://github.com/Akshayshourya
- LinedIn : https://www.linkedin.com/in/akshay-shourya-jain-3b158236a/

### ⭐ If you found this project helpful, consider giving it a star!