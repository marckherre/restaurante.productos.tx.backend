# Etapa 1: Build con Gradle
FROM gradle:8.4-jdk17-alpine AS builder
WORKDIR /app

# Copiar solo archivos de configuración primero (para cachear deps)
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
RUN gradle --no-daemon dependencies

# Copiar el resto del código fuente
COPY src ./src

# Compilar el proyecto y generar el .jar (sin correr tests)
RUN gradle clean build -x test --no-daemon

# Etapa 2: Imagen final ligera solo con el JAR
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar solo el artefacto final
COPY --from=builder /app/build/libs/*.jar app.jar

# Puerto que expone tu app (ajústalo si es distinto)
EXPOSE 7654

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
