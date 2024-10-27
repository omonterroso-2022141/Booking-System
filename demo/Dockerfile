# Usa una imagen de Maven con OpenJDK 17 para construir el proyecto
# Agrega permisos de ejecución a mvnw si está en el proyecto
RUN chmod +x mvnw && ./mvnw clean install -DskipTests

FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Ejecuta el comando de construcción de Maven, saltando los tests
RUN mvn clean install -DskipTests

# Cambia a una imagen más ligera de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación
CMD ["java", "-jar", "app.jar"]
