FROM openjdk:17-oracle
COPY target/ms-broker.jar ms-broker.jar
EXPOSE 8015
ENTRYPOINT ["java", "-jar", "/ms-broker.jar"]