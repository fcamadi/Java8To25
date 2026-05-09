package org.francd.java12;

import java.util.*;
import java.util.stream.*;

public class TeeingCollector {

    public static void main(String[] args) {
        basicTeeing();
        minMaxExample();
        statisticsExample();
        multipleAggregations();
    }

    private static void basicTeeing() {
        System.out.println("=== Teeing Collector (Java 12) ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Teeing merges results of two collectors
        // collect(teeing(collector1, collector2, merger))

        String result = numbers.stream()
            .collect(Collectors.teeing(
                Collectors.summingInt(Integer::intValue),  // First collector
                Collectors.counting(),                     // Second collector
                (sum, count) -> "Sum: " + sum + ", Count: " + count  // Merger
            ));
        System.out.println("Teeing result: " + result);

        // Find min and max together - simpler version
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        String min = words.stream().min(Comparator.naturalOrder()).orElse("");
        String max = words.stream().max(Comparator.naturalOrder()).orElse("");
        System.out.println("Min: " + min + ", Max: " + max);
    }

    private static void minMaxExample() {
        System.out.println("\n=== Min/Max with Teeing ===");

        List<Integer> temps = Arrays.asList(20, 25, 18, 22, 30, 15, 28);

        String tempStats = temps.stream()
            .collect(Collectors.teeing(
                Collectors.minBy(Integer::compareTo),
                Collectors.maxBy(Integer::compareTo),
                (min, max) -> "Lowest: " + min.orElse(0) + "°C, Highest: " + max.orElse(0) + "°C"
            ));
        System.out.println("Temperature: " + tempStats);

        // With String
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");

        String nameStats = names.stream()
            .collect(Collectors.teeing(
                Collectors.minBy(Comparator.comparingInt(String::length)),
                Collectors.maxBy(Comparator.comparingInt(String::length)),
                (shortest, longest) -> "Shortest: " + shortest.orElse("") +
                    " (" + shortest.map(String::length).orElse(0) + "), " +
                    "Longest: " + longest.orElse("") +
                    " (" + longest.map(String::length).orElse(0) + ")"
            ));
        System.out.println("Name stats: " + nameStats);
    }

    private static void statisticsExample() {
        System.out.println("\n=== Statistics with Teeing ===");

        List<Double> prices = Arrays.asList(19.99, 29.99, 9.99, 49.99, 14.99, 39.99);

        // Calculate average and total
        String stats = prices.stream()
            .collect(Collectors.teeing(
                Collectors.summingDouble(Double::doubleValue),
                Collectors.averagingDouble(Double::doubleValue),
                (total, avg) -> "Total: $" + String.format("%.2f", total) +
                    ", Average: $" + String.format("%.2f", avg)
            ));
        System.out.println("Price stats: " + stats);

        // More complex: group by and collect
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.00),
            new Product("Phone", 800.00),
            new Product("Laptop", 1500.00),
            new Product("Tablet", 500.00),
            new Product("Phone", 900.00)
        );

        Map<String, String> categoryStats = products.stream()
            .collect(Collectors.groupingBy(
                Product::getName,
                Collectors.teeing(
                    Collectors.summingDouble(Product::getPrice),
                    Collectors.counting(),
                    (sum, count) -> "Sum: " + sum + ", Count: " + count
                )
            ));
        System.out.println("Category stats: " + categoryStats);
    }

    private static void multipleAggregations() {
        System.out.println("\n=== Multiple Aggregations ===");

        // Collect to a pair/object
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        String evenOdd = numbers.stream()
            .collect(Collectors.teeing(
                Collectors.partitioningBy(n -> n % 2 == 0),
                Collectors.partitioningBy(n -> n % 3 == 0),
                (evens, threes) -> "Evens: " + evens.size() + ", Divisible by 3: " + threes.size()
            ));
        System.out.println("Partition stats: " + evenOdd);

        // Combining with grouping
        List<String> words = Arrays.asList("hello", "world", "java", "stream", "collect");

        String wordStats = words.stream()
            .collect(Collectors.teeing(
                Collectors.filtering(s -> s.length() > 4, Collectors.toList()),
                Collectors.filtering(s -> s.length() <= 4, Collectors.toList()),
                (longWords, shortWords) -> "Long (>4): " + longWords + ", Short (≤4): " + shortWords
            ));
        System.out.println("Word stats: " + wordStats);
    }

    static class Product {
        private final String name;
        private final double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        String getName() { return name; }
        double getPrice() { return price; }
    }
}