# Usar una imagen base de Java 17
FROM openjdk:17-jdk-slim

# Crear un directorio para la aplicación dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
COPY build/libs/restaurante-productos-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa la aplicación (7654 en tu caso)
EXPOSE 7654

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
