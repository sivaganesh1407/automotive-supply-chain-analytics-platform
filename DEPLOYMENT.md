# Deployment Guide

Deploy this project to showcase it on your portfolio. Free options:

## Option 1: Render.com (Recommended) - One-Click Blueprint

1. Push code to GitHub
2. Go to [dashboard.render.com](https://dashboard.render.com) → **New** → **Blueprint**
3. Connect your GitHub repo: `sivaganesh1407/automotive-supply-chain-analytics-platform`
4. Render will detect `render.yaml` and create the Web Service + PostgreSQL
5. Click **Apply**
6. Wait for deploy (~5 min)

Your API will be live at `https://automotive-supply-chain-api.onrender.com`

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
