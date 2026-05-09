package org.francd.java19;

public class VirtualThreads {

    public static void main(String[] args) throws Exception {
        virtualThreadBasics();
        structuredConcurrency();
    }

    private static void virtualThreadBasics() throws InterruptedException {
        System.out.println("=== Virtual Threads (Preview 19, Final 21) ===");

        // Create virtual thread
        Thread vt = Thread.ofVirtual().start(() -> {
            System.out.println("Running in virtual thread: " + Thread.currentThread().isVirtual());
        });
        vt.join();

        // Using Thread.ofVirtual().factory
        var factory = Thread.ofVirtual().factory();
        Thread vt2 = factory.newThread(() -> {
            System.out.println("Virtual thread from factory");
        });
        vt2.start();
        vt2.join();

        // Using Executors
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> System.out.println("Task in virtual thread"));
        }
    }

    private static void structuredConcurrency() throws Exception {
        System.out.println("\n=== Structured Concurrency (Incubator) ===");

        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            var future1 = executor.submit(() -> "Result 1");
            var future2 = executor.submit(() -> "Result 2");

            // Both complete or one fails
            String r1 = future1.get();
            String r2 = future2.get();
            System.out.println("Results: " + r1 + ", " + r2);
        }
    }
}