package org.francd.java9.process;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ProcessApiDemo {

    public static void main(String[] args) throws Exception {
        currentProcessInfo();
        processManagement();
        processBuilderDemo();
    }

    private static void currentProcessInfo() {
        System.out.println("=== Process API Improvements (Java 9) ===");

        ProcessHandle current = ProcessHandle.current();
        System.out.println("PID: " + current.pid());

        System.out.println("Is alive: " + current.isAlive());

        ProcessHandle.Info info = current.info();
        System.out.println("Command: " + info.command().orElse("unknown"));
        System.out.println("Arguments: " + info.arguments().map(a -> String.join(" ", a)).orElse("none"));
        System.out.println("Start time: " + info.startInstant().orElse(null));
        System.out.println("CPU duration: " + info.totalCpuDuration().orElse(Duration.ZERO));

        Optional<ProcessHandle> parent = current.parent();
        System.out.println("Parent PID: " + parent.map(p -> p.pid()).orElse(-1L));

        System.out.println("Children: " + current.children().count());
        System.out.println("Descendants: " + current.descendants().count());
    }

    private static void processManagement() {
        System.out.println("\n=== Process Management ===");

        List<ProcessHandle> allProcesses = ProcessHandle.allProcesses()
            .limit(10)
                .collect(Collectors.toList());

        System.out.println("First 10 processes:");
        for (ProcessHandle ph : allProcesses) {
            String name = ph.info().command().map(c -> {
                int idx = c.lastIndexOf('/');
                return idx >= 0 ? c.substring(idx + 1) : c;
            }).orElse("unknown");
            System.out.println("  PID " + ph.pid() + ": " + name);
        }

        ProcessHandle.current().children().forEach(child -> {
            System.out.println("Child: " + child.pid());
        });

        Optional<ProcessHandle> finder = ProcessHandle.allProcesses()
            .filter(p -> p.info().command().map(c -> c.contains("java")).orElse(false))
            .findFirst();

        System.out.println("\nFirst Java process: " + finder.map(p -> p.pid()).orElse(-1L));
    }

    private static void processBuilderDemo() throws IOException {
        System.out.println("\n=== ProcessBuilder Improvements ===");

        ProcessBuilder pb = new ProcessBuilder("ls", "-la");
        pb.inheritIO();

        System.out.println("RedirectError: " + pb.redirectError());
        System.out.println("RedirectInput: " + pb.redirectInput());

        pb = new ProcessBuilder()
            .command("echo", "Hello from Java 9 Process API")
            .directory(new java.io.File("."))
            .inheritIO();

        try {
            Process process = pb.start();
            int exitCode = process.waitFor();
            System.out.println("Exit code: " + exitCode);
        } catch (Exception e) {
            System.out.println("Process error: " + e.getMessage());
        }

        pb = new ProcessBuilder()
            .command("java", "-version")
            .redirectErrorStream(true);

        try {
            Process p = pb.start();
            String output = new String(p.getInputStream().readAllBytes());
            System.out.println("Java version output:\n" + output);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}