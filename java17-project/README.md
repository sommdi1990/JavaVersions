# Java 17 Project

A simple Java 17 project demonstrating Java 17 features using Maven.

## Features

- Java 17 with Maven build system
- Docker support
- JAR output with executable main class
- Demonstrates Java 17 features like text blocks, pattern matching, and sealed classes

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker (optional)

## Building the Project

```bash
# Build the project
mvn clean package

# The JAR file will be created in target/java17-project-1.0.0.jar
```

## Running the Project

### Using Java directly:
```bash
java -jar target/java17-project-1.0.0.jar
```

### Using Docker:
```bash
# Build Docker image
docker build -t java17-project .

# Run Docker container
docker run java17-project
```

## Project Structure

```
java17-project/
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

## Java 17 Features Demonstrated

- **Text Blocks**: Multi-line string literals
- **Pattern Matching for instanceof**: Enhanced type checking
- **Sealed Classes**: Restricted inheritance hierarchies
