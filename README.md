# SpringWeb Project

SpringWeb is a Java application built with the Spring Boot framework, using standard Spring and Java libraries to keep the code clean, simple, and maintainable.
It exposes a RESTful API for basic CRUD operations and includes solutions for common web development tasks like security/authentication, efficient data handling from the database, and logging.

## Technologies and Libraries Used

- **Spring Data:**  
  Currently using JPA for flexible database querying, including native queries, JPQL, and projections.

- **Spring Starter Web (Jackson):**  
  Handles JSON data in API requests and responses.

- **MapStruct:**  
  Simplifies mapping between DTOs and entities, reducing boilerplate.

- **Lombok:**  
  Reduces code verbosity by generating boilerplate code (getters, setters, `equals()` & `hashCode()`, `toString()`, basic constructors, etc.).

- **Logback + SLF4J:**  
  Custom XML-based logging configuration for better readability. Can also log to file if needed.

- **Swagger UI:**  
  Interactive interface to explore and test API endpoints.

- **Spring Starter Test (JUnit, Mockito):**  
  For unit testing (in progress).

- **Spring Security and JWT:**  
  Secures endpoints with token-based authentication and role-based access control.

- **Jenkins and GitHub Actions:**  
  Runs builds and tests before merging pull requests.

- **Resilience4j:**  
  Limits and controls HTTP requests to specific endpoints.

## Planned Future Enhancements

- **JPA Specification for grid management:**  
  For building dynamic queries in paginated grids.

- **Spring Actuator, Spring Batch, Spring AOP:**  
  Exploring potential integrations to enhance application functionality.

- **Docker:**  
  Containerizing the app for easier deployment using Docker Compose.

---

This project is developed in my free time.
