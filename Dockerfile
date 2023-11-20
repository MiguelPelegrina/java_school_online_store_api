FROM eclipse-temurin:19

COPY ./target/final-task3-0.0.1-SNAPSHOT.jar ./app/app.jar

EXPOSE 8081/tcp

ENTRYPOINT ["java", "-jar", "./app/app.jar"]