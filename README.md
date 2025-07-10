# Vendor Service

A Spring Boot REST API for managing vendors, products, orders, and shipments in an e-commerce platform.

---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [API Documentation](#api-documentation)
  - [Product APIs](#product-apis)
  - [Vendor APIs](#vendor-apis)
  - [Order APIs](#order-apis)
  - [Shipment APIs](#shipment-apis)
- [Running with Docker](#running-with-docker)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This service provides RESTful endpoints for:
- Vendor registration and authentication
- Product CRUD operations
- Order management
- Shipment creation and tracking

It is designed for use as a microservice in a larger e-commerce system.

---

## Features
- Vendor sign-up and login
- Product management (create, read, update, delete)
- Order creation and retrieval
- Shipment creation, update, and tracking
- MongoDB integration
- Docker support

---

## Tech Stack
- Java 17
- Spring Boot 3.4.0
- MongoDB
- Maven
- Docker

---

## Getting Started

### Prerequisites
- Java 17+
- Maven
- MongoDB instance (local or cloud)

### Installation
1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd vendor-service
   ```
2. **Configure environment variables:**
   Create a `.env` file or set the following variables in your environment:
   - `MONGO_DATABASE`
   - `MONGO_ADMIN`
   - `MONGO_PASSWORD`
   - `MONGO_CLUSTER`

3. **Build the project:**
   ```bash
   ./mvnw clean install
   ```
4. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The service will start on port `8081` by default.

---

## Environment Variables

These are required for MongoDB connection (see `src/main/resources/application.properties`):
- `MONGO_DATABASE` - MongoDB database name
- `MONGO_ADMIN` - MongoDB username
- `MONGO_PASSWORD` - MongoDB password
- `MONGO_CLUSTER` - MongoDB cluster URL

---

## API Documentation

### Product APIs (`/api/products`)

| Method | Endpoint                | Description           | Body/Params |
|--------|-------------------------|-----------------------|-------------|
| GET    | `/api/products`         | Get all products      | -           |
| GET    | `/api/products/{id}`    | Get product by ID     | -           |
| POST   | `/api/products`         | Create product        | `{ name, price, description, category, stockQuantity, vendorID }` |
| PUT    | `/api/products/{id}`    | Update product        | `{ name, description, price, category, stockQuantity, vendorID }` |
| DELETE | `/api/products/{id}`    | Delete product        | `{ vendorID }` |

---

### Vendor APIs (`/api/vendors`)

| Method | Endpoint                      | Description           | Body/Params |
|--------|-------------------------------|-----------------------|-------------|
| POST   | `/api/vendors/signup`         | Register vendor       | `{ name, email, password, phoneNumber }` |
| POST   | `/api/vendors/login`          | Vendor login          | `{ email, password }` |
| GET    | `/api/vendors/shipments/{id}` | Vendor's shipments    | -           |

---

### Order APIs (`/api/orders`)

| Method | Endpoint           | Description           | Body/Params |
|--------|--------------------|-----------------------|-------------|
| GET    | `/api/orders/{id}` | Get order by ID       | -           |
| GET    | `/api/orders`      | Get all orders        | -           |
| POST   | `/api/orders/`     | Create order          | `{ address, phoneNumber, totalAmount, status }` |

---

### Shipment APIs (`/api/shipments`)

| Method | Endpoint                        | Description           | Body/Params |
|--------|----------------------------------|-----------------------|-------------|
| POST   | `/api/shipments/{vendorId}`      | Create shipment       | `{ carrier, orderId }` |
| GET    | `/api/shipments/vendor/{vendor}` | Get vendor shipments  | -           |
| PUT    | `/api/shipments/{id}`            | Update shipment       | `{ carrier, deliveryDate }` (at least one required) |

---

## Running with Docker

1. **Build the JAR:**
   ```bash
   ./mvnw clean package
   ```
2. **Build the Docker image:**
   ```bash
   docker build -t vendor-service .
   ```
3. **Run the container:**
   ```bash
   docker run -p 8081:8081 --env-file .env vendor-service
   ```

---
