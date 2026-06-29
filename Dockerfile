FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /tmp/build
COPY . /tmp/build

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:25-jre-alpine

WORKDIR /app
COPY --from=build /tmp/build/build/libs/budgeteer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
