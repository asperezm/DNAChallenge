# Usa una imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . .

# Instala Gradle (si no está instalado)
RUN apt-get update && apt-get install -y gradle

# Ejecuta la compilación de tu proyecto
RUN gradle build

# Expón el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["gradle", "bootRun"]
