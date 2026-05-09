# Java 9 Features

## Overview
Java 9 (released September 2017) introduced the Module System, JShell REPL, and many smaller improvements.

---

## Immutable Collections (Factory Methods)

### List.of()
```java
List<String> list = List.of("Apple", "Banana", "Cherry");
List<String> single = List.of("Only one");
List<String> empty = List.of();

// Properties
- Immutable - throws UnsupportedOperationException on modify
- No null elements allowed
- Supports all standard List operations
```

### Set.of()
```java
Set<String> set = Set.of("Red", "Green", "Blue");

// Throws IllegalArgumentException if duplicates
Set.of("A", "A", "B"); // IllegalArgumentException
```

### Map.of() and Map.ofEntries()
```java
Map<String, Integer> map = Map.of(
    "One", 1,
    "Two", 2,
    "Three", 3
);

// For more than 10 entries
Map<String, Integer> largeMap = Map.ofEntries(
    Map.entry("A", 1),
    Map.entry("B", 2),
    Map.entry("C", 3)
);
```

### Immutable vs Unmodifiable
```java
// Collections.unmodifiableList - wraps, changes reflect through
List<String> unmodifiable = Collections.unmodifiableList(original);
original.add("Change"); // Reflects in unmodifiable

// List.of() - truly immutable, no connection to original
List<String> immutable = List.of("Immutable");
```

---

## Private Methods in Interfaces

```java
interface PaymentProcessor {
    void process(double amount);

    // Private method (Java 9+)
    private boolean validateAmount(double amount) {
        return amount > 0 && amount <= 1000000;
    }

    private void logTransaction(String type, double amount) {
        System.out.println("[" + type + "] $" + amount);
    }

    // Default method using private helper
    default void processWithValidation(double amount) {
        if (!validateAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        logTransaction("PROCESS", amount);
        process(amount);
    }
}
```

### Benefits
- Code reuse within interfaces
- Hide implementation details
- Better encapsulation

---

## Stream Improvements

### takeWhile()
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Takes elements while condition is true, stops at first false
List<Integer> result = numbers.stream()
    .takeWhile(n -> n < 5)
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4]

// If first element fails, returns empty
numbers.stream()
    .takeWhile(n -> n > 10)
    .collect(Collectors.toList());
// Result: []
```

### dropWhile()
```java
// Drops elements while condition is true, takes the rest
List<Integer> result = numbers.stream()
    .dropWhile(n -> n < 5)
    .collect(Collectors.toList());
// Result: [5, 6, 7, 8, 9, 10]

// If first element fails, takes all
numbers.stream()
    .dropWhile(n -> n > 10)
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

### Stream.iterate() with Predicate
```java
// Old way (Java 8) - unlimited, needed limit()
Stream.iterate(1, n -> n + 1).limit(10);

// New way (Java 9) - has termination condition
Stream.iterate(1, n -> n <= 10, n -> n + 1)
// Produces: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

// Fibonacci example
Stream.iterate(
    new int[]{0, 1},
    arr -> arr[0] + arr[1] < 100,
    arr -> new int[]{arr[1], arr[0] + arr[1]}
).map(arr -> arr[0])
// Produces: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
```

### Stream.ofNullable()
```java
// Java 8 - would create stream with one null element
Stream.of(null); // Creates stream with one null

// Java 9 - creates empty stream for null
Stream<String> stream = Stream.ofNullable(null); // Empty stream
Stream<String> stream2 = Stream.ofNullable("value"); // Stream with one element

// Practical use - filter nulls from list
List<String> names = Arrays.asList("John", null, "Jane", null);
List<String> nonNull = names.stream()
    .flatMap(Stream::ofNullable)
    .collect(Collectors.toList());
// Result: ["John", "Jane"]
```

### Optional.stream()
```java
// Convert Optional to Stream
Optional<String> opt = Optional.of("value");
List<String> list = opt.stream().collect(Collectors.toList());
// Result: ["value"]

Optional<String> empty = Optional.empty();
List<String> emptyList = empty.stream().collect(Collectors.toList());
// Result: []

// Practical - flatten list of Optionals
List<Optional<String>> optionals = Arrays.asList(
    Optional.of("A"), Optional.empty(), Optional.of("B")
);
List<String> flattened = optionals.stream()
    .flatMap(Optional::stream)
    .collect(Collectors.toList());
// Result: ["A", "B"]
```

---

## Module System (JPMS)

### module-info.java
```java
module com.example.myapp {
    // Required modules
    requires java.sql;
    requires org.apache.commons;

    // Exports packages (public API)
    exports com.example.myapp.api;
    exports com.example.myapp.model;

    // Opens for reflection
    opens com.example.myapp.internal to org.junit;
    opens com.example.myapp.model to com.fasterxml.jackson;
}
```

### Module Commands
```java
// Get module info at runtime
Module current = MyClass.class.getModule();
String name = current.getName();

// Check module relationships
boolean canRead = moduleA.canRead(moduleB);

// Get packages
Set<String> packages = current.getPackages();

// Use ModuleLayer
ModuleLayer boot = ModuleLayer.boot();
Optional<Module> javaBase = boot.findModule("java.base");
```

### ServiceLoader
```java
// Define service interface
public interface MessageFormatter {
    String format(String message);
}

// Implement service
public class JsonFormatter implements MessageFormatter {
    @Override
    public String format(String message) {
        return "{\"message\": \"" + message + "\"}";
    }
}

// Load services
ServiceLoader<MessageFormatter> loader = ServiceLoader.load(MessageFormatter.class);
for (MessageFormatter formatter : loader) {
    System.out.println(formatter.format("Hello"));
}
```

---

## Process API Improvements

### ProcessHandle
```java
// Get current process
ProcessHandle current = ProcessHandle.current();
long pid = current.getPid();
boolean alive = current.isAlive();

// Process information
ProcessHandle.Info info = current.info();
String command = info.command().orElse("unknown");
String[] args = info.arguments().orElse(new String[0]);
Duration cpu = info.totalCpuDuration().orElse(Duration.ZERO);
Instant start = info.startInstant().orElse(null);

// Parent process
Optional<ProcessHandle> parent = current.parent();

// Children and descendants
Stream<ProcessHandle> children = current.children();
Stream<ProcessHandle> descendants = current.descendants();
```

### List All Processes
```java
// All processes in system
List<ProcessHandle> allProcesses = ProcessHandle.allProcesses().toList();

// Filter processes
ProcessHandle.allProcesses()
    .filter(p -> p.info().command().map(c -> c.contains("java")).orElse(false))
    .findFirst();
```

### ProcessBuilder Improvements
```java
// Already available in earlier versions, enhanced in Java 9
ProcessBuilder pb = new ProcessBuilder("ls", "-la");
pb.inheritIO();

Process p = pb.start();
int exitCode = p.waitFor();
```

---

## Enhanced Try-With-Resources

### Java 7 (Basic)
```java
try (FileInputStream in = new FileInputStream("file.txt")) {
    // Use resource
}
```

### Java 9 (Effectively Final)
```java
// Resources declared outside try block
BufferedReader reader = new BufferedReader(new StringReader("Hello"));

try (reader) {
    String line = reader.readLine();
    System.out.println(line);
}
// reader is automatically closed
```

### Multiple Resources
```java
Path input = Paths.get("input.txt");
Path output = Paths.get("output.txt");

try (var in = Files.newBufferedReader(input);
     var out = Files.newBufferedWriter(output)) {
    // Use both resources
}
```

---

## JShell (REPL)

### Starting JShell
```bash
$ jshell
|  Welcome to JShell -- Version 9
|  For an introduction type: /help
jshell>
```

### Basic Usage
```java
jshell> int x = 10
x ==> 10

jshell> String s = "Hello"
s ==> "Hello"

jshell> 2 + 3
$1 ==> 5

jshell> "java".toUpperCase()
$2 ==> "JAVA"
```

### Defining Methods
```java
jshell> int add(int a, int b) {
   ...> return a + b;
   ...> }
|  created method add(int, int)

jshell> add(5, 3)
$3 ==> 8
```

### Defining Classes
```java
jshell> class Calculator {
   ...>     public int multiply(int a, int b) {
   ...>         return a * b;
   ...>     }
   ...> }
|  created class Calculator

jshell> new Calculator().multiply(4, 5)
$4 ==> 20
```

### Commands
```java
/list              // Show all snippets
/edit              // Edit current
/drop <name>       // Delete snippet
/methods <class>   // Show methods
/types             // Show types
/imports          // Show imports
/save <file>       // Save session
/open <file>       // Load file
/exit             // Exit JShell
```

---

## Other Java 9 Features

### Diamond Syntax for Anonymous Classes
```java
// Now works with anonymous classes
Handler<Integer> handler = new Handler<>() {
    @Override
    public void handle(Integer value) {
        System.out.println(value);
    }
};
```

### Collection.toArray(Generator)
```java
List<String> list = List.of("a", "b", "c");
String[] array = list.toArray(String[]::new);
```

### Optional.or() Method
```java
Optional<String> opt = Optional.of("value");
Optional<String> fallback = opt.or(() -> Optional.of("fallback"));
```

### Stream TakeWhile/DropWhile (described above)

### Multi-Release JARs
```text
myapp.jar
├── META-INF
│   └── MANIFEST.MF
├── org
│   └── example
│       └── App.class
└── org
    └── example
        └── App.class (Java 9+)
```

### HTTP/2 Client (Incubator)
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://example.com"))
    .GET()
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

### Stack Walker
```java
StackWalker walker = StackWalker.getInstance();
walker.forEach(frame -> System.out.println(frame.getMethodName()));
```

---

## Summary

| Feature | Description |
|---------|-------------|
| **Module System** | JPMS with `module-info.java` |
| **Private Methods** | Code reuse in interfaces |
| **Immutable Collections** | `List.of()`, `Set.of()`, `Map.of()` |
| **Stream Improvements** | `takeWhile`, `dropWhile`, `iterate` with predicate |
| **Process API** | `ProcessHandle` for process management |
| **Try-With-Resources** | Effectively final resources |
| **JShell** | Interactive REPL |
| **HTTP Client** | Incubating HTTP/2 client |