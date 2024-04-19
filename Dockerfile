FROM openjdk:23-ea-17-jdk-oraclelinux8

COPY ./target/online_store-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8081/tcp
