package strategy;

import model.LogEntry;
import java.util.List;
import java.util.Locale;

public class KeywordSearchAnalyzer implements LogAnalyzer {
    private final String keyword;

    public KeywordSearchAnalyzer(String keyword) {
        this.keyword = keyword.toLowerCase(Locale.ROOT);
    }

    public void analyze(List<LogEntry> entries) {
        System.out.println("\n🔍 Logs containing keyword: \"" + keyword + "\"");

        long matchCount = 0;

        for (LogEntry entry : entries) {
            String message = entry.getMessage().toLowerCase(Locale.ROOT);
            if (message.contains(keyword)) {
                System.out.println(entry); // This will print timestamp + level + message
                matchCount++;
            }
        }

        if (matchCount == 0) {
            System.out.println("No logs found with the keyword.");
        } else {
            System.out.println("\nTotal matches: " + matchCount);
        }
    }
}
