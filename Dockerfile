# Usa la imagen de OpenJDK 21
FROM openjdk:21-jdk-slim

# Copia el archivo JAR de tu aplicación al contenedor
COPY build/libs/my-app.jar /app/my-app.jar

# Establece el directorio de trabajo
WORKDIR /app

# Expón el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "my-app.jar"]
