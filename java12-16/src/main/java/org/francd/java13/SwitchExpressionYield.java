package org.francd.java13;

public class SwitchExpressionYield {

    public static void main(String[] args) {
        yieldInSwitch();
        textBlocks();
    }

    private static void yieldInSwitch() {
        System.out.println("=== yield in Switch Expressions (Java 13) ===");

        int day = 3;

        // Using yield for complex logic
        String dayType = switch (day) {
            case 1, 2, 3, 4, 5 -> {
                String result = "Weekday";
                yield result;
            }
            case 6, 7 -> {
                yield "Weekend";
            }
            default -> {
                yield "Invalid";
            }
        };
        System.out.println("Day type: " + dayType);

        // More complex example
        String grade = "B";
        String assessment = switch (grade.toUpperCase()) {
            case "A" -> {
                yield "Excellent - Outstanding performance";
            }
            case "B" -> {
                yield "Very Good - Above average performance";
            }
            case "C" -> {
                yield "Good - Average performance";
            }
            case "D" -> {
                yield "Pass - Below average";
            }
            case "F" -> {
                yield "Fail - Did not meet requirements";
            }
            default -> {
                yield "Invalid grade entered";
            }
        };
        System.out.println("Assessment: " + assessment);

        // Using yield with multiple values
        char operation = '+';
        int result = switch (operation) {
            case '+' -> {
                int r = 10 + 5;
                yield r;
            }
            case '-' -> {
                yield 10 - 5;
            }
            case '*' -> {
                yield 10 * 5;
            }
            case '/' -> {
                yield 10 / 5;
            }
            default -> {
                yield 0;
            }
        };
        System.out.println("Result: " + result);
    }

    private static void textBlocks() {
        System.out.println("\n=== Text Blocks (Preview in Java 13, Final in 15) ===");

        // Before - awkward string concatenation
        String oldStyle = "<html>\n" +
            "  <body>\n" +
            "    <p>Hello World</p>\n" +
            "  </body>\n" +
            "</html>";

        // Java 13+ - text blocks
        String html = """
            <html>
              <body>
                <p>Hello World</p>
              </body>
            </html>
            """;
        System.out.println("HTML Block:");
        System.out.println(html);

        // JSON example
        String json = """
            {
                "name": "John",
                "age": 30,
                "skills": ["Java", "Python", "Go"]
            }
            """;
        System.out.println("JSON:");
        System.out.println(json);

        // SQL example
        String sql = """
            SELECT id, name, email
            FROM users
            WHERE active = true
            AND created_at > '2024-01-01'
            ORDER BY name
            LIMIT 100
            """;
        System.out.println("SQL:");
        System.out.println(sql);

        // Indentation control
        String unindented = """
            Line 1
            Line 2
            Line 3""";
        System.out.println("Unindented:");
        System.out.println(unindented);
    }
}