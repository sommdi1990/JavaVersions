# Java 21 Project

A simple Java 21 project demonstrating Java 21 features using Maven.

## Features

- Java 21 with Maven build system
- Docker support
- JAR output with executable main class
- Demonstrates Java 21 features like virtual threads, sequenced collections, and pattern matching

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Docker (optional)

## Building the Project

```bash
# Build the project
mvn clean package

# The JAR file will be created in target/java21-project-1.0.0.jar
```

## Running the Project

### Using Java directly:
```bash
java -jar target/java21-project-1.0.0.jar
```

### Using Docker:
```bash
# Build Docker image
docker build -t java21-project .

# Run Docker container
docker run java21-project
```

## Project Structure

```
java21-project/
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

## Java 21 Features Demonstrated

- **Virtual Threads**: Lightweight threads for high-concurrency applications
- **Sequenced Collections**: New interfaces for ordered collections
- **Pattern Matching**: Enhanced type checking and casting
- **Text Blocks**: Multi-line string literals
