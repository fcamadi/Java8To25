# Java 13 Features

## Switch Expressions (yield)

### Using yield
```java
String dayType = switch (day) {
    case 1, 2, 3, 4, 5 -> {
        yield "Weekday";
    }
    case 6, 7 -> {
        yield "Weekend";
    }
    default -> {
        yield "Invalid";
    }
};
```

## Text Blocks (Preview)

```java
String html = """
    <html>
      <body>
        <p>Hello World</p>
      </body>
    </html>
    """;
```

- Newlines and indentation handled automatically
- `\s` preserves trailing whitespace
- Quotes don't need escaping

## Summary

| Feature | Status |
|---------|--------|
| Switch Expressions | Preview (final in 14) |
| Text Blocks | Preview (final in 15) |
| yield keyword | New |

---

## Java 13 Key Points

- Introduced `yield` for switch expressions
- Text blocks as preview feature
- `String.formatted()` method