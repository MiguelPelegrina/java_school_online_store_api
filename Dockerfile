FROM openjdk:17-jdk-alpine

COPY target/final-task3-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "./app.jar"]