package com.PrevailUzodinma.factory;

import com.PrevailUzodinma.strategy.LogAnalyzer;
import com.PrevailUzodinma.model.LogEntry;

import java.util.List;
import java.util.Scanner;

public interface AnalyzerFactory {
    LogAnalyzer createAnalyzer(String type, Scanner scanner);
    String getSummaryInput(Scanner scanner);
}
