package org.francd.java18;

public class UTF8DefaultCharset {

    public static void main(String[] args) {
        defaultCharsetUTF8();
        newStringMethods();
    }

    private static void defaultCharsetUTF8() {
        System.out.println("=== Default Charset is UTF-8 (Java 18) ===");

        // Before Java 18: System charset depended on platform
        // Java 18+: Always UTF-8

        System.out.println("Default charset: " + java.nio.charset.Charset.defaultCharset());

        // File reading without specifying charset
        try {
            var temp = java.nio.file.Files.createTempFile("test", ".txt");
            java.nio.file.Files.writeString(temp, "Héllo Wörld");
            String read = java.nio.file.Files.readString(temp);
            System.out.println("Read: " + read);
            java.nio.file.Files.deleteIfExists(temp);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // InputStreamReader defaults to UTF-8
        // OutputStreamWriter defaults to UTF-8
    }

    private static void newStringMethods() {
        System.out.println("\n=== New String Methods (Java 18) ===");

        // String.isBlank() already in Java 11
        // String.lines() already in Java 11

        // getBytes() changes
        String text = "Hello";
        byte[] utf8 = text.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("UTF-8 bytes: " + java.util.Arrays.toString(utf8));
    }
}