# Task Manager API

A RESTful API for managing tasks built with Java 21 and Spring Boot.

---

## Tech Stack

| Layer      | Technology             |
|------------|------------------------|
| Language   | Java 21                |
| Framework  | Spring Boot 4.1.0-M2   |
| Persistence| H2 (file-based)        |
| ORM        | Spring Data JPA        |
| Validation | Spring Boot Validation |
| Build Tool | Maven                  |
| CI/CD      | GitHub Actions         |

---

## Getting Started

**Prerequisites:** Java 21+ (Maven wrapper included)
```bash
git clone https://github.com/ColtonRandall/task-manager-api.git
cd task-manager-api
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`. To build a JAR:
```bash
./mvnw -B package
java -jar target/task-manager-api-0.0.1-SNAPSHOT.jar
```

---

## Database

This project uses an H2 file-based database, stored at `./data/taskdb`. Data persists between restarts.

To inspect the database while the app is running, open the H2 console at `http://localhost:8080/h2-console` with the following settings:

| Field    | Value                        |
|----------|------------------------------|
| JDBC URL | `jdbc:h2:file:./data/taskdb` |
| Username | `sa`                         |
| Password | *<blank>*                    |

---

## Endpoints

| Method | Path                    | Description             |
|--------|-------------------------|-------------------------|
| POST   | `/tasks`                | Create a task           |
| GET    | `/tasks`                | Fetch all tasks         |
| GET    | `/tasks/{id}`           | Fetch a task by ID      |
| GET    | `/tasks/search/{name}`  | Fetch a task by name    |
| PATCH  | `/tasks/{id}/complete`  | Mark a task as complete |
| DELETE | `/tasks/{id}`           | Soft delete a task      |
| PATCH  | `/tasks/{id}`           | Undo a delete           |

### Examples

**Create a task**
```bash
curl -s -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"name":"Buy groceries","description":"Milk, eggs, bread"}'
```

**Get all tasks**
```bash
curl -s http://localhost:8080/tasks
```

**Complete a task**
```bash
curl -s -X PATCH http://localhost:8080/tasks/{id}/complete
```

**Delete a task (soft delete)**
```bash
curl -s -X DELETE http://localhost:8080/tasks/{id}
```

**Undo a deleted task**
```bash
curl -s -X PATCH http://localhost:8080/tasks/{id}
```

---

## Tests
```bash
./mvnw test
```

---

## Project Structure
```
src/
├── main/
│   ├── java/com/taskmanagerapi/
│   │   ├── controller/
│   │   │   └── TaskController.java
│   │   ├── model/
│   │   │   ├── Task.java
│   │   │   └── TaskStatus.java
│   │   ├── repository/
│   │   │   └── TaskRepository.java
│   │   ├── service/
│   │   │   └── TaskService.java
│   │   └── TaskManagerApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/taskmanagerapi/
        ├── TaskManagerApiApplicationTests.java
        └── TaskTests.java
```