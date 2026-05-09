package org.francd.java8.functional;

import java.util.function.*;

public class BuiltInFunctionalInterfaces {

    public static void main(String[] args) {
        predicateDemo();
        functionDemo();
        supplierConsumerDemo();
        operatorsDemo();
        binaryOperators();
    }

    private static void predicateDemo() {
        System.out.println("=== Predicate<T> ===");

        Predicate<String> isEmpty = String::isEmpty;
        System.out.println("Empty string: " + isEmpty.test(""));
        System.out.println("Non-empty: " + isEmpty.test("Java"));

        Predicate<Integer> isPositive = n -> n > 0;
        System.out.println("5 positive: " + isPositive.test(5));
        System.out.println("-3 positive: " + isPositive.test(-3));

        Predicate<String> startsWithJ = s -> s.startsWith("J");
        Predicate<String> length5 = s -> s.length() == 5;

        Predicate<String> combined = startsWithJ.and(length5);
        System.out.println("Java (and): " + combined.test("Java"));
        System.out.println("Python (and): " + combined.test("Python"));

        Predicate<String> orExample = startsWithJ.or(s -> s.startsWith("P"));
        System.out.println("Java (or): " + orExample.test("Java"));
        System.out.println("Python (or): " + orExample.test("Python"));

        Predicate<Integer> negate = isPositive.negate();
        System.out.println("5 negative: " + negate.test(5));
    }

    private static void functionDemo() {
        System.out.println("\n=== Function<T, R> ===");

        Function<String, Integer> stringLength = String::length;
        System.out.println("Length: " + stringLength.apply("Java"));

        Function<String, String> toUpper = String::toUpperCase;
        System.out.println("Upper: " + toUpper.apply("hello"));

        Function<Integer, String> intToString = Object::toString;
        System.out.println("To string: " + intToString.apply(42));

        Function<String, String> composed = toUpper.andThen(s -> "Result: " + s);
        System.out.println("AndThen: " + composed.apply("hello"));

        Function<String, String> composed2 = ((Function<String, String>) String::toLowerCase).compose(s -> s.trim());
        System.out.println("Compose: " + composed2.apply("  JAVA  "));

        Function<String, Integer> pipeline = s -> {
            int len = s.length();
            return len * 2;
        };
        System.out.println("Pipeline: " + pipeline.apply("test"));
    }

    private static void supplierConsumerDemo() {
        System.out.println("\n=== Supplier & Consumer ===");

        Supplier<Double> random = Math::random;
        System.out.println("Random: " + random.get());

        Supplier<String> currentTime = () -> java.time.LocalTime.now().toString();
        System.out.println("Time: " + currentTime.get());

        Consumer<String> printer = System.out::println;
        printer.accept("Hello from Consumer!");

        Consumer<String> upperPrinter = s -> System.out.println("UPPER: " + s.toUpperCase());
        Consumer<String> combined = printer.andThen(upperPrinter);
        combined.accept("Mixed Case");

        Consumer<Integer> doublePrinter = n -> System.out.println(n + " -> " + (n * 2));
        java.util.Arrays.asList(1, 2, 3, 4, 5).forEach(doublePrinter);
    }

    private static void operatorsDemo() {
        System.out.println("\n=== UnaryOperator & BinaryOperator ===");

        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square: " + square.apply(5));

        UnaryOperator<String> identity = s -> s;
        System.out.println("Identity: " + identity.apply("test"));

        UnaryOperator<String> pipeline = s -> {
            String lower = s.toLowerCase();
            String trimmed = lower.trim();
            return "[" + trimmed + "]";
        };
        System.out.println("Pipeline: " + pipeline.apply("  JAVA  "));

        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println("Sum: " + sum.apply(10, 20));

        BinaryOperator<Integer> max = Integer::max;
        System.out.println("Max: " + max.apply(15, 8));

        BinaryOperator<String> concat = (a, b) -> a + " " + b;
        System.out.println("Concat: " + concat.apply("Hello", "World"));

        BinaryOperator<Integer> withIdentity = (a, b) -> b == 0 ? a : a + b;
        System.out.println("With identity (b=0): " + withIdentity.apply(42, 0));
    }

    private static void binaryOperators() {
        System.out.println("\n=== Specialized Primitive Interfaces ===");

        IntPredicate even = n -> n % 2 == 0;
        System.out.println("IntPredicate 4: " + even.test(4));
        System.out.println("IntPredicate 5: " + even.test(5));

        LongPredicate large = l -> l > 1000;
        System.out.println("LongPredicate 5000: " + large.test(5000));
        System.out.println("LongPredicate 50: " + large.test(50));

        DoublePredicate positive = d -> d > 0;
        System.out.println("DoublePredicate -5: " + positive.test(-5));

        IntFunction<String> intToStr = i -> "Number: " + i;
        System.out.println("IntFunction: " + intToStr.apply(42));

        ToIntFunction<String> toInt = s -> s.length();
        System.out.println("ToIntFunction: " + toInt.applyAsInt("Java"));

        ObjIntConsumer<String> indexedPrint = (s, i) ->
            System.out.println("Index " + i + ": " + s);
        indexedPrint.accept("Apple", 0);
        indexedPrint.accept("Banana", 1);
    }
}