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
        // First run the original analysis
        super.analyze(logs);

        // Then add the summary section
        System.out.println("\n📊 Summary Report:");
        System.out.println("Total logs analyzed: " + logs.size());
    }
}
