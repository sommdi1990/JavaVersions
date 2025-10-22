# Java 25 Features Documentation

This document provides comprehensive documentation for Java 25 features demonstrated in the project.

## Table of Contents

1. [Value Classes](#value-classes)
2. [Enhanced String Templates](#enhanced-string-templates)
3. [Performance Improvements](#performance-improvements)
4. [Language Evolution](#language-evolution)
5. [JVM Improvements](#jvm-improvements)
6. [Performance Benchmarks](#performance-benchmarks)
7. [Best Practices](#best-practices)

## Value Classes

### Overview
Value classes are a new kind of class that provides immutable data carriers with value semantics. They are designed to be more efficient than traditional classes for representing simple data.

### Basic Syntax
```java
// Value class definition
public value class Point {
    public final double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

### Features
- **Immutability**: All fields are final
- **Value semantics**: Equality based on content, not identity
- **Automatic methods**: equals, hashCode, toString
- **Performance**: Optimized for memory usage and performance
- **Validation**: Can include validation in constructors

### Advanced Examples
```java
// Value class with validation
public value class Money {
    public final double amount;
    public final String currency;
    
    public Money(double amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        this.amount = amount;
        this.currency = currency;
    }
    
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(this.amount + other.amount, this.currency);
    }
    
    public String format() {
        return String.format("%.2f %s", amount, currency);
    }
}

// Value class with complex data
public value class Person {
    public final String name;
    public final int age;
    public final String email;
    
    public Person(String name, int age, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    public String getAgeGroup() {
        if (age < 18) return "Minor";
        if (age < 65) return "Adult";
        return "Senior";
    }
    
    public boolean isAdult() {
        return age >= 18;
    }
}
```

### Real-world Examples
```java
// Financial calculations
Money usd100 = new Money(100, "USD");
Money usd200 = new Money(200, "USD");
Money eur100 = new Money(100, "EUR");

System.out.println("USD 100 + USD 200 = " + usd100.add(usd200));
System.out.println("USD 100 equals EUR 100: " + usd100.equals(eur100));

// Geographic coordinates
Coordinate nyc = new Coordinate(40.7128, -74.0060);
Coordinate london = new Coordinate(51.5074, -0.1278);

System.out.println("NYC: " + nyc);
System.out.println("London: " + london);
System.out.println("Distance NYC to London: " + nyc.distanceTo(london) + " km");

// Color representation
Color red = new Color(255, 0, 0);
Color green = new Color(0, 255, 0);
Color blue = new Color(0, 0, 255);

System.out.println("Red: " + red);
System.out.println("Green: " + green);
System.out.println("Blue: " + blue);
System.out.println("Red + Green = " + red.mix(green));
```

## Enhanced String Templates

### Overview
Enhanced string templates provide improved performance and additional features for string interpolation, making them more efficient and powerful.

### Basic Usage
```java
String name = "Alice";
int age = 25;
String city = "New York";

// Enhanced STR template with better performance
String enhancedTemplate = STR."Hello, my name is \{name} and I am \{age} years old.";

// Template with complex expressions
String complexTemplate = STR."User: \{name.toUpperCase()}, Age: \{age * 2}, City: \{city.toLowerCase()}";

// Template with method calls
String methodTemplate = STR."Current time: \{new Date()}, Random: \{Math.random()}";
```

### Advanced Template Processors
```java
// Enhanced JSON processor with validation
String jsonTemplate = JSON."""
        {
            "student": {
                "name": "\{name}",
                "score": \{score},
                "subject": "\{subject}",
                "grade": "\{score >= 90 ? "A" : score >= 80 ? "B" : "C"}",
                "timestamp": "\{new Date()}"
            }
        }
        """;

// Enhanced HTML processor with escaping
String htmlTemplate = HTML."""
        <div class="student-card">
            <h2>\{name}</h2>
            <p>Score: \{score}</p>
            <p>Subject: \{subject}</p>
            <p>Grade: \{score >= 90 ? "A" : score >= 80 ? "B" : "C"}</p>
            <p>Generated: \{new Date()}</p>
        </div>
        """;

// Enhanced SQL processor with parameterization
String sqlTemplate = SQL."""
        SELECT u.id, u.name, u.score, u.subject
        FROM users u
        WHERE u.name = '\{name}'
        AND u.score >= \{score}
        ORDER BY u.score DESC
        LIMIT 10
        """;
```

### Template Composition
```java
// Compose templates
String userTemplate = STR."User: \{name}, Age: \{age}";
String contactTemplate = STR."Email: \{email}";
String composedTemplate = STR."\{userTemplate}, \{contactTemplate}";

// Nested templates
String outerTemplate = STR."""
        <div class="user-info">
            \{STR."<h1>\{name}</h1>"}
            \{STR."<p>Age: \{age}</p>"}
            \{STR."<p>Email: \{email}</p>"}
        </div>
        """;

// Template with conditional logic
String conditionalTemplate = STR."""
        User Profile:
        Name: \{name}
        Age: \{age}
        Status: \{age >= 18 ? "Adult" : "Minor"}
        Email: \{email}
        """;
```

## Performance Improvements

### Overview
Java 25 includes significant performance improvements across the JVM, compiler, and runtime.

### JVM Optimizations
- **Improved garbage collection**: Better memory management
- **Enhanced JIT compilation**: Faster code execution
- **Optimized virtual threads**: Better performance for concurrent applications
- **Memory efficiency**: Reduced memory footprint

### Compiler Improvements
- **Faster compilation**: Improved build times
- **Better optimization**: More efficient generated code
- **Enhanced type inference**: Better type checking
- **Improved error messages**: More helpful diagnostics

### Runtime Enhancements
- **Faster startup**: Reduced application startup time
- **Better memory usage**: More efficient memory allocation
- **Enhanced concurrency**: Improved thread performance
- **Optimized collections**: Better performance for data structures

## Language Evolution

### Overview
Java 25 continues the evolution of the Java language with new features and improvements.

### New Language Features
- **Enhanced pattern matching**: More powerful type checking
- **Improved switch expressions**: Better exhaustiveness checking
- **Enhanced records**: More features for data classes
- **Better type inference**: Improved type checking

### API Improvements
- **Enhanced collections**: New methods and improvements
- **Better stream operations**: More efficient processing
- **Improved concurrency**: Better thread handling
- **Enhanced I/O**: More efficient file operations

## JVM Improvements

### Overview
The JVM in Java 25 includes significant improvements for performance, memory management, and concurrency.

### Garbage Collection
- **Improved algorithms**: Better memory management
- **Reduced pauses**: Less impact on application performance
- **Better tuning**: More configuration options
- **Enhanced monitoring**: Better visibility into GC behavior

### Memory Management
- **Optimized allocation**: More efficient memory usage
- **Better deallocation**: Faster memory cleanup
- **Enhanced monitoring**: Better memory tracking
- **Improved debugging**: Better memory analysis tools

### Concurrency
- **Enhanced virtual threads**: Better performance
- **Improved synchronization**: More efficient locking
- **Better thread pools**: More efficient task execution
- **Enhanced monitoring**: Better thread visibility

## Performance Benchmarks

### Value Classes vs Traditional Classes
```java
int numberOfObjects = 100000;

// Test with value classes
long startTime = System.nanoTime();
List<Point> valuePoints = new ArrayList<>();
for (int i = 0; i < numberOfObjects; i++) {
    valuePoints.add(new Point(i, i * 2));
}

// Test equality operations
int equalityCount = 0;
for (int i = 0; i < valuePoints.size() - 1; i++) {
    if (valuePoints.get(i).equals(valuePoints.get(i + 1))) {
        equalityCount++;
    }
}

// Test hash code operations
int hashSum = 0;
for (Point point : valuePoints) {
    hashSum += point.hashCode();
}

long valueClassTime = System.nanoTime() - startTime;

// Test with traditional classes
startTime = System.nanoTime();
List<TraditionalPoint> traditionalPoints = new ArrayList<>();
for (int i = 0; i < numberOfObjects; i++) {
    traditionalPoints.add(new TraditionalPoint(i, i * 2));
}

// Test equality operations
int traditionalEqualityCount = 0;
for (int i = 0; i < traditionalPoints.size() - 1; i++) {
    if (traditionalPoints.get(i).equals(traditionalPoints.get(i + 1))) {
        traditionalEqualityCount++;
    }
}

// Test hash code operations
int traditionalHashSum = 0;
for (TraditionalPoint point : traditionalPoints) {
    traditionalHashSum += point.hashCode();
}

long traditionalTime = System.nanoTime() - startTime;
```

### Enhanced String Templates vs Traditional Approaches
```java
int iterations = 100000;
String name = "TestUser";
int age = 25;
String city = "TestCity";

// Traditional concatenation
long startTime = System.nanoTime();
for (int i = 0; i < iterations; i++) {
    String traditional = "User: " + name + ", Age: " + age + ", City: " + city;
}
long traditionalTime = System.nanoTime() - startTime;

// Enhanced string template
startTime = System.nanoTime();
for (int i = 0; i < iterations; i++) {
    String template = STR."User: \{name}, Age: \{age}, City: \{city}";
}
long templateTime = System.nanoTime() - startTime;

// Enhanced FMT template
startTime = System.nanoTime();
for (int i = 0; i < iterations; i++) {
    String fmtTemplate = FMT."User: %s, Age: %d, City: %s".formatted(name, age, city);
}
long fmtTime = System.nanoTime() - startTime;
```

## Best Practices

### Value Classes
1. Use for immutable data carriers
2. Include validation in constructors
3. Add methods for business logic
4. Don't use for mutable data
5. Consider performance implications

### Enhanced String Templates
1. Use appropriate template processor
2. Use STR for simple templates
3. Use FMT for formatted output
4. Use RAW for special characters
5. Use custom processors for domain-specific templates

### Performance Optimization
1. Use value classes for data carriers
2. Leverage enhanced string templates
3. Monitor performance metrics
4. Use appropriate data structures
5. Consider memory usage

### General Guidelines
1. Write readable and maintainable code
2. Use appropriate Java 25 features
3. Consider performance implications
4. Follow coding standards
5. Write comprehensive tests

## Common Pitfalls

### Value Classes
- Don't use for mutable data
- Don't add too much business logic
- Don't use for complex inheritance
- Be careful with performance implications

### Enhanced String Templates
- Don't use for simple single-line strings
- Be careful with performance implications
- Don't overuse for performance-critical code
- Consider readability over performance

### Performance
- Don't optimize prematurely
- Monitor actual performance
- Consider memory usage
- Use appropriate tools for measurement

## Migration Guide

### From Java 21 to Java 25
1. Implement value classes for data carriers
2. Use enhanced string templates
3. Take advantage of performance improvements
4. Explore new language features
5. Update build and deployment processes

### Code Examples
```java
// Before Java 25
public class Point {
    private final double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // ... equals, hashCode, toString methods
}

// Java 25
public value class Point {
    public final double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
```

## Conclusion

Java 25 introduces significant improvements to the language and runtime, with value classes, enhanced string templates, and performance optimizations. These features provide powerful tools for modern Java development, enabling more efficient and maintainable code. Understanding these features and their proper usage is essential for writing high-quality Java applications.
