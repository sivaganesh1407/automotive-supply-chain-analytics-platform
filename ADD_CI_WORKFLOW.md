# Add CI Workflow (One-Time Setup)

If `.github/workflows/ci.yml` is not yet in your remote repository, add it manually:

1. Open your repository on GitHub
2. Click **Add file** → **Create new file**
3. In the filename box, type: `.github/workflows/ci.yml`
4. Copy the content from `ci-workflow.yml` in this repo (or from `.github/workflows/ci.yml` if already present locally)
5. Click **Commit new file**

Done! The CI badge will work after you update `OWNER` and `REPO` in [README.md](README.md). The workflow builds both backend and frontend.
