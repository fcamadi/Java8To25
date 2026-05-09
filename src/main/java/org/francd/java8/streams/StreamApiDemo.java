package org.francd.java8.streams;

import java.util.*;
import java.util.stream.*;

public class StreamApiDemo {

    public static void main(String[] args) {
        streamCreation();
        intermediateOperations();
        terminalOperations();
        reductionOperations();
        parallelStreams();
    }

    private static void streamCreation() {
        System.out.println("=== Stream Creation ===");

        Stream<String> fromArray = Stream.of("a", "b", "c");
        fromArray.forEach(System.out::print);
        System.out.println();

        List<String> list = Arrays.asList("one", "two", "three");
        //list.stream().forEach(System.out::print); <- not really needed for this
        list.forEach(System.out::print);
        System.out.println();

        Stream<Integer> fromIterator = Stream.iterate(1, n -> n + 2).limit(5);
        System.out.print("Iterate: ");
        fromIterator.forEach(s -> System.out.print(s + " "));
        System.out.println();

        Stream<Double> random = Stream.generate(Math::random).limit(10);
        System.out.print("Random: ");
        random.forEach(s -> System.out.printf("%.2f ", s));
        System.out.println();

        Stream<String> empty = Stream.empty();
        System.out.println("Empty stream count: " + empty.count());

        IntStream intRange = IntStream.range(1, 5);
        System.out.print("Int range: ");
        intRange.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    private static void intermediateOperations() {
        System.out.println("\n=== Intermediate Operations ===");

        List<String> words = Arrays.asList("hello", "world", "java", "streams", "lambda");

        words.stream()
            .filter(s -> s.length() > 4)
            .forEach(s -> System.out.print("Filter: " + s + " "));
        System.out.println();

        words.stream()
            .map(String::toUpperCase)
            .forEach(s -> System.out.print("Map: " + s + " "));
        System.out.println();

        words.stream()
            .distinct()
            .forEach(s -> System.out.print("Distinct: " + s + " "));
        System.out.println();

        Stream.iterate(1, n -> n + 1)
            .limit(10)
            .skip(5)
            .forEach(s -> System.out.print("Skip: " + s + " "));
        System.out.println();

        Stream.of(3, 1, 4, 1, 5, 9)
            .sorted()
            .forEach(s -> System.out.print("Sorted: " + s + " "));
        System.out.println();

        Stream.of(1, 2, 3, 4, 5)
            .peek(n -> System.out.println("Peek: " + n))
            .filter(n -> n % 2 == 0)
            .forEach(n -> {});
    }

    private static void terminalOperations() {
        System.out.println("\n=== Terminal Operations ===");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");

        names.stream()
            .forEach(name -> System.out.println("ForEach: " + name));

        long count = names.stream().filter(s -> s.length() > 3).count();
        System.out.println("Count: " + count);

        boolean anyLong = names.stream().anyMatch(s -> s.length() > 5);
        System.out.println("AnyMatch: " + anyLong);

        boolean allShort = names.stream().allMatch(s -> s.length() < 10);
        System.out.println("AllMatch: " + allShort);

        boolean noneLong = names.stream().noneMatch(s -> s.startsWith("X"));
        System.out.println("NoneMatch: " + noneLong);

        Optional<String> first = names.stream().findFirst();
        System.out.println("FindFirst: " + first.orElse("none"));

        Optional<String> any = names.stream().findAny();
        System.out.println("FindAny: " + any.orElse("none"));
    }

    private static void reductionOperations() {
        System.out.println("\n=== Reduction Operations ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        System.out.println("Reduce sum: " + sum.orElse(0));

        Integer sumWithIdentity = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Reduce with identity: " + sumWithIdentity);

        Integer max = numbers.stream().reduce(Integer::max).orElse(0);
        System.out.println("Reduce max: " + max);

        String concat = names().stream().reduce("", String::concat);
        System.out.println("Reduce concat: " + concat);

        //String joined = names().stream().collect(Collectors.joining(", "));
        String joined = String.join(", ", names());
        System.out.println("Joining: " + joined);

        Map<Integer, List<String>> grouped = names().stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouping: " + grouped);

        Map<Boolean, List<String>> partitioned = names().stream()
            .collect(Collectors.partitioningBy(s -> s.length() > 4));
        System.out.println("Partitioning: " + partitioned);
    }

    private static void parallelStreams() {
        System.out.println("\n=== Parallel Streams ===");

        List<Integer> largeList = IntStream.range(1, 100000001).boxed().toList();

        long start = System.currentTimeMillis();
        long sequentialSum = largeList.stream()
            .filter(n -> n % 2 == 0)
            .mapToLong(n -> n)
            .sum();
        long seqTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        long parallelSum = largeList.parallelStream()
            .filter(n -> n % 2 == 0)
            .mapToLong(n -> n)
            .sum();
        long parTime = System.currentTimeMillis() - start;

        System.out.println("Sequential sum: " + sequentialSum + " (" + seqTime + "ms)");
        System.out.println("Parallel sum: " + parallelSum + " (" + parTime + "ms)");

        System.out.println("Current thread: " + Thread.currentThread().getName());
    }

    private static List<String> names() {
        return Arrays.asList("Alice", "Bob", "Charlie");
    }
}