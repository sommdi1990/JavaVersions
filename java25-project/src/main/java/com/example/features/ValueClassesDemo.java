package com.example.features;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates Java 25 Value Classes with comprehensive examples
 * Shows how to create immutable data carriers with value semantics
 */
public class ValueClassesDemo {
    
    public static void main(String[] args) {
        ValueClassesDemo demo = new ValueClassesDemo();
        
        System.out.println("=== Java 25 Value Classes Demo ===\n");
        
        demo.basicValueClasses();
        demo.valueClassFeatures();
        demo.performanceBenchmark();
        demo.realWorldExamples();
        demo.bestPractices();
    }
    
    /**
     * Basic value class examples
     */
    public void basicValueClasses() {
        System.out.println("1. Basic Value Classes:");
        
        // Create value class instances
        Point point1 = new Point(10, 20);
        Point point2 = new Point(10, 20);
        Point point3 = new Point(30, 40);
        
        System.out.println("Point 1: " + point1);
        System.out.println("Point 2: " + point2);
        System.out.println("Point 3: " + point3);
        
        // Value equality
        System.out.println("Point 1 equals Point 2: " + point1.equals(point2));
        System.out.println("Point 1 equals Point 3: " + point1.equals(point3));
        
        // Hash code
        System.out.println("Point 1 hash code: " + point1.hashCode());
        System.out.println("Point 2 hash code: " + point2.hashCode());
        System.out.println("Hash codes equal: " + (point1.hashCode() == point2.hashCode()));
        
        // String representation
        System.out.println("Point 1 toString: " + point1.toString());
        
        // Immutability demonstration
        System.out.println("Point 1 before: " + point1);
        // point1.x = 100; // This would cause compilation error
        System.out.println("Point 1 after (unchanged): " + point1);
        
        System.out.println();
    }
    
    /**
     * Demonstrates value class features
     */
    public void valueClassFeatures() {
        System.out.println("2. Value Class Features:");
        
        // Value classes with methods
        Money money1 = new Money(100, "USD");
        Money money2 = new Money(200, "USD");
        Money money3 = new Money(100, "EUR");
        
        System.out.println("Money 1: " + money1);
        System.out.println("Money 2: " + money2);
        System.out.println("Money 3: " + money3);
        
        // Method calls
        System.out.println("Money 1 formatted: " + money1.format());
        System.out.println("Money 1 + Money 2: " + money1.add(money2));
        System.out.println("Money 1 equals Money 2: " + money1.equals(money2));
        System.out.println("Money 1 equals Money 3: " + money1.equals(money3));
        
        // Value classes with validation
        try {
            Money validMoney = new Money(50, "USD");
            System.out.println("Valid money: " + validMoney);
            
            Money invalidMoney = new Money(-10, "USD");
            System.out.println("Invalid money: " + invalidMoney);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        }
        
        // Value classes with complex data
        Person person1 = new Person("Alice", 25, "alice@example.com");
        Person person2 = new Person("Bob", 30, "bob@example.com");
        
        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);
        System.out.println("Person 1 age group: " + person1.getAgeGroup());
        System.out.println("Person 1 is adult: " + person1.isAdult());
        
        // Value classes with collections
        List<Point> points = Arrays.asList(
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6)
        );
        
        System.out.println("Points: " + points);
        System.out.println("Total distance: " + calculateTotalDistance(points));
        
        System.out.println();
    }
    
    /**
     * Performance benchmark comparing value classes vs traditional classes
     */
    public void performanceBenchmark() {
        System.out.println("3. Performance Benchmark:");
        
        int numberOfObjects = 100000;
        
        // Test with value classes
        long startTime = System.nanoTime();
        List<Point> valuePoints = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            valuePoints.add(new Point(i, i * 2));
        }
        
        // Test equality operations
        int equalityCount = 0;
        for (int i = 0; i < valuePoints.size() - 1; i++) {
            if (valuePoints.get(i).equals(valuePoints.get(i + 1))) {
                equalityCount++;
            }
        }
        
        // Test hash code operations
        int hashSum = 0;
        for (Point point : valuePoints) {
            hashSum += point.hashCode();
        }
        
        long valueClassTime = System.nanoTime() - startTime;
        
        // Test with traditional classes
        startTime = System.nanoTime();
        List<TraditionalPoint> traditionalPoints = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            traditionalPoints.add(new TraditionalPoint(i, i * 2));
        }
        
        // Test equality operations
        int traditionalEqualityCount = 0;
        for (int i = 0; i < traditionalPoints.size() - 1; i++) {
            if (traditionalPoints.get(i).equals(traditionalPoints.get(i + 1))) {
                traditionalEqualityCount++;
            }
        }
        
        // Test hash code operations
        int traditionalHashSum = 0;
        for (TraditionalPoint point : traditionalPoints) {
            traditionalHashSum += point.hashCode();
        }
        
        long traditionalTime = System.nanoTime() - startTime;
        
        System.out.println("Value classes time: " + (valueClassTime / 1_000_000) + " ms");
        System.out.println("Traditional classes time: " + (traditionalTime / 1_000_000) + " ms");
        System.out.println("Performance ratio: " + ((double) valueClassTime / traditionalTime) + "x");
        System.out.println("Equality count: " + equalityCount + " vs " + traditionalEqualityCount);
        System.out.println("Hash sum: " + hashSum + " vs " + traditionalHashSum);
        
        System.out.println();
    }
    
    /**
     * Real-world practical examples
     */
    public void realWorldExamples() {
        System.out.println("4. Real-world Examples:");
        
        // Financial calculations
        Money usd100 = new Money(100, "USD");
        Money usd200 = new Money(200, "USD");
        Money eur100 = new Money(100, "EUR");
        
        System.out.println("USD 100 + USD 200 = " + usd100.add(usd200));
        System.out.println("USD 100 equals EUR 100: " + usd100.equals(eur100));
        
        // Geographic coordinates
        Coordinate nyc = new Coordinate(40.7128, -74.0060);
        Coordinate london = new Coordinate(51.5074, -0.1278);
        
        System.out.println("NYC: " + nyc);
        System.out.println("London: " + london);
        System.out.println("Distance NYC to London: " + nyc.distanceTo(london) + " km");
        
        // Color representation
        Color red = new Color(255, 0, 0);
        Color green = new Color(0, 255, 0);
        Color blue = new Color(0, 0, 255);
        
        System.out.println("Red: " + red);
        System.out.println("Green: " + green);
        System.out.println("Blue: " + blue);
        System.out.println("Red + Green = " + red.mix(green));
        
        // Time representation
        Time time1 = new Time(14, 30, 0);
        Time time2 = new Time(16, 45, 30);
        
        System.out.println("Time 1: " + time1);
        System.out.println("Time 2: " + time2);
        System.out.println("Time difference: " + time1.difference(time2) + " seconds");
        
        // Product information
        Product product1 = new Product("Laptop", 999.99, "USD");
        Product product2 = new Product("Mouse", 29.99, "USD");
        
        System.out.println("Product 1: " + product1);
        System.out.println("Product 2: " + product2);
        System.out.println("Product 1 formatted: " + product1.format());
        
        System.out.println();
    }
    
    /**
     * Best practices and common patterns
     */
    public void bestPractices() {
        System.out.println("5. Best Practices:");
        
        // Good: Use value classes for immutable data
        System.out.println("Good: Use value classes for immutable data");
        
        // Good: Implement proper equals and hashCode
        Point point1 = new Point(10, 20);
        Point point2 = new Point(10, 20);
        System.out.println("Good: Proper equals implementation - " + point1.equals(point2));
        
        // Good: Use value classes in collections
        Set<Point> pointSet = new HashSet<>();
        pointSet.add(point1);
        pointSet.add(point2);
        System.out.println("Good: Value classes in collections - " + pointSet.size());
        
        // Good: Use value classes for method parameters
        System.out.println("Good: Use value classes for method parameters");
        double distance = calculateDistance(point1, point2);
        System.out.println("Distance: " + distance);
        
        // Good: Use value classes for return values
        System.out.println("Good: Use value classes for return values");
        Point midpoint = calculateMidpoint(point1, point2);
        System.out.println("Midpoint: " + midpoint);
        
        // Good: Use value classes with validation
        try {
            Money validMoney = new Money(100, "USD");
            System.out.println("Good: Validation in value classes - " + validMoney);
        } catch (IllegalArgumentException e) {
            System.out.println("Good: Validation error handling - " + e.getMessage());
        }
        
        System.out.println();
    }
    
    // Helper methods
    private double calculateTotalDistance(List<Point> points) {
        double total = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            total += points.get(i).distanceTo(points.get(i + 1));
        }
        return total;
    }
    
    private double calculateDistance(Point p1, Point p2) {
        return p1.distanceTo(p2);
    }
    
    private Point calculateMidpoint(Point p1, Point p2) {
        return new Point((p1.x() + p2.x()) / 2, (p1.y() + p2.y()) / 2);
    }
    
    // Value class definitions
    public value class Point {
        public final double x, y;
        
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public double distanceTo(Point other) {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }
    
    public value class Money {
        public final double amount;
        public final String currency;
        
        public Money(double amount, String currency) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative");
            }
            if (currency == null || currency.trim().isEmpty()) {
                throw new IllegalArgumentException("Currency cannot be null or empty");
            }
            this.amount = amount;
            this.currency = currency;
        }
        
        public Money add(Money other) {
            if (!this.currency.equals(other.currency)) {
                throw new IllegalArgumentException("Cannot add different currencies");
            }
            return new Money(this.amount + other.amount, this.currency);
        }
        
        public String format() {
            return String.format("%.2f %s", amount, currency);
        }
    }
    
    public value class Person {
        public final String name;
        public final int age;
        public final String email;
        
        public Person(String name, int age, String email) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }
            this.name = name;
            this.age = age;
            this.email = email;
        }
        
        public String getAgeGroup() {
            if (age < 18) return "Minor";
            if (age < 65) return "Adult";
            return "Senior";
        }
        
        public boolean isAdult() {
            return age >= 18;
        }
    }
    
    public value class Coordinate {
        public final double latitude;
        public final double longitude;
        
        public Coordinate(double latitude, double longitude) {
            if (latitude < -90 || latitude > 90) {
                throw new IllegalArgumentException("Latitude must be between -90 and 90");
            }
            if (longitude < -180 || longitude > 180) {
                throw new IllegalArgumentException("Longitude must be between -180 and 180");
            }
            this.latitude = latitude;
            this.longitude = longitude;
        }
        
        public double distanceTo(Coordinate other) {
            // Haversine formula for calculating distance between two points
            double earthRadius = 6371; // Earth's radius in kilometers
            double lat1Rad = Math.toRadians(this.latitude);
            double lat2Rad = Math.toRadians(other.latitude);
            double deltaLatRad = Math.toRadians(other.latitude - this.latitude);
            double deltaLonRad = Math.toRadians(other.longitude - this.longitude);
            
            double a = Math.sin(deltaLatRad / 2) * Math.sin(deltaLatRad / 2) +
                    Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                    Math.sin(deltaLonRad / 2) * Math.sin(deltaLonRad / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            
            return earthRadius * c;
        }
    }
    
    public value class Color {
        public final int red;
        public final int green;
        public final int blue;
        
        public Color(int red, int green, int blue) {
            if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
                throw new IllegalArgumentException("RGB values must be between 0 and 255");
            }
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
        
        public Color mix(Color other) {
            return new Color(
                    (this.red + other.red) / 2,
                    (this.green + other.green) / 2,
                    (this.blue + other.blue) / 2
            );
        }
    }
    
    public value class Time {
        public final int hours;
        public final int minutes;
        public final int seconds;
        
        public Time(int hours, int minutes, int seconds) {
            if (hours < 0 || hours > 23) {
                throw new IllegalArgumentException("Hours must be between 0 and 23");
            }
            if (minutes < 0 || minutes > 59) {
                throw new IllegalArgumentException("Minutes must be between 0 and 59");
            }
            if (seconds < 0 || seconds > 59) {
                throw new IllegalArgumentException("Seconds must be between 0 and 59");
            }
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }
        
        public int difference(Time other) {
            int thisTotalSeconds = this.hours * 3600 + this.minutes * 60 + this.seconds;
            int otherTotalSeconds = other.hours * 3600 + other.minutes * 60 + other.seconds;
            return Math.abs(thisTotalSeconds - otherTotalSeconds);
        }
    }
    
    public value class Product {
        public final String name;
        public final double price;
        public final String currency;
        
        public Product(String name, double price, String currency) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be null or empty");
            }
            if (price < 0) {
                throw new IllegalArgumentException("Price cannot be negative");
            }
            if (currency == null || currency.trim().isEmpty()) {
                throw new IllegalArgumentException("Currency cannot be null or empty");
            }
            this.name = name;
            this.price = price;
            this.currency = currency;
        }
        
        public String format() {
            return String.format("%s: %.2f %s", name, price, currency);
        }
    }
    
    // Traditional class for comparison
    public static class TraditionalPoint {
        private final double x, y;
        
        public TraditionalPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        public double getX() { return x; }
        public double getY() { return y; }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            TraditionalPoint that = (TraditionalPoint) obj;
            return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public String toString() {
            return "TraditionalPoint{x=" + x + ", y=" + y + "}";
        }
    }
}
