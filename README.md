# Automotive Supply Chain Analytics Platform

This project is a personal portfolio project built independently. It uses only public technologies and does not include any proprietary code or systems.

After you push this repository to GitHub, replace `OWNER` and `REPO` in the badge URL below with your account and repository name.

[![CI](https://github.com/OWNER/REPO/actions/workflows/ci.yml/badge.svg)](https://github.com/OWNER/REPO/actions/workflows/ci.yml)

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

**Backend:**
```bash
cd backend && mvn spring-boot:run
```

**Frontend:**
```bash
cd frontend && npm install && npm run dev
```

- **Frontend UI:** http://localhost:5174
- **API:** http://localhost:9090
- **Swagger:** http://localhost:9090/swagger-ui.html

Optional: copy `frontend/.env.example` to `frontend/.env` if you need a non-default API URL.

## Run Tests

```bash
cd backend && mvn test
```

## Deploy to Render (Free)

1. Go to [dashboard.render.com](https://dashboard.render.com) → **New** → **Blueprint**
2. Connect your fork or copy of this GitHub repository
3. Click **Apply** (`render.yaml` configures the web service and database)
4. Use the HTTPS URL Render assigns to your service (for example `https://<your-service-name>.onrender.com`)

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
