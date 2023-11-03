FROM openjdk:11
EXPOSE 8082
ADD target/devops-1.0.jar devops-1.0.jar
ENTRYPOINT ["java", "-jar", "/devops-1.0.jar"]