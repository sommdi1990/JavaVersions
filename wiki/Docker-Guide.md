# Docker Guide for Java Projects

This guide explains how to use Docker with the Java projects in this repository.

## Overview

Each Java project includes a Dockerfile for containerization, making it easy to run the applications in isolated environments.

## Prerequisites

- Docker installed on your system
- Basic understanding of Docker concepts

## Project Dockerfiles

### Java 8 Project
```dockerfile
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY target/java8-project-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### Java 17 Project
```dockerfile
FROM openjdk:17-jre-alpine
WORKDIR /app
COPY target/java17-project-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### Java 21 Project
```dockerfile
FROM openjdk:21-jre-alpine
WORKDIR /app
COPY target/java21-project-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### Java 25 Project
```dockerfile
FROM openjdk:25-jre-alpine
WORKDIR /app
COPY target/java25-project-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

## Building and Running

### Step 1: Build the Java Project
```bash
# Navigate to project directory
cd java8-project  # or any other project

# Build the Maven project
mvn clean package
```

### Step 2: Build Docker Image
```bash
# Build Docker image
docker build -t java8-project .

# Or with specific tag
docker build -t java8-project:latest .
```

### Step 3: Run Docker Container
```bash
# Run the container
docker run java8-project

# Run with port mapping
docker run -p 8080:8080 java8-project

# Run in detached mode
docker run -d java8-project

# Run with custom name
docker run --name my-java-app java8-project
```

## Docker Commands Reference

### Basic Commands
```bash
# List running containers
docker ps

# List all containers
docker ps -a

# List Docker images
docker images

# Remove container
docker rm <container_id>

# Remove image
docker rmi <image_id>

# Stop container
docker stop <container_id>
```

### Advanced Commands
```bash
# Run with environment variables
docker run -e JAVA_OPTS="-Xmx512m" java8-project

# Run with volume mounting
docker run -v /host/path:/container/path java8-project

# Run with custom command
docker run java8-project java -jar app.jar --help

# View container logs
docker logs <container_id>

# Execute command in running container
docker exec -it <container_id> /bin/sh
```

## Multi-Stage Builds

For production applications, consider using multi-stage builds to reduce image size:

```dockerfile
# Build stage
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Runtime stage
FROM openjdk:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

## Docker Compose

For running multiple services, create a `docker-compose.yml`:

```yaml
version: '3.8'
services:
  java8-app:
    build: ./java8-project
    ports:
      - "8080:8080"
  
  java17-app:
    build: ./java17-project
    ports:
      - "8081:8080"
  
  java21-app:
    build: ./java21-project
    ports:
      - "8082:8080"
  
  java25-app:
    build: ./java25-project
    ports:
      - "8083:8080"
```

Run with:
```bash
docker-compose up
```

## Best Practices

### Security
- Use non-root users in containers
- Keep base images updated
- Scan images for vulnerabilities
- Use specific image tags instead of `latest`

### Performance
- Use Alpine Linux base images for smaller size
- Optimize layer caching
- Use multi-stage builds
- Set appropriate JVM memory limits

### Development
- Use volume mounting for development
- Keep Dockerfiles simple
- Use .dockerignore files
- Document Docker setup in README

## Troubleshooting

### Common Issues

1. **Port already in use**
   ```bash
   # Find process using port
   lsof -i :8080
   
   # Kill process
   kill -9 <pid>
   ```

2. **Permission denied**
   ```bash
   # Fix file permissions
   chmod +x target/*.jar
   ```

3. **Out of memory**
   ```bash
   # Run with memory limit
   docker run -m 512m java8-project
   ```

4. **Container won't start**
   ```bash
   # Check logs
   docker logs <container_id>
   
   # Run interactively
   docker run -it java8-project /bin/sh
   ```

## Monitoring

### Health Checks
```dockerfile
# Add health check to Dockerfile
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1
```

### Resource Monitoring
```bash
# Monitor container resources
docker stats

# Monitor specific container
docker stats <container_id>
```

## Production Considerations

- Use specific Java versions
- Set appropriate JVM options
- Configure logging
- Implement health checks
- Use container orchestration (Kubernetes, Docker Swarm)
- Set up monitoring and alerting
- Implement backup strategies
