#!/bin/sh
# Convert Render's postgresql:// to jdbc:postgresql:// for Spring Boot
if [ -n "$DATABASE_URL" ] && [ -z "$SPRING_DATASOURCE_URL" ]; then
  export SPRING_DATASOURCE_URL="jdbc:$(echo $DATABASE_URL | sed 's|^postgresql://[^@]*@|postgresql://|')"
  [ -n "$DATABASE_USER" ] && export SPRING_DATASOURCE_USERNAME="$DATABASE_USER"
  [ -n "$DATABASE_PASSWORD" ] && export SPRING_DATASOURCE_PASSWORD="$DATABASE_PASSWORD"
fi
exec java -jar app.jar
