package com.example.features;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Demonstrates Java 8 Optional class with comprehensive examples
 * Shows how to avoid NullPointerException and write safer code
 */
public class OptionalDemo {
    
    public static void main(String[] args) {
        OptionalDemo demo = new OptionalDemo();
        
        System.out.println("=== Java 8 Optional Demo ===\n");
        
        demo.basicOptionalOperations();
        demo.optionalMethods();
        demo.optionalWithStreams();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic Optional operations
     */
    public void basicOptionalOperations() {
        System.out.println("1. Basic Optional Operations:");
        
        // Creating Optional instances
        Optional<String> emptyOptional = Optional.empty();
        Optional<String> presentOptional = Optional.of("Hello World");
        Optional<String> nullableOptional = Optional.ofNullable(null);
        Optional<String> nullablePresentOptional = Optional.ofNullable("Present Value");
        
        System.out.println("Empty optional: " + emptyOptional);
        System.out.println("Present optional: " + presentOptional);
        System.out.println("Nullable null: " + nullableOptional);
        System.out.println("Nullable present: " + nullablePresentOptional);
        
        // Checking if value is present
        System.out.println("Empty is present: " + emptyOptional.isPresent());
        System.out.println("Present is present: " + presentOptional.isPresent());
        
        // Getting values
        System.out.println("Present value: " + presentOptional.get());
        System.out.println("Empty or else: " + emptyOptional.orElse("Default Value"));
        
        System.out.println();
    }
    
    /**
     * Demonstrates Optional methods
     */
    public void optionalMethods() {
        System.out.println("2. Optional Methods:");
        
        Optional<String> optional = Optional.of("Java 8");
        
        // map - transform value if present
        Optional<Integer> length = optional.map(String::length);
        System.out.println("Length: " + length.orElse(0));
        
        // filter - keep value if condition is met
        Optional<String> filtered = optional.filter(s -> s.length() > 5);
        System.out.println("Filtered (>5 chars): " + filtered.orElse("Not found"));
        
        // flatMap - flatten nested Optionals
        Optional<String> flatMapped = optional.flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("Flat mapped: " + flatMapped.orElse("Empty"));
        
        // orElseGet - lazy default value
        String lazyDefault = optional.orElseGet(() -> "Lazy default");
        System.out.println("Lazy default: " + lazyDefault);
        
        // orElseThrow - throw exception if empty
        try {
            Optional.empty().orElseThrow(() -> new RuntimeException("Value not found"));
        } catch (RuntimeException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
        
        // ifPresent - execute if value is present
        optional.ifPresent(value -> System.out.println("Value is present: " + value));
        
        // ifPresentOrElse - execute different actions
        optional.ifPresentOrElse(
                value -> System.out.println("Value: " + value),
                () -> System.out.println("No value present")
        );
        
        System.out.println();
    }
    
    /**
     * Demonstrates Optional with Streams
     */
    public void optionalWithStreams() {
        System.out.println("3. Optional with Streams:");
        
        List<Optional<String>> optionals = Arrays.asList(
                Optional.of("Hello"),
                Optional.empty(),
                Optional.of("World"),
                Optional.empty(),
                Optional.of("Java")
        );
        
        // Filter out empty optionals and collect values
        List<String> presentValues = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Present values: " + presentValues);
        
        // Alternative approach using flatMap
        List<String> flatMappedValues = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println("Flat mapped values: " + flatMappedValues);
        
        // Find first present value
        Optional<String> firstPresent = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
        System.out.println("First present: " + firstPresent.orElse("None"));
        
        // Count present values
        long presentCount = optionals.stream()
                .filter(Optional::isPresent)
                .count();
        System.out.println("Present count: " + presentCount);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing Optional vs null checks
     */
    public void performanceBenchmark() {
        System.out.println("4. Performance Benchmark:");
        
        List<String> testData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            testData.add(i % 2 == 0 ? "Value" + i : null);
        }
        
        // Traditional null check approach
        long startTime = System.nanoTime();
        int nullCheckCount = 0;
        for (String value : testData) {
            if (value != null && value.length() > 5) {
                nullCheckCount++;
            }
        }
        long nullCheckTime = System.nanoTime() - startTime;
        
        // Optional approach
        startTime = System.nanoTime();
        long optionalCount = testData.stream()
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(value -> value.length() > 5)
                .count();
        long optionalTime = System.nanoTime() - startTime;
        
        System.out.println("Null check approach: " + nullCheckCount + " (Time: " + (nullCheckTime / 1_000_000) + " ms)");
        System.out.println("Optional approach: " + optionalCount + " (Time: " + (optionalTime / 1_000_000) + " ms)");
        System.out.println("Performance ratio: " + ((double) optionalTime / nullCheckTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("5. Real-world Examples:");
        
        // User service simulation
        UserService userService = new UserService();
        
        // Find user by ID
        Optional<User> user = userService.findById(1);
        System.out.println("User found: " + user.map(User::getName).orElse("Unknown"));
        
        // Get user email with fallback
        String email = userService.findById(1)
                .map(User::getEmail)
                .orElse("no-email@example.com");
        System.out.println("User email: " + email);
        
        // Chain operations safely
        String displayName = userService.findById(1)
                .map(User::getProfile)
                .map(Profile::getDisplayName)
                .orElse("Anonymous");
        System.out.println("Display name: " + displayName);
        
        // Configuration example
        ConfigService configService = new ConfigService();
        String dbUrl = configService.getConfig("database.url")
                .orElse("jdbc:default:localhost");
        System.out.println("Database URL: " + dbUrl);
        
        // API response handling
        ApiResponse response = new ApiResponse();
        String message = response.getData()
                .map(Data::getMessage)
                .orElse("No message available");
        System.out.println("API message: " + message);
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("6. Best Practices:");
        
        // Good: Use Optional for return types
        System.out.println("Good: Optional<String> findUser(String id)");
        
        // Bad: Don't use Optional for parameters
        System.out.println("Bad: void processUser(Optional<User> user)");
        
        // Good: Use Optional for fields that might be null
        System.out.println("Good: private Optional<String> middleName;");
        
        // Good: Chain operations safely
        Optional<String> result = Optional.of("Hello")
                .map(String::toUpperCase)
                .filter(s -> s.length() > 3)
                .map(s -> s + " World");
        System.out.println("Chained result: " + result.orElse("Empty"));
        
        // Good: Use orElseGet for expensive operations
        String expensiveDefault = Optional.<String>empty()
                .orElseGet(() -> {
                    System.out.println("Computing expensive default...");
                    return "Expensive Default";
                });
        System.out.println("Expensive default: " + expensiveDefault);
        
        // Good: Use orElseThrow for required values
        try {
            String required = Optional.<String>empty()
                    .orElseThrow(() -> new IllegalArgumentException("Value is required"));
        } catch (IllegalArgumentException e) {
            System.out.println("Required value exception: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    // Helper classes for examples
    static class User {
        private String name;
        private String email;
        private Profile profile;
        
        public User(String name, String email, Profile profile) {
            this.name = name;
            this.email = email;
            this.profile = profile;
        }
        
        public String getName() { return name; }
        public String getEmail() { return email; }
        public Profile getProfile() { return profile; }
    }
    
    static class Profile {
        private String displayName;
        
        public Profile(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() { return displayName; }
    }
    
    static class UserService {
        private Map<Integer, User> users = new HashMap<>();
        
        public UserService() {
            users.put(1, new User("Alice", "alice@example.com", new Profile("Alice P.")));
            users.put(2, new User("Bob", "bob@example.com", new Profile("Bob S.")));
        }
        
        public Optional<User> findById(int id) {
            return Optional.ofNullable(users.get(id));
        }
    }
    
    static class ConfigService {
        private Map<String, String> config = new HashMap<>();
        
        public ConfigService() {
            config.put("database.url", "jdbc:mysql://localhost:3306/mydb");
            config.put("cache.enabled", "true");
        }
        
        public Optional<String> getConfig(String key) {
            return Optional.ofNullable(config.get(key));
        }
    }
    
    static class ApiResponse {
        private Data data;
        
        public Optional<Data> getData() {
            return Optional.ofNullable(data);
        }
    }
    
    static class Data {
        private String message;
        
        public Data(String message) {
            this.message = message;
        }
        
        public String getMessage() { return message; }
    }
}
