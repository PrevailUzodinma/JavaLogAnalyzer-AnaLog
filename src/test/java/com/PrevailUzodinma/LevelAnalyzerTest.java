package com.PrevailUzodinma;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.strategy.LevelAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelAnalyzerTest {

    private LevelAnalyzer analyzer;
    private List<LogEntry> sampleLogs;

    @BeforeEach
    void setUp() {
        analyzer = new LevelAnalyzer();

        sampleLogs = List.of(
                new LogEntry(LocalDateTime.parse("2024-04-01T10:00:00"), "INFO", "System started"),
                new LogEntry(LocalDateTime.parse("2024-04-01T10:05:00"), "ERROR", "NullPointerException"),
                new LogEntry(LocalDateTime.parse("2024-04-01T10:10:00"), "WARN", "Low memory"),
                new LogEntry(LocalDateTime.parse("2024-04-01T10:15:00"), "ERROR", "OutOfMemoryError")
        );
    }

    @Test
    void testAnalyzeGroupsAndReturnsAllLogs() {
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        // It should return all logs, just grouped/printed by level
        assertEquals(4, result.size());

        // Check that all original logs are present in the result
        assertTrue(result.containsAll(sampleLogs));
    }

    @Test
    void testAnalyzeWithEmptyList() {
        List<LogEntry> result = analyzer.analyze(List.of());
        assertTrue(result.isEmpty());
    }
}
