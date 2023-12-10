FROM gradle:jdk17 AS build
COPY .  .
RUN gradle build --no-daemon

FROM openjdk:17-0.1-jdk-slim
COPY --from=build /home/gradle/src/build/libs/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "demo.jar"]