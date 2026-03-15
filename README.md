# Task Manager API

A RESTful API for managing tasks built with Java 21 and Spring Boot.

---

## Tech Stack

| Layer          | Technology             |
|----------------|------------------------|
| Language       | Java 21                |
| Framework      | Spring Boot 4.1.0-M2   |
| Persistence    | H2 (file-based)        |
| ORM            | Spring Data JPA        |
| Validation     | Spring Boot Validation |
| Error Handling | @ControllerAdvice      |
| Build Tool     | Maven                  |
| CI/CD          | GitHub Actions         |

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
| Password | *(leave blank)*              |

---

## Endpoints

| Method | Path                           | Description              | Status |
|--------|--------------------------------|--------------------------|--------|
| POST   | `/tasks`                       | Create a task            | 201    |
| GET    | `/tasks`                       | Fetch all tasks          | 200    |
| GET    | `/tasks/{id}`                  | Fetch a task by ID       | 200    |
| GET    | `/tasks/search/{name}`         | Fetch a task by name     | 200    |
| GET    | `/tasks/search/status/{status}`| Fetch tasks by status    | 200    |
| PATCH  | `/tasks/{id}/complete`         | Mark a task as complete  | 200    |
| DELETE | `/tasks/{id}`                  | Soft delete a task       | 200    |
| PATCH  | `/tasks/{id}`                  | Undo a delete            | 200    |

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

## Error Handling

All errors return a consistent JSON response:
```json
{
  "error": "Task not found: abc-123",
  "status": 404,
  "timestamp": "2026-03-15T10:00:00"
}
```

| Scenario                  | Status |
|---------------------------|--------|
| Task not found            | 404    |
| Invalid request body      | 400    |
| Invalid status value      | 400    |
| Unexpected server error   | 500    |

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
│   │   ├── dto/
│   │   │   ├── TaskRequest.java
│   │   │   └── TaskResponse.java
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── TaskNotFoundException.java
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