# Projet3 Spring Boot Application

## Overview
This Spring Boot application provides a secure REST API with MySQL database integration, JWT authentication, and comprehensive API documentation.

## Technical Stack
- Spring Boot 3.3.5
- Spring Security
- Spring Data JPA
- MySQL Database
- Maven
- JWT Authentication

## Requirements
- Java 17+
- Maven
- MySQL 8.0+
- Available port 3001

## Quick Start Guide

### 1. Database Setup
1. Install MySQL 8.0 or higher
2. Import the database using the SQL file:
   ```bash
   mysql -u root -p < projet3oc.sql

### 2. Application Configuration
Create or modify src/main/resources/application.properties with the following settings:

# Server Configuration
spring.application.name=projet3
server.port=3001

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/projet3oc
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# Security Configuration
spring.security.user.name=user
spring.security.user.password=password

# JWT Configuration
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
jwt.issuer=projet3oc
jwt.audience=tester


### 3. Build and Run

# Build the project:
    mvn clean install
# Run the application:
    mvn spring-boot:run

### 4. Access the Application and documentation

- Access the application at http://localhost:3001
- Access the API documentation at http://localhost:3001/swagger-ui/index.html

