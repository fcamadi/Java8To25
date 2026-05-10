package org.francd.java11;

import java.util.*;

public class StringMethods {

    public static void main(String[] args) {
        newStringMethods();
        isBlankLinesStrip();
        repeatAndIndent();
    }

    private static void newStringMethods() {
        System.out.println("=== New String Methods (Java 11) ===");

        String text = "  Hello Java 11  ";

        // isBlank() - checks if string is empty or whitespace only
        System.out.println("isBlank(): '" + text.isBlank() + "'");

        String notBlank = "Hello";
        System.out.println("Not blank: '" + notBlank.isBlank() + "'");

        // lines() - split by line terminators
        String multiline = "Line 1\nLine 2\rLine 3\r\nLine 4";
        System.out.println("lines():");
        multiline.lines().forEach(System.out::println);

        // strip() vs trim()
        String withSpaces = "   text   ";
        System.out.println("strip(): '" + withSpaces.strip() + "'");
        System.out.println("trim(): '" + withSpaces.trim() + "'");

        // stripLeading() and stripTrailing()
        String padded = "   Hello   ";
        System.out.println("stripLeading(): '" + padded.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + padded.stripTrailing() + "'");

        // repeat(n) - repeat string n times
        String repeated = "Ab".repeat(3);
        System.out.println("repeat(3): '" + repeated + "'");

        String empty = "".repeat(5);
        System.out.println("Empty repeat: '" + empty + "'");
    }

    private static void isBlankLinesStrip() {
        System.out.println("\n=== isBlank(), lines(), strip() ===");

        // isBlank() examples
        List<String> inputs = Arrays.asList("  ", " \t \n ", "hello", "", "   world   ");

        for (String s : inputs) {
            System.out.println("'" + s + "' isBlank: " + s.isBlank());
        }

        // lines() - handles all line terminators
        String poem = "Roses are red\nViolets are blue\nJava 11 is here";
        System.out.println("\nPoem lines:");
        poem.lines().forEachOrdered(line -> System.out.println("  " + line));

        // Processing blank lines
        String withBlankLines = "Line1\n\nLine2\n   \nLine3";
        System.out.println("\nNon-blank lines:");
        withBlankLines.lines()
            .filter(line -> !line.isBlank())
            .forEach(System.out::println);
    }

    private static void repeatAndIndent() {
        System.out.println("\n=== repeat() and indent() ===");

        // repeat()
        String star = "*".repeat(10);
        System.out.println("Stars: " + star);

        StringBuilder buffer = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            buffer.append("*".repeat(i)).append("\n");
        }
        System.out.println("Triangle:\n" + buffer);

        // indent() - adds indentation            <- indent comes in Java 12
        String text = "Line 1\nLine 2\nLine 3";
        //String indented = text.indent(4);
        //System.out.println("Indented:\n" + indented);

        // Negative indent - removes leading whitespace
        String overIndented = "    Indented text";
        //String unindented = overIndented.indent(-4);
        //System.out.println("Unindented: '" + unindented + "'");

        // Combining indent and repeat
        //String formatted = "Hello".indent(2).repeat(3);
        //System.out.println("Formatted:\n" + formatted);
    }
}