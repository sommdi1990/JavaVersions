package com.example.features;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates Java 21 Sequenced Collections with comprehensive examples
 * Shows new interfaces and methods for ordered collections
 */
public class SequencedCollectionsDemo {
    
    public static void main(String[] args) {
        SequencedCollectionsDemo demo = new SequencedCollectionsDemo();
        
        System.out.println("=== Java 21 Sequenced Collections Demo ===\n");
        
        demo.basicSequencedOperations();
        demo.sequencedSetDemo();
        demo.sequencedMapDemo();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic sequenced collection operations
     */
    public void basicSequencedOperations() {
        System.out.println("1. Basic Sequenced Operations:");
        
        // SequencedCollection operations
        SequencedCollection<String> sequencedList = new ArrayList<>();
        sequencedList.add("First");
        sequencedList.add("Second");
        sequencedList.add("Third");
        
        System.out.println("Original list: " + sequencedList);
        
        // Add to beginning and end
        sequencedList.addFirst("Zero");
        sequencedList.addLast("Fourth");
        System.out.println("After addFirst/addLast: " + sequencedList);
        
        // Get first and last elements
        System.out.println("First element: " + sequencedList.getFirst());
        System.out.println("Last element: " + sequencedList.getLast());
        
        // Remove first and last elements
        String removedFirst = sequencedList.removeFirst();
        String removedLast = sequencedList.removeLast();
        System.out.println("Removed first: " + removedFirst);
        System.out.println("Removed last: " + removedLast);
        System.out.println("After removals: " + sequencedList);
        
        // Reverse the collection
        SequencedCollection<String> reversed = sequencedList.reversed();
        System.out.println("Reversed: " + reversed);
        
        System.out.println();
    }
    
    /**
     * Demonstrates SequencedSet operations
     */
    public void sequencedSetDemo() {
        System.out.println("2. SequencedSet Operations:");
        
        // LinkedHashSet implements SequencedSet
        SequencedSet<String> sequencedSet = new LinkedHashSet<>();
        sequencedSet.add("Apple");
        sequencedSet.add("Banana");
        sequencedSet.add("Cherry");
        sequencedSet.add("Date");
        
        System.out.println("Original set: " + sequencedSet);
        
        // Add to beginning and end
        sequencedSet.addFirst("Apricot");
        sequencedSet.addLast("Elderberry");
        System.out.println("After addFirst/addLast: " + sequencedSet);
        
        // Get first and last elements
        System.out.println("First element: " + sequencedSet.getFirst());
        System.out.println("Last element: " + sequencedSet.getLast());
        
        // Remove first and last elements
        String removedFirst = sequencedSet.removeFirst();
        String removedLast = sequencedSet.removeLast();
        System.out.println("Removed first: " + removedFirst);
        System.out.println("Removed last: " + removedLast);
        System.out.println("After removals: " + sequencedSet);
        
        // Reverse the set
        SequencedSet<String> reversed = sequencedSet.reversed();
        System.out.println("Reversed: " + reversed);
        
        // Test with duplicate elements
        sequencedSet.add("Apple"); // This won't add because it's a set
        System.out.println("After adding duplicate: " + sequencedSet);
        
        System.out.println();
    }
    
    /**
     * Demonstrates SequencedMap operations
     */
    public void sequencedMapDemo() {
        System.out.println("3. SequencedMap Operations:");
        
        // LinkedHashMap implements SequencedMap
        SequencedMap<String, Integer> sequencedMap = new LinkedHashMap<>();
        sequencedMap.put("First", 1);
        sequencedMap.put("Second", 2);
        sequencedMap.put("Third", 3);
        
        System.out.println("Original map: " + sequencedMap);
        
        // Add to beginning and end
        sequencedMap.putFirst("Zero", 0);
        sequencedMap.putLast("Fourth", 4);
        System.out.println("After putFirst/putLast: " + sequencedMap);
        
        // Get first and last entries
        Map.Entry<String, Integer> firstEntry = sequencedMap.firstEntry();
        Map.Entry<String, Integer> lastEntry = sequencedMap.lastEntry();
        System.out.println("First entry: " + firstEntry);
        System.out.println("Last entry: " + lastEntry);
        
        // Get first and last keys/values
        System.out.println("First key: " + sequencedMap.firstKey());
        System.out.println("Last key: " + sequencedMap.lastKey());
        System.out.println("First value: " + sequencedMap.getFirst());
        System.out.println("Last value: " + sequencedMap.getLast());
        
        // Remove first and last entries
        Map.Entry<String, Integer> removedFirst = sequencedMap.pollFirstEntry();
        Map.Entry<String, Integer> removedLast = sequencedMap.pollLastEntry();
        System.out.println("Removed first: " + removedFirst);
        System.out.println("Removed last: " + removedLast);
        System.out.println("After removals: " + sequencedMap);
        
        // Reverse the map
        SequencedMap<String, Integer> reversed = sequencedMap.reversed();
        System.out.println("Reversed: " + reversed);
        
        // Test with duplicate keys
        sequencedMap.put("Second", 22); // This will update the existing value
        System.out.println("After updating Second: " + sequencedMap);
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing different collection types
     */
    public void performanceBenchmark() {
        System.out.println("4. Performance Benchmark:");
        
        int numberOfElements = 100000;
        
        // Test ArrayList vs LinkedList for sequenced operations
        System.out.println("Testing ArrayList vs LinkedList for sequenced operations...");
        
        // ArrayList performance
        long startTime = System.nanoTime();
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            arrayList.add("Element" + i);
        }
        // Add to beginning (expensive for ArrayList)
        arrayList.add(0, "FirstElement");
        // Add to end (cheap for ArrayList)
        arrayList.add("LastElement");
        long arrayListTime = System.nanoTime() - startTime;
        
        // LinkedList performance
        startTime = System.nanoTime();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < numberOfElements; i++) {
            linkedList.add("Element" + i);
        }
        // Add to beginning (cheap for LinkedList)
        linkedList.add(0, "FirstElement");
        // Add to end (cheap for LinkedList)
        linkedList.add("LastElement");
        long linkedListTime = System.nanoTime() - startTime;
        
        System.out.println("ArrayList time: " + (arrayListTime / 1_000_000) + " ms");
        System.out.println("LinkedList time: " + (linkedListTime / 1_000_000) + " ms");
        System.out.println("Performance ratio: " + ((double) arrayListTime / linkedListTime) + "x");
        
        // Test HashMap vs LinkedHashMap for sequenced operations
        System.out.println("\nTesting HashMap vs LinkedHashMap for sequenced operations...");
        
        // HashMap performance
        startTime = System.nanoTime();
        Map<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numberOfElements; i++) {
            hashMap.put("Key" + i, i);
        }
        long hashMapTime = System.nanoTime() - startTime;
        
        // LinkedHashMap performance
        startTime = System.nanoTime();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < numberOfElements; i++) {
            linkedHashMap.put("Key" + i, i);
        }
        long linkedHashMapTime = System.nanoTime() - startTime;
        
        System.out.println("HashMap time: " + (hashMapTime / 1_000_000) + " ms");
        System.out.println("LinkedHashMap time: " + (linkedHashMapTime / 1_000_000) + " ms");
        System.out.println("Performance ratio: " + ((double) linkedHashMapTime / hashMapTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("5. Real-world Examples:");
        
        // Task queue with priority
        System.out.println("Task queue with priority:");
        SequencedCollection<Task> taskQueue = new ArrayList<>();
        taskQueue.add(new Task("Low", "Task 1"));
        taskQueue.add(new Task("Medium", "Task 2"));
        taskQueue.add(new Task("High", "Task 3"));
        
        // Add urgent task to front
        taskQueue.addFirst(new Task("Urgent", "Critical Task"));
        System.out.println("Task queue: " + taskQueue);
        
        // Process tasks in order
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.removeFirst();
            System.out.println("Processing: " + task);
        }
        
        // Recent items tracking
        System.out.println("\nRecent items tracking:");
        SequencedSet<String> recentItems = new LinkedHashSet<>();
        String[] items = {"Item1", "Item2", "Item3", "Item1", "Item4", "Item2", "Item5"};
        
        for (String item : items) {
            // Remove if already exists to move to end
            recentItems.remove(item);
            recentItems.addLast(item);
            
            // Keep only last 3 items
            while (recentItems.size() > 3) {
                recentItems.removeFirst();
            }
            
            System.out.println("Recent items: " + recentItems);
        }
        
        // Configuration with order preservation
        System.out.println("\nConfiguration with order preservation:");
        SequencedMap<String, String> config = new LinkedHashMap<>();
        config.put("database.host", "localhost");
        config.put("database.port", "3306");
        config.put("cache.enabled", "true");
        config.put("cache.ttl", "3600");
        
        // Add new configuration at the end
        config.putLast("logging.level", "INFO");
        
        // Update existing configuration (maintains position)
        config.put("cache.ttl", "7200");
        
        System.out.println("Configuration order: " + config.keySet());
        System.out.println("First config: " + config.firstEntry());
        System.out.println("Last config: " + config.lastEntry());
        
        // History tracking
        System.out.println("\nHistory tracking:");
        SequencedCollection<String> history = new ArrayList<>();
        String[] actions = {"login", "view_profile", "edit_settings", "logout", "login", "view_dashboard"};
        
        for (String action : actions) {
            history.addLast(action);
            
            // Keep only last 5 actions
            while (history.size() > 5) {
                history.removeFirst();
            }
            
            System.out.println("Action history: " + history);
        }
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("6. Best Practices:");
        
        // Good: Use SequencedCollection for order-sensitive operations
        System.out.println("Good: Use SequencedCollection for order-sensitive operations");
        
        // Good: Use LinkedHashSet for ordered unique elements
        SequencedSet<String> orderedSet = new LinkedHashSet<>();
        orderedSet.add("First");
        orderedSet.add("Second");
        orderedSet.add("Third");
        System.out.println("Good: LinkedHashSet maintains insertion order - " + orderedSet);
        
        // Good: Use LinkedHashMap for ordered key-value pairs
        SequencedMap<String, Integer> orderedMap = new LinkedHashMap<>();
        orderedMap.put("First", 1);
        orderedMap.put("Second", 2);
        orderedMap.put("Third", 3);
        System.out.println("Good: LinkedHashMap maintains insertion order - " + orderedMap);
        
        // Good: Use reversed() for reverse iteration
        SequencedCollection<String> reversed = orderedSet.reversed();
        System.out.println("Good: Use reversed() for reverse iteration - " + reversed);
        
        // Good: Use first/last methods for queue-like operations
        System.out.println("Good: Use getFirst()/getLast() for queue-like operations");
        System.out.println("First element: " + orderedSet.getFirst());
        System.out.println("Last element: " + orderedSet.getLast());
        
        // Good: Use addFirst/addLast for priority operations
        orderedSet.addFirst("Priority");
        System.out.println("Good: Use addFirst() for priority - " + orderedSet);
        
        System.out.println();
    }
    
    // Helper class for examples
    record Task(String priority, String description) {
        @Override
        public String toString() {
            return priority + ": " + description;
        }
    }
}
