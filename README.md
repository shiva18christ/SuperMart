# Supermarket Project

## Overview
This is a Spring Boot application that manages a supermarket system with features for user management, shop management, inventory tracking, and integrated weather services. The application uses MongoDB for data storage, Redis for caching, and includes security features using JWT authentication.

## Features
- User authentication and authorization
- Shop management
- Inventory tracking
- Weather service integration
- Email notification service
- API documentation with Swagger
- Data caching with Redis
- Scheduled tasks for automated operations

## Technologies
- **Java 8**
- **Spring Boot 2.7.18**
- **Spring Security** - For authentication and authorization
- **MongoDB** - Primary database
- **Redis** - For caching
- **JWT** - For secure API authentication
- **Swagger/OpenAPI** - For API documentation
- **Project Lombok** - To reduce boilerplate code
- **Spring Mail** - For email services
- **RestTemplate** - For external API calls

## Project Structure
- **controller**: REST controllers for handling HTTP requests
- **config**: Configuration classes (Security, Swagger, Redis, CORS)
- **Utility**: Utility classes including JWT handling
- **Services**: Business logic implementations
- **Filter**: Request filters such as JWT authentication
- **Scheduler**: Scheduled tasks for automated operations
- **repository**: Data access objects for MongoDB interaction
- **base**: Base entity classes (User, Shop, ConfigEntity)
- **Cache**: Redis caching implementation
- **ApiResponse**: API response models

## Setup Instructions

### Prerequisites
- Java 8 JDK
- Maven 3.6+
- MongoDB
- Redis server

### Installation
1. Clone the repository
   ```
   git clone <repository-url>
   ```
   
2. Navigate to the project directory
   ```
   cd Supermarket
   ```
   
3. Build the project
   ```
   mvn clean install
   ```
   
4. Run the application
   ```
   mvn spring-boot:run
   ```
   
### Configuration
Configure your application by updating `application.properties` with your specific settings:
- MongoDB connection details
- Redis configuration
- Email service credentials
- Weather API key

## API Documentation
The API documentation is available via Swagger UI at:
```
http://localhost:8080/shiva
```

## Main APIs
- **/api/shop** - Shop management endpoints
- **/api/admin** - Admin operations
- **/api/user** - User management
- **/api/weather** - Weather information services
- **/api/public** - Public accessible endpoints

## License
This project is licensed under the MIT License.
