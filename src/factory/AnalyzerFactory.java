package factory;

import analyzer.LogAnalyzer;
import model.LogEntry;

import java.util.List;
import java.util.Scanner;

public interface AnalyzerFactory {
    LogAnalyzer createAnalyzer(String type, Scanner scanner);
}
