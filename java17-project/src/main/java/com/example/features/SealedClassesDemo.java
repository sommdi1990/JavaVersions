package com.example.features;

import java.util.*;
import java.util.function.Function;

/**
 * Demonstrates Java 17 Sealed Classes with comprehensive examples
 * Shows how to create restricted inheritance hierarchies
 */
public class SealedClassesDemo {
    
    public static void main(String[] args) {
        SealedClassesDemo demo = new SealedClassesDemo();
        
        System.out.println("=== Java 17 Sealed Classes Demo ===\n");
        
        demo.basicSealedClasses();
        demo.patternMatchingWithSealed();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic sealed class examples
     */
    public void basicSealedClasses() {
        System.out.println("1. Basic Sealed Classes:");
        
        // Create instances of sealed classes
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(10.0, 8.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);
        
        // Demonstrate polymorphism
        List<Shape> shapes = Arrays.asList(circle, rectangle, triangle);
        for (Shape shape : shapes) {
            System.out.println("Shape: " + shape.getClass().getSimpleName());
            System.out.println("Area: " + calculateArea(shape));
            System.out.println("Perimeter: " + calculatePerimeter(shape));
            System.out.println();
        }
        
        // Demonstrate exhaustive pattern matching
        String shapeDescription = describeShape(circle);
        System.out.println("Shape description: " + shapeDescription);
        
        System.out.println();
    }
    
    /**
     * Demonstrates pattern matching with sealed classes
     */
    public void patternMatchingWithSealed() {
        System.out.println("2. Pattern Matching with Sealed Classes:");
        
        // Create different types of expressions
        Expression expr1 = new Number(42);
        Expression expr2 = new Add(new Number(10), new Number(20));
        Expression expr3 = new Multiply(new Number(5), new Add(new Number(2), new Number(3)));
        Expression expr4 = new Variable("x");
        
        List<Expression> expressions = Arrays.asList(expr1, expr2, expr3, expr4);
        
        for (Expression expr : expressions) {
            System.out.println("Expression: " + expr);
            System.out.println("Evaluated: " + evaluateExpression(expr, Map.of("x", 5.0)));
            System.out.println("Variables: " + getVariables(expr));
            System.out.println();
        }
        
        // Demonstrate exhaustive pattern matching
        String exprType = getExpressionType(expr2);
        System.out.println("Expression type: " + exprType);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing sealed classes vs traditional inheritance
     */
    public void performanceBenchmark() {
        System.out.println("3. Performance Benchmark:");
        
        // Create large collections of different shapes
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            switch (i % 3) {
                case 0 -> shapes.add(new Circle(i % 10 + 1));
                case 1 -> shapes.add(new Rectangle(i % 10 + 1, i % 10 + 1));
                case 2 -> shapes.add(new Triangle(i % 10 + 1, i % 10 + 1, i % 10 + 1));
            }
        }
        
        // Traditional instanceof approach
        long startTime = System.nanoTime();
        double traditionalSum = 0;
        for (Shape shape : shapes) {
            if (shape instanceof Circle) {
                Circle circle = (Circle) shape;
                traditionalSum += Math.PI * circle.radius() * circle.radius();
            } else if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                traditionalSum += rectangle.width() * rectangle.height();
            } else if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                double s = (triangle.a() + triangle.b() + triangle.c()) / 2;
                traditionalSum += Math.sqrt(s * (s - triangle.a()) * (s - triangle.b()) * (s - triangle.c()));
            }
        }
        long traditionalTime = System.nanoTime() - startTime;
        
        // Pattern matching approach
        startTime = System.nanoTime();
        double patternSum = 0;
        for (Shape shape : shapes) {
            patternSum += switch (shape) {
                case Circle(double radius) -> Math.PI * radius * radius;
                case Rectangle(double width, double height) -> width * height;
                case Triangle(double a, double b, double c) -> {
                    double s = (a + b + c) / 2;
                    yield Math.sqrt(s * (s - a) * (s - b) * (s - c));
                }
            };
        }
        long patternTime = System.nanoTime() - startTime;
        
        System.out.println("Traditional approach: " + traditionalSum + " (Time: " + (traditionalTime / 1_000_000) + " ms)");
        System.out.println("Pattern matching: " + patternSum + " (Time: " + (patternTime / 1_000_000) + " ms)");
        System.out.println("Performance ratio: " + ((double) patternTime / traditionalTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("4. Real-world Examples:");
        
        // HTTP response handling
        HttpResponse successResponse = new SuccessResponse(200, "OK", new UserData("Alice", 25));
        HttpResponse errorResponse = new ErrorResponse(404, "Not Found", "User not found");
        HttpResponse redirectResponse = new RedirectResponse(302, "Found", "https://example.com");
        
        List<HttpResponse> responses = Arrays.asList(successResponse, errorResponse, redirectResponse);
        
        for (HttpResponse response : responses) {
            String responseInfo = switch (response) {
                case SuccessResponse(int code, String message, Object data) -> 
                    "Success: " + code + " - " + message + " - Data: " + data;
                case ErrorResponse(int code, String message, String error) -> 
                    "Error: " + code + " - " + message + " - Error: " + error;
                case RedirectResponse(int code, String message, String location) -> 
                    "Redirect: " + code + " - " + message + " - Location: " + location;
            };
            System.out.println("Response: " + responseInfo);
        }
        
        // Database operation results
        DbResult insertResult = new InsertResult(1, "User created successfully");
        DbResult updateResult = new UpdateResult(5, "User updated successfully");
        DbResult deleteResult = new DeleteResult(1, "User deleted successfully");
        DbResult errorResult = new ErrorResult("Database connection failed");
        
        List<DbResult> dbResults = Arrays.asList(insertResult, updateResult, deleteResult, errorResult);
        
        for (DbResult result : dbResults) {
            String resultInfo = switch (result) {
                case InsertResult(int id, String message) -> 
                    "Inserted record with ID: " + id + " - " + message;
                case UpdateResult(int affectedRows, String message) -> 
                    "Updated " + affectedRows + " rows - " + message;
                case DeleteResult(int deletedRows, String message) -> 
                    "Deleted " + deletedRows + " rows - " + message;
                case ErrorResult(String error) -> 
                    "Database error: " + error;
            };
            System.out.println("DB Result: " + resultInfo);
        }
        
        // File system operations
        FileSystemItem file = new File("document.txt", 1024);
        FileSystemItem directory = new Directory("documents", Arrays.asList(file));
        FileSystemItem symlink = new Symlink("link.txt", "document.txt");
        
        List<FileSystemItem> items = Arrays.asList(file, directory, symlink);
        
        for (FileSystemItem item : items) {
            String itemInfo = switch (item) {
                case File(String name, long size) -> 
                    "File: " + name + " (" + size + " bytes)";
                case Directory(String name, List<FileSystemItem> children) -> 
                    "Directory: " + name + " (" + children.size() + " items)";
                case Symlink(String name, String target) -> 
                    "Symlink: " + name + " -> " + target;
            };
            System.out.println("File System Item: " + itemInfo);
        }
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("5. Best Practices:");
        
        // Good: Use sealed classes for restricted hierarchies
        System.out.println("Good: Sealed classes provide compile-time exhaustiveness checking");
        
        // Good: Combine with pattern matching
        Shape shape = new Circle(5.0);
        String shapeInfo = switch (shape) {
            case Circle(double radius) -> "Circle with radius " + radius;
            case Rectangle(double width, double height) -> 
                "Rectangle " + width + "x" + height;
            case Triangle(double a, double b, double c) -> 
                "Triangle with sides " + a + ", " + b + ", " + c;
        };
        System.out.println("Good: Exhaustive pattern matching - " + shapeInfo);
        
        // Good: Use records for data classes
        System.out.println("Good: Records provide automatic equals, hashCode, and toString");
        
        // Good: Use sealed interfaces for multiple inheritance
        System.out.println("Good: Sealed interfaces allow multiple implementations");
        
        // Good: Document the purpose of sealed classes
        System.out.println("Good: Document why certain classes are sealed");
        
        System.out.println();
    }
    
    // Helper methods
    private double calculateArea(Shape shape) {
        return switch (shape) {
            case Circle(double radius) -> Math.PI * radius * radius;
            case Rectangle(double width, double height) -> width * height;
            case Triangle(double a, double b, double c) -> {
                double s = (a + b + c) / 2;
                yield Math.sqrt(s * (s - a) * (s - b) * (s - c));
            }
        };
    }
    
    private double calculatePerimeter(Shape shape) {
        return switch (shape) {
            case Circle(double radius) -> 2 * Math.PI * radius;
            case Rectangle(double width, double height) -> 2 * (width + height);
            case Triangle(double a, double b, double c) -> a + b + c;
        };
    }
    
    private String describeShape(Shape shape) {
        return switch (shape) {
            case Circle(double radius) -> "A circle with radius " + radius;
            case Rectangle(double width, double height) -> 
                "A rectangle with dimensions " + width + "x" + height;
            case Triangle(double a, double b, double c) -> 
                "A triangle with sides " + a + ", " + b + ", " + c;
        };
    }
    
    private double evaluateExpression(Expression expr, Map<String, Double> variables) {
        return switch (expr) {
            case Number(double value) -> value;
            case Add(Expression left, Expression right) -> 
                evaluateExpression(left, variables) + evaluateExpression(right, variables);
            case Multiply(Expression left, Expression right) -> 
                evaluateExpression(left, variables) * evaluateExpression(right, variables);
            case Variable(String name) -> variables.getOrDefault(name, 0.0);
        };
    }
    
    private Set<String> getVariables(Expression expr) {
        return switch (expr) {
            case Number(double value) -> Set.of();
            case Add(Expression left, Expression right) -> {
                Set<String> vars = new HashSet<>(getVariables(left));
                vars.addAll(getVariables(right));
                yield vars;
            }
            case Multiply(Expression left, Expression right) -> {
                Set<String> vars = new HashSet<>(getVariables(left));
                vars.addAll(getVariables(right));
                yield vars;
            }
            case Variable(String name) -> Set.of(name);
        };
    }
    
    private String getExpressionType(Expression expr) {
        return switch (expr) {
            case Number(double value) -> "Number";
            case Add(Expression left, Expression right) -> "Addition";
            case Multiply(Expression left, Expression right) -> "Multiplication";
            case Variable(String name) -> "Variable";
        };
    }
    
    // Sealed class definitions
    sealed interface Shape permits Circle, Rectangle, Triangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double width, double height) implements Shape {}
    record Triangle(double a, double b, double c) implements Shape {}
    
    sealed interface Expression permits Number, Add, Multiply, Variable {}
    record Number(double value) implements Expression {}
    record Add(Expression left, Expression right) implements Expression {}
    record Multiply(Expression left, Expression right) implements Expression {}
    record Variable(String name) implements Expression {}
    
    sealed interface HttpResponse permits SuccessResponse, ErrorResponse, RedirectResponse {}
    record SuccessResponse(int code, String message, Object data) implements HttpResponse {}
    record ErrorResponse(int code, String message, String error) implements HttpResponse {}
    record RedirectResponse(int code, String message, String location) implements HttpResponse {}
    
    sealed interface DbResult permits InsertResult, UpdateResult, DeleteResult, ErrorResult {}
    record InsertResult(int id, String message) implements DbResult {}
    record UpdateResult(int affectedRows, String message) implements DbResult {}
    record DeleteResult(int deletedRows, String message) implements DbResult {}
    record ErrorResult(String error) implements DbResult {}
    
    sealed interface FileSystemItem permits File, Directory, Symlink {}
    record File(String name, long size) implements FileSystemItem {}
    record Directory(String name, List<FileSystemItem> children) implements FileSystemItem {}
    record Symlink(String name, String target) implements FileSystemItem {}
    
    record UserData(String name, int age) {}
}
