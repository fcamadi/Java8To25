package org.francd.java9.streams;

import java.util.*;
import java.util.stream.*;

public class StreamImprovements {

    public static void main(String[] args) {
        takeWhileDemo();
        dropWhileDemo();
        iterateDemo();
        ofNullableDemo();
        optionalStreamDemo();
    }

    private static void takeWhileDemo() {
        System.out.println("=== takeWhile() (Java 9) ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> taken = numbers.stream()
            .takeWhile(n -> n < 5)
            .collect(Collectors.toList());
        System.out.println("Take while < 5: " + taken);

        List<String> words = Arrays.asList("one", "two", "three", "four", "five");

        List<String> shortWords = words.stream()
            .takeWhile(s -> s.length() < 5)
            .collect(Collectors.toList());
        System.out.println("Take while length < 5: " + shortWords);

        List<Integer> emptyResult = numbers.stream()
            .takeWhile(n -> n > 10)
            .collect(Collectors.toList());
        System.out.println("Take while none match: " + emptyResult);

        List<String> fruits = Arrays.asList("apple", "banana", "cherry");
        List<String> allMatch = fruits.stream()
            .takeWhile(s -> s.startsWith("a"))
            .collect(Collectors.toList());
        System.out.println("All match: " + allMatch);
    }

    private static void dropWhileDemo() {
        System.out.println("\n=== dropWhile() (Java 9) ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> dropped = numbers.stream()
            .dropWhile(n -> n < 5)
            .collect(Collectors.toList());
        System.out.println("Drop while < 5: " + dropped);

        List<String> words = Arrays.asList("one", "two", "three", "four", "five");

        List<String> remaining = words.stream()
            .dropWhile(s -> s.length() < 5)
            .collect(Collectors.toList());
        System.out.println("Drop while length < 5: " + remaining);

        List<Integer> dropAll = numbers.stream()
            .dropWhile(n -> n < 100)
            .collect(Collectors.toList());
        System.out.println("Drop while all match: " + dropAll);

        List<String> keepAll = words.stream()
            .dropWhile(s -> s.startsWith("x"))
            .collect(Collectors.toList());
        System.out.println("Drop while none match: " + keepAll);
    }

    private static void iterateDemo() {
        System.out.println("\n=== Stream.iterate() with Predicate (Java 9) ===");

        List<Integer> limited = Stream.iterate(1, n -> n <= 10, n -> n + 1)
            .collect(Collectors.toList());
        System.out.println("Iterate 1-10: " + limited);

        List<Integer> evens = Stream.iterate(0, n -> n <= 20, n -> n + 2)
            .collect(Collectors.toList());
        System.out.println("Even numbers 0-20: " + evens);

        List<Integer> powers = Stream.iterate(1, n -> n <= 1000, n -> n * 2)
            .collect(Collectors.toList());
        System.out.println("Powers of 2: " + powers);

        List<String> alphabet = Stream.iterate(0, n -> n < 26, n -> n + 1)
            .map(n -> String.valueOf((char) ('A' + n)))
            .collect(Collectors.toList());
        System.out.println("Alphabet: " + alphabet);

        List<Integer> fibonacci = Stream.iterate(
            new int[]{0, 1},
            arr -> arr[0] + arr[1] < 100,
            arr -> new int[]{arr[1], arr[0] + arr[1]}
        ).map(arr -> arr[0]).collect(Collectors.toList());
        System.out.println("Fibonacci: " + fibonacci);
    }

    private static void ofNullableDemo() {
        System.out.println("\n=== Stream.ofNullable() (Java 9) ===");

        String nullable = null;
        Stream<String> stream1 = Stream.ofNullable(nullable);
        System.out.println("Null stream count: " + stream1.count());

        String value = "Hello";
        Stream<String> stream2 = Stream.ofNullable(value);
        System.out.println("Value stream: " + stream2.collect(Collectors.toList()));

        List<String> withNulls = Arrays.asList("A", null, "B", null, "C");
        List<String> filtered = withNulls.stream()
            .flatMap(s -> Stream.ofNullable(s))
            .collect(Collectors.toList());
        System.out.println("FlatMap ofNullable: " + filtered);

        List<String> names = Arrays.asList("John", null, "Jane", null);
        long nonNull = names.stream()
            .flatMap(Stream::ofNullable)
            .count();
        System.out.println("Non-null count: " + nonNull);
    }

    private static void optionalStreamDemo() {
        System.out.println("\n=== Optional.stream() (Java 9) ===");

        Optional<String> empty = Optional.empty();
        List<String> emptyList = empty.stream().collect(Collectors.toList());
        System.out.println("Empty optional to list: " + emptyList);

        Optional<String> present = Optional.of("Value");
        List<String> singleList = present.stream().collect(Collectors.toList());
        System.out.println("Present optional to list: " + singleList);

        List<Optional<String>> optionals = Arrays.asList(
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B"),
            Optional.empty(),
            Optional.of("C")
        );

        List<String> flattened = optionals.stream()
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
        System.out.println("Flattened optionals: " + flattened);

        long countWithNulls = optionals.stream()
            .flatMap(Optional::stream)
            .filter(s -> !s.isEmpty())
            .count();
        System.out.println("Non-empty count: " + countWithNulls);
    }
}