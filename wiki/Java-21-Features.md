# Java 21 Features Documentation

This document provides comprehensive documentation for Java 21 features demonstrated in the project.

## Table of Contents

1. [Virtual Threads](#virtual-threads)
2. [Sequenced Collections](#sequenced-collections)
3. [String Templates](#string-templates)
4. [Pattern Matching for Switch](#pattern-matching-for-switch)
5. [Foreign Function & Memory API](#foreign-function--memory-api)
6. [Performance Benchmarks](#performance-benchmarks)
7. [Best Practices](#best-practices)

## Virtual Threads

### Overview
Virtual threads are lightweight threads that are managed by the Java runtime rather than the operating system. They enable high-concurrency applications with minimal resource overhead.

### Basic Usage
```java
// Create virtual threads
Thread virtualThread = Thread.ofVirtual().start(() -> {
    System.out.println("Virtual thread: " + Thread.currentThread());
    System.out.println("Is virtual: " + Thread.currentThread().isVirtual());
});

// Create virtual thread with name
Thread namedVirtualThread = Thread.ofVirtual()
        .name("my-virtual-thread")
        .start(() -> {
            System.out.println("Named virtual thread: " + Thread.currentThread().getName());
        });
```

### ExecutorService Integration
```java
// Virtual thread executor
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    List<Future<String>> futures = urls.stream()
            .map(url -> executor.submit(() -> simulateHttpRequest(url)))
            .toList();
    
    for (Future<String> future : futures) {
        String response = future.get();
        System.out.println("Response: " + response);
    }
}
```

### Performance Benefits
- **High concurrency**: Can handle millions of virtual threads
- **Low memory overhead**: Minimal stack space per thread
- **Better throughput**: Improved performance for I/O-bound operations
- **Simplified programming**: No need for complex thread pools

### Real-world Examples
```java
// HTTP request simulation
public String simulateHttpRequest(String url) {
    try {
        Thread.sleep(100 + (int)(Math.random() * 100)); // Simulate network delay
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    return "Response from " + url + " (processed by " + Thread.currentThread().getName() + ")";
}

// Database operations
public User fetchUserFromDatabase(int id) {
    try {
        Thread.sleep(50 + (int)(Math.random() * 50)); // Simulate database delay
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
    return new User(id, "User" + id, "user" + id + "@example.com");
}
```

## Sequenced Collections

### Overview
Sequenced collections provide new interfaces for ordered collections with methods to access first and last elements, and add/remove elements at the beginning and end.

### Basic Operations
```java
// SequencedCollection operations
SequencedCollection<String> sequencedList = new ArrayList<>();
sequencedList.add("First");
sequencedList.add("Second");
sequencedList.add("Third");

// Add to beginning and end
sequencedList.addFirst("Zero");
sequencedList.addLast("Fourth");

// Get first and last elements
System.out.println("First element: " + sequencedList.getFirst());
System.out.println("Last element: " + sequencedList.getLast());

// Remove first and last elements
String removedFirst = sequencedList.removeFirst();
String removedLast = sequencedList.removeLast();

// Reverse the collection
SequencedCollection<String> reversed = sequencedList.reversed();
```

### SequencedSet Operations
```java
// LinkedHashSet implements SequencedSet
SequencedSet<String> sequencedSet = new LinkedHashSet<>();
sequencedSet.add("Apple");
sequencedSet.add("Banana");
sequencedSet.add("Cherry");

// Add to beginning and end
sequencedSet.addFirst("Apricot");
sequencedSet.addLast("Elderberry");

// Get first and last elements
System.out.println("First element: " + sequencedSet.getFirst());
System.out.println("Last element: " + sequencedSet.getLast());
```

### SequencedMap Operations
```java
// LinkedHashMap implements SequencedMap
SequencedMap<String, Integer> sequencedMap = new LinkedHashMap<>();
sequencedMap.put("First", 1);
sequencedMap.put("Second", 2);
sequencedMap.put("Third", 3);

// Add to beginning and end
sequencedMap.putFirst("Zero", 0);
sequencedMap.putLast("Fourth", 4);

// Get first and last entries
Map.Entry<String, Integer> firstEntry = sequencedMap.firstEntry();
Map.Entry<String, Integer> lastEntry = sequencedMap.lastEntry();
```

### Real-world Examples
```java
// Task queue with priority
SequencedCollection<Task> taskQueue = new ArrayList<>();
taskQueue.add(new Task("Low", "Task 1"));
taskQueue.add(new Task("Medium", "Task 2"));
taskQueue.add(new Task("High", "Task 3"));

// Add urgent task to front
taskQueue.addFirst(new Task("Urgent", "Critical Task"));

// Process tasks in order
while (!taskQueue.isEmpty()) {
    Task task = taskQueue.removeFirst();
    System.out.println("Processing: " + task);
}

// Recent items tracking
SequencedSet<String> recentItems = new LinkedHashSet<>();
String[] items = {"Item1", "Item2", "Item3", "Item1", "Item4", "Item2", "Item5"};

for (String item : items) {
    // Remove if already exists to move to end
    recentItems.remove(item);
    recentItems.addLast(item);
    
    // Keep only last 3 items
    while (recentItems.size() > 3) {
        recentItems.removeFirst();
    }
    
    System.out.println("Recent items: " + recentItems);
}
```

## String Templates

### Overview
String templates provide a new way to create strings with embedded expressions, making string interpolation more readable and efficient.

### Basic Usage
```java
String name = "Alice";
int age = 25;
String city = "New York";

// Basic string template
String template = STR."Hello, my name is \{name} and I am \{age} years old.";

// Template with expressions
String expressionTemplate = STR."The result of 5 + 3 is \{5 + 3}";

// Template with method calls
String methodTemplate = STR."Current time: \{new Date()}";
```

### Template Processors
```java
// STR processor (default)
String strTemplate = STR."User: \{name}, Age: \{age}";

// FMT processor (formatted)
String fmtTemplate = FMT."User: %-10s, Age: %3d".formatted(name, age);

// RAW processor (no processing)
String rawTemplate = RAW."Path: C:\\Users\\\{name}\\Documents";
```

### Real-world Examples
```java
// JSON generation
String userJson = JSON."""
        {
            "id": 1,
            "name": "\{name}",
            "email": "\{email}",
            "active": true,
            "roles": ["user", "admin"]
        }
        """;

// HTML template
String htmlTemplate = HTML."""
        <div class="user-card">
            <h2>\{name}</h2>
            <p>Email: \{email}</p>
            <p>Age: \{age}</p>
        </div>
        """;

// SQL query
String sqlQuery = SQL."""
        SELECT u.id, u.name, u.email
        FROM users u
        WHERE u.active = true
        AND u.age >= \{minAge}
        ORDER BY u.name
        """;
```

## Pattern Matching for Switch

### Overview
Pattern matching for switch extends the switch expression to support pattern matching, making it more powerful and expressive.

### Basic Syntax
```java
// Pattern matching with switch
String result = switch (obj) {
    case String str when str.length() > 10 -> "Long string: " + str;
    case String str -> "Short string: " + str;
    case Integer num when num > 100 -> "Large number: " + num;
    case Integer num -> "Small number: " + num;
    case Double num -> "Decimal: " + num;
    case Boolean bool -> "Boolean: " + bool;
    default -> "Unknown type";
};
```

### Advanced Features
```java
// Switch with sealed classes
String shapeInfo = switch (shape) {
    case Circle(double radius) -> "Circle with radius " + radius;
    case Rectangle(double width, double height) -> 
        "Rectangle " + width + "x" + height;
    case Triangle(double a, double b, double c) -> 
        "Triangle with sides " + a + ", " + b + ", " + c;
};

// Switch with multiple patterns
String category = switch (obj) {
    case String str when str.length() > 10 -> "Long string";
    case String str when str.length() > 5 -> "Medium string";
    case String str -> "Short string";
    case Integer num when num > 100 -> "Large integer";
    case Integer num when num > 50 -> "Medium integer";
    case Integer num -> "Small integer";
    case Double num when num > 100.0 -> "Large double";
    case Double num -> "Small double";
    case Boolean bool -> "Boolean value";
    default -> "Unknown";
};
```

## Foreign Function & Memory API

### Overview
The Foreign Function & Memory API provides a way to call native code and access native memory from Java, enabling interoperability with C libraries.

### Basic Usage
```java
// Linker for calling native functions
Linker linker = Linker.nativeLinker();

// Symbol lookup
SymbolLookup stdlib = linker.defaultLookup();

// Function descriptor
FunctionDescriptor descriptor = FunctionDescriptor.of(
    ValueLayout.JAVA_INT,  // return type
    ValueLayout.JAVA_INT,  // parameter type
    ValueLayout.JAVA_INT   // parameter type
);

// Method handle
MethodHandle add = linker.downcallHandle(
    stdlib.find("add").orElseThrow(),
    descriptor
);

// Call native function
int result = (int) add.invoke(5, 3);
System.out.println("Result: " + result);
```

### Memory Management
```java
// Memory segment
try (MemorySegment segment = MemorySegment.allocateNative(100)) {
    // Access memory
    segment.set(ValueLayout.JAVA_INT, 0, 42);
    int value = segment.get(ValueLayout.JAVA_INT, 0);
    System.out.println("Value: " + value);
}
```

## Performance Benchmarks

### Virtual Threads vs Platform Threads
```java
int numberOfThreads = 1000;

// Platform threads
long startTime = System.nanoTime();
List<Thread> platformThreads = new ArrayList<>();
for (int i = 0; i < numberOfThreads; i++) {
    Thread thread = Thread.ofPlatform().start(() -> {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });
    platformThreads.add(thread);
}
// Wait for completion
for (Thread thread : platformThreads) {
    try {
        thread.join();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
long platformTime = System.nanoTime() - startTime;

// Virtual threads
startTime = System.nanoTime();
List<Thread> virtualThreads = new ArrayList<>();
for (int i = 0; i < numberOfThreads; i++) {
    Thread thread = Thread.ofVirtual().start(() -> {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });
    virtualThreads.add(thread);
}
// Wait for completion
for (Thread thread : virtualThreads) {
    try {
        thread.join();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
long virtualTime = System.nanoTime() - startTime;
```

### String Templates vs Traditional Approaches
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

// String template
startTime = System.nanoTime();
for (int i = 0; i < iterations; i++) {
    String template = STR."User: \{name}, Age: \{age}, City: \{city}";
}
long templateTime = System.nanoTime() - startTime;
```

## Best Practices

### Virtual Threads
1. Use for I/O-bound operations
2. Don't use for CPU-intensive tasks
3. Use Executors.newVirtualThreadPerTaskExecutor()
4. Monitor performance and resource usage
5. Use structured concurrency for error handling

### Sequenced Collections
1. Use for order-sensitive operations
2. Use LinkedHashSet for ordered unique elements
3. Use LinkedHashMap for ordered key-value pairs
4. Use reversed() for reverse iteration
5. Use first/last methods for queue-like operations

### String Templates
1. Use appropriate template processor
2. Use STR for simple templates
3. Use FMT for formatted output
4. Use RAW for special characters
5. Use custom processors for domain-specific templates

### Pattern Matching
1. Use for type checking and casting
2. Combine with conditions for better logic
3. Use switch expressions for multiple cases
4. Handle null cases appropriately
5. Use with sealed classes for exhaustiveness

## Common Pitfalls

### Virtual Threads
- Don't use for CPU-intensive tasks
- Be careful with thread-local variables
- Don't block virtual threads unnecessarily
- Monitor memory usage

### Sequenced Collections
- Don't use for unordered operations
- Be careful with performance implications
- Don't overuse for simple operations

### String Templates
- Don't use for simple single-line strings
- Be careful with performance implications
- Don't overuse for performance-critical code

## Migration Guide

### From Java 17 to Java 21
1. Migrate to virtual threads for better concurrency
2. Use sequenced collections for ordered data
3. Implement string templates for string interpolation
4. Use pattern matching in switch expressions
5. Leverage Foreign Function & Memory API for native code

### Code Examples
```java
// Before Java 21
ExecutorService executor = Executors.newFixedThreadPool(100);
for (String url : urls) {
    executor.submit(() -> processUrl(url));
}

// Java 21
try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (String url : urls) {
        executor.submit(() -> processUrl(url));
    }
}
```

## Conclusion

Java 21 introduced revolutionary features that significantly improve concurrency, string handling, and type safety. Virtual threads, sequenced collections, and string templates provide powerful tools for modern Java development. Understanding these features and their proper usage is essential for writing high-performance, maintainable Java applications.
