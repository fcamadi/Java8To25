package org.francd.java9.jshell;

public class JShellDemo {

    public static void main(String[] args) {
        System.out.println("=== JShell - Java 9 REPL ===\n");
        System.out.println("JShell is an interactive tool introduced in Java 9.");
        System.out.println("Run it from command line: jshell\n");

        System.out.println("=== Common JShell Commands ===");
        System.out.println("""
            /list              - Show all source code
            /edit              - Edit current method/class
            /drop <name>      - Delete a snippet
            /methods <class>  - Show methods of a class
            /types            - Show declared types
            /imports          - Show imported packages

            /exit             - Exit JShell
            /save <file>      - Save session to file
            /open <file>      - Load file into session
            """);

        demonstrateJShellSnippets();
    }

    private static void demonstrateJShellSnippets() {
        System.out.println("=== Example JShell Sessions ===\n");

        System.out.println("--- Variables ---");
        System.out.println("jshell> int x = 10");
        System.out.println("x ==> 10");
        System.out.println("jshell> String name = \"Java 9\"");
        System.out.println("name ==> \"Java 9\"");

        System.out.println("\n--- Expressions ---");
        System.out.println("jshell> 2 + 3");
        System.out.println("$1 ==> 5");
        System.out.println("jshell> \"Hello\".toUpperCase()");
        System.out.println("$2 ==> \"HELLO\"");

        System.out.println("\n--- Methods ---");
        System.out.println("""
            jshell> int add(int a, int b) {
                ...> return a + b;
                ...> }
            |  created method add(int, int)

            jshell> add(5, 3)
            $3 ==> 8
            """);

        System.out.println("\n--- Classes ---");
        System.out.println("""
            jshell> class Calculator {
                ...>     public int multiply(int a, int b) {
                ...>         return a * b;
                ...>     }
                ...> }
            |  created class Calculator

            jshell> new Calculator().multiply(4, 5)
            $4 ==> 20
            """);

        System.out.println("\n--- Imports ---");
        System.out.println("""
            jshell> import java.util.stream.*
            jshell> IntStream.range(1, 5).sum()
            $5 ==> 10
            """);

        System.out.println("\n--- Exception Handling ---");
        System.out.println("""
            jshell> try {
                ...>     int result = 10 / 0;
                ...> } catch (ArithmeticException e) {
                ...>     System.out.println("Cannot divide by zero");
                ...> }
            Cannot divide by zero
            """);

        System.out.println("=== How to Run JShell ===");
        System.out.println("""
            1. Open terminal
            2. Run: jshell
            3. Type expressions, statements, methods, or classes
            4. Use /help for more options
            5. Type /exit to quit
            """);

        demonstrateJShellFeatures();
    }

    private static void demonstrateJShellFeatures() {
        System.out.println("\n=== Advanced JShell Features ===");

        System.out.println("--- Tab Completion ---");
        System.out.println("Type 'Stri' + Tab -> 'String'");
        System.out.println("Type 'System.out.pr' + Tab -> 'println'");

        System.out.println("\n--- Reverse Engineering ---");
        System.out.println("jshell> /methods java.util.List");
        System.out.println("boolean add(E)");
        System.out.println("void add(int, E)");
        System.out.println("E get(int)");
        System.out.println("...");

        System.out.println("\n--- Snippet History ---");
        System.out.println("jshell> /list");
        System.out.println("1 : int x = 10;");
        System.out.println("2 : String name = \"Java 9\";");
        System.out.println("3 : 2 + 3");

        System.out.println("\n--- Save and Load ---");
        System.out.println("jshell> /save session.jsh");
        System.out.println("jshell> /open session.jsh");

        System.out.println("\n--- JShell API (Programmatic) ---");
        System.out.println("JShell can be used programmatically:");
        System.out.println("""
            JShell js = JShell.create();
            js.eval("int x = 5;");
            js.eval("System.out.println(x);");
            js.close();
            """);
    }
}