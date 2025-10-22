# Use OpenJDK 25 as base image (using latest available)
FROM openjdk:25-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/java25-project-1.0.0.jar app.jar

# Expose port (optional)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
