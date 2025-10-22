package com.example.features;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Demonstrates Java 8 Lambda Expressions with real-world examples
 * Includes performance benchmarks and practical use cases
 */
public class LambdaExpressionsDemo {
    
    public static void main(String[] args) {
        LambdaExpressionsDemo demo = new LambdaExpressionsDemo();
        
        System.out.println("=== Java 8 Lambda Expressions Demo ===\n");
        
        demo.basicLambdaExamples();
        demo.functionalInterfacesDemo();
        demo.collectionOperationsDemo();
        demo.performanceBenchmark();
        demo.realWorldExamples();
    }
    
    /**
     * Basic lambda expression examples
     */
    public void basicLambdaExamples() {
        System.out.println("1. Basic Lambda Expressions:");
        
        // Traditional anonymous class vs Lambda
        Runnable traditionalRunnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional anonymous class");
            }
        };
        
        Runnable lambdaRunnable = () -> System.out.println("Lambda expression");
        
        traditionalRunnable.run();
        lambdaRunnable.run();
        
        // Lambda with parameters
        Comparator<String> stringComparator = (s1, s2) -> s1.compareTo(s2);
        System.out.println("String comparison: " + stringComparator.compare("apple", "banana"));
        
        // Lambda with multiple statements
        Function<String, String> processor = (input) -> {
            String result = input.toUpperCase();
            return "Processed: " + result;
        };
        System.out.println(processor.apply("hello world"));
        
        System.out.println();
    }
    
    /**
     * Demonstrates built-in functional interfaces
     */
    public void functionalInterfacesDemo() {
        System.out.println("2. Built-in Functional Interfaces:");
        
        // Predicate - boolean test
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));
        
        // Function - transform input to output
        Function<String, Integer> stringLength = String::length;
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        
        // Consumer - consumes input, no return
        Consumer<String> printer = s -> System.out.println("Consumed: " + s);
        printer.accept("Lambda Consumer");
        
        // Supplier - produces output, no input
        Supplier<Double> randomSupplier = Math::random;
        System.out.println("Random number: " + randomSupplier.get());
        
        // BiFunction - two inputs, one output
        BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;
        System.out.println("5 + 3 = " + adder.apply(5, 3));
        
        System.out.println();
    }
    
    /**
     * Demonstrates lambda usage with collections
     */
    public void collectionOperationsDemo() {
        System.out.println("3. Collection Operations with Lambdas:");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
        
        // Filter names longer than 3 characters
        List<String> longNames = names.stream()
                .filter(name -> name.length() > 3)
                .collect(Collectors.toList());
        System.out.println("Long names: " + longNames);
        
        // Transform to uppercase
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase names: " + upperNames);
        
        // Find first name starting with 'A'
        Optional<String> firstA = names.stream()
                .filter(name -> name.startsWith("A"))
                .findFirst();
        System.out.println("First name starting with 'A': " + firstA.orElse("Not found"));
        
        // Count names with even length
        long evenLengthCount = names.stream()
                .filter(name -> name.length() % 2 == 0)
                .count();
        System.out.println("Names with even length: " + evenLengthCount);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing traditional vs lambda approaches
     */
    public void performanceBenchmark() {
        System.out.println("4. Performance Benchmark:");
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            numbers.add(i);
        }
        
        // Traditional approach
        long startTime = System.nanoTime();
        List<Integer> traditionalResult = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                traditionalResult.add(number * 2);
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
        
        System.out.println("Traditional approach time: " + (traditionalTime / 1_000_000) + " ms");
        System.out.println("Lambda approach time: " + (lambdaTime / 1_000_000) + " ms");
        System.out.println("Results equal: " + traditionalResult.equals(lambdaResult));
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("5. Real-world Examples:");
        
        // Event handling simulation
        List<String> events = Arrays.asList("click", "hover", "focus", "blur");
        events.forEach(event -> System.out.println("Handling event: " + event));
        
        // Data validation
        List<String> emails = Arrays.asList("user@example.com", "invalid-email", "admin@test.org");
        Predicate<String> emailValidator = email -> email.contains("@") && email.contains(".");
        List<String> validEmails = emails.stream()
                .filter(emailValidator)
                .collect(Collectors.toList());
        System.out.println("Valid emails: " + validEmails);
        
        // Configuration processing
        Map<String, String> config = new HashMap<>();
        config.put("database.url", "jdbc:mysql://localhost:3306/mydb");
        config.put("database.username", "admin");
        config.put("cache.enabled", "true");
        
        config.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("database"))
                .forEach(entry -> System.out.println("DB Config: " + entry.getKey() + " = " + entry.getValue()));
        
        System.out.println();
    }
}
