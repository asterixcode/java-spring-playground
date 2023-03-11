FROM azul/zulu-openjdk-alpine:17-latest

ARG JAR_FILE=./target/rest-postgres-0.0.1.jar
COPY ${JAR_FILE} gamers-directory.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "gamers-directory.jar"]