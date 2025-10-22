# Java 8 Features Documentation

This document provides comprehensive documentation for Java 8 features demonstrated in the project.

## Table of Contents

1. [Lambda Expressions](#lambda-expressions)
2. [Streams API](#streams-api)
3. [Optional Class](#optional-class)
4. [Method References](#method-references)
5. [Default Methods](#default-methods)
6. [Performance Benchmarks](#performance-benchmarks)
7. [Best Practices](#best-practices)

## Lambda Expressions

### Overview
Lambda expressions provide a concise way to represent anonymous functions. They enable functional programming in Java and make code more readable and maintainable.

### Basic Syntax
```java
// Traditional anonymous class
Runnable traditional = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello World");
    }
};

// Lambda expression
Runnable lambda = () -> System.out.println("Hello World");
```

### Functional Interfaces
Java 8 introduced several built-in functional interfaces:

#### Predicate<T>
```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(10)); // true
```

#### Function<T, R>
```java
Function<String, Integer> stringLength = String::length;
System.out.println(stringLength.apply("Hello")); // 5
```

#### Consumer<T>
```java
Consumer<String> printer = s -> System.out.println("Value: " + s);
printer.accept("Hello"); // prints: Value: Hello
```

#### Supplier<T>
```java
Supplier<Double> randomSupplier = Math::random;
System.out.println(randomSupplier.get()); // random number
```

### Real-world Examples
```java
// Event handling
List<String> events = Arrays.asList("click", "hover", "focus");
events.forEach(event -> System.out.println("Handling: " + event));

// Data validation
Predicate<String> emailValidator = email -> 
    email.contains("@") && email.contains(".");
List<String> validEmails = emails.stream()
    .filter(emailValidator)
    .collect(Collectors.toList());
```

### Performance Considerations
- Lambda expressions have minimal overhead
- JVM optimizations improve performance over time
- Use for I/O-bound operations and data processing

## Streams API

### Overview
The Streams API provides a functional approach to processing collections of data. It enables declarative programming and supports parallel processing.

### Basic Operations

#### Creating Streams
```java
// From collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();

// From array
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// From values
Stream<String> stream3 = Stream.of("a", "b", "c");
```

#### Intermediate Operations
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Filter
List<String> longNames = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Map
List<String> upperNames = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Distinct
List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4);
List<Integer> unique = numbers.stream()
    .distinct()
    .collect(Collectors.toList());
```

#### Terminal Operations
```java
// Collect
List<String> result = names.stream()
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Reduce
int sum = numbers.stream()
    .reduce(0, Integer::sum);

// Count
long count = names.stream()
    .filter(name -> name.length() > 3)
    .count();
```

### Parallel Streams
```java
// Sequential processing
long sequentialSum = numbers.stream()
    .mapToLong(Integer::longValue)
    .sum();

// Parallel processing
long parallelSum = numbers.parallelStream()
    .mapToLong(Integer::longValue)
    .sum();
```

### Performance Benchmarks
- Parallel streams are beneficial for large datasets
- Use when CPU-intensive operations are involved
- Consider thread overhead for small datasets

## Optional Class

### Overview
The Optional class helps avoid NullPointerException by providing a container for values that might be null.

### Basic Usage
```java
// Creating Optional instances
Optional<String> empty = Optional.empty();
Optional<String> present = Optional.of("Hello");
Optional<String> nullable = Optional.ofNullable(null);

// Checking presence
if (present.isPresent()) {
    System.out.println("Value: " + present.get());
}

// Safe value retrieval
String value = present.orElse("Default");
String lazyValue = present.orElseGet(() -> "Lazy Default");
```

### Advanced Operations
```java
// Map operation
Optional<Integer> length = optional.map(String::length);

// Filter operation
Optional<String> filtered = optional.filter(s -> s.length() > 5);

// FlatMap operation
Optional<String> flatMapped = optional.flatMap(s -> Optional.of(s.toUpperCase()));

// If present
optional.ifPresent(value -> System.out.println("Value: " + value));
```

### Best Practices
- Use Optional for return types, not parameters
- Don't use Optional for fields
- Use orElseGet for expensive default values
- Use orElseThrow for required values

## Method References

### Overview
Method references provide a shorthand for lambda expressions when calling existing methods.

### Types of Method References

#### Static Method References
```java
// Lambda
Function<String, Integer> parser = s -> Integer.parseInt(s);

// Method reference
Function<String, Integer> parser2 = Integer::parseInt;
```

#### Instance Method References
```java
// Lambda
Function<String, String> upper = s -> s.toUpperCase();

// Method reference
Function<String, String> upper2 = String::toUpperCase;
```

#### Constructor References
```java
// Lambda
Supplier<List<String>> supplier = () -> new ArrayList<>();

// Constructor reference
Supplier<List<String>> supplier2 = ArrayList::new;
```

## Default Methods

### Overview
Default methods allow adding new methods to interfaces without breaking existing implementations.

### Example
```java
interface Calculator {
    int add(int a, int b);
    
    // Default method
    default int multiply(int a, int b) {
        return a * b;
    }
    
    // Static method
    static int subtract(int a, int b) {
        return a - b;
    }
}
```

## Performance Benchmarks

### Lambda vs Traditional Approaches
```java
// Traditional approach
long startTime = System.nanoTime();
for (Integer number : numbers) {
    if (number % 2 == 0) {
        result.add(number * 2);
    }
}
long traditionalTime = System.nanoTime() - startTime;

// Lambda approach
startTime = System.nanoTime();
List<Integer> lambdaResult = numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 2)
    .collect(Collectors.toList());
long lambdaTime = System.nanoTime() - startTime;
```

### Results
- Lambda expressions are generally faster for small datasets
- Traditional loops may be faster for very large datasets
- Parallel streams provide significant speedup for CPU-intensive operations

## Best Practices

### Lambda Expressions
1. Keep lambda expressions short and readable
2. Use method references when possible
3. Avoid complex logic in lambda expressions
4. Use meaningful variable names

### Streams API
1. Use streams for data processing pipelines
2. Prefer parallel streams for large datasets
3. Use appropriate terminal operations
4. Consider performance implications

### Optional Class
1. Use Optional for return types
2. Don't use Optional for parameters
3. Use orElseGet for expensive operations
4. Handle null cases properly

### General Guidelines
1. Write readable and maintainable code
2. Use appropriate Java 8 features
3. Consider performance implications
4. Follow coding standards
5. Write comprehensive tests

## Common Pitfalls

### Lambda Expressions
- Avoid capturing mutable variables
- Don't use lambda expressions for complex logic
- Be careful with variable scope

### Streams API
- Don't use streams for simple operations
- Avoid side effects in stream operations
- Consider memory usage for large datasets

### Optional Class
- Don't use Optional for fields
- Avoid Optional.ofNullable for known non-null values
- Don't use Optional for collections

## Migration Guide

### From Java 7 to Java 8
1. Replace anonymous classes with lambda expressions
2. Use streams for collection processing
3. Implement Optional for null-safe programming
4. Add default methods to interfaces
5. Use method references where appropriate

### Code Examples
```java
// Before Java 8
Collections.sort(list, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});

// Java 8
list.sort(String::compareTo);
```

## Conclusion

Java 8 introduced revolutionary features that transformed Java programming. Lambda expressions, streams, and Optional provide powerful tools for writing more expressive and maintainable code. Understanding these features and their proper usage is essential for modern Java development.
