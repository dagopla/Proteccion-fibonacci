# Etapa de construcción
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

# Copiar archivos necesarios para la construcción
COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw .

# Descargar dependencias y compilar la aplicación
RUN ./mvnw clean package -Dmaven.test.skip=true -Dmaven.main.skip=true -Dspring-boot.repackage.skip=true

# Copiar el código fuente y compilar la aplicación
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa final
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de construcción
COPY --from=builder /app/target/fibonacci-0.0.1-SNAPSHOT.jar .

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8002

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "fibonacci-0.0.1-SNAPSHOT.jar"]
