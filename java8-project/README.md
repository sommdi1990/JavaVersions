# Java 8 Project

A simple Java 8 project demonstrating Java 8 features using Maven.

## Features

- Java 8 with Maven build system
- Docker support
- JAR output with executable main class
- Demonstrates Java 8 features like streams, lambda expressions, and method references

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Docker (optional)

## Building the Project

```bash
# Build the project
mvn clean package

# The JAR file will be created in target/java8-project-1.0.0.jar
```

## Running the Project

### Using Java directly:
```bash
java -jar target/java8-project-1.0.0.jar
```

### Using Docker:
```bash
# Build Docker image
docker build -t java8-project .

# Run Docker container
docker run java8-project
```

## Project Structure

```
java8-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       └── Main.java
│   │   └── resources/
│   └── test/
│       └── java/
├── target/
├── Dockerfile
├── pom.xml
└── README.md
```
