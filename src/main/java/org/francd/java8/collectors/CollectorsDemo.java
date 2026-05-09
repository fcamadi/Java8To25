package org.francd.java8.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo {

    private static final List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("Shirt", "Clothing", 50),
            new Product("Pants", "Clothing", 80),
            new Product("Table", "Furniture", 500)
    );

    public static void main(String[] args) {
        basicCollectors();
        groupingAndPartitioning();
        aggregationCollectors();
        downstreamCollectors();
    }

    private static void basicCollectors() {
        System.out.println("=== Basic Collectors ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");

        List<String> toList = names.stream()
            .filter(s -> s.length() > 3)
            .toList();
        System.out.println("ToList: " + toList);

        Set<String> toSet = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toSet());
        System.out.println("ToSet: " + toSet);

        String joined = names.stream()
            .collect(Collectors.joining(", "));
        System.out.println("Joining: " + joined);

        String withPrefix = names.stream()
            .collect(Collectors.joining(" | ", "[", "]"));
        System.out.println("Joining with delimiters: " + withPrefix);

        Map<String, Integer> toMap = names.stream()
            .collect(Collectors.toMap(s -> s, String::length));
        System.out.println("ToMap: " + toMap);
    }

    private static void groupingAndPartitioning() {
        System.out.println("\n=== Grouping and Partitioning ===");

        Map<String, List<Product>> byCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        System.out.println("GroupingBy:");
        byCategory.forEach((k, v) -> System.out.println("  " + k + ": " + v));

        Map<String, Long> countByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println("Counting: " + countByCategory);

        Map<String, Double> sumByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.summingDouble(Product::getPrice)
            ));
        System.out.println("Sum by category: " + sumByCategory);

        Map<Boolean, List<Product>> partitioned = products.stream()
            .collect(Collectors.partitioningBy(p -> p.getPrice() > 500));
        System.out.println("Partitioning (price > 500):");
        partitioned.forEach((k, v) -> System.out.println("  " + k + ": " + v));

        Map<String, Map<String, List<Product>>> nested = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.groupingBy(p -> p.getPrice() > 300 ? "Expensive" : "Affordable")
            ));
        System.out.println("Nested grouping: " + nested);
    }

    private static void aggregationCollectors() {
        System.out.println("\n=== Aggregation Collectors ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //Typical AI garbage:
        //Long count = numbers.stream().collect(Collectors.counting());
        long count = numbers.size();
        System.out.println("Counting: " + count);

        //Optional<Integer> min = numbers.stream().collect(Collectors.minBy(Integer::compare));
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        System.out.println("Min: " + min.orElse(0));

        //Optional<Integer> max = numbers.stream().collect(Collectors.maxBy(Integer::compare));
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        System.out.println("Max: " + max.orElse(0));

        Double average = numbers.stream().collect(Collectors.averagingDouble(n -> n));
        System.out.println("Average: " + average);

        IntSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingInt(n -> n));
        System.out.println("Summarizing: " + stats);

        //Optional<Integer> reduced = numbers.stream().collect(Collectors.reducing(Integer::sum));
        Optional<Integer> reduced = numbers.stream().reduce(Integer::sum);
        System.out.println("Reducing: " + reduced.orElse(0));

        //Integer summed = numbers.stream().collect(Collectors.reducing(0, n -> n, Integer::sum));
        Integer summed = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Reducing with identity: " + summed);
    }

    private static void downstreamCollectors() {
        System.out.println("\n=== Downstream Collectors ===");

        //Set<String> categories = products.stream().collect(Collectors.mapping(Product::getCategory, Collectors.toSet()));
        Set<String> categories = products.stream().map(Product::getCategory).collect(Collectors.toSet());
        System.out.println("Mapping to Set: " + categories);

        Set<String> categorySet = products.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.mapping(Product::getCategory, Collectors.toSet()),
                Set::copyOf
            ));
        System.out.println("CollectingAndThen: " + categorySet);

        double totalValue = products.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.summingDouble(Product::getPrice),
                total -> total * 1.21
            ));
        System.out.println("Total with tax: " + totalValue);

        Map<String, Double> cheapestByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.minBy(Comparator.comparing(Product::getPrice))
            ))
            .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().map(Product::getPrice).orElse(0.0)
            ));
        System.out.println("Cheapest by category: " + cheapestByCategory);
    }

    //It could be a Record, but this is "Java 8"
    static class Product {
        private final String name;
        private final String category;
        private final double price;

        Product(String name, String category, double price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        String getName() { return name; }
        String getCategory() { return category; }
        double getPrice() { return price; }

        @Override
        public String toString() {
            return name + "($" + price + ")";
        }
    }
}