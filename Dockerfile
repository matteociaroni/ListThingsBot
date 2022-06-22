FROM openjdk:latest
COPY ./target/ListThingsBot-1.0-SNAPSHOT-jar-with-dependencies.jar bot.jar
ENTRYPOINT ["java", "-jar", "bot.jar"]