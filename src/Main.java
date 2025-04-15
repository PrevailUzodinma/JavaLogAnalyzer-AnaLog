import strategy.*;
import factory.UserInputAnalyzerFactory;
import reader.*;
import singleton.LogConfig;
import model.LogEntry;
import parser.SimpleLogParser;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize LogManager and load logs

        LogConfig logManager = LogConfig.getInstance();
        logManager.setLogFilePath("logs/sample.log");
        String filePath = logManager.getLogFilePath();

        // Read logs using configured file path
        LogReader logReader = new SimpleLogReader(new SimpleLogParser());
        List<LogEntry> logs = logReader.readLogs(filePath);

        // Create an instance of the UserInputAnalyzerFactory
        UserInputAnalyzerFactory analyzerFactory = new UserInputAnalyzerFactory();
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the type of analysis they want to perform
        System.out.println("Choose the type of analysis you want to perform:");
        System.out.println("1. Count by Level");
        System.out.println("2. Keyword Search");
        System.out.println("3. Time-based Analysis (Day or Hour)");
        System.out.println("4. Time Range Analysis");

        String choice = scanner.nextLine().toLowerCase(); // Read user input as lowercase

        // Create the appropriate analyzer based on the user's choice and pass scanner for dynamic input
        LogAnalyzer analyzer = analyzerFactory.createAnalyzer(choice, scanner);

        // Perform the analysis and display results
        Map<?, ?> result;
        if (analyzer instanceof CountByLevelAnalyzer) {
            ((CountByLevelAnalyzer) analyzer).analyze( logs);
        } else if (analyzer instanceof KeywordSearchAnalyzer) {
            ((KeywordSearchAnalyzer) analyzer).analyze(logs); // Keyword is passed dynamically from factory
        } else if (analyzer instanceof TimeBasedAnalyzer) {
            ((TimeBasedAnalyzer) analyzer).analyze(logs);
        } else if (analyzer instanceof TimeRangeAnalyzer) {
            ((TimeRangeAnalyzer) analyzer).analyze(logs);
        } else {
            System.out.println("Invalid analyzer type.");
        }

        scanner.close();
    }
}
