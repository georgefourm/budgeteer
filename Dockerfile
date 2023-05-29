FROM eclipse-temurin:17-alpine

WORKDIR /tmp/build
COPY . /tmp/build

RUN ./gradlew bootJar

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/budgeteer-0.0.1-SNAPSHOT.jar"]