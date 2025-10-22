package com.example.features;

import java.util.*;
import java.util.function.Predicate;

/**
 * Demonstrates Java 17 Pattern Matching with comprehensive examples
 * Shows instanceof pattern matching and switch expressions
 */
public class PatternMatchingDemo {
    
    public static void main(String[] args) {
        PatternMatchingDemo demo = new PatternMatchingDemo();
        
        System.out.println("=== Java 17 Pattern Matching Demo ===\n");
        
        demo.instanceofPatternMatching();
        demo.switchExpressions();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Demonstrates instanceof pattern matching
     */
    public void instanceofPatternMatching() {
        System.out.println("1. instanceof Pattern Matching:");
        
        // Traditional instanceof with casting
        Object obj1 = "Hello World";
        if (obj1 instanceof String) {
            String str = (String) obj1;
            System.out.println("Traditional: String length = " + str.length());
        }
        
        // Pattern matching with instanceof
        if (obj1 instanceof String str) {
            System.out.println("Pattern matching: String length = " + str.length());
        }
        
        // Multiple conditions
        Object obj2 = "Java 17";
        if (obj2 instanceof String str && str.length() > 5) {
            System.out.println("Long string: " + str);
        }
        
        // With different types
        Object obj3 = 42;
        if (obj3 instanceof Integer num) {
            System.out.println("Integer value: " + num);
        } else if (obj3 instanceof String str) {
            System.out.println("String value: " + str);
        } else {
            System.out.println("Unknown type: " + obj3.getClass().getSimpleName());
        }
        
        // Complex example with collections
        List<Object> mixedList = Arrays.asList("Hello", 42, 3.14, true, "World");
        for (Object item : mixedList) {
            if (item instanceof String str) {
                System.out.println("String: " + str.toUpperCase());
            } else if (item instanceof Integer num) {
                System.out.println("Integer: " + (num * 2));
            } else if (item instanceof Double num) {
                System.out.println("Double: " + (num + 1.0));
            } else if (item instanceof Boolean bool) {
                System.out.println("Boolean: " + !bool);
            }
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates switch expressions with pattern matching
     */
    public void switchExpressions() {
        System.out.println("2. Switch Expressions:");
        
        // Basic switch expression
        String day = "MONDAY";
        String dayType = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        System.out.println("Day type: " + dayType);
        
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
        System.out.println("Result: " + result);
        
        // Switch with pattern matching
        Object value = "Hello";
        String description = switch (value) {
            case String str when str.length() > 5 -> "Long string: " + str;
            case String str -> "Short string: " + str;
            case Integer num when num > 100 -> "Large number: " + num;
            case Integer num -> "Small number: " + num;
            case Double num -> "Decimal: " + num;
            case Boolean bool -> "Boolean: " + bool;
            default -> "Unknown type";
        };
        System.out.println("Description: " + description);
        
        // Complex switch with multiple conditions
        Object obj = 150;
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
        System.out.println("Category: " + category);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing traditional vs pattern matching
     */
    public void performanceBenchmark() {
        System.out.println("3. Performance Benchmark:");
        
        List<Object> testData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            if (i % 4 == 0) testData.add("String" + i);
            else if (i % 4 == 1) testData.add(i);
            else if (i % 4 == 2) testData.add(i * 1.5);
            else testData.add(i % 2 == 0);
        }
        
        // Traditional instanceof with casting
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
        
        System.out.println("Traditional approach: " + traditionalCount + " (Time: " + (traditionalTime / 1_000_000) + " ms)");
        System.out.println("Pattern matching: " + patternCount + " (Time: " + (patternTime / 1_000_000) + " ms)");
        System.out.println("Performance ratio: " + ((double) patternTime / traditionalTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("4. Real-world Examples:");
        
        // API response handling
        Object apiResponse = new ApiResponse("success", new UserData("Alice", 25, "alice@example.com"));
        String responseMessage = switch (apiResponse) {
            case ApiResponse(String status, UserData user) when "success".equals(status) -> 
                "User " + user.name() + " retrieved successfully";
            case ApiResponse(String status, Object data) when "error".equals(status) -> 
                "Error occurred: " + data;
            default -> "Unknown response type";
        };
        System.out.println("API Response: " + responseMessage);
        
        // Data validation
        List<Object> userInputs = Arrays.asList("123", 456, "invalid", 789.0, "valid@email.com");
        for (Object input : userInputs) {
            String validation = switch (input) {
                case String str when str.matches("\\d+") -> "Valid number string: " + str;
                case String str when str.contains("@") -> "Valid email: " + str;
                case String str -> "Invalid string: " + str;
                case Integer num when num > 0 -> "Valid positive integer: " + num;
                case Integer num -> "Invalid integer: " + num;
                case Double num when num > 0 -> "Valid positive double: " + num;
                case Double num -> "Invalid double: " + num;
                default -> "Unknown input type";
            };
            System.out.println("Validation: " + validation);
        }
        
        // Event handling
        List<Event> events = Arrays.asList(
                new ClickEvent(100, 200),
                new KeyEvent("Enter"),
                new MouseEvent("move", 150, 250),
                new CustomEvent("user_action", Map.of("action", "login"))
        );
        
        for (Event event : events) {
            String eventDescription = switch (event) {
                case ClickEvent(int x, int y) -> "Click at (" + x + ", " + y + ")";
                case KeyEvent(String key) -> "Key pressed: " + key;
                case MouseEvent(String action, int x, int y) -> 
                    "Mouse " + action + " at (" + x + ", " + y + ")";
                case CustomEvent(String type, Map<String, Object> data) -> 
                    "Custom event: " + type + " with data: " + data;
                default -> "Unknown event type";
            };
            System.out.println("Event: " + eventDescription);
        }
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("5. Best Practices:");
        
        // Good: Use pattern matching for type checking
        Object obj = "Hello World";
        if (obj instanceof String str) {
            System.out.println("Good: Direct pattern matching - " + str.length());
        }
        
        // Good: Combine with conditions
        if (obj instanceof String str && str.length() > 5) {
            System.out.println("Good: Pattern matching with condition - " + str);
        }
        
        // Good: Use switch expressions for multiple cases
        String result = switch (obj) {
            case String str when str.length() > 10 -> "Long string";
            case String str -> "Short string";
            case Integer num when num > 100 -> "Large number";
            case Integer num -> "Small number";
            default -> "Other type";
        };
        System.out.println("Good: Switch expression - " + result);
        
        // Good: Handle null cases
        Object nullableObj = null;
        String nullResult = switch (nullableObj) {
            case String str -> "String: " + str;
            case Integer num -> "Integer: " + num;
            case null -> "Null value";
            default -> "Unknown";
        };
        System.out.println("Good: Null handling - " + nullResult);
        
        // Good: Use sealed classes for exhaustive pattern matching
        Shape shape = new Circle(5.0);
        String shapeInfo = switch (shape) {
            case Circle(double radius) -> "Circle with radius " + radius;
            case Rectangle(double width, double height) -> 
                "Rectangle " + width + "x" + height;
            case Triangle(double a, double b, double c) -> 
                "Triangle with sides " + a + ", " + b + ", " + c;
        };
        System.out.println("Good: Sealed class pattern matching - " + shapeInfo);
        
        System.out.println();
    }
    
    // Helper classes for examples
    record ApiResponse(String status, Object data) {}
    record UserData(String name, int age, String email) {}
    
    sealed interface Event permits ClickEvent, KeyEvent, MouseEvent, CustomEvent {}
    record ClickEvent(int x, int y) implements Event {}
    record KeyEvent(String key) implements Event {}
    record MouseEvent(String action, int x, int y) implements Event {}
    record CustomEvent(String type, Map<String, Object> data) implements Event {}
    
    sealed interface Shape permits Circle, Rectangle, Triangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double width, double height) implements Shape {}
    record Triangle(double a, double b, double c) implements Shape {}
}
