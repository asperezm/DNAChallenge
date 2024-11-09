# Usa una imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . .

# Instalar Gradle 7.5
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.5-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-7.5-bin.zip && \
    rm /tmp/gradle-7.5-bin.zip && \
    ln -s /opt/gradle/gradle-7.5/bin/gradle /usr/bin/gradle

# Ejecuta la compilación de tu proyecto
RUN gradle build

# Expón el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["gradle", "bootRun"]
