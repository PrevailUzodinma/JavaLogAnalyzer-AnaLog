package analyzer;

import model.LogEntry;
import java.util.List;

public class KeywordSearchAnalyzer implements LogAnalyzer{
    private final String keyword;

    public KeywordSearchAnalyzer(String keyword) {
        this.keyword = keyword;
    }

    public void analyze(List<LogEntry> entries) {
        long count = entries.stream()
                .filter(entry -> entry.getMessage().contains(keyword))
                .count();

        // Console the output
        System.out.printf("\nNumber of logs containing the keyword '%s': %d%n", keyword, count);
    }
}
