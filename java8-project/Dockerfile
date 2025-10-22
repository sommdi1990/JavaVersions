# Use OpenJDK 8 as base image
FROM openjdk:8-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/java8-project-1.0.0.jar app.jar

# Expose port (optional)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
