FROM openjdk:21
EXPOSE 8080
ADD target/outbox-pattern.jar outbox-pattern.jar
ENTRYPOINT ["java", "-jar", "outbox-pattern.jar"]