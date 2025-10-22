# Maven Guide for Java Projects

This guide explains how to use Maven with the Java projects in this repository.

## Overview

All Java projects in this repository use Maven as the build tool, providing consistent dependency management and build processes.

## Project Structure

Each project follows the standard Maven directory layout:

```
[project-name]/
├── src/
│   ├── main/
│   │   ├── java/           # Main source code
│   │   └── resources/       # Main resources
│   └── test/
│       ├── java/           # Test source code
│       └── resources/      # Test resources
├── target/                 # Build output directory
├── pom.xml                # Maven project file
└── README.md              # Project documentation
```

## Maven Configuration

### POM.xml Structure

Each project's `pom.xml` includes:

- **Project Information**: Group ID, artifact ID, version
- **Java Version**: Compiler source and target
- **Plugins**: Compiler, Surefire, Shade plugins
- **Properties**: Plugin versions and encoding

### Key Plugins

1. **Maven Compiler Plugin**: Compiles Java source code
2. **Maven Surefire Plugin**: Runs unit tests
3. **Maven Shade Plugin**: Creates executable JAR files

## Building Projects

### Basic Commands

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package application
mvn package

# Clean, compile, test, and package
mvn clean package

# Install to local repository
mvn install
```

### Advanced Commands

```bash
# Skip tests
mvn package -DskipTests

# Run specific test
mvn test -Dtest=TestClassName

# Run with specific profile
mvn package -Pproduction

# Show dependency tree
mvn dependency:tree

# Update dependencies
mvn versions:display-dependency-updates
```

## Java Version Configuration

### Java 8 Project
```xml
<properties>
    <maven.compiler.source>8</maven.compiler.source>
    <maven.compiler.target>8</maven.compiler.target>
</properties>
```

### Java 17 Project
```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

### Java 21 Project
```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>
```

### Java 25 Project
```xml
<properties>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
</properties>
```

## JAR Generation

### Shade Plugin Configuration

The Maven Shade plugin creates executable JAR files:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.4.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <mainClass>com.example.Main</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Running JAR Files

```bash
# Run the generated JAR
java -jar target/[project-name]-1.0.0.jar

# Run with specific JVM options
java -Xmx512m -jar target/[project-name]-1.0.0.jar

# Run with system properties
java -Djava.awt.headless=true -jar target/[project-name]-1.0.0.jar
```

## Dependency Management

### Adding Dependencies

To add dependencies to a project, edit the `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
</dependencies>
```

### Common Dependencies

#### Testing
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

#### Logging
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.36</version>
</dependency>
```

#### JSON Processing
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
```

## Profiles

### Development Profile
```xml
<profiles>
    <profile>
        <id>development</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <maven.compiler.source>17</maven.compiler.source>
            <maven.compiler.target>17</maven.compiler.target>
        </properties>
    </profile>
</profiles>
```

### Production Profile
```xml
<profile>
    <id>production</id>
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <compilerArgs>
                        <arg>-Xlint:all</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
</profile>
```

## Best Practices

### Project Structure
- Follow Maven directory layout
- Separate main and test code
- Use appropriate package structure
- Keep resources organized

### Dependencies
- Use specific versions
- Avoid transitive dependency conflicts
- Use dependency management
- Regularly update dependencies

### Build Process
- Use consistent plugin versions
- Configure appropriate compiler options
- Set up proper test execution
- Create executable JARs

### Performance
- Use parallel builds when possible
- Optimize dependency resolution
- Use appropriate JVM options
- Monitor build times

## Troubleshooting

### Common Issues

1. **Compilation Errors**
   ```bash
   # Check Java version
   java -version
   
   # Check Maven version
   mvn -version
   
   # Clean and rebuild
   mvn clean compile
   ```

2. **Dependency Issues**
   ```bash
   # Update dependencies
   mvn dependency:resolve
   
   # Check dependency tree
   mvn dependency:tree
   
   # Force update
   mvn clean install -U
   ```

3. **Test Failures**
   ```bash
   # Run tests with debug
   mvn test -X
   
   # Skip tests temporarily
   mvn package -DskipTests
   ```

4. **JAR Issues**
   ```bash
   # Check JAR contents
   jar -tf target/*.jar
   
   # Verify manifest
   jar -xf target/*.jar META-INF/MANIFEST.MF
   cat META-INF/MANIFEST.MF
   ```

## IDE Integration

### IntelliJ IDEA
- Import as Maven project
- Configure Java SDK
- Set up run configurations
- Enable Maven auto-import

### Eclipse
- Import existing Maven project
- Configure build path
- Set up launch configurations
- Enable Maven nature

### VS Code
- Install Java extension pack
- Configure Java home
- Set up Maven integration
- Configure debugging

## Continuous Integration

### GitHub Actions
```yaml
name: Java CI
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Build with Maven
      run: mvn clean package
```

### Jenkins
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}
```
