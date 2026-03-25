# Automotive Supply Chain Analytics - Frontend

React dashboard for the Automotive Supply Chain Analytics Platform.

## Run

```bash
npm install
npm run dev
```

Open http://localhost:5174

**Note:** Start the backend first (`cd backend && mvn spring-boot:run`)

### If npm install fails with E401 (auth error)

```bash
npm config set registry https://registry.npmjs.org/
npm install
```

## Features

- **Dashboard** - Analytics charts (production, inventory, dealers)
- **Vehicles** - Table of all vehicles
- **Inventory** - Table with status badges
- **Dealers** - Table of dealers by region
- **Load Data (ETL)** - One-click button to load sample data

## Build

```bash
npm run build
```

## API URL

Set `VITE_API_URL` for production builds to your deployed API base URL (see `frontend/.env.example`).
