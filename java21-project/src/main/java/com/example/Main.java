package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class for Java 21 project demonstrating Java 21 features
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Java 21 Project - Hello World!");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // Demonstrate Java 21 features
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Using streams and lambda expressions
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Using method references
        names.forEach(System.out::println);
        
        // Java 21 specific features
        demonstrateTextBlocks();
        demonstratePatternMatching();
        demonstrateVirtualThreads();
        demonstrateSequencedCollections();
    }
    
    private static void demonstrateTextBlocks() {
        // Text blocks (stable since Java 15)
        String json = """
                {
                    "name": "Java 21",
                    "version": "21.0.0",
                    "features": ["Virtual Threads", "Sequenced Collections", "String Templates"]
                }
                """;
        System.out.println("JSON using Text Blocks:");
        System.out.println(json);
    }
    
    private static void demonstratePatternMatching() {
        // Pattern matching for instanceof (stable since Java 16)
        Object obj = "Hello Java 21";
        if (obj instanceof String str) {
            System.out.println("String length: " + str.length());
        }
    }
    
    private static void demonstrateVirtualThreads() {
        // Virtual Threads (Java 21 feature)
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
        // Sequenced Collections (Java 21 feature)
        List<String> list = Arrays.asList("First", "Second", "Third");
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        
        // Add elements to the beginning and end
        list.addFirst("Zero");
        list.addLast("Fourth");
        System.out.println("Updated list: " + list);
    }
}
