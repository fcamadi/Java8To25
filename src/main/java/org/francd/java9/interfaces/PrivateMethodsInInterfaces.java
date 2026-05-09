package org.francd.java9.interfaces;

public class PrivateMethodsInInterfaces {

    public static void main(String[] args) {
        processPayment();
        demonstrateHelperMethods();
    }

    private static void processPayment() {
        System.out.println("=== Private Methods in Interfaces ===");

        PaymentProcessor credit = new CreditCardProcessor();
        credit.process(100.00);
        credit.refund(50.00);

        PaymentProcessor paypal = new PayPalProcessor();
        paypal.process(200.00);
        paypal.refund(75.00);
    }

    private static void demonstrateHelperMethods() {
        System.out.println("\n=== Private Helper Methods ===");

        DatabaseConnection db = new OracleConnection();
        db.connect("localhost", 1521);
        db.disconnect();

        db = new MySQLConnection();
        db.connect("192.168.1.1", 3306);
        db.disconnect();
    }
}

interface PaymentProcessor {
    void process(double amount);
    void refund(double amount);

    default void processWithValidation(double amount) {
        if (!validateAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        logTransaction("PROCESS", amount);
        process(amount);
    }

    private boolean validateAmount(double amount) {
        return amount > 0 && amount <= 1000000;
    }

    private void logTransaction(String type, double amount) {
        System.out.println("[" + type + "] Amount: $" + String.format("%.2f", amount));
    }
}

class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("Credit Card: Charging $" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Credit Card: Refunding $" + amount);
    }
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void process(double amount) {
        System.out.println("PayPal: Charging $" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("PayPal: Refunding $" + amount);
    }
}

interface DatabaseConnection {
    void connect(String host, int port);
    void disconnect();

    private void logConnection(String type, String host, int port) {
        System.out.println("[" + type + "] Connecting to " + host + ":" + port);
    }

    private void validateHost(String host) {
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Host cannot be empty");
        }
    }

    default void connectWithValidation(String host, int port) {
        validateHost(host);
        logConnection("CONNECT", host, port);
        connect(host, port);
    }
}

class OracleConnection implements DatabaseConnection {
    @Override
    public void connect(String host, int port) {
        System.out.println("Oracle connected to " + host + ":" + port);
    }

    @Override
    public void disconnect() {
        System.out.println("Oracle disconnected");
    }
}

class MySQLConnection implements DatabaseConnection {
    @Override
    public void connect(String host, int port) {
        System.out.println("MySQL connected to " + host + ":" + port);
    }

    @Override
    public void disconnect() {
        System.out.println("MySQL disconnected");
    }
}