# Java 25 Project

A simple Java 25 project demonstrating Java 25 features using Maven.

## Features

- Java 25 with Maven build system
- Docker support
- JAR output with executable main class
- Demonstrates Java 25 features like string templates, value classes, virtual threads, and sequenced collections

## Prerequisites

- Java 25 or higher
- Maven 3.6 or higher
- Docker (optional)

## Building the Project

```bash
# Build the project
mvn clean package

# The JAR file will be created in target/java25-project-1.0.0.jar
```

## Running the Project

### Using Java directly:
```bash
java -jar target/java25-project-1.0.0.jar
```

### Using Docker:
```bash
# Build Docker image
docker build -t java25-project .

# Run Docker container
docker run java25-project
```

## Project Structure

```
java25-project/
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

## Java 25 Features Demonstrated

- **String Templates**: Enhanced string interpolation with STR template processor
- **Value Classes**: Immutable data carriers with value semantics (preview feature)
- **Virtual Threads**: Lightweight threads for high-concurrency applications
- **Sequenced Collections**: New interfaces for ordered collections
- **Pattern Matching**: Enhanced type checking and casting
- **Text Blocks**: Multi-line string literals
