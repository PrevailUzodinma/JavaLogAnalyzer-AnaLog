package factory;

import analyzer.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class UserInputAnalyzerFactory implements AnalyzerFactory {

    // Method to create analyzer based on user input
    @Override
    public LogAnalyzer createAnalyzer(String type, Scanner scanner) {
        switch(type.toLowerCase()) {
            case "level":
                return new CountByLevelLogAnalyzer();
            case "keyword":
                System.out.print("Enter the keyword to search for: ");
                String keyword = scanner.nextLine(); // Dynamically input keyword
                return new KeywordSearchAnalyzer(keyword);
            case "day":
                System.out.print("Enter the date format (e.g., yyyy-MM-dd): ");
                String dayFormat = scanner.nextLine();
                return new TimeBasedAnalyzer(dayFormat, "day");

            case "hour":
                System.out.print("Enter the date format (e.g., yyyy-MM-dd HH): ");
                String hourFormat = scanner.nextLine();
                return new TimeBasedAnalyzer(hourFormat, "hour");

            case "timerange":
                System.out.print("Enter the start date and time (yyyy-MM-dd HH:mm): ");
                String startDateStr = scanner.nextLine();
                LocalDateTime startDate = LocalDateTime.parse(startDateStr + ":00"); // Expect format: "yyyy-MM-dd HH:mm"

                System.out.print("Enter the end date and time (yyyy-MM-dd HH:mm): ");
                String endDateStr = scanner.nextLine();
                LocalDateTime endDate = LocalDateTime.parse(endDateStr + ":00");
                return new TimeRangeAnalyzer(startDate, endDate);

            default:
                throw new IllegalArgumentException("Unknown analyzer type: " + type);
        }
    }
}
