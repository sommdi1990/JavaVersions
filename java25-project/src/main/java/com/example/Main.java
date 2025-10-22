package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class for Java 25 project demonstrating Java 25 features
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Java 25 Project - Hello World!");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // Demonstrate Java 25 features
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Using streams and lambda expressions
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Using method references
        names.forEach(System.out::println);
        
        // Java 25 specific features
        demonstrateTextBlocks();
        demonstratePatternMatching();
        demonstrateVirtualThreads();
        demonstrateSequencedCollections();
        demonstrateStringTemplates();
        demonstrateValueClasses();
    }
    
    private static void demonstrateTextBlocks() {
        // Text blocks (stable since Java 15)
        String json = """
                {
                    "name": "Java 25",
                    "version": "25.0.0",
                    "features": ["String Templates", "Value Classes", "Virtual Threads", "Sequenced Collections"]
                }
                """;
        System.out.println("JSON using Text Blocks:");
        System.out.println(json);
    }
    
    private static void demonstratePatternMatching() {
        // Pattern matching for instanceof (stable since Java 16)
        Object obj = "Hello Java 25";
        if (obj instanceof String str) {
            System.out.println("String length: " + str.length());
        }
    }
    
    private static void demonstrateVirtualThreads() {
        // Virtual Threads (stable since Java 21)
        System.out.println("Demonstrating Virtual Threads...");
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Virtual Thread " + taskId + " running on: " + Thread.currentThread());
                });
            }
        }
    }
    
    private static void demonstrateSequencedCollections() {
        // Sequenced Collections (stable since Java 21)
        List<String> list = Arrays.asList("First", "Second", "Third");
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        
        // Add elements to the beginning and end
        list.addFirst("Zero");
        list.addLast("Fourth");
        System.out.println("Updated list: " + list);
    }
    
    private static void demonstrateStringTemplates() {
        // String Templates (Java 21+ feature)
        String name = "Java 25";
        int version = 25;
        String template = STR."Hello \{name} version \{version}!";
        System.out.println("String Template result: " + template);
    }
    
    private static void demonstrateValueClasses() {
        // Value Classes (Java 25+ feature - preview)
        System.out.println("Value Classes are a preview feature in Java 25");
        System.out.println("They provide immutable data carriers with value semantics");
    }
}
