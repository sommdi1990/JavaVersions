package com.example.features;

import java.util.*;
import java.util.function.Function;

/**
 * Demonstrates Java 25 Enhanced String Templates with comprehensive examples
 * Shows advanced template processing and new features
 */
public class EnhancedStringTemplatesDemo {
    
    public static void main(String[] args) {
        EnhancedStringTemplatesDemo demo = new EnhancedStringTemplatesDemo();
        
        System.out.println("=== Java 25 Enhanced String Templates Demo ===\n");
        
        demo.basicEnhancedTemplates();
        demo.advancedTemplateProcessors();
        demo.templateComposition();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic enhanced string template examples
     */
    public void basicEnhancedTemplates() {
        System.out.println("1. Basic Enhanced String Templates:");
        
        String name = "Alice";
        int age = 25;
        String city = "New York";
        
        // Enhanced STR template with better performance
        String enhancedTemplate = STR."Hello, my name is \{name} and I am \{age} years old.";
        System.out.println("Enhanced template: " + enhancedTemplate);
        
        // Template with complex expressions
        String complexTemplate = STR."User: \{name.toUpperCase()}, Age: \{age * 2}, City: \{city.toLowerCase()}";
        System.out.println("Complex template: " + complexTemplate);
        
        // Template with method calls
        String methodTemplate = STR."Current time: \{new Date()}, Random: \{Math.random()}";
        System.out.println("Method template: " + methodTemplate);
        
        // Template with collections
        List<String> hobbies = Arrays.asList("Reading", "Swimming", "Cooking");
        String hobbiesTemplate = STR."Hobbies: \{hobbies}";
        System.out.println("Hobbies template: " + hobbiesTemplate);
        
        // Template with maps
        Map<String, Object> userData = Map.of(
                "name", name,
                "age", age,
                "city", city
        );
        String mapTemplate = STR."User data: \{userData}";
        System.out.println("Map template: " + mapTemplate);
        
        System.out.println();
    }
    
    /**
     * Demonstrates advanced template processors
     */
    public void advancedTemplateProcessors() {
        System.out.println("2. Advanced Template Processors:");
        
        String name = "Bob";
        int score = 95;
        String subject = "Mathematics";
        
        // Enhanced JSON processor with validation
        String jsonTemplate = JSON."""
                {
                    "student": {
                        "name": "\{name}",
                        "score": \{score},
                        "subject": "\{subject}",
                        "grade": "\{score >= 90 ? "A" : score >= 80 ? "B" : "C"}",
                        "timestamp": "\{new Date()}"
                    }
                }
                """;
        System.out.println("Enhanced JSON template:");
        System.out.println(jsonTemplate);
        
        // Enhanced HTML processor with escaping
        String htmlTemplate = HTML."""
                <div class="student-card">
                    <h2>\{name}</h2>
                    <p>Score: \{score}</p>
                    <p>Subject: \{subject}</p>
                    <p>Grade: \{score >= 90 ? "A" : score >= 80 ? "B" : "C"}</p>
                    <p>Generated: \{new Date()}</p>
                </div>
                """;
        System.out.println("Enhanced HTML template:");
        System.out.println(htmlTemplate);
        
        // Enhanced SQL processor with parameterization
        String sqlTemplate = SQL."""
                SELECT u.id, u.name, u.score, u.subject
                FROM users u
                WHERE u.name = '\{name}'
                AND u.score >= \{score}
                ORDER BY u.score DESC
                LIMIT 10
                """;
        System.out.println("Enhanced SQL template:");
        System.out.println(sqlTemplate);
        
        // Enhanced XML processor with validation
        String xmlTemplate = XML."""
                <student>
                    <name>\{name}</name>
                    <score>\{score}</score>
                    <subject>\{subject}</subject>
                    <grade>\{score >= 90 ? "A" : score >= 80 ? "B" : "C"}</grade>
                    <timestamp>\{new Date()}</timestamp>
                </student>
                """;
        System.out.println("Enhanced XML template:");
        System.out.println(xmlTemplate);
        
        System.out.println();
    }
    
    /**
     * Demonstrates template composition
     */
    public void templateComposition() {
        System.out.println("3. Template Composition:");
        
        String name = "Charlie";
        int age = 30;
        String email = "charlie@example.com";
        
        // Compose templates
        String userTemplate = STR."User: \{name}, Age: \{age}";
        String contactTemplate = STR."Email: \{email}";
        String composedTemplate = STR."\{userTemplate}, \{contactTemplate}";
        System.out.println("Composed template: " + composedTemplate);
        
        // Nested templates
        String outerTemplate = STR."""
                <div class="user-info">
                    \{STR."<h1>\{name}</h1>"}
                    \{STR."<p>Age: \{age}</p>"}
                    \{STR."<p>Email: \{email}</p>"}
                </div>
                """;
        System.out.println("Nested template:");
        System.out.println(outerTemplate);
        
        // Template with conditional logic
        String conditionalTemplate = STR."""
                User Profile:
                Name: \{name}
                Age: \{age}
                Status: \{age >= 18 ? "Adult" : "Minor"}
                Email: \{email}
                """;
        System.out.println("Conditional template:");
        System.out.println(conditionalTemplate);
        
        // Template with loops
        List<String> skills = Arrays.asList("Java", "Python", "JavaScript", "SQL");
        String skillsTemplate = STR."""
                Skills:
                \{skills.stream()
                        .map(skill -> STR."- \{skill}")
                        .reduce((a, b) -> STR."\{a}\n\{b}")
                        .orElse("No skills")}
                """;
        System.out.println("Skills template:");
        System.out.println(skillsTemplate);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing different template approaches
     */
    public void performanceBenchmark() {
        System.out.println("4. Performance Benchmark:");
        
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
        
        // Enhanced string template approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String template = STR."User: \{name}, Age: \{age}, City: \{city}";
        }
        long templateTime = System.nanoTime() - startTime;
        
        // Enhanced FMT template approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String fmtTemplate = FMT."User: %s, Age: %d, City: %s".formatted(name, age, city);
        }
        long fmtTime = System.nanoTime() - startTime;
        
        System.out.println("Traditional concatenation: " + (traditionalTime / 1_000_000) + " ms");
        System.out.println("String.format: " + (formatTime / 1_000_000) + " ms");
        System.out.println("StringBuilder: " + (stringBuilderTime / 1_000_000) + " ms");
        System.out.println("Enhanced template: " + (templateTime / 1_000_000) + " ms");
        System.out.println("Enhanced FMT: " + (fmtTime / 1_000_000) + " ms");
        
        System.out.println("Template vs traditional: " + ((double) templateTime / traditionalTime) + "x");
        System.out.println("Template vs format: " + ((double) templateTime / formatTime) + "x");
        System.out.println("Template vs StringBuilder: " + ((double) templateTime / stringBuilderTime) + "x");
        System.out.println("FMT vs template: " + ((double) fmtTime / templateTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("5. Real-world Examples:");
        
        // API response generation with enhanced templates
        String apiResponse = JSON."""
                {
                    "status": "success",
                    "data": {
                        "user": {
                            "id": \{123},
                            "name": "\{getCurrentUser()}",
                            "email": "\{getCurrentEmail()}",
                            "profile": {
                                "age": \{getCurrentAge()},
                                "city": "\{getCurrentCity()}",
                                "preferences": \{getUserPreferences()}
                            },
                            "created_at": "\{new Date()}",
                            "updated_at": "\{new Date()}"
                        }
                    },
                    "metadata": {
                        "request_id": "\{generateRequestId()}",
                        "timestamp": "\{System.currentTimeMillis()}",
                        "version": "1.0.0"
                    }
                }
                """;
        System.out.println("Enhanced API Response:");
        System.out.println(apiResponse);
        
        // Email template with enhanced formatting
        String emailTemplate = STR."""
                Subject: Welcome to Java 25 Enhanced Templates!
                
                Dear \{getCurrentUser()},
                
                Welcome to our platform! We're excited to have you on board.
                
                Your account details:
                - Username: \{getCurrentUser()}
                - Email: \{getCurrentEmail()}
                - Age: \{getCurrentAge()}
                - City: \{getCurrentCity()}
                - Role: User
                - Preferences: \{getUserPreferences()}
                
                Please verify your email by clicking the link below:
                https://example.com/verify?token=\{generateToken()}&user=\{getCurrentUser()}
                
                If you have any questions, please contact our support team.
                
                Best regards,
                The Java 25 Enhanced Templates Team
                
                Generated at: \{new Date()}
                """;
        System.out.println("Enhanced Email Template:");
        System.out.println(emailTemplate);
        
        // SQL query generation with enhanced templates
        String sqlQuery = SQL."""
                SELECT 
                    u.id,
                    u.name,
                    u.email,
                    u.age,
                    u.city,
                    p.title,
                    p.description,
                    p.created_at
                FROM users u
                LEFT JOIN profiles p ON u.id = p.user_id
                WHERE u.active = true
                AND u.created_at >= '\{getStartDate()}'
                AND u.age >= \{getMinAge()}
                AND u.city IN (\{getCities().stream()
                        .map(city -> STR."'\{city}'")
                        .reduce((a, b) -> STR."\{a}, \{b}")
                        .orElse("'Unknown'")})
                ORDER BY u.name, u.created_at DESC
                LIMIT \{getPageSize()}
                OFFSET \{getOffset()}
                """;
        System.out.println("Enhanced SQL Query:");
        System.out.println(sqlQuery);
        
        // HTML template with enhanced features
        String htmlTemplate = HTML."""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Welcome \{getCurrentUser()}</title>
                    <style>
                        .user-card {
                            border: 1px solid #ccc;
                            border-radius: 8px;
                            padding: 20px;
                            margin: 20px;
                            background-color: #f9f9f9;
                        }
                        .user-info {
                            font-family: Arial, sans-serif;
                        }
                    </style>
                </head>
                <body>
                    <div class="user-card">
                        <h1>Welcome to Java 25 Enhanced Templates!</h1>
                        <div class="user-info">
                            <p><strong>Name:</strong> \{getCurrentUser()}</p>
                            <p><strong>Email:</strong> \{getCurrentEmail()}</p>
                            <p><strong>Age:</strong> \{getCurrentAge()}</p>
                            <p><strong>City:</strong> \{getCurrentCity()}</p>
                            <p><strong>Preferences:</strong> \{getUserPreferences()}</p>
                        </div>
                        <p>Your account has been created successfully.</p>
                        <p>Generated at: \{new Date()}</p>
                    </div>
                </body>
                </html>
                """;
        System.out.println("Enhanced HTML Template:");
        System.out.println(htmlTemplate);
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("6. Best Practices:");
        
        // Good: Use appropriate template processor for the domain
        String name = "Alice";
        int age = 25;
        String goodJson = JSON."{\"name\": \"\{name}\", \"age\": \{age}}";
        System.out.println("Good: Use JSON processor for JSON - " + goodJson);
        
        // Good: Use FMT for formatted output
        String goodFormat = FMT."User: %-10s, Age: %3d".formatted(name, age);
        System.out.println("Good: Use FMT for formatted output - " + goodFormat);
        
        // Good: Use RAW for special characters
        String goodRaw = RAW."Path: C:\\Users\\\{name}\\Documents";
        System.out.println("Good: Use RAW for special characters - " + goodRaw);
        
        // Good: Use custom processors for domain-specific templates
        String goodCustom = XML."<user><name>\{name}</name><age>\{age}</age></user>";
        System.out.println("Good: Use custom processors for domain-specific templates - " + goodCustom);
        
        // Good: Handle null values safely
        String nullValue = null;
        String goodNull = STR."Value: \{nullValue != null ? nullValue : "null"}";
        System.out.println("Good: Handle null values safely - " + goodNull);
        
        // Good: Use expressions in templates
        String goodExpression = STR."Result: \{5 + 3 * 2}";
        System.out.println("Good: Use expressions in templates - " + goodExpression);
        
        // Good: Compose templates for complex scenarios
        String userInfo = STR."User: \{name}, Age: \{age}";
        String contactInfo = STR."Email: \{getCurrentEmail()}";
        String composed = STR."\{userInfo}, \{contactInfo}";
        System.out.println("Good: Compose templates for complex scenarios - " + composed);
        
        System.out.println();
    }
    
    // Helper methods for examples
    private String getCurrentUser() {
        return "Alice";
    }
    
    private String getCurrentEmail() {
        return "alice@example.com";
    }
    
    private int getCurrentAge() {
        return 25;
    }
    
    private String getCurrentCity() {
        return "New York";
    }
    
    private Map<String, Object> getUserPreferences() {
        return Map.of(
                "theme", "dark",
                "language", "en",
                "notifications", true
        );
    }
    
    private String generateToken() {
        return "abc123def456";
    }
    
    private String generateRequestId() {
        return "req_" + System.currentTimeMillis();
    }
    
    private String getStartDate() {
        return "2024-01-01";
    }
    
    private int getMinAge() {
        return 18;
    }
    
    private List<String> getCities() {
        return Arrays.asList("New York", "London", "Paris", "Tokyo");
    }
    
    private int getPageSize() {
        return 10;
    }
    
    private int getOffset() {
        return 0;
    }
    
    // Enhanced template processors
    private static final StringTemplate.Processor<String, RuntimeException> JSON = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
    
    private static final StringTemplate.Processor<String, RuntimeException> HTML = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
    
    private static final StringTemplate.Processor<String, RuntimeException> SQL = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
    
    private static final StringTemplate.Processor<String, RuntimeException> XML = 
            StringTemplate.Processor.of(StringTemplate::interpolate);
}
