package org.francd.java11;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FilesMethods {

    public static void main(String[] args) throws IOException {
        readWriteString();
        isSameFile();
    }

    private static void readWriteString() throws IOException {
        System.out.println("=== Files.readString() and writeString() (Java 11) ===");

        Path tempFile = Files.createTempFile("test", ".txt");

        try {
            // writeString() - write content to file
            String content = "Hello from Java 11!\nLine 2\nLine 3";
            Files.writeString(tempFile, content);
            System.out.println("Written to: " + tempFile);

            // readString() - read entire file as string
            String readContent = Files.readString(tempFile);
            System.out.println("Read content:\n" + readContent);

            // readString with charset
            String utf8Content = Files.readString(tempFile, StandardCharsets.UTF_8);
            System.out.println("UTF-8 content length: " + utf8Content.length());

            // writeString with options
            String appendContent = "\nAppended line";
            Files.writeString(tempFile, appendContent, StandardOpenOption.APPEND);
            System.out.println("After append: " + Files.readString(tempFile));

        } finally {
            Files.deleteIfExists(tempFile);
        }

        // Create and write to temp file with StandardOpenOption
        Path newFile = Files.createTempFile("newfile", ".txt");
        try {
            Files.writeString(newFile, "New content", StandardOpenOption.CREATE_NEW);
            String content = Files.readString(newFile);
            System.out.println("New file content: " + content);
        } finally {
            Files.deleteIfExists(newFile);
        }
    }

    private static void isSameFile() throws IOException {
        System.out.println("\n=== Files.isSameFile() ===");

        Path path1 = Paths.get("/tmp/test");
        Path path2 = Paths.get("/tmp/../tmp/test");

        // Create test file
        Files.createDirectories(path1.getParent());
        Files.writeString(path1, "test content");

        boolean same = Files.isSameFile(path1, path2);
        System.out.println("Is same file: " + same);

        // Different paths that resolve to same file
        Path relative = Paths.get("./testfile.txt");
        Path absolute = relative.toAbsolutePath();

        Files.writeString(absolute, "content");
        boolean sameFile = Files.isSameFile(relative, absolute);
        System.out.println("Relative vs Absolute: " + sameFile);

        // Cleanup
        Files.deleteIfExists(absolute);
    }
}