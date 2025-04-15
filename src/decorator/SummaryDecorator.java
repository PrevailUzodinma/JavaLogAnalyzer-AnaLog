package decorator;

import model.LogEntry;
import strategy.LogAnalyzer;

import java.util.List;

// Decorator that adds a summary after the analysis
public class SummaryDecorator implements LogAnalyzer {
    // define the analyzer to wrap
    private final LogAnalyzer wrappedAnalyzer;

    //set up the constructor, so it takes the analyzer to wrap at initialization
    public SummaryDecorator(LogAnalyzer wrappedAnalyzer) {
        this.wrappedAnalyzer = wrappedAnalyzer;
    }
    // POLYMORPHISM IN ACTION - Method overriding of analyze method from LogAnalyzer interface
    public void analyze(List<LogEntry> logs) {
        wrappedAnalyzer.analyze(logs); // Run the original analysis
        System.out.println("\n📝 Summary: Processed " + logs.size() + " log entries.");
    }
}
