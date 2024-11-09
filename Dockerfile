# Usa una imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim AS builder

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia el archivo de configuración de Gradle y los archivos del proyecto
COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/
COPY settings.gradle /app/
COPY src /app/src/

# Instalar Gradle 8.5 (o superior)
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.5-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-8.5-bin.zip && \
    rm /tmp/gradle-8.5-bin.zip && \
    ln -s /opt/gradle/gradle-8.5/bin/gradle /usr/bin/gradle

# Establece memoria para Gradle
RUN echo "org.gradle.jvmargs=-Xmx2048m" >> gradle.properties

# Ejecuta la compilación de tu proyecto
RUN ./gradlew build --no-daemon

# Crear una nueva imagen para la ejecución
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY --from=builder /app/build/libs/*.jar app.jar

# Expón el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["java", "-jar", "app.jar"]
