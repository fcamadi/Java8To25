package org.francd.java11;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public class HttpClientDemo {

    public static void main(String[] args) throws Exception {
        basicGetRequest();
        postRequest();
        asyncRequests();
    }

    private static void basicGetRequest() throws Exception {
        System.out.println("=== HTTP Client (Java 11 Standard) ===");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/get"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Headers: " + response.headers().firstValue("content-type").orElse("none"));
        System.out.println("Body (truncated): " + response.body().substring(0, Math.min(100, response.body().length())) + "...");
    }

    private static void postRequest() throws Exception {
        System.out.println("\n=== POST Request ===");

        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

        String jsonBody = "{\"name\": \"John\", \"age\": 30}";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/post"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body().substring(0, Math.min(150, response.body().length())) + "...");
    }

    private static void asyncRequests() throws Exception {
        System.out.println("\n=== Async Requests ===");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://httpbin.org/delay/1"))
            .GET()
            .build();

        // Async with CompletionStage
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();

        System.out.println("Async request completed");
    }
}