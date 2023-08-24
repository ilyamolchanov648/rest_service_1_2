FROM maven:3.8.4-openjdk-11 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:11
COPY --from=builder /app/target/your-app-name.jar /app/your-app-name.jar
CMD ["java", "-jar", "/app/app-name.jar"]
