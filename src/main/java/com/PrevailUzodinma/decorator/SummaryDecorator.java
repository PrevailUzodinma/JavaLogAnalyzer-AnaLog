package decorator;

import model.LogEntry;
import strategy.LogAnalyzer;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class SummaryDecorator extends AnalyzerDecorator {

    public SummaryDecorator(LogAnalyzer wrappedAnalyzer) {
        super(wrappedAnalyzer);
    }

    @Override
    public List<LogEntry> analyze(List<LogEntry> logs) {
        // First, analyze the logs using the wrapped analyzer
        List<LogEntry> filteredLogs = wrappedAnalyzer.analyze(logs);

        // Then, add the summary section
        System.out.println("\n📊 Summary Report:");
        System.out.println("Total logs analyzed: " + filteredLogs.size());

        // Unique log levels
        Set<String> levels = filteredLogs.stream()
                .map(log -> log.getLevel().toUpperCase())
                .collect(Collectors.toSet());
        System.out.println("Unique log levels: " + levels);

        // Most frequent level
        String mostCommonLevel = filteredLogs.stream()
                .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        System.out.println("Most frequent level: " + mostCommonLevel);

        // Most frequent keyword (excluding common words)
        String mostFrequentKeyword = filteredLogs.stream()
                .map(LogEntry::getMessage)
                .flatMap(msg -> Arrays.stream(msg.split("\\s+")))
                .map(word -> word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase())
                .filter(word -> word.length() > 3 && !List.of("the", "this", "that", "with", "from", "user", "null").contains(word))
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        System.out.println("Most frequent keyword: " + mostFrequentKeyword);

        // Time range
        Optional<LocalDateTime> earliest = filteredLogs.stream().map(LogEntry::getTimestamp).min(LocalDateTime::compareTo);
        Optional<LocalDateTime> latest = filteredLogs.stream().map(LogEntry::getTimestamp).max(LocalDateTime::compareTo);

        if (earliest.isPresent() && latest.isPresent()) {
            System.out.println("Date range: " + earliest.get().toLocalDate() + " to " + latest.get().toLocalDate());
        } else {
            System.out.println("Date range: N/A");
        }

        return filteredLogs; // Return the filtered logs after analysis
    }
}
