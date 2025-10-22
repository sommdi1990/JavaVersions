package com.example.features;

import java.util.*;
import java.util.stream.*;

/**
 * Demonstrates Java 8 Streams API with comprehensive examples
 * Includes performance benchmarks and real-world use cases
 */
public class StreamsApiDemo {
    
    public static void main(String[] args) {
        StreamsApiDemo demo = new StreamsApiDemo();
        
        System.out.println("=== Java 8 Streams API Demo ===\n");
        
        demo.basicStreamOperations();
        demo.intermediateOperations();
        demo.terminalOperations();
        demo.parallelStreamsDemo();
        demo.performanceBenchmark();
        demo.realWorldExamples();
    }
    
    /**
     * Basic stream operations
     */
    public void basicStreamOperations() {
        System.out.println("1. Basic Stream Operations:");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Create stream from collection
        Stream<Integer> numberStream = numbers.stream();
        System.out.println("Number stream created: " + numberStream.count());
        
        // Create stream from array
        int[] array = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(array);
        System.out.println("Array stream sum: " + intStream.sum());
        
        // Create stream from values
        Stream<String> stringStream = Stream.of("Hello", "World", "Java", "8");
        System.out.println("String stream: " + stringStream.collect(Collectors.toList()));
        
        System.out.println();
    }
    
    /**
     * Demonstrates intermediate operations (lazy evaluation)
     */
    public void intermediateOperations() {
        System.out.println("2. Intermediate Operations:");
        
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry", "fig");
        
        // Filter - keep only words longer than 4 characters
        List<String> longWords = words.stream()
                .filter(word -> word.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Long words: " + longWords);
        
        // Map - transform each word to uppercase
        List<String> upperWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase words: " + upperWords);
        
        // FlatMap - flatten nested collections
        List<List<String>> nestedLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e", "f")
        );
        List<String> flattened = nestedLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattened);
        
        // Distinct - remove duplicates
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5);
        List<Integer> uniqueNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique numbers: " + uniqueNumbers);
        
        // Sorted - sort elements
        List<String> unsorted = Arrays.asList("zebra", "apple", "banana", "cherry");
        List<String> sorted = unsorted.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted words: " + sorted);
        
        // Limit - take first N elements
        List<Integer> firstThree = IntStream.range(1, 10)
                .limit(3)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("First three: " + firstThree);
        
        // Skip - skip first N elements
        List<Integer> skipFirstThree = IntStream.range(1, 10)
                .skip(3)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Skip first three: " + skipFirstThree);
        
        System.out.println();
    }
    
    /**
     * Demonstrates terminal operations
     */
    public void terminalOperations() {
        System.out.println("3. Terminal Operations:");
        
        List<Person> people = Arrays.asList(
                new Person("Alice", 25, "Engineer"),
                new Person("Bob", 30, "Manager"),
                new Person("Charlie", 35, "Engineer"),
                new Person("David", 28, "Designer"),
                new Person("Eve", 32, "Manager")
        );
        
        // Collect - collect results
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("Names: " + names);
        
        // Collect to Set
        Set<String> uniqueJobs = people.stream()
                .map(Person::getJob)
                .collect(Collectors.toSet());
        System.out.println("Unique jobs: " + uniqueJobs);
        
        // Collect to Map
        Map<String, Integer> nameToAge = people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("Name to age map: " + nameToAge);
        
        // Group by
        Map<String, List<Person>> peopleByJob = people.stream()
                .collect(Collectors.groupingBy(Person::getJob));
        System.out.println("People by job: " + peopleByJob);
        
        // Reduce - combine elements
        int totalAge = people.stream()
                .mapToInt(Person::getAge)
                .reduce(0, Integer::sum);
        System.out.println("Total age: " + totalAge);
        
        // Average
        double averageAge = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("Average age: " + averageAge);
        
        // Count
        long engineerCount = people.stream()
                .filter(person -> "Engineer".equals(person.getJob()))
                .count();
        System.out.println("Engineer count: " + engineerCount);
        
        // Find first
        Optional<Person> firstEngineer = people.stream()
                .filter(person -> "Engineer".equals(person.getJob()))
                .findFirst();
        System.out.println("First engineer: " + firstEngineer.map(Person::getName).orElse("None"));
        
        // Any match
        boolean hasManager = people.stream()
                .anyMatch(person -> "Manager".equals(person.getJob()));
        System.out.println("Has manager: " + hasManager);
        
        // All match
        boolean allAdults = people.stream()
                .allMatch(person -> person.getAge() >= 18);
        System.out.println("All adults: " + allAdults);
        
        System.out.println();
    }
    
    /**
     * Demonstrates parallel streams
     */
    public void parallelStreamsDemo() {
        System.out.println("4. Parallel Streams:");
        
        List<Integer> numbers = IntStream.range(1, 1000000)
                .boxed()
                .collect(Collectors.toList());
        
        // Sequential processing
        long startTime = System.nanoTime();
        long sequentialSum = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sequentialTime = System.nanoTime() - startTime;
        
        // Parallel processing
        startTime = System.nanoTime();
        long parallelSum = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parallelTime = System.nanoTime() - startTime;
        
        System.out.println("Sequential sum: " + sequentialSum + " (Time: " + (sequentialTime / 1_000_000) + " ms)");
        System.out.println("Parallel sum: " + parallelSum + " (Time: " + (parallelTime / 1_000_000) + " ms)");
        System.out.println("Speedup: " + ((double) sequentialTime / parallelTime) + "x");
        
        System.out.println();
    }
    
    /**
     * Performance benchmark for different operations
     */
    public void performanceBenchmark() {
        System.out.println("5. Performance Benchmark:");
        
        List<Integer> numbers = IntStream.range(1, 100000)
                .boxed()
                .collect(Collectors.toList());
        
        // Traditional loop vs Stream
        long startTime = System.nanoTime();
        int traditionalSum = 0;
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                traditionalSum += number * 2;
            }
        }
        long traditionalTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int streamSum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * 2)
                .sum();
        long streamTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        int parallelStreamSum = numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * 2)
                .sum();
        long parallelStreamTime = System.nanoTime() - startTime;
        
        System.out.println("Traditional loop: " + traditionalSum + " (Time: " + (traditionalTime / 1_000_000) + " ms)");
        System.out.println("Stream: " + streamSum + " (Time: " + (streamTime / 1_000_000) + " ms)");
        System.out.println("Parallel Stream: " + parallelStreamSum + " (Time: " + (parallelStreamTime / 1_000_000) + " ms)");
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("6. Real-world Examples:");
        
        // Data processing pipeline
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T1", 100.0, "USD"),
                new Transaction("T2", 200.0, "EUR"),
                new Transaction("T3", 150.0, "USD"),
                new Transaction("T4", 300.0, "GBP"),
                new Transaction("T5", 250.0, "USD")
        );
        
        // Calculate total USD transactions
        double totalUSD = transactions.stream()
                .filter(t -> "USD".equals(t.getCurrency()))
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total USD transactions: " + totalUSD);
        
        // Group transactions by currency
        Map<String, List<Transaction>> byCurrency = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println("Transactions by currency: " + byCurrency);
        
        // Find highest transaction
        Optional<Transaction> highest = transactions.stream()
                .max(Comparator.comparing(Transaction::getAmount));
        System.out.println("Highest transaction: " + highest.map(Transaction::toString).orElse("None"));
        
        // Calculate average by currency
        Map<String, Double> averageByCurrency = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getCurrency,
                        Collectors.averagingDouble(Transaction::getAmount)
                ));
        System.out.println("Average by currency: " + averageByCurrency);
        
        System.out.println();
    }
    
    // Helper classes for examples
    static class Person {
        private String name;
        private int age;
        private String job;
        
        public Person(String name, int age, String job) {
            this.name = name;
            this.age = age;
            this.job = job;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getJob() { return job; }
        
        @Override
        public String toString() {
            return name + "(" + age + ", " + job + ")";
        }
    }
    
    static class Transaction {
        private String id;
        private double amount;
        private String currency;
        
        public Transaction(String id, double amount, String currency) {
            this.id = id;
            this.amount = amount;
            this.currency = currency;
        }
        
        public String getId() { return id; }
        public double getAmount() { return amount; }
        public String getCurrency() { return currency; }
        
        @Override
        public String toString() {
            return id + ": " + amount + " " + currency;
        }
    }
}
