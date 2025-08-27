# 🛒 StoreFront API

StoreFront API is a **modular, domain-driven RESTful API** built with **Spring Boot 3**, **Jakarta JPA**, and **MySQL**.  
It provides a scalable foundation for an e-commerce platform, handling **products, categories, carts, orders, and authentication**.

The project follows **Domain-Driven Design (DDD)** principles, is fully versioned with **Flyway migrations**, and documented with **Swagger UI** for seamless integration.

---

## 🚀 Features
- **Product Management** - CRUD operations for items & categories
- **Cart & Order Management** - Add items to cart, place orders, track order history
- **Authentication & Authorization** - Secure user registration & login (JWT-ready)
- **Database Migrations** - Managed with Flyway for reproducible schema changes
- **API Documentation** - Interactive Swagger UI
- **Layered + Modular Architecture** - Following industry-standard DDD practices

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 3 (Spring Web, Spring Data JPA, Spring Security)**
- **Jakarta Persistence API (JPA)**
- **MySQL 8**
- **Flyway** (Database migrations)
- **Swagger / OpenAPI** (`springdoc-openapi`)
- **Lombok** (Boilerplate reduction)
- **Maven**
- **JUnit 5 & Mockito** (Testing)

---

## 📂 Project Structure (Domain-Driven Design)

The project is structured by **domain (feature-based)** rather than technical layers, ensuring **high cohesion** and **clear bounded contexts**.

## 📦 Installation

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL running locally or in Docker

### Steps
1. Clone the repository
   ```bash
   git clone https://github.com/theduo34/store-front-api.git
   cd store-front-api

## ⚙️ Environment Setup

This project uses **`.env`** and **`.mvn/jvm.config`** files for configuration.  
Both files have example templates **`.env.example`** and **`.mvn/jvm.config.example`** you can copy and update with your own credentials.

