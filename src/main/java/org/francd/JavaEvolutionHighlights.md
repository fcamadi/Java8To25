# Java 8 to 25 - Key LTS Features

Java has evolved significantly from version 8 to 25. This document highlights the most important features from the **four Long-Term Support (LTS)** releases.

---

4 LTS versions:

     Java 8 - Lambda expressions, Stream API, Optional, new Date/Time API, default methods in interfaces                                                                                           
                                                                                                                                                                                               
     Java 11 - var type inference, HTTP Client, new String methods (isBlank(), lines(), repeat()), immutable collection factories                                                              
                                                                                                                                                                                               
     Java 17 - Records, Sealed classes, Pattern matching for instanceof and switch, switch expressions                                                                                         
                                                                                                                                                                                               
     Java 21 - Virtual threads, Sequenced collections, Record patterns, Scoped values, String templates                                                                                        
                                                                                                                                                                                               
     Each section includes practical code examples showing real-world usage. The file is ~10KB with comprehensive examples for each major feature.     

## Java 8 (March 2014)

The release that changed Java forever.

### Lambda Expressions
```java
// Before - anonymous class
Runnable r = new Runnable() {
    public void run() { System.out.println("Hello"); }
};

// After - lambda
Runnable r = () -> System.out.println("Hello");

// Functional interfaces
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.forEach(name -> System.out.println(name));

// Map with lambda
List<String> result = names.stream()
    .filter(s -> s.length() > 3)
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

### Stream API
```java
// Declarative data processing
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

int sum = numbers.stream()
    .filter(n -> n % 2 == 0)
    .mapToInt(Integer::intValue)
    .sum();

List<String> longWords = words.stream()
    .filter(w -> w.length() > 4)
    .map(String::toLowerCase)
    .distinct()
    .collect(Collectors.toList());
```

### Optional
```java
// No more NullPointerException
Optional<String> name = Optional.of("John");

// Safe retrieval
String result = name.orElse("Default");
String computed = name.orElseGet(() -> expensiveCalculation());
String thrown = name.orElseThrow(() -> new RuntimeException("Required"));

// Chaining
String processed = name
    .map(String::toUpperCase)
    .filter(s -> s.startsWith("J"))
    .orElse("Unknown");
```

### Date/Time API
```java
// No more Date, Calendar mess
LocalDate today = LocalDate.now();
LocalDate birthday = LocalDate.of(1990, Month.MARCH, 15);

LocalTime meeting = LocalTime.of(14, 30);
LocalDateTime appointment = LocalDateTime.of(birthday, meeting);

// Operations
LocalDate nextWeek = today.plusWeeks(1);
LocalDateTime later = appointment.plusHours(2);

// Formatting
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String formatted = today.format(formatter);

// Timezones
ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
```

### Default Methods in Interfaces
```java
interface Vehicle {
    void start();
    void stop();

    // Default method with implementation
    default void honk() {
        System.out.println("Beep!");
    }

    // Static method
    static String info() {
        return "Vehicle interface";
    }
}

class Car implements Vehicle {
    public void start() { System.out.println("Starting..."); }
    public void stop() { System.out.println("Stopping..."); }
}
```

---

## Java 11 (September 2018) - LTS

Modern Java with built-in HTTP client.

### Local Variable Type Inference (var)
```java
// Type inferred from right side
var number = 42;
var text = "Hello";
var list = new ArrayList<String>();
var map = new HashMap<String, Integer>();

// With complex types
var person = new Person("John", 30);
var stream = list.stream();

// In for loops
for (var item : items) {
    System.out.println(item);
}
```

### New String Methods
```java
String text = "  Hello Java  ";

text.isBlank();        // true if empty/whitespace
text.strip();          // removes leading/trailing whitespace
text.stripLeading();   // removes leading
text.stripTrailing();  // removes trailing
text.lines();          // Stream<String> split by line terminators
"Ab".repeat(3);        // "AbAbAb"
text.indent(4);        // add 4 spaces to each line
```

### HTTP Client (Standard)
```java
HttpClient client = HttpClient.newHttpClient();

// GET
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/data"))
    .GET()
    .build();

HttpResponse<String> response = client.send(request,
    HttpResponse.BodyHandlers.ofString());

// POST
HttpRequest postRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/create"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyReaders.ofString(jsonBody))
    .build();

// Async
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println)
    .join();
```

### Collection Improvements
```java
// Immutable copies
List<String> copy = List.copyOf(original);  // throws if contains null
Set<String> setCopy = Set.copyOf(sourceSet);
Map<String, Integer> mapCopy = Map.copyOf(sourceMap);

// Stream.toList() (Java 10+)
List<String> unmodifiable = stream().toList();
```

### Lambda Improvements
```java
// var in lambda parameters (Java 11+)
BiFunction<String, String, String> concat = (var a, var b) -> a + b;

// Predicate.not()
List<String> filtered = names.stream()
    .filter(Predicate.not(String::isBlank))
    .toList();
```

### Running Single-File Programs
```bash
# Direct execution
java HelloWorld.java

# No need to compile first
```

---

## Java 17 (September 2021) - LTS

The most significant LTS since Java 8.

### Sealed Classes
```java
// Restrict class hierarchy
sealed interface Shape permits Circle, Rectangle, Triangle {}

record Circle(double radius) implements Shape {}
record Rectangle(double width, double height) implements Shape {}
record Triangle(double base, double height) implements Shape {}

// Use in switch
double area = switch (shape) {
    case Circle c -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    case Triangle t -> 0.5 * t.base() * t.height();
};
```

### Pattern Matching for switch
```java
// Type checks and casts automatically
String result = switch (obj) {
    case String s -> "String: " + s.length() + " chars";
    case Integer i -> "Integer: " + (i > 0 ? "positive" : "negative");
    case null -> "Null value";
    default -> "Unknown: " + obj.getClass().getSimpleName();
};
```

### Records
```java
// Immutable data classes with automatic equals, hashCode, toString
record Person(String name, int age, String email) {
    // Canonical constructor, accessors, equals, hashCode, toString auto-generated
}

var person = new Person("John", 30, "john@email.com");
System.out.println(person.name());  // "John"

// Can add custom methods
record Point(int x, int y) {
    public int quadrant() {
        if (x > 0 && y > 0) return 1;
        return 4;
    }
}
```

### Pattern Matching for instanceof
```java
// Before Java 16
if (obj instanceof String) {
    String s = (String) obj;  // Need to cast
    System.out.println(s.length());
}

// Java 16+
if (obj instanceof String s) {
    System.out.println(s.length());  // Already typed
}

// With conditions
if (obj instanceof String s && s.length() > 5) {
    System.out.println(s.toUpperCase());
}
```

### Switch Expressions (Standard)
```java
// Can return values
int days = switch (month) {
    case 1, 3, 5, 7, 8, 10, 12 -> 31;
    case 4, 6, 9, 11 -> 30;
    case 2 -> 28;
    default -> 0;
};

// With multiple values and blocks
String grade = switch (score) {
    case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> {
        yield "A";
    }
    case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> "B";
    default -> "F";
};
```

### Strong Encapsulation
```java
// Internal APIs now strongly encapsulated
// --add-opens required to access via reflection
// More secure by default
```

---

## Java 21 (September 2023) - LTS

The future of Java concurrency.

### Virtual Threads
```java
// Lightweight threads - millions per application
Thread vt = Thread.ofVirtual().start(() -> {
    System.out.println("Running in virtual thread: " + Thread.currentThread().isVirtual());
});

// Using executor - perfect for I/O-bound tasks
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 10000; i++) {
        final int id = i;
        executor.submit(() -> handleRequest(id));
    }
}

// When to use: I/O-bound, high concurrency
// When NOT to use: CPU-intensive, need ThreadLocal
```

### Sequenced Collections
```java
// New interface with ordered operations
SequencedCollection<String> list = new ArrayList<>();
list.addFirst("first");
list.addLast("last");
list.getFirst();  // first element
list.getLast();   // last element
list.reversed();  // reversed view

SequencedMap<String, Integer> map = new LinkedHashMap<>();
map.putFirst("A", 1);
map.putLast("Z", 26);

// Works with Set too
SequencedSet<Integer> set = new LinkedHashSet<>();
set.addFirst(1);
```

### Record Patterns
```java
// Pattern matching with records
record Point(int x, int y) {}

Object obj = new Point(10, 20);

// In instanceof
if (obj instanceof Point(int x, int y)) {
    System.out.println("X: " + x + ", Y: " + y);  // x, y are directly accessible
}

// In switch
String quadrant = switch (obj) {
    case Point(int x, int y) when x > 0 && y > 0 -> "Q1";
    case Point(int x, int y) when x < 0 && y > 0 -> "Q2";
    case Point(int x, int y) -> "Other quadrant";
    default -> "Not a point";
};
```

### Scoped Values (Standard in 21)
```java
// Better than ThreadLocal for virtual threads
ScopedValue<String> userContext = ScopedValue.newInstance();

String result = ScopedValue.where(userContext, "admin")
    .get(() -> {
        return userContext.get();  // "admin"
    });
```

### String Templates (Preview in 21, Final in 25)
```java
// Type-safe string interpolation
String name = "John";
int age = 30;

// STR template
String greeting = STR."Hello \{name}, you are \{age} years old";
// Result: "Hello John, you are 30 years old"

// FMT template for formatting
String aligned = FMT."""
    Name:  %-10s\{name}
    Age:   %03d\{age}
""";
/*
    Name:  John
    Age:   030
*/
```

---

## Java 25 (September 2025) - LTS

The newest LTS release bringing string templates to production.

### String Templates (Final)
```java
// Type-safe string interpolation - now standard!
String name = "John";
int age = 30;

// STR template - basic interpolation
String greeting = STR."Hello \{name}, you are \{age} years old";
// Result: "Hello John, you are 30 years old"

// FMT template - formatted output
String aligned = FMT."""
    Name:  %-10s\{name}
    Age:   %03d\{age}
    Score: %6.2f\{95.5}
""";
/*
    Name:  John
    Age:   030
    Score:  95.50
*/

// RAW template - for custom processing
StringTemplate st = RAW."Hello \{name}";
String result = STR.process(st);  // Process template
```

### val Keyword
```java
// val - final local variable inference
val message = "Hello";       // final String message = "Hello"
val number = 42;             // final int number = 42
val list = List.of(1, 2, 3);  // final List<Integer> list = ...

// More concise than var for immutable variables
// Similar to Kotlin's val or Scala's val
```

### const Keyword (Preview)
```java
// const - compile-time constant (preview)
const int MAX_RETRIES = 3;
const String APP_NAME = "MyApp";
const double PI = 3.14159;

// Must be initialized with compile-time constant expression
// Cannot be reassigned
```

### Primitive Classes (Enhanced)
```java
// Value types for better performance
// primitive class Complex(real: double, imaginary: double)
// {
//     public double magnitude() { return Math.sqrt(real*real + imaginary*imaginary); }
// }

// Unlike records - stack-allocated, no object overhead
// Useful for performance-critical numerical code
```

### Other Java 25 Features

- Further performance improvements
- Enhanced security
- Better startup times

---

## Summary Table

| Version | Year | LTS | Key Features |
|---------|------|-----|--------------|
| **8** | 2014 | - | Lambdas, Streams, Optional, Date/Time |
| **11** | 2018 | ✓ | var, HTTP Client, String methods, toList() |
| **17** | 2021 | ✓ | Records, Sealed classes, Pattern matching, Switch expressions |
| **21** | 2023 | ✓ | Virtual threads, Sequenced collections, Record patterns |
| **25** | 2025 | ✓ | String templates (final), val, const, enhanced primitives |

---

## Migration Recommendations

### From Java 8 to 11+
- Replace anonymous classes with lambdas
- Use `var` for local variables
- Use `List.of()`, `Set.of()`, `Map.of()` for immutable collections
- Use new Date/Time API

### From Java 11 to 17+
- Use records for data transfer objects
- Use sealed classes for controlled hierarchies
- Use pattern matching for type checks
- Use switch expressions

### From Java 17 to 21+
- Use virtual threads for I/O-bound concurrency
- Use SequencedCollection for ordered data
- Use records with pattern matching
- Consider string templates when final