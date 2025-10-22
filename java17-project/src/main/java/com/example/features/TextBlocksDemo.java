package com.example.features;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Demonstrates Java 17 Text Blocks with comprehensive examples
 * Shows performance benefits and real-world use cases
 */
public class TextBlocksDemo {
    
    public static void main(String[] args) {
        TextBlocksDemo demo = new TextBlocksDemo();
        
        System.out.println("=== Java 17 Text Blocks Demo ===\n");
        
        demo.basicTextBlocks();
        demo.jsonExamples();
        demo.sqlExamples();
        demo.htmlExamples();
        demo.performanceBenchmark();
        demo.realWorldExamples();
    }
    
    /**
     * Basic text block examples
     */
    public void basicTextBlocks() {
        System.out.println("1. Basic Text Blocks:");
        
        // Simple text block
        String simpleText = """
                This is a text block
                It can span multiple lines
                Without concatenation
                """;
        System.out.println("Simple text block:");
        System.out.println(simpleText);
        
        // Text block with variables
        String name = "Java 17";
        String version = "17.0.0";
        String info = """
                Language: %s
                Version: %s
                Features: Text Blocks, Pattern Matching, Sealed Classes
                """.formatted(name, version);
        System.out.println("Text block with variables:");
        System.out.println(info);
        
        // Preserving whitespace
        String preserved = """
                Line 1
                    Line 2 with indentation
                Line 3
                """;
        System.out.println("Preserved whitespace:");
        System.out.println(preserved);
        
        // Removing common indentation
        String aligned = """
                Line 1
                    Line 2
                Line 3
                """;
        System.out.println("Aligned text block:");
        System.out.println(aligned);
        
        System.out.println();
    }
    
    /**
     * JSON examples with text blocks
     */
    public void jsonExamples() {
        System.out.println("2. JSON Examples:");
        
        // Simple JSON object
        String userJson = """
                {
                    "id": 1,
                    "name": "Alice",
                    "email": "alice@example.com",
                    "active": true,
                    "roles": ["user", "admin"]
                }
                """;
        System.out.println("User JSON:");
        System.out.println(userJson);
        
        // Complex nested JSON
        String configJson = """
                {
                    "database": {
                        "host": "localhost",
                        "port": 3306,
                        "name": "myapp",
                        "credentials": {
                            "username": "admin",
                            "password": "secret"
                        }
                    },
                    "cache": {
                        "enabled": true,
                        "ttl": 3600,
                        "maxSize": 1000
                    },
                    "features": [
                        "authentication",
                        "authorization",
                        "logging"
                    ]
                }
                """;
        System.out.println("Configuration JSON:");
        System.out.println(configJson);
        
        // JSON with dynamic values
        String dynamicJson = """
                {
                    "timestamp": "%s",
                    "level": "%s",
                    "message": "%s",
                    "metadata": {
                        "thread": "%s",
                        "class": "%s"
                    }
                }
                """.formatted(
                        new Date(),
                        "INFO",
                        "Application started",
                        Thread.currentThread().getName(),
                        TextBlocksDemo.class.getSimpleName()
                );
        System.out.println("Dynamic JSON:");
        System.out.println(dynamicJson);
        
        System.out.println();
    }
    
    /**
     * SQL examples with text blocks
     */
    public void sqlExamples() {
        System.out.println("3. SQL Examples:");
        
        // Simple SELECT query
        String selectQuery = """
                SELECT u.id, u.name, u.email, p.title
                FROM users u
                LEFT JOIN profiles p ON u.id = p.user_id
                WHERE u.active = true
                ORDER BY u.name
                """;
        System.out.println("SELECT query:");
        System.out.println(selectQuery);
        
        // Complex JOIN query
        String complexQuery = """
                SELECT 
                    o.id as order_id,
                    o.order_date,
                    c.name as customer_name,
                    c.email,
                    SUM(oi.quantity * oi.price) as total_amount
                FROM orders o
                JOIN customers c ON o.customer_id = c.id
                JOIN order_items oi ON o.id = oi.order_id
                WHERE o.order_date >= ?
                AND o.status = 'COMPLETED'
                GROUP BY o.id, o.order_date, c.name, c.email
                HAVING total_amount > ?
                ORDER BY total_amount DESC
                """;
        System.out.println("Complex JOIN query:");
        System.out.println(complexQuery);
        
        // CREATE TABLE statement
        String createTable = """
                CREATE TABLE products (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(255) NOT NULL,
                    description TEXT,
                    price DECIMAL(10,2) NOT NULL,
                    category_id BIGINT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (category_id) REFERENCES categories(id)
                )
                """;
        System.out.println("CREATE TABLE statement:");
        System.out.println(createTable);
        
        System.out.println();
    }
    
    /**
     * HTML examples with text blocks
     */
    public void htmlExamples() {
        System.out.println("4. HTML Examples:");
        
        // Simple HTML page
        String htmlPage = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Java 17 Demo</title>
                </head>
                <body>
                    <h1>Welcome to Java 17</h1>
                    <p>This page demonstrates text blocks.</p>
                </body>
                </html>
                """;
        System.out.println("HTML page:");
        System.out.println(htmlPage);
        
        // HTML template with placeholders
        String htmlTemplate = """
                <div class="user-card">
                    <h2>%s</h2>
                    <p>Email: %s</p>
                    <p>Role: %s</p>
                    <p>Status: %s</p>
                </div>
                """;
        String userCard = htmlTemplate.formatted("Alice", "alice@example.com", "Admin", "Active");
        System.out.println("User card HTML:");
        System.out.println(userCard);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing text blocks vs string concatenation
     */
    public void performanceBenchmark() {
        System.out.println("5. Performance Benchmark:");
        
        int iterations = 100000;
        
        // Traditional string concatenation
        long startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String traditional = "{\n" +
                    "  \"id\": " + i + ",\n" +
                    "  \"name\": \"User" + i + "\",\n" +
                    "  \"email\": \"user" + i + "@example.com\",\n" +
                    "  \"active\": true\n" +
                    "}";
        }
        long traditionalTime = System.nanoTime() - startTime;
        
        // Text block approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String textBlock = """
                    {
                      "id": %d,
                      "name": "User%d",
                      "email": "user%d@example.com",
                      "active": true
                    }
                    """.formatted(i, i, i);
        }
        long textBlockTime = System.nanoTime() - startTime;
        
        // StringBuilder approach
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            sb.append("  \"id\": ").append(i).append(",\n");
            sb.append("  \"name\": \"User").append(i).append("\",\n");
            sb.append("  \"email\": \"user").append(i).append("@example.com\",\n");
            sb.append("  \"active\": true\n");
            sb.append("}");
            String sbResult = sb.toString();
        }
        long stringBuilderTime = System.nanoTime() - startTime;
        
        System.out.println("Traditional concatenation: " + (traditionalTime / 1_000_000) + " ms"));
        System.out.println("Text block: " + (textBlockTime / 1_000_000) + " ms");
        System.out.println("StringBuilder: " + (stringBuilderTime / 1_000_000) + " ms");
        System.out.println("Text block vs traditional: " + ((double) textBlockTime / traditionalTime) + "x");
        System.out.println("Text block vs StringBuilder: " + ((double) textBlockTime / stringBuilderTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("6. Real-world Examples:");
        
        // API response template
        String apiResponse = """
                {
                    "status": "success",
                    "data": {
                        "users": [
                            {
                                "id": 1,
                                "name": "Alice",
                                "email": "alice@example.com"
                            },
                            {
                                "id": 2,
                                "name": "Bob",
                                "email": "bob@example.com"
                            }
                        ],
                        "pagination": {
                            "page": 1,
                            "size": 10,
                            "total": 2
                        }
                    },
                    "timestamp": "%s"
                }
                """.formatted(new Date());
        System.out.println("API Response Template:");
        System.out.println(apiResponse);
        
        // Email template
        String emailTemplate = """
                Subject: Welcome to Java 17!
                
                Dear %s,
                
                Welcome to our platform! We're excited to have you on board.
                
                Your account details:
                - Username: %s
                - Email: %s
                - Role: %s
                
                Please verify your email by clicking the link below:
                %s
                
                Best regards,
                The Java 17 Team
                """;
        String welcomeEmail = emailTemplate.formatted(
                "Alice",
                "alice",
                "alice@example.com",
                "User",
                "https://example.com/verify?token=abc123"
        );
        System.out.println("Welcome Email Template:");
        System.out.println(welcomeEmail);
        
        // Configuration file content
        String configContent = """
                # Application Configuration
                app.name=Java 17 Demo
                app.version=1.0.0
                
                # Database Configuration
                db.host=localhost
                db.port=3306
                db.name=myapp
                db.username=admin
                db.password=secret
                
                # Cache Configuration
                cache.enabled=true
                cache.ttl=3600
                cache.maxSize=1000
                
                # Logging Configuration
                logging.level=INFO
                logging.file=logs/application.log
                """;
        System.out.println("Configuration File Content:");
        System.out.println(configContent);
        
        // Dockerfile content
        String dockerfile = """
                FROM openjdk:17-jre-alpine
                
                WORKDIR /app
                
                COPY target/java17-project-1.0.0.jar app.jar
                
                EXPOSE 8080
                
                HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \\
                  CMD curl -f http://localhost:8080/health || exit 1
                
                CMD ["java", "-jar", "app.jar"]
                """;
        System.out.println("Dockerfile Content:");
        System.out.println(dockerfile);
        
        System.out.println();
    }
}
