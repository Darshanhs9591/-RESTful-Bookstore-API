# 📚 Bookstore RESTful API

A comprehensive RESTful API for managing a bookstore built with Spring Boot, featuring complete CRUD operations, pagination, sorting, filtering, and interactive API documentation.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 🚀 Features

- ✨ **Complete CRUD Operations** for Authors and Books
- 📄 **Pagination & Sorting** for efficient data retrieval
- 🔍 **Advanced Filtering** by title and author name
- 🛡️ **Input Validation** with Bean Validation
- ⚠️ **Global Exception Handling** with meaningful error messages
- 📖 **Interactive API Documentation** with Swagger UI
- 💾 **H2 In-Memory Database** for quick setup
- 🔗 **Entity Relationships** (ManyToOne/OneToMany)
- 📦 **RESTful Design** following best practices

## 📋 Table of Contents

- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Usage Examples](#usage-examples)
- [Testing](#testing)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 3.2.0 | Framework |
| Spring Data JPA | 3.2.0 | Data Access Layer |
| H2 Database | 2.2.224 | In-Memory Database |
| Hibernate | 6.3.1 | ORM Framework |
| Springdoc OpenAPI | 2.2.0 | API Documentation |
| Lombok | 1.18.30 | Boilerplate Code Reduction |
| Maven | 3.6+ | Build Tool |

## 📁 Project Structure

```
bookstore-api/
├── src/
│   ├── main/
│   │   ├── java/com/bookstore/
│   │   │   ├── BookstoreApplication.java          # Main application class
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java             # Swagger configuration
│   │   │   ├── controller/
│   │   │   │   ├── AuthorController.java          # Author REST endpoints
│   │   │   │   └── BookController.java            # Book REST endpoints
│   │   │   ├── entity/
│   │   │   │   ├── Author.java                    # Author entity
│   │   │   │   └── Book.java                      # Book entity
│   │   │   ├── repository/
│   │   │   │   ├── AuthorRepository.java          # Author data access
│   │   │   │   └── BookRepository.java            # Book data access
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java # Custom exception
│   │   │   │   ├── GlobalExceptionHandler.java    # Exception handler
│   │   │   │   └── ErrorResponse.java             # Error response DTO
│   │   │   └── dto/
│   │   │       └── BookFilterDto.java             # Filter DTO
│   │   └── resources/
│   │       └── application.properties              # App configuration
│   └── test/
├── pom.xml                                          # Maven dependencies
└── README.md                                        # Project documentation
```

## 🏁 Getting Started

### Prerequisites

Ensure you have the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.6+** or higher
- **Git** (optional, for cloning)
- **Postman** (optional, for API testing)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/bookstore-api.git
   cd bookstore-api
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR file:
   ```bash
   java -jar target/bookstore-api-1.0.0.jar
   ```

4. **Access the application**
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **H2 Console**: http://localhost:8080/h2-console
   - **API Docs**: http://localhost:8080/api-docs

### Quick Test

Once the application is running, you can quickly test it:

```bash
# Create an author
curl -X POST http://localhost:8080/authors \
  -H "Content-Type: application/json" \
  -d '{"name":"J.K. Rowling","email":"jk.rowling@example.com"}'

# Get all authors
curl http://localhost:8080/authors
```

## 🌐 API Endpoints

### Author Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/authors` | Create a new author | `{"name":"string","email":"string"}` |
| GET | `/authors` | Get all authors | - |
| GET | `/authors/{id}` | Get author by ID | - |
| PUT | `/authors/{id}` | Update author | `{"name":"string","email":"string"}` |
| DELETE | `/authors/{id}` | Delete author | - |

### Book Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/books` | Create a new book | `{"title":"string","isbn":"string","price":0.0,"author":{"id":0}}` |
| GET | `/books` | Get all books (paginated) | - |
| GET | `/books/{id}` | Get book by ID | - |
| PUT | `/books/{id}` | Update book | `{"title":"string","isbn":"string","price":0.0,"author":{"id":0}}` |
| DELETE | `/books/{id}` | Delete book | - |

### Query Parameters for `/books`

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `page` | Integer | 0 | Page number (0-indexed) |
| `size` | Integer | 10 | Items per page |
| `sortBy` | String | id | Sort field (id, title, price) |
| `sortDir` | String | asc | Sort direction (asc, desc) |
| `title` | String | - | Filter by book title (partial match) |
| `authorName` | String | - | Filter by author name (partial match) |

## 💡 Usage Examples

### Create an Author

**Request:**
```http
POST /authors HTTP/1.1
Content-Type: application/json

{
  "name": "J.K. Rowling",
  "email": "jk.rowling@example.com"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "J.K. Rowling",
  "email": "jk.rowling@example.com"
}
```

### Create a Book

**Request:**
```http
POST /books HTTP/1.1
Content-Type: application/json

{
  "title": "Harry Potter and the Philosopher's Stone",
  "isbn": "978-0747532699",
  "price": 19.99,
  "author": {
    "id": 1
  }
}
```

**Response:**
```json
{
  "id": 1,
  "title": "Harry Potter and the Philosopher's Stone",
  "isbn": "978-0747532699",
  "price": 19.99,
  "author": {
    "id": 1,
    "name": "J.K. Rowling",
    "email": "jk.rowling@example.com"
  }
}
```

### Get Books with Pagination and Sorting

**Request:**
```http
GET /books?page=0&size=5&sortBy=price&sortDir=asc HTTP/1.1
```

**Response:**
```json
{
  "books": [
    {
      "id": 1,
      "title": "Harry Potter and the Philosopher's Stone",
      "isbn": "978-0747532699",
      "price": 19.99,
      "author": {
        "id": 1,
        "name": "J.K. Rowling",
        "email": "jk.rowling@example.com"
      }
    }
  ],
  "currentPage": 0,
  "totalItems": 1,
  "totalPages": 1
}
```

### Filter Books

**Request:**
```http
GET /books?title=harry&authorName=rowling HTTP/1.1
```

### Error Response Example

**Request:**
```http
GET /authors/999 HTTP/1.1
```

**Response (404):**
```json
{
  "timestamp": "2024-12-19T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Author not found with id : '999'",
  "path": "/authors/999"
}
```

## 🧪 Testing

### Using Swagger UI

1. Navigate to http://localhost:8080/swagger-ui.html
2. Expand any endpoint
3. Click "Try it out"
4. Fill in the request body/parameters
5. Click "Execute"
6. View the response

### Using Postman

Import the provided Postman collection and test all endpoints:

1. Open Postman
2. Click **Import**
3. Paste the Postman collection JSON
4. Start testing the endpoints

### Using cURL

```bash
# Create Author
curl -X POST http://localhost:8080/authors \
  -H "Content-Type: application/json" \
  -d '{"name":"George R.R. Martin","email":"george@example.com"}'

# Get All Authors
curl http://localhost:8080/authors

# Create Book
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title":"A Game of Thrones","isbn":"978-0553103540","price":24.99,"author":{"id":1}}'

# Get Books with Filters
curl "http://localhost:8080/books?title=game&page=0&size=10"

# Update Book
curl -X PUT http://localhost:8080/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"A Game of Thrones","isbn":"978-0553103540","price":29.99,"author":{"id":1}}'

# Delete Book
curl -X DELETE http://localhost:8080/books/1
```

## ⚙️ Configuration

### Database Configuration

The application uses H2 in-memory database. Access the console at:
- **URL**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:bookstoredb`
- **Username**: `sa`
- **Password**: (leave empty)

### Application Properties

Key configurations in `application.properties`:

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:bookstoredb
spring.h2.console.enabled=true

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Swagger
springdoc.swagger-ui.path=/swagger-ui.html
```

### Changing Server Port

Edit `application.properties`:
```properties
server.port=8081
```

## 🏗️ Architecture

### Design Patterns Used

- **MVC Pattern**: Separation of concerns with Controllers, Services, and Repositories
- **Repository Pattern**: Data access abstraction
- **DTO Pattern**: Data transfer objects for API communication
- **Builder Pattern**: Entity creation (via Lombok)
- **Singleton Pattern**: Spring beans

### Database Schema

```sql
-- Authors Table
CREATE TABLE authors (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
);

-- Books Table
CREATE TABLE books (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  isbn VARCHAR(255) NOT NULL UNIQUE,
  price DOUBLE NOT NULL,
  author_id BIGINT NOT NULL,
  FOREIGN KEY (author_id) REFERENCES authors(id)
);
```

## 🔧 Troubleshooting

### Common Issues

**Issue: Port 8080 already in use**
```
Solution: Change port in application.properties to 8081 or kill the process using port 8080
```

**Issue: Maven dependencies not downloading**
```bash
mvn dependency:purge-local-repository
mvn clean install
```

**Issue: Java version mismatch**
```bash
# Check Java version
java -version

# Ensure JDK 17+ is installed
```

## 🚀 Future Enhancements

- [ ] Add Service layer for business logic
- [ ] Implement JWT authentication
- [ ] Add role-based access control
- [ ] Integrate PostgreSQL/MySQL
- [ ] Add Redis caching
- [ ] Implement GraphQL endpoint
- [ ] Add Docker support
- [ ] Create CI/CD pipeline
- [ ] Add integration tests
- [ ] Implement file upload for book covers

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
