# Bookstore Backend with Kafka

## Overview
This project is a **Spring Boot** backend for a bookstore application.  

---

## Technologies
- **Java**: 21
- **Spring Boot**: 3.5.3
- **Spring Data JPA**
- **Spring Kafka**
- **PostgreSQL**
- **Docker / Podman**
- **Maven**

---

## Project Structure

src/
├── main/java/com/bookstore/bookstore
│ ├── controller/ # REST controllers
│ ├── dto/ # Data Transfer Objects
│ ├── model/ # Entities (JPA)
│ ├── repository/ # Database access
│ ├── service/ # Business logic
│ ├── kafka/ # Kafka producers and consumers
│ ├── configuration/ # Kafka config
│ └── BookstoreApplication.java
└── resources/
├── application.yml # App config
└── docker-compose.yml # DB + Kafka setup

## Features
### 1. **Books Management**
- Create and retrieve books
- Kafka producer sends "book created" events
- Kafka consumer logs book events

### 2. **User Management**
- Create and manage users
- Users can place orders

### 3. **Orders**
- Orders link `Book` and `User`
- Order creation sends Kafka events

### 4. **Notifications**
- Kafka producer sends notifications on order creation
- Consumer logs received notifications

---
