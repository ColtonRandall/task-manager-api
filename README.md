# Task Manager API

A RESTful API for managing tasks built with Spring Boot. Uses an H2 in-memory database to keep things simple while the core functionality is being developed.

---

## Tech Stack

| Layer       | Technology             |
|-------------|------------------------|
| Language    | Java 21                |
| Framework   | Spring Boot 3.4.1      |
| Persistence | Spring Data JPA        |
| Validation  | Spring Boot Validation |
| Build Tool  | Maven                  |
| CI/CD       | GitHub Actions         |

## Getting Started

**Prerequisites:** Java 21+ (the Maven wrapper is included, no local Maven install needed)

```bash
git clone https://github.com/ColtonRandall/task-manager-api.git
cd task-manager-api
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`. To build a JAR instead:

```bash
./mvnw -B package
java -jar target/task-manager-api-0.0.1-SNAPSHOT.jar
```

## Manual Testing

The following `curl` commands can be used to verify the controller works. Start the app first with `./mvnw spring-boot:run`.

> **Note:** The task store HashMap is in-memory and does not persist between restarts.

---

### POST /tasks — Create a task

```bash
curl -s -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"name":"Buy groceries","description":"Milk, eggs, bread"}'
```

**Expected:** `200 OK` with the new task body including a generated `id` and `createdAt` timestamp.

```json
{
  "id": "e3b0c442-...",
  "name": "Buy groceries",
  "description": "Milk, eggs, bread",
  "createdAt": "2026-03-04T10:00:00"
}
```

---

### GET /tasks — Fetch all tasks

```bash
curl -s http://localhost:8080/tasks
```

**Expected:** `200 OK` with an array of all tasks.

---

### GET /tasks/{id} — Fetch a task by ID

```bash
curl -s http://localhost:8080/tasks/{id}
```

**Expected:** `200 OK` with the matching task, or `404` if not found.

---

### DELETE /tasks/{id} — Delete a task

```bash
curl -s -o /dev/null -w "HTTP Status: %{http_code}" -X DELETE http://localhost:8080/tasks/{id}
```

**Expected:** `204 No Content`

---

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
        └── TaskServiceTests.java
```
