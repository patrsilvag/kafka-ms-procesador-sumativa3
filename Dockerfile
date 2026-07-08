# 1. Etapa de compilación (Multi-stage build)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Etapa de ejecución
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copiamos solo el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]