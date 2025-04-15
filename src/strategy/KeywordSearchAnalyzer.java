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
        if (entries.isEmpty()) {
            System.out.println("Oops! Sorry, there are no logs in this file to analyze.");
            return;
        }

        System.out.println("\n🔍 Logs containing keyword: \"" + keyword + "\"");

        // Create a new filtered list
        List<LogEntry> filteredEntries = new java.util.ArrayList<>();

        for (LogEntry entry : entries) {
            String message = entry.getMessage().toLowerCase(Locale.ROOT);
            if (message.contains(keyword)) {
                filteredEntries.add(entry);  // Keep the matching entry
                System.out.println(entry);   // Print it
            }
        }

        if (filteredEntries.isEmpty()) {
            System.out.println("No logs found with the keyword.");
        } else {
            System.out.println("\nTotal matches: " + filteredEntries.size());

            // Clear original list and add back filtered logs
            entries.clear();
            entries.addAll(filteredEntries);
        }
    }

}
