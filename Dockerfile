FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar /app/your-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/your-application.jar"]
