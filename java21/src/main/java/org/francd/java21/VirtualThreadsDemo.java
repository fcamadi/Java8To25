package org.francd.java21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class VirtualThreadsDemo {

    public static void main(String[] args) {
        demonstrateVirtualThreads();
    }

    private static void demonstrateVirtualThreads() {
        System.out.println("=== Virtual Threads (Final in Java 21) ===\n");

        System.out.println("--- Basic Virtual Thread ---");
        Thread vt = Thread.ofVirtual().start(() -> {
            System.out.println("Running in virtual thread: " + Thread.currentThread().isVirtual());
        });
        try {
            vt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Virtual Thread Per Task Executor ---");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10).forEach(i -> {
                executor.submit(() -> {
                    System.out.println("Task " + i + " running in " +
                        (Thread.currentThread().isVirtual() ? "virtual" : "platform") + " thread");
                    return null;
                });
            });
        }

        System.out.println("\n--- Millions of virtual threads possible ---");
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            long start = System.currentTimeMillis();
            int count = 10000;
            for (int i = 0; i < count; i++) {
                final int id = i;
                executor.submit(() -> {
                    Thread.sleep(1);
                    return id;
                });
            }
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Created " + count + " virtual threads in " + elapsed + "ms");
        }

        System.out.println("\n--- Virtual vs Platform Threads ---");
        System.out.println("Virtual threads: Lightweight, low memory, millions possible");
        System.out.println("Platform threads: Traditional, heavier, limited by OS");
        System.out.println("Best for: I/O-bound, high-concurrency tasks");
    }
}