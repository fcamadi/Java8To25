package org.francd.java12;

import java.time.DayOfWeek;
import java.time.Month;

public class SwitchExpressions {

    public static void main(String[] args) {
        traditionalSwitch();
        newSwitchExpression();
        switchWithArrow();
        switchWithMultipleValues();
        switchWithBlock();
    }

    private static void traditionalSwitch() {
        System.out.println("=== Traditional Switch ===");

        int day = 3;
        String dayName;

        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            default:
                dayName = "Weekend";
        }
        System.out.println("Traditional: " + dayName);
    }

    private static void newSwitchExpression() {
        System.out.println("\n=== Switch Expression (Java 12+) ===");

        int day = 3;

        // Expression form - returns a value
        String dayName = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            default -> "Weekend";
        };
        System.out.println("Switch expression: " + dayName);

        // With enum
        Month month = Month.APRIL;
        String quarter = switch (month) {
            case JANUARY, FEBRUARY, MARCH -> "Q1";
            case APRIL, MAY, JUNE -> "Q2";
            case JULY, AUGUST, SEPTEMBER -> "Q3";
            default -> "Q4";
        };
        System.out.println("Quarter: " + quarter);
    }

    private static void switchWithArrow() {
        System.out.println("\n=== Switch with Arrow Syntax ===");

        String color = "RED";

        // Arrow syntax - no fall-through!
        String result = switch (color.toUpperCase()) {
            case "RED" -> "Apple";
            case "YELLOW" -> "Banana";
            case "ORANGE" -> "Orange";
            case "GREEN" -> "Grape";
            default -> "Unknown fruit";
        };
        System.out.println("Fruit: " + result);

        // Multiple statements with block
        int number = 2;
        String numericWord = switch (number) {
            case 1 -> {
                String result1 = "one";
                yield result1;
            }
            case 2 -> {
                String result2 = "two";
                yield result2;
            }
            default -> {
                yield "other";
            }
        };
        System.out.println("Number word: " + numericWord);
    }

    private static void switchWithMultipleValues() {
        System.out.println("\n=== Switch with Multiple Values ===");

        char grade = 'B';

        String description = switch (grade) {
            case 'A' -> "Excellent";
            case 'B', 'C' -> "Good";
            case 'D' -> "Pass";
            case 'F' -> "Fail";
            default -> "Invalid";
        };
        System.out.println("Description: " + description);

        // With enum
        DayOfWeek today = DayOfWeek.WEDNESDAY;
        boolean isWeekend = switch (today) {
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };
        System.out.println("Is weekend: " + isWeekend);

        // Mixed arrows with multiple values
        String type = "TUESDAY";
        boolean isWorkday = switch (type) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> true;
            default -> false;
        };
        System.out.println("Is workday: " + isWorkday);
    }

    private static void switchWithBlock() {
        System.out.println("\n=== Switch Expression with Block ===");

        String input = "java";
        int result = switch (input.toLowerCase()) {
            case "java" -> {
                int version = 12;
                yield version;
            }
            case "python" -> {
                yield 3;
            }
            case "javascript" -> {
                yield 6;
            }
            default -> {
                String msg = "Unknown language";
                yield -1;
            }
        };
        System.out.println("Result: " + result);

        // Complex switch with condition
        int score = 85;
        String grade = switch (score) {
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100 -> "A";
            case 80, 81, 82, 83, 84, 85, 86, 87, 88, 89 -> "B";
            case 70, 71, 72, 73, 74, 75, 76, 77, 78, 79 -> "C";
            default -> "F";
        };
        System.out.println("Grade: " + grade);
    }
}