package org.francd.java9.modules;

public class JsonFormatter implements MessageFormatter {
    public JsonFormatter() {}
    @Override
    public String format(String message) {
        return "{\"message\": \"" + message + "\"}";
    }
}