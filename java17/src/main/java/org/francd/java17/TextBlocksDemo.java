package org.francd.java17;

public class TextBlocksDemo {

    public static void main(String[] args) {
        demonstrateTextBlocks();
    }

    private static void demonstrateTextBlocks() {
        System.out.println("=== Text Blocks (Final in Java 15) ===");

        String escaped = """
            Line 1\s   # Result: "Line 1   " (preserve spaces)
            """;
        System.out.println("Escaped: [" + escaped + "]");

        String json = """
            {
                "name": "John",
                "age": 30
            }
            """;
        System.out.println("JSON: " + json);

        String sql = """
            SELECT * FROM table WHERE name = 'John'
            """;
        System.out.println("SQL: " + sql);

        String html = """
            <html>
                <body>
                    <h1>Hello</h1>
                </body>
            </html>
            """;
        System.out.println("HTML:\n" + html);

        String poem = """
            Roses are red,
            Violets are blue,
            Java is awesome,
            And so are you!
            """;
        System.out.println("Poem:\n" + poem);
    }
}