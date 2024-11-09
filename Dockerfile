# Usa una imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . .

# Da permisos de ejecución al archivo gradlew
RUN chmod +x gradlew

# Establece JAVA_HOME
ENV JAVA_HOME=/usr/local/openjdk-21
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Instalar Gradle 8.5 (o superior)
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.5-bin.zip -P /tmp && \
    unzip -d /opt/gradle /tmp/gradle-8.5-bin.zip && \
    rm /tmp/gradle-8.5-bin.zip && \
    ln -s /opt/gradle/gradle-8.5/bin/gradle /usr/bin/gradle

# Verifica si Java está correctamente instalado
RUN java -version

# Ejecuta la compilación de tu proyecto
RUN ./gradlew clean build -x check -x test

# Expón el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["gradle", "bootRun"]
