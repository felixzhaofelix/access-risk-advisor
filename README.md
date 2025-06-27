# Access Risk Advisor

A Spring Boot microservice that evaluates system access requests based on rule-based logic.  
Designed to become part of a modular Identity & Access Management (IAM) solution.

---

## 📌 Table of Contents

- [Introduction](#-introduction)
- [Features](#-features)
- [API Documentation](#-api-documentation)
- [Testing](#-testing)
- [Project Structure](#-project-structure)
- [Future Enhancements](#-future-enhancements)
- [License](#-license)
- [Author](#-author)

---

## 🚀 Introduction

Access Risk Advisor is a lightweight API that simulates **risk scoring** for access requests between users, roles, and systems.

Its goal is to serve as one part of a larger IAM system with **clean separation of concerns**, where:

- ✅ This service handles **risk evaluation**
- ⬜ A separate **API Gateway** (future) manages **authorization**
- ⬜ A **frontend** or **client** consumes the API
- ⬜ Deployment can scale from local to cloud

Inspired by solution-style .NET architectures, the service is ready to grow into a multi-layered system with low coupling and clean interfaces.

---

## 📦 Features

- [x] REST API to evaluate access requests
- [x] Rule-based scoring (e.g., self-request, role mismatch)
- [x] Custom response model with decision + reason
- [x] Input validation with detailed errors
- [x] Global exception handling
- [x] Swagger UI for testing

---

## 📚 API Documentation

Once the app is running locally:

🔗 **Swagger UI:**  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Try:
- `POST /access-request` to evaluate access
- `GET /access-request/{id}` to review a decision

---

## 🧪 Testing

```bash
./mvnw test
```
Tests include:

✅ Validation failures (missing fields)

✅ Service logic (approve vs flag)

✅ Controller integration

⬜ Authorization layer (planned)

⬜ Persistence layer (planned)

## 🗂 Project Structure
```plain
access-risk-advisor/
├── controller/       # Exposes REST API
├── service/          # Risk evaluation logic
├── model/            # Request + response types
├── exception/        # Global error handling
└── AccessRiskAdvisorApplication.java
```

## 🔮 Future Enhancements
⬜ API Gateway for authorization

⬜ AI/ML-powered risk engine

⬜ Database integration (PostgreSQL, MongoDB, etc.)

⬜ Docker support

⬜ Public deployment (Render, Fly.io, etc.)

⬜ OpenAPI spec publishing

## 📄 License
MIT License (or adapt as needed)

## 👤 Author
Built by Felix Zhao
Designed for modular IAM development, security prototyping, and Spring Boot learning.
