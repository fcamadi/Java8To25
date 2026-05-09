package org.francd.java12;

import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.*;
import java.text.*;
import java.time.*;
import java.util.*;

public class OtherFeatures {

    public static void main(String[] args) throws IOException {
        stringFormatting();
        numberFormatting();
        fileMismatch();
        compactNumberFormat();
    }

    private static void stringFormatting() {
        System.out.println("=== String.formatted() (Java 12) ===");

        // String.formatted() - similar to String.format()
        String template = "Hello, {0}! You have {1} messages.";
        String result = template.formatted("John", 5);
        System.out.println("Formatted: " + result);

        // Using positional arguments
        String recipe = "Step {0}: {1}\nStep {2}: {3}";
        String steps = recipe.formatted(1, "Mix ingredients", 2, "Bake at 350°F");
        System.out.println("Recipe:\n" + steps);

        // Multiple same argument
        String duplicate = "{0} is the same as {0}";
        System.out.println(duplicate.formatted("Java 12"));

        // Named parameters (using different approach)
        String named = "Name: %s, Age: %d".formatted("Alice", 30);
        System.out.println("Named: " + named);
    }

    private static void numberFormatting() {
        System.out.println("\n=== Number Formatting Improvements ===");

        // Compact number formatting (Java 12)
        NumberFormat compact = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println("Compact 1000: " + compact.format(1000));
        System.out.println("Compact 1000000: " + compact.format(1000000));
        System.out.println("Compact 1000000000: " + compact.format(1000000000));

        // Different styles
        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println("Long 1000: " + longFormat.format(1000));

        // With different locales
        NumberFormat germanCompact = NumberFormat.getCompactNumberInstance(Locale.GERMAN, NumberFormat.Style.SHORT);
        System.out.println("German compact: " + germanCompact.format(1000000));

        // Rounding with BigDecimal
        var bd = new java.math.BigDecimal("2.567");
        var rounded = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println("Rounded: " + rounded);
    }

    private static void fileMismatch() throws IOException {
        System.out.println("\n=== Files.mismatch() (Java 12) ===");

        Path file1 = Files.createTempFile("file1", ".txt");
        Path file2 = Files.createTempFile("file2", ".txt");

        try {
            // Write different content
            Files.writeString(file1, "Hello World");
            Files.writeString(file2, "Hello Java");

            // Find first mismatch position
            long mismatch = Files.mismatch(file1, file2);
            System.out.println("Mismatch at position: " + mismatch);

            // Write same content
            Files.writeString(file1, "Same content");
            Files.writeString(file2, "Same content");

            mismatch = Files.mismatch(file1, file2);
            System.out.println("Same files mismatch: " + mismatch);

            // Different lengths
            Files.writeString(file1, "Short");
            Files.writeString(file2, "Much longer content");

            mismatch = Files.mismatch(file1, file2);
            System.out.println("Length mismatch: " + mismatch);

        } finally {
            Files.deleteIfExists(file1);
            Files.deleteIfExists(file2);
        }
    }

    private static void compactNumberFormat() {
        System.out.println("\n=== Compact Number Formatting ===");

        // Short form
        NumberFormat shortFmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        System.out.println("Short:");
        System.out.println("  1K: " + shortFmt.format(1000));
        System.out.println("  1M: " + shortFmt.format(1000000));
        System.out.println("  1B: " + shortFmt.format(1000000000));

        // Long form
        NumberFormat longFmt = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println("Long:");
        System.out.println("  1K: " + longFmt.format(1000));
        System.out.println("  1M: " + longFmt.format(1000000));

        // With currency
        NumberFormat currencyCompact = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Currency compact: " + currencyCompact.format(1234567));

        // Using in objects
        Map<String, Long> stats = new LinkedHashMap<>();
        stats.put("Views", 1500000L);
        stats.put("Likes", 45000L);
        stats.put("Shares", 2300L);

        System.out.println("\nSocial media stats:");
        stats.forEach((key, value) ->
            System.out.println("  " + key + ": " + shortFmt.format(value)));
    }
}