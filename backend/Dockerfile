# Build stage
FROM eclipse-temurin:11-jdk-alpine AS build
WORKDIR /app

COPY pom.xml .
RUN apk add --no-cache maven && mvn dependency:go-offline -B

COPY src ./src
RUN mvn package -DskipTests -B

# Runtime stage
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Convert Render's postgresql:// to jdbc:postgresql:// for Spring Boot
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]
