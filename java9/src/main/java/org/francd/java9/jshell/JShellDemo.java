package org.francd.java9.jshell;

public class JShellDemo {

    public static void main(String[] args) {
        System.out.println("=== JShell - Java 9 REPL ===\n");
        System.out.println("JShell is an interactive tool introduced in Java 9.");
        System.out.println("Run it from command line: jshell\n");

        System.out.println("=== Common JShell Commands ===");
        System.out.println("/list              - Show all source code");
        System.out.println("/edit              - Edit current method/class");
        System.out.println("/drop <name>      - Delete a snippet");
        System.out.println("/methods <class>  - Show methods of a class");
        System.out.println("/types            - Show declared types");
        System.out.println("/imports          - Show imported packages");
        System.out.println("/exit             - Exit JShell");
        System.out.println("/save <file>      - Save session to file");
        System.out.println("/open <file>      - Load file into session");

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
        System.out.println("jshell> int add(int a, int b) {");
        System.out.println("    ...> return a + b;");
        System.out.println("    ...> }");
        System.out.println("|  created method add(int, int)");
        System.out.println("");
        System.out.println("jshell> add(5, 3)");
        System.out.println("$3 ==> 8");

        System.out.println("\n--- Classes ---");
        System.out.println("jshell> class Calculator {");
        System.out.println("    ...>     public int multiply(int a, int b) {");
        System.out.println("    ...>         return a * b;");
        System.out.println("    ...>     }");
        System.out.println("    ...> }");
        System.out.println("|  created class Calculator");
        System.out.println("");
        System.out.println("jshell> new Calculator().multiply(4, 5)");
        System.out.println("$4 ==> 20");

        System.out.println("\n--- Imports ---");
        System.out.println("jshell> import java.util.stream.*");
        System.out.println("jshell> IntStream.range(1, 5).sum()");
        System.out.println("$5 ==> 10");

        System.out.println("\n--- Exception Handling ---");
        System.out.println("jshell> try {");
        System.out.println("    ...>     int result = 10 / 0;");
        System.out.println("    ...> } catch (ArithmeticException e) {");
        System.out.println("    ...>     System.out.println(\"Cannot divide by zero\");");
        System.out.println("    ...> }");
        System.out.println("Cannot divide by zero");

        System.out.println("=== How to Run JShell ===");
        System.out.println("1. Open terminal");
        System.out.println("2. Run: jshell");
        System.out.println("3. Type expressions, statements, methods, or classes");
        System.out.println("4. Use /help for more options");
        System.out.println("5. Type /exit to quit");

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
        System.out.println("JShell js = JShell.create();");
        System.out.println("js.eval(\"int x = 5;\");");
        System.out.println("js.eval(\"System.out.println(x);\");");
        System.out.println("js.close();");
    }
}