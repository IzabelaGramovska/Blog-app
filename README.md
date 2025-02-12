
# Blog App REST API

## Overview
The **Blog App REST API** is a Spring Boot application that provides endpoints for managing blog posts, user authentication, and authorization. The application supports role-based access control with `USER` and `ADMIN` roles.

## Features
- User authentication using JWT
- Role-based access control (User, Admin)
- CRUD operations for blog posts and comments
- User registration and login
- Secure API endpoints with Spring Security
- Database connectivity using MySQL and JPA

## Technologies Used
- **Java** (Spring Boot)
- **Spring Security** (JWT Authentication)
- **Spring Data JPA** (Persistence Layer)
- **MySQL** (Database)
- **Swagger** (API Documentation)

## Installation & Setup
### Prerequisites
- **Java 17+** installed
- **MySQL Database** running
- **Maven** installed
- **IntelliJ IDEA** or any Java IDE

### Clone the Repository
```bash
git clone https://github.com/IzabelaGramovska/Blog-app.git
cd Blog-app
```

### Configure Database
Update `application.properties` or `application.yml` file:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Run the Application
```bash
mvn spring-boot:run
```
Or using IntelliJ:

##### 1. Open the project in IntelliJ.
##### 2. Select the `SpringbootBlogRestApiApplication` class.
##### 3. Click **Run**.

## Project Structure
	Blog-app
	├── src/main/java/com/springboot/blog
	│   ├── controller     Controllers for handling API requests
	│   ├── service        Service layer for business logic
	│   ├── repository     Data access layer (JPA repositories)
	│   ├── model          Entity classes
	│   ├── security       JWT security configuration
	│   ├── config         Spring Boot configuration
	│   └── utils          Utility classes
	├── src/main/resources
	│   ├── application.properties   App configurations
	├── pom.xml       Maven dependencies
	└── README.md     Project documentation

##  Authentication & Roles

* Roles: Admin, User

* JWT Authentication: Users need to authenticate and receive a JWT token for accessing secured endpoints.


## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate user and get JWT

### Blog Posts
- `GET /api/posts` - Get all posts
- `POST /api/posts` - Create a new post (Admin only)
- `GET /api/posts/{id}` - Get post by ID
- `PUT /api/posts/{id}` - Update post (Admin only)
- `DELETE /api/posts/{id}` - Delete post (Admin only)

### Comments
- `POST /api/posts/{id}/comments` - Add comment to post
- `GET /api/posts/{id}/comments` - Get comments for a post

## Common Issues & Troubleshooting
### 1. MySQL Connection Error
**Error:** `Communications link failure`
**Solution:**
- Ensure MySQL is running and accessible.
- Verify credentials in `application.properties`.
- Allow remote connections in MySQL.

### 2. Port 5000 Already in Use
**Solution:** Change server port in `application.properties`:
```properties
server.port=8080
```

## Contact

For any questions, feel free to reach out:

* Author: Izabela Gramovska

* GitHub: IzabelaGramovska



