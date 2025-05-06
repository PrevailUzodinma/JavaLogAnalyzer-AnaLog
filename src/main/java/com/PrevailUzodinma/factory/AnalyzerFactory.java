package com.PrevailUzodinma.factory;

import com.PrevailUzodinma.strategy.LogAnalyzerStrategy;

import java.util.Scanner;

public interface AnalyzerFactory {
    LogAnalyzerStrategy createAnalyzer(String type, Scanner scanner);
    String getSummaryInput(Scanner scanner);
}
