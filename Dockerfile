## Stage 1: bulid the app
#FROM maven:3.8.4-openjdk-17-slim AS build
#WORKDIR /app
#COPY pom.xml .
#COPY settings.xml /usr/share/maven/ref/
#COPY src src
#RUN mvn clean package -DskipTests
#
## Stage 2: Run the app
FROM openjdk:17-jdk
WORKDIR /app
#COPY --from=build /app/target/*.jar ./spending-tracker.jar
COPY ./target/*.jar ./spending-tracker.jar
EXPOSE 8080
CMD ["java", "-jar", "spending-tracker.jar"]