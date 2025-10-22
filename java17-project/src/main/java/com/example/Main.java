package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class for Java 17 project demonstrating Java 17 features
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Java 17 Project - Hello World!");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // Demonstrate Java 17 features
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Using streams and lambda expressions
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Using method references
        names.forEach(System.out::println);
        
        // Java 17 specific features
        demonstrateTextBlocks();
        demonstratePatternMatching();
    }
    
    private static void demonstrateTextBlocks() {
        // Text blocks (Java 15+ feature, stable in Java 17)
        String json = """
                {
                    "name": "Java 17",
                    "version": "17.0.0",
                    "features": ["Text Blocks", "Pattern Matching", "Sealed Classes"]
                }
                """;
        System.out.println("JSON using Text Blocks:");
        System.out.println(json);
    }
    
    private static void demonstratePatternMatching() {
        // Pattern matching for instanceof (Java 16+ feature, stable in Java 17)
        Object obj = "Hello Java 17";
        if (obj instanceof String str) {
            System.out.println("String length: " + str.length());
        }
    }
}
