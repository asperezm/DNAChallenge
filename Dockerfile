# Usa una imagen oficial de OpenJDK 21 con Gradle
FROM gradle:7.5-jdk21 AS build

# Establece el directorio de trabajo en la imagen
WORKDIR /app

# Copia los archivos de tu proyecto al contenedor
COPY . .

# Ejecuta la compilación de tu proyecto
RUN gradle build

# Expón el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
CMD ["gradle", "bootRun"]
