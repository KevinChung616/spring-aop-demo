# Use the official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app


# Copy the packaged JAR file into the container at /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/spring-validator-swagger.jar

# Expose the port your application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "spring-validator-swagger.jar"]