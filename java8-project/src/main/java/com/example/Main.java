package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class for Java 8 project demonstrating Java 8 features
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Java 8 Project - Hello World!");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        
        // Demonstrate Java 8 features
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        // Using streams and lambda expressions
        List<String> filteredNames = names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        
        System.out.println("Filtered names: " + filteredNames);
        
        // Using method references
        names.forEach(System.out::println);
    }
}
