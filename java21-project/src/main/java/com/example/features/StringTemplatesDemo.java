package com.example.features;

import java.util.*;
import java.util.function.Function;

/**
 * Demonstrates Java 21 String Templates with comprehensive examples
 * Shows different template processors and real-world use cases
 */
public class StringTemplatesDemo {
    
    public static void main(String[] args) {
        StringTemplatesDemo demo = new StringTemplatesDemo();
        
        System.out.println("=== Java 21 String Templates Demo ===\n");
        
        demo.basicStringTemplates();
        demo.strTemplateProcessor();
        demo.fmtTemplateProcessor();
        demo.rawTemplateProcessor();
        demo.customTemplateProcessor();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic string template examples
     */
    public void basicStringTemplates() {
        System.out.println("1. Basic String Templates:");
        
        String name = "Alice";
        int age = 25;
        String city = "New York";
        
        // Basic string template
        String template = STR."Hello, my name is \{name} and I am \{age} years old.";
        System.out.println("Basic template: " + template);
        
        // Template with expressions
        String expressionTemplate = STR."The result of 5 + 3 is \{5 + 3}";
        System.out.println("Expression template: " + expressionTemplate);
        
        // Template with method calls
        String methodTemplate = STR."Current time: \{new Date()}";
        System.out.println("Method template: " + methodTemplate);
        
        // Template with complex expressions
        String complexTemplate = STR."User: \{name.toUpperCase()}, Age: \{age * 2}, City: \{city.toLowerCase()}";
        System.out.println("Complex template: " + complexTemplate);
        
        // Template with null handling
        String nullValue = null;
        String nullTemplate = STR."Value: \{nullValue != null ? nullValue : "null"}";
        System.out.println("Null template: " + nullTemplate);
        
        System.out.println();
    }
    
    /**
     * Demonstrates STR template processor
     */
    public void strTemplateProcessor() {
        System.out.println("2. STR Template Processor:");
        
        String name = "Bob";
        int score = 95;
        String subject = "Mathematics";
        
        // Simple STR template
        String strTemplate = STR."Student: \{name} scored \{score} in \{subject}";
        System.out.println("STR template: " + strTemplate);
        
        // STR with multiple expressions
        String multiTemplate = STR."""
                Student Report:
                Name: \{name}
                Score: \{score}
                Grade: \{score >= 90 ? "A" : score >= 80 ? "B" : "C"}
                Subject: \{subject}
                """;
        System.out.println("Multi-line STR template:");
        System.out.println(multiTemplate);
        
        // STR with collections
        List<String> subjects = Arrays.asList("Math", "Science", "English");
        String listTemplate = STR."Subjects: \{subjects}";
        System.out.println("List template: " + listTemplate);
        
        // STR with maps
        Map<String, Integer> grades = Map.of("Math", 95, "Science", 87, "English", 92);
        String mapTemplate = STR."Grades: \{grades}";
        System.out.println("Map template: " + mapTemplate);
        
        System.out.println();
    }
    
    /**
     * Demonstrates FMT template processor
     */
    public void fmtTemplateProcessor() {
        System.out.println("3. FMT Template Processor:");
        
        String name = "Charlie";
        double salary = 75000.50;
        int years = 5;
        
        // FMT with formatting
        String fmtTemplate = FMT."Employee: %s, Salary: $%.2f, Years: %d".formatted(name, salary, years);
        System.out.println("FMT template: " + fmtTemplate);
        
        // FMT with alignment
        String alignedTemplate = FMT."""
                Employee Report:
                Name: %-10s \{name}
                Salary: $%10.2f \{salary}
                Years: %5d \{years}
                """;
        System.out.println("Aligned FMT template:");
        System.out.println(alignedTemplate);
        
        // FMT with different formats
        String formatTemplate = FMT."""
                Formatting Examples:
                Integer: %d \{42}
                Float: %.2f \{3.14159}
                String: %s \{"Hello"}
                Boolean: %b \{true}
                """;
        System.out.println("Format FMT template:");
        System.out.println(formatTemplate);
        
        System.out.println();
    }
    
    /**
     * Demonstrates RAW template processor
     */
    public void rawTemplateProcessor() {
        System.out.println("4. RAW Template Processor:");
        
        String name = "David";
        int age = 30;
        
        // RAW template (no processing)
        String rawTemplate = RAW."Name: \{name}, Age: \{age}";
        System.out.println("RAW template: " + rawTemplate);
        
        // RAW with special characters
        String specialTemplate = RAW."Path: C:\\Users\\\{name}\\Documents";
        System.out.println("Special characters template: " + specialTemplate);
        
        // RAW with SQL-like syntax
        String sqlTemplate = RAW."SELECT * FROM users WHERE name = '\{name}' AND age = \{age}";
        System.out.println("SQL-like template: " + sqlTemplate);
        
        System.out.println();
    }
    
    /**
     * Demonstrates custom template processor
     */
    public void customTemplateProcessor() {
        System.out.println("5. Custom Template Processor:");
        
        String name = "Eve";
        String email = "eve@example.com";
        int id = 123;
        
        // Custom JSON template processor
        String jsonTemplate = JSON."""
                {
                    "id": \{id},
                    "name": "\{name}",
                    "email": "\{email}",
                    "active": true
                }
                """;
        System.out.println("JSON template: " + jsonTemplate);
        
        // Custom HTML template processor
        String htmlTemplate = HTML."""
                <div class="user-card">
                    <h2>\{name}</h2>
                    <p>Email: \{email}</p>
                    <p>ID: \{id}</p>
                </div>
                """;
        System.out.println("HTML template: " + htmlTemplate);
        
        // Custom SQL template processor
        String sqlTemplate = SQL."""
                INSERT INTO users (id, name, email)
                VALUES (\{id}, '\{name}', '\{email}')
                """;
        System.out.println("SQL template: " + sqlTemplate);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing string templates vs traditional approaches
     */
    public void performanceBenchmark() {
        System.out.println("6. Performance Benchmark:");
        
        int iterations = 100000;
        String name = "TestUser";
        int age = 25;
        String city = "TestCity";
        
        // Traditional string concatenation
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String traditional = "User: " + name + ", Age: " + age + ", City: " + city;
        }
        long traditionalTime = System.nanoTime() - startTime;
        
        // String.format approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String format = String.format("User: %s, Age: %d, City: %s", name, age, city);
        }
        long formatTime = System.nanoTime() - startTime;
        
        // StringBuilder approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("User: ").append(name).append(", Age: ").append(age).append(", City: ").append(city);
            String sbResult = sb.toString();
        }
        long stringBuilderTime = System.nanoTime() - startTime;
        
        // String template approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String template = STR."User: \{name}, Age: \{age}, City: \{city}";
        }
        long templateTime = System.nanoTime() - startTime;
        
        System.out.println("Traditional concatenation: " + (traditionalTime / 1_000_000) + " ms");
        System.out.println("String.format: " + (formatTime / 1_000_000) + " ms");
        System.out.println("StringBuilder: " + (stringBuilderTime / 1_000_000) + " ms");
        System.out.println("String template: " + (templateTime / 1_000_000) + " ms");
        
        System.out.println("Template vs traditional: " + ((double) templateTime / traditionalTime) + "x");
        System.out.println("Template vs format: " + ((double) templateTime / formatTime) + "x");
        System.out.println("Template vs StringBuilder: " + ((double) templateTime / stringBuilderTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("7. Real-world Examples:");
        
        // API response generation
        String apiResponse = JSON."""
                {
                    "status": "success",
                    "data": {
                        "user": {
                            "id": \{123},
                            "name": "\{getCurrentUser()}",
                            "email": "\{getCurrentEmail()}",
                            "created_at": "\{new Date()}"
                        }
                    },
                    "timestamp": "\{System.currentTimeMillis()}"
                }
                """;
        System.out.println("API Response:");
        System.out.println(apiResponse);
        
        // Email template
        String emailTemplate = STR."""
                Subject: Welcome to Java 21!
                
                Dear \{getCurrentUser()},
                
                Welcome to our platform! We're excited to have you on board.
                
                Your account details:
                - Username: \{getCurrentUser()}
                - Email: \{getCurrentEmail()}
                - Role: User
                
                Please verify your email by clicking the link below:
                https://example.com/verify?token=\{generateToken()}
                
                Best regards,
                The Java 21 Team
                """;
        System.out.println("Email Template:");
        System.out.println(emailTemplate);
        
        // SQL query generation
        String sqlQuery = SQL."""
                SELECT u.id, u.name, u.email, p.title
                FROM users u
                LEFT JOIN profiles p ON u.id = p.user_id
                WHERE u.active = true
                AND u.created_at >= '\{getStartDate()}'
                ORDER BY u.name
                LIMIT \{getPageSize()}
                """;
        System.out.println("SQL Query:");
        System.out.println(sqlQuery);
        
        // HTML template
        String htmlTemplate = HTML."""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Welcome \{getCurrentUser()}</title>
                </head>
                <body>
                    <h1>Welcome to Java 21!</h1>
                    <p>Hello \{getCurrentUser()},</p>
                    <p>Your account has been created successfully.</p>
                    <p>Email: \{getCurrentEmail()}</p>
                </body>
                </html>
                """;
        System.out.println("HTML Template:");
        System.out.println(htmlTemplate);
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("8. Best Practices:");
        
        // Good: Use appropriate template processor
        String name = "Alice";
        int age = 25;
        String goodTemplate = STR."User: \{name}, Age: \{age}";
        System.out.println("Good: Use STR for simple templates - " + goodTemplate);
        
        // Good: Use FMT for formatted output
        String goodFormat = FMT."User: %-10s, Age: %3d".formatted(name, age);
        System.out.println("Good: Use FMT for formatted output - " + goodFormat);
        
        // Good: Use RAW for special characters
        String goodRaw = RAW."Path: C:\\Users\\\{name}\\Documents";
        System.out.println("Good: Use RAW for special characters - " + goodRaw);
        
        // Good: Use custom processors for domain-specific templates
        String goodJson = JSON."{\"name\": \"\{name}\", \"age\": \{age}}";
        System.out.println("Good: Use custom processors for domain-specific templates - " + goodJson);
        
        // Good: Handle null values
        String nullValue = null;
        String goodNull = STR."Value: \{nullValue != null ? nullValue : "null"}";
        System.out.println("Good: Handle null values - " + goodNull);
        
        // Good: Use expressions in templates
        String goodExpression = STR."Result: \{5 + 3 * 2}";
        System.out.println("Good: Use expressions in templates - " + goodExpression);
        
        System.out.println();
    }
    
    // Helper methods for examples
    private String getCurrentUser() {
        return "Alice";
    }
    
    private String getCurrentEmail() {
        return "alice@example.com";
    }
    
    private String generateToken() {
        return "abc123def456";
    }
    
    private String getStartDate() {
        return "2024-01-01";
    }
    
    private int getPageSize() {
        return 10;
    }
    
    // Custom template processors
    private static final StringTemplate.Processor<String, RuntimeException> JSON = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
    
    private static final StringTemplate.Processor<String, RuntimeException> HTML = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
    
    private static final StringTemplate.Processor<String, RuntimeException> SQL = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
}
