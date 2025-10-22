package com.example.features;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates Java 21 Virtual Threads with comprehensive examples
 * Shows performance benefits and real-world use cases
 */
public class VirtualThreadsDemo {
    
    public static void main(String[] args) {
        VirtualThreadsDemo demo = new VirtualThreadsDemo();
        
        System.out.println("=== Java 21 Virtual Threads Demo ===\n");
        
        demo.basicVirtualThreads();
        demo.virtualThreadsVsPlatformThreads();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic virtual threads examples
     */
    public void basicVirtualThreads() {
        System.out.println("1. Basic Virtual Threads:");
        
        // Create virtual threads
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Virtual thread: " + Thread.currentThread());
            System.out.println("Is virtual: " + Thread.currentThread().isVirtual());
        });
        
        // Create platform thread for comparison
        Thread platformThread = Thread.ofPlatform().start(() -> {
            System.out.println("Platform thread: " + Thread.currentThread());
            System.out.println("Is virtual: " + Thread.currentThread().isVirtual());
        });
        
        try {
            virtualThread.join();
            platformThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Create virtual thread with name
        Thread namedVirtualThread = Thread.ofVirtual()
                .name("my-virtual-thread")
                .start(() -> {
                    System.out.println("Named virtual thread: " + Thread.currentThread().getName());
                });
        
        try {
            namedVirtualThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    /**
     * Demonstrates virtual threads vs platform threads
     */
    public void virtualThreadsVsPlatformThreads() {
        System.out.println("2. Virtual Threads vs Platform Threads:");
        
        int numberOfThreads = 1000;
        
        // Test with platform threads
        System.out.println("Creating " + numberOfThreads + " platform threads...");
        long startTime = System.nanoTime();
        List<Thread> platformThreads = new ArrayList<>();
        
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = Thread.ofPlatform().start(() -> {
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            platformThreads.add(thread);
        }
        
        // Wait for all platform threads to complete
        for (Thread thread : platformThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long platformTime = System.nanoTime() - startTime;
        
        // Test with virtual threads
        System.out.println("Creating " + numberOfThreads + " virtual threads...");
        startTime = System.nanoTime();
        List<Thread> virtualThreads = new ArrayList<>();
        
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = Thread.ofVirtual().start(() -> {
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            virtualThreads.add(thread);
        }
        
        // Wait for all virtual threads to complete
        for (Thread thread : virtualThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long virtualTime = System.nanoTime() - startTime;
        
        System.out.println("Platform threads time: " + (platformTime / 1_000_000) + " ms");
        System.out.println("Virtual threads time: " + (virtualTime / 1_000_000) + " ms");
        System.out.println("Speedup: " + ((double) platformTime / virtualTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Performance benchmark for different scenarios
     */
    public void performanceBenchmark() {
        System.out.println("3. Performance Benchmark:");
        
        int numberOfTasks = 10000;
        int workDuration = 50; // milliseconds
        
        // Test with ExecutorService and platform threads
        System.out.println("Testing with platform thread executor...");
        long startTime = System.nanoTime();
        try (ExecutorService platformExecutor = Executors.newFixedThreadPool(100)) {
            List<Future<Void>> platformFutures = new ArrayList<>();
            for (int i = 0; i < numberOfTasks; i++) {
                Future<Void> future = platformExecutor.submit(() -> {
                    try {
                        Thread.sleep(workDuration);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                });
                platformFutures.add(future);
            }
            
            // Wait for all tasks to complete
            for (Future<Void> future : platformFutures) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        long platformTime = System.nanoTime() - startTime;
        
        // Test with virtual thread executor
        System.out.println("Testing with virtual thread executor...");
        startTime = System.nanoTime();
        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Void>> virtualFutures = new ArrayList<>();
            for (int i = 0; i < numberOfTasks; i++) {
                Future<Void> future = virtualExecutor.submit(() -> {
                    try {
                        Thread.sleep(workDuration);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                });
                virtualFutures.add(future);
            }
            
            // Wait for all tasks to complete
            for (Future<Void> future : virtualFutures) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        long virtualTime = System.nanoTime() - startTime;
        
        System.out.println("Platform thread executor: " + (platformTime / 1_000_000) + " ms");
        System.out.println("Virtual thread executor: " + (virtualTime / 1_000_000) + " ms");
        System.out.println("Speedup: " + ((double) platformTime / virtualTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("4. Real-world Examples:");
        
        // HTTP request simulation
        System.out.println("Simulating HTTP requests...");
        List<String> urls = Arrays.asList(
                "https://api.example.com/users",
                "https://api.example.com/products",
                "https://api.example.com/orders",
                "https://api.example.com/categories",
                "https://api.example.com/reviews"
        );
        
        // Simulate HTTP requests with virtual threads
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = urls.stream()
                    .map(url -> executor.submit(() -> simulateHttpRequest(url)))
                    .toList();
            
            for (Future<String> future : futures) {
                try {
                    String response = future.get();
                    System.out.println("Response: " + response);
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Request failed: " + e.getMessage());
                }
            }
        }
        
        // Database operations simulation
        System.out.println("\nSimulating database operations...");
        List<Integer> userIds = Arrays.asList(1, 2, 3, 4, 5);
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<User>> futures = userIds.stream()
                    .map(id -> executor.submit(() -> fetchUserFromDatabase(id)))
                    .toList();
            
            for (Future<User> future : futures) {
                try {
                    User user = future.get();
                    System.out.println("User: " + user);
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Database operation failed: " + e.getMessage());
                }
            }
        }
        
        // File processing simulation
        System.out.println("\nSimulating file processing...");
        List<String> files = Arrays.asList("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt");
        
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<String>> futures = files.stream()
                    .map(file -> executor.submit(() -> processFile(file)))
                    .toList();
            
            for (Future<String> future : futures) {
                try {
                    String result = future.get();
                    System.out.println("File processed: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("File processing failed: " + e.getMessage());
                }
            }
        }
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("5. Best Practices:");
        
        // Good: Use virtual threads for I/O-bound operations
        System.out.println("Good: Use virtual threads for I/O-bound operations");
        
        // Good: Use Executors.newVirtualThreadPerTaskExecutor()
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            System.out.println("Good: Use virtual thread executor for task execution");
        }
        
        // Good: Don't use virtual threads for CPU-intensive tasks
        System.out.println("Good: Don't use virtual threads for CPU-intensive tasks");
        
        // Good: Use structured concurrency for better error handling
        System.out.println("Good: Use structured concurrency for better error handling");
        
        // Good: Monitor virtual thread usage
        System.out.println("Good: Monitor virtual thread usage and performance");
        
        // Good: Use virtual threads with CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Virtual thread result";
        });
        
        try {
            String result = future.get();
            System.out.println("Good: CompletableFuture with virtual threads - " + result);
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println();
    }
    
    // Helper methods for examples
    private String simulateHttpRequest(String url) {
        try {
            Thread.sleep(100 + (int)(Math.random() * 100)); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Response from " + url + " (processed by " + Thread.currentThread().getName() + ")";
    }
    
    private User fetchUserFromDatabase(int id) {
        try {
            Thread.sleep(50 + (int)(Math.random() * 50)); // Simulate database delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new User(id, "User" + id, "user" + id + "@example.com");
    }
    
    private String processFile(String filename) {
        try {
            Thread.sleep(200 + (int)(Math.random() * 100)); // Simulate file processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return filename + " processed by " + Thread.currentThread().getName();
    }
    
    // Helper classes
    record User(int id, String name, String email) {}
}
