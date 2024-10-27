# Usa una imagen de Maven con OpenJDK 17 para construir el proyecto
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Agrega permisos de ejecución a mvnw
RUN chmod +x mvnw

# Ejecuta el comando de construcción de Maven, saltando los tests
RUN ./mvnw clean install -DskipTests
