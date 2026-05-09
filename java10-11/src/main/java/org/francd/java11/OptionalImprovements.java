package org.francd.java11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalImprovements {

    public static void main(String[] args) {
        isEmptyMethod();
        orElseThrowDefault();
    }

    private static void isEmptyMethod() {
        System.out.println("=== Optional.isEmpty() (Java 11) ===");

        Optional<String> present = Optional.of("Value");
        Optional<String> empty = Optional.empty();
        Optional<String> blank = Optional.of("   ");

        // Before Java 11 - check with isPresent() and get()
        if (!present.isPresent()) {
            System.out.println("Not present");
        } else {
            System.out.println("Value: " + present.get());
        }

        // Java 11 - isEmpty() is cleaner
        System.out.println("present.isEmpty(): " + present.isEmpty());
        System.out.println("empty.isEmpty(): " + empty.isEmpty());
        System.out.println("blank.isEmpty(): " + blank.isEmpty());

        // In stream operations
        List<Optional<String>> optionals = Arrays.asList(
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B"),
            Optional.empty()
        );

        long emptyCount = optionals.stream()
            .filter(Optional::isEmpty)
            .count();
        System.out.println("Empty count: " + emptyCount);

        long nonEmptyCount = optionals.stream()
            .filter(Optional::isEmpty)
            .count();
        System.out.println("Non-empty count: " + (optionals.size() - nonEmptyCount));

        // More readable than the old way
        List<String> values = optionals.stream()
            .flatMap(opt -> opt.isEmpty() ? Stream.empty() : Stream.of(opt.get()))
            .collect(Collectors.toList());
        System.out.println("Values: " + values);
    }

    private static void orElseThrowDefault() {
        System.out.println("\n=== orElseThrow() Default (Java 11) ===");

        // orElseThrow() with no arguments - throws NoSuchElementException
        Optional<String> empty = Optional.empty();

        try {
            String result = empty.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("Default orElseThrow: " + e.getClass().getSimpleName());
        }

        // orElseThrow with supplier - was available before but more prominent now
        Optional<Integer> opt = Optional.of(42);
        try {
            Integer val = opt.orElseThrow(() -> new IllegalArgumentException("Custom"));
            System.out.println("Value: " + val);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // Optional with conditional processing
        Optional<String> name = Optional.of("John");
        name.ifPresentOrElse(
            n -> System.out.println("Found: " + n),
            () -> System.out.println("Not found")
        );

        // Combining with stream
        List<Optional<String>> list = Arrays.asList(
            Optional.of("A"), Optional.empty(), Optional.of("C")
        );

        // Filter and transform
        String result = list.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .reduce((a, b) -> a + "," + b)
            .orElse("none");
        System.out.println("Combined: " + result);
    }
}