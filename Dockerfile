# Stage 1: Build
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
