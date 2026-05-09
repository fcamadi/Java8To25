package org.francd.java8.datetime;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.Date;

public class DateTimeApiDemo {

    public static void main(String[] args) {
        localDateTime();
        periodsAndDurations();
        formatting();
        timezoneOperations();
        adjustmentsAndQueries();
    }

    private static void localDateTime() {
        System.out.println("=== LocalDateTime ===");

        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);

        LocalDate specific = LocalDate.of(2024, Month.JANUARY, 15);
        System.out.println("Specific date: " + specific);

        LocalTime now = LocalTime.now();
        System.out.println("Now: " + now);

        LocalTime specificTime = LocalTime.of(14, 30, 45);
        System.out.println("Specific time: " + specificTime);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("DateTime: " + dateTime);

        LocalDateTime fromParts = LocalDateTime.of(2024, 6, 20, 10, 30);
        System.out.println("From parts: " + fromParts);

        System.out.println("Components: " + dateTime.getYear() + ", " +
            dateTime.getMonth() + ", " + dateTime.getDayOfMonth());

        LocalDate plusDays = today.plusDays(10);
        System.out.println("Plus 10 days: " + plusDays);

        LocalDateTime plusHours = dateTime.plusHours(5);
        System.out.println("Plus 5 hours: " + plusHours);

        LocalDate minusWeeks = today.minusWeeks(2);
        System.out.println("Minus 2 weeks: " + minusWeeks);
    }

    private static void periodsAndDurations() {
        System.out.println("\n=== Periods and Durations ===");

        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 12, 31);

        Period period = Period.between(start, end);
        System.out.println("Period between: " + period.getDays() + " days");

        Period yearMonthDay = Period.of(2, 6, 15);
        System.out.println("Period of: " + yearMonthDay);

        LocalDate withPeriod = start.plus(yearMonthDay);
        System.out.println("Plus period: " + withPeriod);

        Duration duration = Duration.ofHours(2);
        System.out.println("Duration hours: " + duration.toHours());

        Duration minutes = Duration.ofMinutes(90);
        System.out.println("90 min to hours: " + minutes.toHours());

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 30);
        Duration workDay = Duration.between(startTime, endTime);
        System.out.println("Work day: " + workDay.toHours() + " hours");

        LocalDateTime dtStart = LocalDateTime.of(2024, 1, 1, 9, 0);
        LocalDateTime dtEnd = LocalDateTime.of(2024, 1, 1, 12, 30);
        Duration meeting = Duration.between(dtStart, dtEnd);
        System.out.println("Meeting: " + meeting.toMinutes() + " minutes");
    }

    private static void formatting() {
        System.out.println("\n=== Formatting ===");

        LocalDateTime dt = LocalDateTime.of(2024, 3, 15, 14, 30, 45);

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Pattern 1: " + dt.format(f1));

        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Pattern 2: " + dt.format(f2));

        DateTimeFormatter f3 = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        System.out.println("Pattern 3: " + dt.format(f3));

        DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE;
        System.out.println("ISO: " + LocalDate.now().format(iso));

        String parsed = "2024-05-20";
        LocalDate parsedDate = LocalDate.parse(parsed, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("Parsed: " + parsedDate);

        String custom = "20/05/2024";
        DateTimeFormatter spanish = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate spanishDate = LocalDate.parse(custom, spanish);
        System.out.println("Spanish: " + spanishDate);
    }

    private static void timezoneOperations() {
        System.out.println("\n=== Timezone Operations ===");

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Now with zone: " + now);

        ZonedDateTime madrid = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        System.out.println("Madrid: " + madrid);

        ZonedDateTime tokyo = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Tokyo: " + tokyo);

        ZonedDateTime madridTime = ZonedDateTime.of(
            LocalDateTime.of(2024, 6, 15, 12, 0),
            ZoneId.of("Europe/Madrid")
        );
        System.out.println("Madrid noon: " + madridTime);

        ZonedDateTime tokyoTime = madridTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println("Tokyo same instant: " + tokyoTime);

        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);

        Instant fromEpoch = Instant.ofEpochSecond(1700000000);
        System.out.println("From epoch: " + fromEpoch);

        ZonedDateTime fromInstant = instant.atZone(ZoneId.of("UTC"));
        System.out.println("At UTC: " + fromInstant);

        System.out.println("Available zones: " + ZoneId.getAvailableZoneIds().size());
    }

    private static void adjustmentsAndQueries() {
        System.out.println("\n=== Adjustments and Queries ===");

        LocalDate date = LocalDate.of(2024, 1, 15);

        LocalDate nextMonday = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("Next Monday: " + nextMonday);

        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last day of month: " + lastDayOfMonth);

        LocalDate firstInMonth = date.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY));
        System.out.println("First Friday: " + firstInMonth);

        LocalDate nextWorking = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println("Next or same Friday: " + nextWorking);

        boolean isFriday = date.getDayOfWeek() == DayOfWeek.FRIDAY;
        System.out.println("Is Friday: " + isFriday);

        int dayOfYear = date.get(ChronoField.DAY_OF_YEAR);
        System.out.println("Day of year: " + dayOfYear);

        LocalDate withAdjuster = date.with(new NextHoliday());
        System.out.println("With custom adjuster: " + withAdjuster);

        boolean supported = date.isSupported(ChronoUnit.HOURS);
        System.out.println("Hours supported: " + supported);
    }

    static class NextHoliday implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            LocalDate date = LocalDate.from(temporal);
            if (date.getMonth() == Month.DECEMBER && date.getDayOfMonth() >= 25) {
                return date.withYear(date.getYear() + 1).withMonth(1).withDayOfMonth(1);
            }
            return LocalDate.of(date.getYear(), 12, 25);
        }
    }
}