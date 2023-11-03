FROM openjdk:11
EXPOSE 8082
ADD target/DevOps-1.0.jar DevOps-1.0.jar
ENTRYPOINT ["java", "-jar", "/DevOps-1.0.jar"]