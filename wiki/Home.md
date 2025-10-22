# Java Learning Projects Wiki

Welcome to the Java Learning Projects wiki! This repository contains four Java projects demonstrating different Java versions and their features.

## Projects Overview

### [Java 8 Project](../java8-project/)
- **Java Version**: 8
- **Key Features**: Lambda expressions, Streams API, Method references
- **Docker Support**: ✅
- **Maven Build**: ✅

### [Java 17 Project](../java17-project/)
- **Java Version**: 17 (LTS)
- **Key Features**: Text blocks, Pattern matching, Sealed classes
- **Docker Support**: ✅
- **Maven Build**: ✅

### [Java 21 Project](../java21-project/)
- **Java Version**: 21 (LTS)
- **Key Features**: Virtual threads, Sequenced collections, String templates
- **Docker Support**: ✅
- **Maven Build**: ✅

### [Java 25 Project](../java25-project/)
- **Java Version**: 25
- **Key Features**: Value classes, Enhanced string templates, Latest Java features
- **Docker Support**: ✅
- **Maven Build**: ✅

## Getting Started

1. **Prerequisites**
   - Java 8+ (depending on the project)
   - Maven 3.6+
   - Docker (optional)

2. **Building Projects**
   ```bash
   # Navigate to any project directory
   cd java8-project  # or java17-project, java21-project, java25-project
   
   # Build the project
   mvn clean package
   ```

3. **Running Projects**
   ```bash
   # Run with Java
   java -jar target/[project-name]-1.0.0.jar
   
   # Or run with Docker
   docker build -t [project-name] .
   docker run [project-name]
   ```

## Project Structure

Each project follows the standard Maven directory layout:
```
[project-name]/
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

## Features by Java Version

### Java 8 Features
- Lambda expressions
- Streams API
- Method references
- Optional class
- Default methods in interfaces

### Java 17 Features
- Text blocks
- Pattern matching for instanceof
- Sealed classes
- Records
- Switch expressions

### Java 21 Features
- Virtual threads
- Sequenced collections
- String templates
- Pattern matching for switch
- Foreign Function & Memory API

### Java 25 Features
- Value classes (preview)
- Enhanced string templates
- Latest language features
- Performance improvements

## Contributing

Feel free to contribute to any of the projects by:
1. Adding new features
2. Improving documentation
3. Adding tests
4. Optimizing performance

## License

This project is open source and available under the MIT License.
