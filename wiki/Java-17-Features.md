# Java 17 Features Documentation

This document provides comprehensive documentation for Java 17 features demonstrated in the project.

## Table of Contents

1. [Text Blocks](#text-blocks)
2. [Pattern Matching](#pattern-matching)
3. [Sealed Classes](#sealed-classes)
4. [Records](#records)
5. [Switch Expressions](#switch-expressions)
6. [Performance Benchmarks](#performance-benchmarks)
7. [Best Practices](#best-practices)

## Text Blocks

### Overview
Text blocks provide a clean way to write multi-line string literals in Java. They eliminate the need for escape sequences and concatenation for multi-line strings.

### Basic Syntax
```java
// Traditional approach
String json = "{\n" +
              "  \"name\": \"Java 17\",\n" +
              "  \"version\": \"17.0.0\"\n" +
              "}";

// Text block approach
String json = """
        {
            "name": "Java 17",
            "version": "17.0.0"
        }
        """;
```

### Features
- **Automatic indentation removal**: Common leading whitespace is removed
- **Preserved formatting**: Maintains relative indentation
- **No escape sequences**: Newlines and quotes don't need escaping
- **Expression interpolation**: Can include variables and expressions

### Real-world Examples

#### JSON Generation
```java
String userJson = """
        {
            "id": 1,
            "name": "Alice",
            "email": "alice@example.com",
            "active": true,
            "roles": ["user", "admin"]
        }
        """;
```

#### SQL Queries
```java
String query = """
        SELECT u.id, u.name, u.email, p.title
        FROM users u
        LEFT JOIN profiles p ON u.id = p.user_id
        WHERE u.active = true
        ORDER BY u.name
        """;
```

#### HTML Templates
```java
String html = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Java 17 Demo</title>
        </head>
        <body>
            <h1>Welcome to Java 17</h1>
        </body>
        </html>
        """;
```

### Performance Benefits
- **Reduced string concatenation**: No need for multiple string concatenations
- **Better memory usage**: Single string literal instead of multiple concatenated strings
- **Improved readability**: Code is more maintainable and easier to read

## Pattern Matching

### Overview
Pattern matching simplifies type checking and casting by combining instanceof checks with variable declaration.

### Basic Syntax
```java
// Traditional approach
if (obj instanceof String) {
    String str = (String) obj;
    System.out.println("Length: " + str.length());
}

// Pattern matching approach
if (obj instanceof String str) {
    System.out.println("Length: " + str.length());
}
```

### Advanced Features
```java
// Multiple conditions
if (obj instanceof String str && str.length() > 5) {
    System.out.println("Long string: " + str);
}

// Switch expressions with pattern matching
String result = switch (obj) {
    case String str when str.length() > 10 -> "Long string: " + str;
    case String str -> "Short string: " + str;
    case Integer num when num > 100 -> "Large number: " + num;
    case Integer num -> "Small number: " + num;
    default -> "Unknown type";
};
```

### Real-world Examples
```java
// API response handling
Object response = new ApiResponse("success", userData);
String message = switch (response) {
    case ApiResponse(String status, UserData user) when "success".equals(status) -> 
        "User " + user.name() + " retrieved successfully";
    case ApiResponse(String status, Object data) when "error".equals(status) -> 
        "Error occurred: " + data;
    default -> "Unknown response type";
};

// Data validation
String validation = switch (input) {
    case String str when str.matches("\\d+") -> "Valid number string: " + str;
    case String str when str.contains("@") -> "Valid email: " + str;
    case String str -> "Invalid string: " + str;
    case Integer num when num > 0 -> "Valid positive integer: " + num;
    default -> "Unknown input type";
};
```

## Sealed Classes

### Overview
Sealed classes restrict which classes can extend or implement them, providing better control over inheritance hierarchies.

### Basic Syntax
```java
// Sealed interface
sealed interface Shape permits Circle, Rectangle, Triangle {}

// Implementing classes
record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record Triangle(double a, double b, double c) implements Shape {}
```

### Features
- **Compile-time exhaustiveness**: Ensures all possible cases are handled
- **Better type safety**: Prevents unexpected subclasses
- **Pattern matching integration**: Works seamlessly with pattern matching
- **Documentation**: Makes inheritance hierarchies explicit

### Real-world Examples
```java
// HTTP response hierarchy
sealed interface HttpResponse permits SuccessResponse, ErrorResponse, RedirectResponse {}
record SuccessResponse(int code, String message, Object data) implements HttpResponse {}
record ErrorResponse(int code, String message, String error) implements HttpResponse {}
record RedirectResponse(int code, String message, String location) implements HttpResponse {}

// Database operation results
sealed interface DbResult permits InsertResult, UpdateResult, DeleteResult, ErrorResult {}
record InsertResult(int id, String message) implements DbResult {}
record UpdateResult(int affectedRows, String message) implements DbResult {}
record DeleteResult(int deletedRows, String message) implements DbResult {}
record ErrorResult(String error) implements DbResult {}
```

### Pattern Matching with Sealed Classes
```java
// Exhaustive pattern matching
String result = switch (dbResult) {
    case InsertResult(int id, String message) -> 
        "Inserted record with ID: " + id + " - " + message;
    case UpdateResult(int affectedRows, String message) -> 
        "Updated " + affectedRows + " rows - " + message;
    case DeleteResult(int deletedRows, String message) -> 
        "Deleted " + deletedRows + " rows - " + message;
    case ErrorResult(String error) -> 
        "Database error: " + error;
};
```

## Records

### Overview
Records provide a concise way to declare classes that are transparent holders for immutable data.

### Basic Syntax
```java
// Traditional class
public class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String name() { return name; }
    public int age() { return age; }
    
    @Override
    public boolean equals(Object obj) { /* implementation */ }
    @Override
    public int hashCode() { /* implementation */ }
    @Override
    public String toString() { /* implementation */ }
}

// Record equivalent
public record Person(String name, int age) {}
```

### Features
- **Automatic methods**: equals, hashCode, toString
- **Immutable**: All fields are final
- **Compact syntax**: Reduces boilerplate code
- **Validation**: Can include validation in constructor

### Advanced Examples
```java
// Record with validation
public record Money(double amount, String currency) {
    public Money {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
    }
    
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(this.amount + other.amount, this.currency);
    }
}

// Record with methods
public record Point(double x, double y) {
    public double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

## Switch Expressions

### Overview
Switch expressions provide a more concise and expressive way to write switch statements, with better type safety and exhaustiveness checking.

### Basic Syntax
```java
// Traditional switch statement
String dayType;
switch (day) {
    case "MONDAY":
    case "TUESDAY":
    case "WEDNESDAY":
    case "THURSDAY":
    case "FRIDAY":
        dayType = "Weekday";
        break;
    case "SATURDAY":
    case "SUNDAY":
        dayType = "Weekend";
        break;
    default:
        dayType = "Unknown";
        break;
}

// Switch expression
String dayType = switch (day) {
    case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
    case "SATURDAY", "SUNDAY" -> "Weekend";
    default -> "Unknown";
};
```

### Advanced Features
```java
// Switch with multiple statements
String result = switch (day) {
    case "MONDAY" -> {
        System.out.println("Start of work week");
        yield "Monday";
    }
    case "FRIDAY" -> {
        System.out.println("End of work week");
        yield "Friday";
    }
    default -> "Other day";
};

// Switch with pattern matching
String description = switch (value) {
    case String str when str.length() > 5 -> "Long string: " + str;
    case String str -> "Short string: " + str;
    case Integer num when num > 100 -> "Large number: " + num;
    case Integer num -> "Small number: " + num;
    case Double num -> "Decimal: " + num;
    case Boolean bool -> "Boolean: " + bool;
    default -> "Unknown type";
};
```

## Performance Benchmarks

### Text Blocks vs String Concatenation
```java
// Performance comparison
int iterations = 100000;

// Traditional concatenation
long startTime = System.nanoTime();
for (int i = 0; i < iterations; i++) {
    String traditional = "{\n" +
                        "  \"id\": " + i + ",\n" +
                        "  \"name\": \"User" + i + "\"\n" +
                        "}";
}
long traditionalTime = System.nanoTime() - startTime;

// Text block approach
startTime = System.nanoTime();
for (int i = 0; i < iterations; i++) {
    String textBlock = """
            {
              "id": %d,
              "name": "User%d"
            }
            """.formatted(i, i);
}
long textBlockTime = System.nanoTime() - startTime;
```

### Pattern Matching vs Traditional instanceof
```java
// Performance comparison
List<Object> testData = new ArrayList<>();
for (int i = 0; i < 100000; i++) {
    if (i % 4 == 0) testData.add("String" + i);
    else if (i % 4 == 1) testData.add(i);
    else if (i % 4 == 2) testData.add(i * 1.5);
    else testData.add(i % 2 == 0);
}

// Traditional approach
long startTime = System.nanoTime();
int traditionalCount = 0;
for (Object obj : testData) {
    if (obj instanceof String) {
        String str = (String) obj;
        if (str.length() > 5) {
            traditionalCount++;
        }
    } else if (obj instanceof Integer) {
        Integer num = (Integer) obj;
        if (num > 100) {
            traditionalCount++;
        }
    }
}
long traditionalTime = System.nanoTime() - startTime;

// Pattern matching approach
startTime = System.nanoTime();
int patternCount = 0;
for (Object obj : testData) {
    if (obj instanceof String str && str.length() > 5) {
        patternCount++;
    } else if (obj instanceof Integer num && num > 100) {
        patternCount++;
    }
}
long patternTime = System.nanoTime() - startTime;
```

## Best Practices

### Text Blocks
1. Use for multi-line strings
2. Maintain consistent indentation
3. Use for configuration files, templates, and data formats
4. Consider readability over performance for small strings

### Pattern Matching
1. Use for type checking and casting
2. Combine with conditions for better logic
3. Use switch expressions for multiple cases
4. Handle null cases appropriately

### Sealed Classes
1. Use for restricted inheritance hierarchies
2. Document the purpose of sealing
3. Use with pattern matching for exhaustiveness
4. Consider alternative designs before sealing

### Records
1. Use for immutable data carriers
2. Include validation in constructors
3. Add methods for business logic
4. Don't use for mutable data

### Switch Expressions
1. Use for multiple case handling
2. Prefer switch expressions over statements
3. Use pattern matching for complex conditions
4. Ensure exhaustiveness for sealed classes

## Common Pitfalls

### Text Blocks
- Don't use for simple single-line strings
- Be careful with indentation
- Don't overuse for performance-critical code

### Pattern Matching
- Don't use for simple type checks
- Be careful with variable scope
- Don't ignore null cases

### Sealed Classes
- Don't seal unnecessarily
- Consider future extensibility
- Don't use for public APIs without careful design

### Records
- Don't use for mutable data
- Don't add too much business logic
- Don't use for complex inheritance

## Migration Guide

### From Java 8 to Java 17
1. Replace string concatenation with text blocks
2. Use pattern matching for type checking
3. Implement sealed classes for restricted hierarchies
4. Use records for data classes
5. Replace switch statements with expressions

### Code Examples
```java
// Before Java 17
String json = "{\n" +
              "  \"name\": \"" + name + "\",\n" +
              "  \"age\": " + age + "\n" +
              "}";

// Java 17
String json = """
        {
            "name": "%s",
            "age": %d
        }
        """.formatted(name, age);
```

## Conclusion

Java 17 introduced significant improvements to the language, making code more readable, maintainable, and expressive. Text blocks, pattern matching, sealed classes, and records provide powerful tools for modern Java development. Understanding these features and their proper usage is essential for writing high-quality Java applications.
