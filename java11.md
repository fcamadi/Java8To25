# Java 11 Features

## Overview
Java 11 (released September 2018) is a Long-Term Support (LTS) version with significant new features including the HTTP Client, new String methods, and lambda improvements.

---

## New String Methods

### isBlank()
```java
String blank = "   ";
String text = "Hello";

blank.isBlank()  // true
text.isBlank()   // false
```

### lines()
```java
String multiline = "Line1\nLine2\rLine3";
multiline.lines()  // Returns Stream<String> with each line

// Useful for processing text
text.lines()
    .filter(line -> !line.isBlank())
    .map(String::trim)
    .forEach(System.out::println);
```

### strip(), stripLeading(), stripTrailing()
```java
String padded = "   text   ";

padded.strip()           // "text" - removes both leading/trailing
padded.stripLeading()    // "text   " - removes leading
padded.stripTrailing()   // "   text" - removes trailing

// Note: trim() removes ASCII whitespace
// strip() removes Unicode whitespace (more comprehensive)
```

### repeat(n)
```java
"Ab".repeat(3)    // "AbAbAb"
"".repeat(5)      // ""
"\n".repeat(3)    // "\n\n\n"
```

### indent(n)
```java
String text = "Line1\nLine2";
text.indent(4)    // Adds 4 spaces to each line
text.indent(-4)   // Removes up to 4 leading spaces per line
```

---

## New File Methods (java.nio.file.Files)

### readString()
```java
Path file = Paths.get("data.txt");

// Read entire file as String
String content = Files.readString(file);

// With charset
String utf8 = Files.readString(file, StandardCharsets.UTF_8);
```

### writeString()
```java
Path file = Paths.get("output.txt");

// Write String to file
Files.writeString(file, "Hello World");

// With options
Files.writeString(file, "Appended", StandardOpenOption.APPEND);
Files.writeString(file, "Content", StandardCharsets.UTF_8);
```

### isSameFile()
```java
Path p1 = Paths.get("/tmp/file");
Path p2 = Paths.get("/tmp/../tmp/file");

Files.isSameFile(p1, p2)  // true - same file, different representation
```

---

## HTTP Client (java.net.http)

### Basic GET Request
```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .build();

HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());

System.out.println(response.statusCode());
System.out.println(response.body());
```

### POST Request
```java
HttpClient client = HttpClient.newHttpClient();

String json = "{\"name\": \"John\", \"age\": 30}";

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/create"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString(json))
    .build();

HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());
```

### Async Request
```java
HttpClient client = HttpClient.newHttpClient();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .build();

client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println)
    .join();
```

### Client Configuration
```java
HttpClient client = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(10))
    .version(HttpClient.Version.HTTP_2)
    .followRedirects(HttpClient.Redirect.NORMAL)
    .authenticator(new Authenticator() { /* ... */ })
    .cookieHandler(new CookieManager())
    .build();
```

---

## Lambda Parameter Improvements

### var in Lambda Parameters (Java 11)
```java
// Java 10 - explicit types
Function<String, String> fn = (String s) -> s.toUpperCase();

// Java 11 - var inference
Function<String, String> fn = (var s) -> s.toUpperCase();

// Multiple parameters with var
BiFunction<String, String, String> concat = (var a, var b) -> a + b;

// With annotations (NEW in Java 11)
Function<String, String> notNull = (@NonNull var s) -> s.toUpperCase();
```

### Benefits
- Consistency with local variable type inference
- Allows adding annotations to parameters
- Makes lambda more readable for complex types

---

## Predicate Improvements

### Predicate.not()
```java
List<String> names = Arrays.asList("Alice", "", "Bob", "   ");

// Before Java 11
names.stream()
    .filter(s -> !s.isEmpty())

// Java 11 - more readable
names.stream()
    .filter(Predicate.not(String::isEmpty))
    .filter(Predicate.not(String::isBlank))

// With method references
names.stream()
    .filter(Predicate.not(Objects::isNull))
```

---

## Collection Improvements

### toArray(Generator)
```java
List<String> list = Arrays.asList("A", "B", "C");

// Before Java 11
String[] array1 = list.toArray(new String[0]);

// Java 11 - generator
String[] array2 = list.toArray(String[]::new);

// Works with any collection type
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
Integer[] nums = set.toArray(Integer[]::new);

// Stream equivalent
String[] streamArray = list.stream().toArray(String[]::new);
```

---

## Optional Improvements

### isEmpty()
```java
Optional<String> present = Optional.of("Value");
Optional<String> empty = Optional.empty();

present.isEmpty()  // false
empty.isEmpty()    // true

// More readable than !optional.isPresent()
optionals.stream()
    .filter(Optional::isEmpty)
    .count();
```

### orElseThrow() Default
```java
Optional<String> empty = Optional.empty();

// No-arg version - throws NoSuchElementException
empty.orElseThrow()  // Throws NoSuchElementException

// With custom exception
empty.orElseThrow(() -> new IllegalArgumentException("Required"))
```

---

## Other Java 11 Features

### String in switch (Preview in 12, Final in 14)
```java
// Not in Java 11, but note the progression
String color = "red";
switch (color) {
    case "red" -> System.out.println("Apple");
    case "yellow" -> System.out.println("Banana");
    default -> System.out.println("Unknown");
}
```

### Collection.toList() and toSet()
```java
// Already in Java 10 for List, enhanced in 11
List<String> list = stream().toList();
Set<String> set = stream().toSet();
```

### Running Single-File Programs
```bash
# Java 11 can run single-file programs directly
java HelloWorld.java
```

---

## Summary

| Feature | Description |
|---------|-------------|
| **String.isBlank()** | Check if string is empty/whitespace |
| **String.lines()** | Split by line terminators |
| **String.strip()** | Remove Unicode whitespace |
| **String.repeat(n)** | Repeat string n times |
| **String.indent(n)** | Add/remove indentation |
| **Files.readString()** | Read file as String |
| **Files.writeString()** | Write String to file |
| **HTTP Client** | Standard HTTP/2 client |
| **var in lambdas** | Type inference for lambda params |
| **Predicate.not()** | Negate predicate |
| **toArray(generator)** | Collection.toArray(T[]::new) |
| **Optional.isEmpty()** | Check if empty |

---

## Java 11 is LTS
- Released: September 2018
- Support until: September 2026 (Oracle)
- Many production systems use Java 11 as baseline

---

## Migration Notes

### HTTP Client Migration
```java
// Before (Apache HttpClient)
HttpClient client = HttpClientBuilder.create().build();

// After (Java 11)
HttpClient client = HttpClient.newHttpClient();
```

### String Processing
```java
// Before
text.trim()
text.split("\n")

// After
text.strip()
text.lines()
```