FROM openjdk:17-jdk-slim AS build

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR word-bank-backend
COPY --from=build target/*.jar word-bank-backend.jar
ENTRYPOINT ["java", "-jar", "word-bank-backend.jar"]