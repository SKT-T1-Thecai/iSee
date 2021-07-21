# build stage
FROM maven:3.8.1-jdk-11-alpine as build-stage
COPY . .
RUN mvn clean package

# production stage
FROM openjdk:11 as production-stage
COPY --from=build-stage ./target/iSee-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10000
CMD ["java", "-jar", "app.jar"]
