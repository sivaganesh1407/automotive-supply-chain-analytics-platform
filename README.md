# Automotive Supply Chain Analytics Platform

[![CI](https://github.com/sivaganesh1407/automotive-supply-chain-analytics-platform/actions/workflows/ci.yml/badge.svg)](https://github.com/sivaganesh1407/automotive-supply-chain-analytics-platform/actions/workflows/ci.yml)

Backend system simulating automotive manufacturing and supply chain data pipelines with **ETL processing** and **analytics APIs** using Spring Boot, JPA, and PostgreSQL.

## Tech Stack

- **Java 11** · **Spring Boot 2.7** · **Spring Data JPA** · **H2 / PostgreSQL**
- **Maven** · **Docker** · **Swagger/OpenAPI** · **Actuator**

## Features

- REST API for vehicles, inventory, dealers
- ETL pipeline (Extract-Transform-Load) from JSON
- Production, inventory, and dealer analytics
- Unit & integration tests
- API documentation (Swagger UI)
- Docker support
- CI/CD with GitHub Actions

## Quick Start

```bash
# Option 1: Run script
./run.sh

# Option 2: Direct
cd backend && mvn spring-boot:run
```

- **API:** http://localhost:8080
- **Swagger:** http://localhost:8080/swagger-ui.html
- **Health:** http://localhost:8080/actuator/health

## Run Tests

```bash
cd backend && mvn test
```

## Deploy to Render (Free)

1. Go to [dashboard.render.com](https://dashboard.render.com) → **New** → **Blueprint**
2. Connect repo: `sivaganesh1407/automotive-supply-chain-analytics-platform`
3. Click **Apply** (render.yaml auto-configures everything)
4. API live at `https://automotive-supply-chain-api.onrender.com`

See [DEPLOYMENT.md](DEPLOYMENT.md) for Railway, Fly.io options.

## Next: 3rd Project?

See [PROJECT_IDEAS.md](PROJECT_IDEAS.md) for recommendations (frontend for this project, e-commerce API, or real-time chat).

## Project Structure

```
backend/
├── controller/    # REST endpoints
├── service/      # Business logic
├── repository/   # JPA repositories
├── model/        # Entities
├── config/       # Configuration
└── dto/          # Data transfer objects
```

## License

MIT
