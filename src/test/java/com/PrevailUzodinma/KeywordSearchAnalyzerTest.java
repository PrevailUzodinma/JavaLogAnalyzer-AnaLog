package com.PrevailUzodinma;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.strategy.KeywordSearchAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordSearchAnalyzerTest {

    private List<LogEntry> sampleLogs;

    @BeforeEach
    void setUp() {
        sampleLogs = List.of(
                log("2024-04-01T09:00:00", "INFO", "System initialized successfully."),
                log("2024-04-01T09:30:00", "ERROR", "Disk failure detected."),
                log("2024-04-01T10:00:00", "WARN", "Low disk space warning."),
                log("2024-04-01T10:15:00", "INFO", "User logged in.")
        );
    }

    private LogEntry log(String datetime, String level, String message) {
        return new LogEntry(LocalDateTime.parse(datetime), level, message);
    }

    @Test
    void testAnalyzeFindsMatchingLogs() {
        KeywordSearchAnalyzer analyzer = new KeywordSearchAnalyzer("disk");
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(log -> log.getMessage().toLowerCase().contains("disk")));
    }

    @Test
    void testAnalyzeIsCaseInsensitive() {
        KeywordSearchAnalyzer analyzer = new KeywordSearchAnalyzer("UsEr");
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertEquals(1, result.size());
        assertEquals("User logged in.", result.get(0).getMessage());
    }

    @Test
    void testAnalyzeWithNoMatchReturnsEmptyList() {
        KeywordSearchAnalyzer analyzer = new KeywordSearchAnalyzer("network");
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeWithEmptyLogList() {
        KeywordSearchAnalyzer analyzer = new KeywordSearchAnalyzer("disk");
        List<LogEntry> result = analyzer.analyze(List.of());

        assertTrue(result.isEmpty());
    }
}
