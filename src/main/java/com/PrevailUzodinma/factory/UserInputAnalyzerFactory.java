package com.PrevailUzodinma.factory;

import com.PrevailUzodinma.singleton.LogConfig;
import com.PrevailUzodinma.strategy.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInputAnalyzerFactory implements AnalyzerFactory {

    // Method to create analyzer based on user input
    @Override
    public LogAnalyzer createAnalyzer(String type, Scanner scanner) {
        String pattern = LogConfig.getInstance().getTimestampPattern();
        switch (type.toLowerCase()) {
            case "1":
                return new LevelAnalyzer();

            case "2":
                System.out.print("Enter the keyword to search for: ");
                String keyword = scanner.nextLine(); // Dynamically input keyword
                if (keyword.isEmpty()) {
                    System.out.println("Invalid keyword. Please try again.");
                    return null; // Returning null will send control back to main
                }
                return new KeywordSearchAnalyzer(keyword);

            case "3":
                return createDayBasedAnalyzer(scanner); // Day-based analysis

            case "4":
                return createHourBasedAnalyzer(scanner); // Hour-based analysis

            case "5":
                return createTimeRangeAnalyzer(scanner); // Time range analysis

            default:
                return null;
        }
    }

    // Method for day-based analysis with input validation
    private LogAnalyzer createDayBasedAnalyzer(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter the date (yyyy-MM-dd) for which you want to analyze logs: ");
                String dayStr = scanner.nextLine();
                LocalDateTime day = LocalDateTime.parse(dayStr + "T00:00:00");  // Set the time to midnight
                return new TimeBasedAnalyzer("day", day);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the correct format (yyyy-MM-dd).");
            }
        }
    }

    // Method for hour-based analysis with input validation
    private LogAnalyzer createHourBasedAnalyzer(Scanner scanner) {
        String pattern = LogConfig.getInstance().getTimestampPattern();
        while (true) {
            try {
                System.out.print("Enter the date and hour (yyyy-MM-dd HH) for which you want to analyze logs: ");
                String hourStr = scanner.nextLine();
                LocalDateTime hour = LocalDateTime.parse(hourStr, DateTimeFormatter.ofPattern(pattern));
                return new TimeBasedAnalyzer("hour", hour);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date-time format. Please enter the date and hour in the correct format (yyyy-MM-dd HH).");
            }
        }
    }

    // Method for time-range analysis with input validation
    private LogAnalyzer createTimeRangeAnalyzer(Scanner scanner) {
        String pattern = LogConfig.getInstance().getTimestampPattern();
        while (true) {
            try {
                // Get start and end date and time from user, using the pattern from config
                System.out.print("Enter the start date and time (" + pattern + "): ");
                String startDateStr = scanner.nextLine();
                LocalDateTime startDate = LocalDateTime.parse(startDateStr, DateTimeFormatter.ofPattern(pattern));

                System.out.print("Enter the end date and time (" + pattern + "): ");
                String endDateStr = scanner.nextLine();
                LocalDateTime endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern(pattern));

                return new TimeRangeAnalyzer(startDate, endDate);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date-time format. Please enter the start and end dates in the correct format (" + pattern + ").");
            }
        }
    }

    // Method to handle the summary input and validation - to keep the main program clean.
    // I am moving the logic for user input for summary option and input validation in the factory layer
    public String getSummaryInput(Scanner scanner) {
        String showSummary;
        while (true) {
            System.out.print("Do you want to see a summary report after the analysis is done? (y/n): ");
            showSummary = scanner.nextLine().toLowerCase();

            if (showSummary.equals("y") || showSummary.equals("n")) {
                return showSummary;  // Return the valid input
            } else {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }

        }
    }
}