# Use a base image with Java 11
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/DevOps_Project-1.0.jar /app/app.jar

# Expose the port as defined in application.properties
EXPOSE 8082

# Define the command to run your application with the specified application.properties
CMD ["java", "-jar", "app.jar", "--spring.config.location=file:/app/application.properties"]
