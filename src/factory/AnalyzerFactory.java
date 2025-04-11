package factory;

import analyzer.LogAnalyzer;

public interface AnalyzerFactory {
    LogAnalyzer createAnalyzer(String type);
}
