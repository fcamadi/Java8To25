package org.francd.java9.resources;

import java.io.*;
import java.sql.*;
import java.util.*;

public class TryWithResources {

    public static void main(String[] args) {
        basicTryWithResources();
        effectivelyFinalResources();
        multipleResources();
        suppressedExceptions();
    }

    private static void basicTryWithResources() {
        System.out.println("=== Enhanced Try-With-Resources (Java 9) ===");

        try (var reader = new BufferedReader(new StringReader("Hello World"))) {
            String line = reader.readLine();
            System.out.println("Read: " + line);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Resource used and closed successfully");
    }

    private static void effectivelyFinalResources() {
        System.out.println("\n=== Effectively Final Resources ===");

        BufferedReader reader = new BufferedReader(new StringReader("Line 1\nLine 2"));

        try (reader) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Resource closed automatically");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
             Statement stmt = conn.createStatement()) {

            stmt.execute("CREATE TABLE test (id INT, name TEXT)");
            stmt.execute("INSERT INTO test VALUES (1, 'Test')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM test");
            while (rs.next()) {
                System.out.println("Row: " + rs.getInt(1) + ", " + rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    private static void multipleResources() {
        System.out.println("\n=== Multiple Resources ===");

        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (var in = new FileInputStream(inputFile);
             var out = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Files copied");

        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(System.out)) {
            writer.println("Enter your name:");
            String name = scanner.nextLine();
            writer.println("Hello, " + name);
        }
    }

    private static void suppressedExceptions() {
        System.out.println("\n=== Suppressed Exceptions ===");

        try (Resource1 r1 = new Resource1();
             Resource2 r2 = new Resource2()) {
            throw new RuntimeException("Primary exception");
        } catch (Exception e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Suppressed: " + Arrays.toString(e.getSuppressed()));
        }

        System.out.println("\n--- Manual handling ---");
        Exception primary = null;
        Throwable suppressed = null;

        try {
            try (var r = new ResourceThrows()) {
                throw new Exception("Primary");
            }
        } catch (Exception e) {
            primary = e;
            suppressed = e.getSuppressed()[0];
        }

        System.out.println("Primary: " + primary.getMessage());
        System.out.println("Suppressed: " + suppressed.getMessage());
    }

    static class Resource1 implements AutoCloseable {
        public void close() {
            System.out.println("Resource1 closed");
        }
    }

    static class Resource2 implements AutoCloseable {
        public void close() {
            System.out.println("Resource2 closed");
        }
    }

    static class ResourceThrows implements AutoCloseable {
        public void close() throws Exception {
            throw new Exception("During close");
        }
    }
}