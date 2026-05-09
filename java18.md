# Java 18 Features

## UTF-8 by Default

```java
// Default charset is always UTF-8
Charset.defaultCharset(); // UTF-8

// No need to specify charset
Files.readString(path); // Uses UTF-8
Files.writeString(path, content); // Uses UTF-8
```

## Other Features

- New String methods (already in 11-15)
- InternetAddress for hostnames
- Enhanced security

## Summary

| Feature | Description |
|---------|-------------|
| UTF-8 default charset | Platform-independent |
| Code tools | More robust |
| Unicode 13 support | Added characters |

---

## Java 18 Key Points

- UTF-8 is default charset across all platforms
- Better internationalization support
- First non-LTS after 17