import factory.AnalyzerFactory;
import strategy.*;
import factory.UserInputAnalyzerFactory;
import reader.*;
import singleton.LogConfig;
import model.LogEntry;
import parser.*;
import java.util.List;
import java.util.Scanner;
import decorator.SummaryDecorator;

public class Main {
    public static void main(String[] args) {
        // Initialize LogManager and load logs - implementing my SINGLETON PATTERN
        LogConfig logManager = LogConfig.getInstance();
        logManager.setLogFilePath("logs/sample.log");
        String filePath = logManager.getLogFilePath();

        // Read logs using configured file path
        LogReader logReader = new SimpleLogReader(new SimpleLogParser());
        List<LogEntry> logs = logReader.readLogs(filePath);

        // Exit if the file is empty/wrong filePath/cannot be read due to file permissions.
        if (logs.isEmpty()) {
            return;
        }

        // Create an instance of the UserInputAnalyzerFactory using AnalyzerFactory Interface
        AnalyzerFactory analyzerFactory = new UserInputAnalyzerFactory();
        Scanner scanner = new Scanner(System.in);

        // Start the continuous loop
        String choice;
        while (true) {
            // Display the menu
            System.out.println("\n\nWelcome to the AnaLog - The Java Log Analyzer! (or type 'exit' to quit))");
            System.out.println("\nChoose an analysis type:");
            System.out.println("1. Level Analysis");
            System.out.println("2. Keyword Search Analysis");
            System.out.println("3. Day-based Analysis");
            System.out.println("4. Hour-based Analysis");
            System.out.println("5. Time range Analysis");
            System.out.print("Your choice: ");

            // Read user input
            choice = scanner.nextLine().toLowerCase();

            // Exit condition
            if (choice.equals("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

            // USING FACTORY DESIGN PATTERN: create the appropriate analyzer based on the user's choice and pass scanner for dynamic input
            LogAnalyzer baseAnalyzer = analyzerFactory.createAnalyzer(choice, scanner);

            // Apply decorator
            LogAnalyzer analyzer = new SummaryDecorator(baseAnalyzer);

            // Perform the analysis and display results
            if (analyzer != null) {
                analyzer.analyze(logs);
            } else {
                System.out.println("Invalid analyzer type.");
            }
        }

        scanner.close();  // Close the scanner when the loop ends
    }
}
