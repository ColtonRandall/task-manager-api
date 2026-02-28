# Task Manager API

A RESTful API for managing tasks built with Spring Boot. Uses an H2 in-memory database to keep things simple while the core functionality is being developed.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.1.0 |
| Persistence | Spring Data JPA |
| Validation | Spring Boot Validation |
| Build Tool | Maven |
| CI/CD | GitHub Actions |

## Getting Started

**Prerequisites**
- Java 21+
- Maven 3.9+

**Clone & run**

```bash
git clone https://github.com/ColtonRandall/task-manager-api.git
cd task-manager-api
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`.

**Build only**

```bash
./mvnw -B package
```

## Tests

```bash
./mvnw test
```

## CI/CD

GitHub Actions runs a build and test on every push and pull request to `main`, and submits the dependency graph to GitHub for Dependabot alerts.

See [`.github/workflows/maven.yml`](.github/workflows/maven.yml).


## Project Structure

```
src/
├── main/
│   ├── java/com/taskmanagerapi/
│   │   ├── model/
│   │   │   ├── Task.java
│   │   │   └── TaskStatus.java
│   │   └── TaskManagerApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/taskmanagerapi/
        └── TaskManagerApiApplicationTests.java
```
