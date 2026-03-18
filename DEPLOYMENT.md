# Deployment Guide

Deploy this project to showcase it on your portfolio. Free options:

## Option 1: Render.com (Recommended)

1. Push code to GitHub
2. Go to [render.com](https://render.com) → New → Web Service
3. Connect your GitHub repo
4. Configure:
   - **Root Directory:** `backend`
   - **Runtime:** Docker
   - **Instance Type:** Free
5. Add PostgreSQL: New → PostgreSQL (free tier)
6. Add environment variables:
   - `SPRING_PROFILES_ACTIVE` = `prod`
   - `SPRING_DATASOURCE_URL` = (from PostgreSQL connection string)
   - `SPRING_DATASOURCE_USERNAME` = (from PostgreSQL)
   - `SPRING_DATASOURCE_PASSWORD` = (from PostgreSQL)
7. Deploy

Your API will be live at `https://your-app.onrender.com`

## Option 2: Railway

1. Go to [railway.app](https://railway.app)
2. New Project → Deploy from GitHub
3. Select repo, set root to `backend`
4. Add PostgreSQL plugin
5. Railway auto-injects `DATABASE_URL` - add `SPRING_DATASOURCE_*` from it
6. Deploy

## Option 3: Fly.io

```bash
cd backend
fly launch
fly postgres create
fly secrets set SPRING_DATASOURCE_URL=...
fly deploy
```

## After Deployment

- Add the live URL to your README and resume
- Test: `GET https://your-app.onrender.com/`
- Run ETL: `POST https://your-app.onrender.com/etl/run` with sample JSON
