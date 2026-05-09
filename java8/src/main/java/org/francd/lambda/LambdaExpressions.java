package org.francd.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.*;

public class LambdaExpressions {

    public static void main(String[] args) {
        basicLambdaSyntax();
        functionalInterfaces();
        methodReferences();
        closureBehavior();
    }

    private static void basicLambdaSyntax() {
        System.out.println("=== Basic Lambda Syntax ===");

        Runnable noParams = () -> System.out.println("Hello from lambda!");
        noParams.run();

        Consumer<String> oneParam = s -> System.out.println("Consumed: " + s);
        oneParam.accept("Hello");

        BiFunction<Integer, Integer, Integer> twoParams = (a, b) -> a + b;
        System.out.println("Sum: " + twoParams.apply(5, 3));

        BinaryOperator<Integer> withType = (Integer a, Integer b) -> a * b;
        System.out.println("Product: " + withType.apply(4, 5));

        Function<String, Integer> multiStatement = s -> {
            int length = s.length();
            return length * 2;
        };
        System.out.println("Length doubled: " + multiStatement.apply("Java"));
    }

    private static void functionalInterfaces() {
        System.out.println("\n=== Functional Interfaces ===");

        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("Is empty: " + isEmpty.test(""));

        Predicate<String> notEmpty = s -> !s.isEmpty();
        System.out.println("Is not empty: " + notEmpty.test("Java"));

        Function<String, String> upperCase = String::toUpperCase;
        System.out.println("Uppercase: " + upperCase.apply("java"));

        Supplier<List<String>> listSupplier = ArrayList::new;
        List<String> newList = listSupplier.get();
        newList.add("Item");
        System.out.println("New list: " + newList);

        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("Square of 7: " + square.apply(7));

        BiConsumer<String, Integer> printPair = (key, value) ->
            System.out.println(key + " = " + value);
        printPair.accept("Age", 25);
    }

    private static void methodReferences() {
        System.out.println("\n=== Method References ===");

        Function<String, String> toUpper = String::toUpperCase;
        System.out.println("Upper: " + toUpper.apply("hello"));

        Supplier<String> stringSupplier = String::new;
        System.out.println("New string: '" + stringSupplier.get() + "'");

        BiFunction<String, String, String> concat = String::concat;
        System.out.println("Concat: " + concat.apply("Hello ", "World"));

        Function<Integer, String> intToString = Object::toString;
        System.out.printf("Int to String: %s%n", intToString.apply(42));

        Predicate<String> isNull = Objects::isNull;
        System.out.println("Is null: " + isNull.test(null));

        Consumer<String> printer = System.out::println;
        printer.accept("Printed via method reference");
    }

    private static void closureBehavior() {
        System.out.println("\n=== Closure Behavior ===");

        int effectivelyFinal = 10;
        Runnable run = () -> System.out.println("Value: " + effectivelyFinal);
        run.run();

        Consumer<String> spanishGreeter = createGreeter("Hola");
        spanishGreeter.accept("Carlos");

        Consumer<String> englishGreeter = createGreeter("Hello");
        englishGreeter.accept("John");
    }

    private static Consumer<String> createGreeter(String prefix) {
        return name -> System.out.println(prefix + ", " + name + "!");
    }
}