# Automotive Supply Chain Analytics Platform - Backend

Spring Boot backend for automotive manufacturing data pipelines, ETL processing, and analytics APIs.

## Requirements

- Java 11+
- Maven 3.6+

## Run the Application

```bash
mvn spring-boot:run
```

The server starts at `http://localhost:9090` (override with `PORT=...` or `server.port` if needed).

## Run Tests

```bash
mvn test
```

## API Documentation

- **Swagger UI**: http://localhost:9090/swagger-ui.html
- **OpenAPI JSON**: http://localhost:9090/api-docs

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | / | API info and endpoints |
| GET | /vehicles | List all vehicles |
| GET | /vehicles/{id} | Get vehicle by ID |
| GET | /inventory | List all inventory |
| GET | /inventory/{id} | Get inventory by ID |
| GET | /inventory/status/{status} | Get inventory by status |
| GET | /dealers | List all dealers |
| GET | /dealers/{id} | Get dealer by ID |
| GET | /analytics/production | Production analytics |
| GET | /analytics/inventory | Inventory analytics |
| GET | /analytics/dealers | Dealer analytics |
| POST | /etl/run | Run ETL pipeline (JSON body) |
| GET | /actuator/health | Health check |

## Profiles

- **default** / **dev**: H2 in-memory database
- **prod**: PostgreSQL — set `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD` (or use `docker-compose`, which sets them). Render supplies `DATABASE_*`; `entrypoint.sh` maps them to Spring properties.
- **test**: H2 for tests

## H2 Console (dev only)

Access at `http://localhost:9090/h2-console`

- JDBC URL: `jdbc:h2:mem:automotive_db`
- Username: `sa`
- Password: (empty)

## Docker

```bash
# Build and run with PostgreSQL
docker-compose up --build

# App: http://localhost:9090
# PostgreSQL: localhost:5432
```

## Sample ETL Payload

See `src/main/resources/sample-etl-payload.json` for a sample POST body for `/etl/run`.

```bash
curl -X POST http://localhost:9090/etl/run \
  -H "Content-Type: application/json" \
  -d @src/main/resources/sample-etl-payload.json
```
