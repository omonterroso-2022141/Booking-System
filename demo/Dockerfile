# Usa una imagen de Maven con OpenJDK 17 para construir el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Agrega permisos de ejecución a mvnw
RUN chmod +x mvnw

# Cambia a una imagen más ligera de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación
CMD ["java", "-jar", "app.jar"]
