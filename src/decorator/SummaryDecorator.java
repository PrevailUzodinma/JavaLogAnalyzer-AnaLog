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
    public void analyze(List<LogEntry> logs) {
        System.out.println("Before analysis, logs size: " + logs.size()); // Debugging
        super.analyze(logs);
        System.out.println("After analysis, logs size: " + logs.size()); // Debugging
        // Then add the summary section
        System.out.println("\n📊 Summary Report:");
        System.out.println("Total logs analyzed: " + logs.size());
    }
}
