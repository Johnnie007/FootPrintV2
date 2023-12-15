#FROM gradle:jdk17 AS build
#COPY .  .
#RUN ./gradlew build
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build build/libs/footprint-0.0.1-SNAPSHOT.jar footprint.jar
#EXPOSE 8080
#ENTRYPOINT ["sh","-c","java","-jar", "/footprint.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#
#FROM azul/zulu-openjdk:17-latest
#VOLUME /tmp
#COPY build/libs/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM eclipse-temurin:17-jdk-alpine
#
## Copy the built jar file into the image
#COPY build/libs/*.jar app.jar
#
## Set the entry point to run your application
#ENTRYPOINT ["java","-jar","/app.jar"]
