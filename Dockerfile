# syntax = docker/dockerfile:1.2
FROM gradle:jdk17 AS build
COPY .  .
RUN --mount=type=secret,id=_env,dst=/etc/secrets/.env cat /etc/secrets/.env
RUN ./gradlew build --no-daemon

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /home/gradle/src/build/libs/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "demo.jar"]