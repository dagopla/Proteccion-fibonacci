# Usa una imagen base de OpenJDK 17
FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/fibonacci-0.0.1-SNAPSHOT.jar /app/fibonacci-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "fibonacci-0.0.1-SNAPSHOT.jar"]
