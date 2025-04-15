package factory;

import singleton.LogConfig;
import strategy.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInputAnalyzerFactory implements AnalyzerFactory {

    // Method to create analyzer based on user input
    @Override
    public LogAnalyzer createAnalyzer(String type, Scanner scanner) {
        String pattern = LogConfig.getInstance().getTimestampPattern();
        switch(type.toLowerCase()) {
            case "level":
                return new CountByLevelAnalyzer();
            case "keyword":
                System.out.print("Enter the keyword to search for: ");
                String keyword = scanner.nextLine(); // Dynamically input keyword
                return new KeywordSearchAnalyzer(keyword);
            case "day":
                System.out.print("Enter the date (yyyy-MM-dd) for which you want to analyze logs: ");
                String dayStr = scanner.nextLine();
                LocalDateTime day = LocalDateTime.parse(dayStr + "T00:00:00");  // Set the time to midnight
                return new DayBasedAnalyzer("day", day);

            case "hour":
                // Ask user for the specific hour they want to analyze (e.g., yyyy-MM-dd HH)
                System.out.print("Enter the date and hour (yyyy-MM-dd HH) for which you want to analyze logs: ");
                String hourStr = scanner.nextLine();

                // Retrieve the timestamp pattern from LogConfig singleton

                // Append ':00:00' to the input to represent minutes and seconds
                LocalDateTime hour = LocalDateTime.parse(hourStr, DateTimeFormatter.ofPattern(pattern));

                // Return TimeBasedAnalyzer with the parsed date-time
                return new DayBasedAnalyzer("hour", hour);


            case "timerange":
                // Get timestamp pattern from config
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);

                // Get start and end date and time from user, using the pattern from config
                System.out.print("Enter the start date and time (" + pattern + "): ");
                String startDateStr = scanner.nextLine();
                LocalDateTime startDate = LocalDateTime.parse(startDateStr, dtf); // Parse using config pattern

                System.out.print("Enter the end date and time (" + pattern + "): ");
                String endDateStr = scanner.nextLine();
                LocalDateTime endDate = LocalDateTime.parse(endDateStr, dtf); // Parse using config pattern

                return new TimeRangeAnalyzer(startDate, endDate);

            default:
                throw new IllegalArgumentException("Unknown analyzer type: " + type);
        }
    }
}
