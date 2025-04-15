package decorator;

import model.LogEntry;
import strategy.LogAnalyzer;

import java.util.List;

public abstract class AnalyzerDecorator implements LogAnalyzer {
    protected final LogAnalyzer wrappedAnalyzer;

    public AnalyzerDecorator(LogAnalyzer wrappedAnalyzer) {
        this.wrappedAnalyzer = wrappedAnalyzer;
    }

    @Override
    public void analyze(List<LogEntry> logs) {
        wrappedAnalyzer.analyze(logs);
    }
}
