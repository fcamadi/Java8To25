package org.francd.java22;

import java.lang.ScopedValue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScopedValuesDemo {

    private static final ScopedValue<String> USER_CONTEXT = ScopedValue.newInstance();

    public static void main(String[] args) {
        demonstrateScopedValues();
    }

    private static void demonstrateScopedValues() {
        System.out.println("=== Scoped Values (Standard in Java 21) ===\n");

        System.out.println("--- Basic ScopedValue ---");
        System.out.println("Setting value in scope...");
        ScopedValue.where(USER_CONTEXT, "Alice").run(() -> {
            System.out.println("In scope, USER_CONTEXT = " + USER_CONTEXT.get());
        });

        System.out.println("\n--- Value outside scope ---");
        System.out.println("Value outside: " + USER_CONTEXT.orElse("none"));

        System.out.println("\n--- Scoped Values with Virtual Threads ---");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 3; i++) {
                final int userId = i;
                executor.submit(() -> {
                    String userName = "User" + userId;
                    ScopedValue.where(USER_CONTEXT, userName).run(() -> processRequest(userId));
                });
            }
        }

        System.out.println("\n--- ScopedValue vs ThreadLocal ---");
        System.out.println("ScopedValue: Better for virtual threads, immutable, safer");
        System.out.println("ThreadLocal: Legacy, can cause memory leaks");
    }

    private static void processRequest(int requestId) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Request " + requestId + " by " + USER_CONTEXT.get());
    }
}