# Java Learning Projects

This repository contains four Java projects demonstrating different Java versions and their features, along with comprehensive documentation.

## 🚀 Projects Overview

| Project | Java Version | Key Features | Status |
|---------|-------------|--------------|---------|
| [Java 8 Project](./java8-project/) | 8 | Lambda expressions, Streams API, Method references | ✅ Ready |
| [Java 17 Project](./java17-project/) | 17 (LTS) | Text blocks, Pattern matching, Sealed classes | ✅ Ready |
| [Java 21 Project](./java21-project/) | 21 (LTS) | Virtual threads, Sequenced collections, String templates | ✅ Ready |
| [Java 25 Project](./java25-project/) | 25 | Value classes, Enhanced string templates, Latest features | ✅ Ready |

## 📁 Repository Structure

```
LearnJava/
├── java8-project/          # Java 8 project with Maven & Docker
├── java17-project/         # Java 17 project with Maven & Docker
├── java21-project/         # Java 21 project with Maven & Docker
├── java25-project/         # Java 25 project with Maven & Docker
├── wiki/                   # GitHub wiki documentation
│   ├── Home.md
│   ├── Java-Versions.md
│   ├── Docker-Guide.md
│   └── Maven-Guide.md
└── README.md
```

## 🛠️ Prerequisites

- **Java**: 8+ (depending on the project)
- **Maven**: 3.6+
- **Docker**: Latest version (optional)
- **Git**: For version control

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd LearnJava
```

### 2. Choose a Project
```bash
# Navigate to any project
cd java8-project    # or java17-project, java21-project, java25-project
```

### 3. Build the Project
```bash
# Build with Maven
mvn clean package
```

### 4. Run the Project

#### Option A: Direct Java Execution
```bash
java -jar target/[project-name]-1.0.0.jar
```

#### Option B: Docker Execution
```bash
# Build Docker image
docker build -t [project-name] .

# Run Docker container
docker run [project-name]
```

## 📚 Documentation

### Wiki Pages
- **[Home](./wiki/Home.md)**: Overview of all projects
- **[Java Versions](./wiki/Java-Versions.md)**: Detailed comparison of Java versions
- **[Docker Guide](./wiki/Docker-Guide.md)**: Complete Docker setup and usage
- **[Maven Guide](./wiki/Maven-Guide.md)**: Maven configuration and best practices

### Project-Specific Documentation
Each project includes its own README.md with:
- Feature demonstrations
- Build instructions
- Running instructions
- Project structure

## 🔧 Features by Java Version

### Java 8 Features
- ✅ Lambda expressions
- ✅ Streams API
- ✅ Method references
- ✅ Optional class
- ✅ Default methods in interfaces

### Java 17 Features
- ✅ Text blocks
- ✅ Pattern matching for instanceof
- ✅ Sealed classes
- ✅ Records
- ✅ Switch expressions

### Java 21 Features
- ✅ Virtual threads
- ✅ Sequenced collections
- ✅ String templates
- ✅ Pattern matching for switch
- ✅ Foreign Function & Memory API

### Java 25 Features
- ✅ Value classes (preview)
- ✅ Enhanced string templates
- ✅ Latest language features
- ✅ Performance improvements

## 🐳 Docker Support

All projects include Docker support with:
- Optimized base images
- Multi-stage builds (where applicable)
- Health checks
- Port configuration
- Environment variable support

### Docker Commands
```bash
# Build all projects
docker build -t java8-project ./java8-project
docker build -t java17-project ./java17-project
docker build -t java21-project ./java21-project
docker build -t java25-project ./java25-project

# Run all projects
docker run java8-project
docker run java17-project
docker run java21-project
docker run java25-project
```

## 📦 Maven Configuration

Each project uses Maven with:
- **Compiler Plugin**: Java version-specific compilation
- **Surefire Plugin**: Unit testing
- **Shade Plugin**: Executable JAR creation
- **Dependency Management**: Clean dependency resolution

### Build Commands
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package application
mvn package

# Install to local repository
mvn install
```

## 🧪 Testing

Each project includes:
- Unit test structure
- Test configuration
- Test execution via Maven
- Docker testing support

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=TestClassName

# Skip tests
mvn package -DskipTests
```

## 🔄 Continuous Integration

The repository is configured for CI/CD with:
- GitHub Actions support
- Docker build automation
- Multi-version testing
- Automated deployment

## 📈 Performance Considerations

### JVM Options
```bash
# Memory optimization
java -Xmx512m -Xms256m -jar target/*.jar

# Garbage collection tuning
java -XX:+UseG1GC -jar target/*.jar

# Virtual threads (Java 21+)
java --enable-preview -jar target/*.jar
```

### Docker Optimization
```bash
# Multi-stage builds
docker build --target production .

# Resource limits
docker run -m 512m --cpus="1.0" [project-name]
```

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](./CONTRIBUTING.md) for details.

### How to Contribute
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

## 🆘 Support

If you encounter any issues:
1. Check the [Wiki](./wiki/) for detailed documentation
2. Review the project-specific README files
3. Check the [Issues](../../issues) page
4. Create a new issue if needed

## 🔗 Useful Links

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Docker Documentation](https://docs.docker.com/)
- [GitHub Wiki](https://github.com/your-username/LearnJava/wiki)

---

**Happy Coding! 🎉**
