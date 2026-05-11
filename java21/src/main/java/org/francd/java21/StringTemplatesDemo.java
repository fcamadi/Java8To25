package org.francd.java21;

public class StringTemplatesDemo {

    public static void main(String[] args) {
        demonstrateStringTemplates();
    }

    private static void demonstrateStringTemplates() {
        System.out.println("=== String Templates (Preview in Java 21, Final in Java 25) ===\n");

        String name = "Alice";
        int age = 30;
        double score = 95.5;

        System.out.println("--- Basic formatted strings ---");
        String basic = "Hello %s, you are %d years old".formatted(name, age);
        System.out.println(basic);

        System.out.println("\n--- String.formatted() ---");
        String template = "Name: {name}, Age: {age}, Score: {score}";
        String result = template
            .replace("{name}", name)
            .replace("{age}", String.valueOf(age))
            .replace("{score}", String.valueOf(score));
        System.out.println(result);

        System.out.println("\n--- Multi-line formatting ---");
        String multiline = """
            Name:   %s
            Age:    %d
            Score:  %.2f
            """.formatted(name, age, score);
        System.out.println(multiline);

        System.out.println("\n--- TextBlock-like formatting ---");
        String json = """
            {
                "name": "%s",
                "age": %d
            }
            """.formatted(name, age);
        System.out.println(json);

        System.out.println("\nNote: STR.\"...\" template syntax is in Java 25+");
    }
}