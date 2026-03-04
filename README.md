# Task Manager API

A RESTful API for managing tasks built with Spring Boot. Uses an H2 in-memory database to keep things simple while the core functionality is being developed.

---

## Tech Stack

| Layer       | Technology             |
|-------------|------------------------|
| Language    | Java 21                |
| Framework   | Spring Boot 4.1.0      |
| Persistence | Spring Data JPA        |
| Validation  | Spring Boot Validation |
| Build Tool  | Maven                  |
| CI/CD       | GitHub Actions         |

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

## Manual Testing

The following `curl` commands can be used to verify the controller works. Start the app first with `./mvnw spring-boot:run`.

> **Note:** The controller currently uses an in-memory `HashMap` as a temporary task store. Data does not persist between restarts and will be replaced with a proper repository layer.

---

### POST /tasks — Create a task

```bash
curl -s -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"name":"Buy groceries","description":"Milk, eggs, bread"}'
```

**Expected:** `201 Created` with the new task body including a generated `id` and `createdAt` timestamp.

```json
{
  "id": "e3b0c442-...",
  "name": "Buy groceries",
  "description": "Milk, eggs, bread",
  "createdAt": "2026-03-04T10:00:00"
}
```

---

### GET /tasks/{id} — Fetch a task by ID

Replace `{id}` with the `id` returned from the POST above.

```bash
curl -s http://localhost:8080/tasks/{id}
```

**Expected:** `200 OK` with the matching task body.

---

### GET /tasks/{id} — Unknown ID

```bash
curl -s -o /dev/null -w "HTTP Status: %{http_code}" http://localhost:8080/tasks/bad-id
```

**Expected:** `HTTP Status: 404`

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
