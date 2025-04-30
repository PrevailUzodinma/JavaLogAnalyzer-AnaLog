package com.PrevailUzodinma;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.strategy.TimeRangeAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeRangeAnalyzerTest {

    private List<LogEntry> sampleLogs;

    @BeforeEach
    void setUp() {
        sampleLogs = List.of(
                log("2024-04-01T09:00:00", "INFO", "System initialized successfully."),
                log("2024-04-01T09:30:00", "ERROR", "Disk failure detected."),
                log("2024-04-01T10:00:00", "WARN", "Low disk space warning."),
                log("2024-04-02T10:15:00", "INFO", "User logged in.")
        );
    }

    private LogEntry log(String datetime, String level, String message) {
        return new LogEntry(LocalDateTime.parse(datetime), level, message);
    }

    @Test
    void testAnalyzeInRange() {
        // Define a time range from 2024-04-01T09:00:00 to 2024-04-01T10:00:00
        TimeRangeAnalyzer analyzer = new TimeRangeAnalyzer(
                LocalDateTime.parse("2024-04-01T09:00:00"),
                LocalDateTime.parse("2024-04-01T10:00:00")
        );
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertEquals(3, result.size()); // Should return logs from 9 AM to 10 AM
        assertTrue(result.stream().allMatch(log ->
                log.getTimestamp().isAfter(LocalDateTime.parse("2024-04-01T09:00:00").minusSeconds(1)) &&
                        log.getTimestamp().isBefore(LocalDateTime.parse("2024-04-01T10:00:00").plusSeconds(1))
        ));
    }

    @Test
    void testAnalyzeOutOfRange() {
        // Define a time range that truly matches no logs
        TimeRangeAnalyzer analyzer = new TimeRangeAnalyzer(
                LocalDateTime.parse("2024-04-03T00:00:00"),
                LocalDateTime.parse("2024-04-03T23:59:59")
        );
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeEmptyLogList() {
        TimeRangeAnalyzer analyzer = new TimeRangeAnalyzer(
                LocalDateTime.parse("2024-04-01T09:00:00"),
                LocalDateTime.parse("2024-04-01T10:00:00")
        );
        List<LogEntry> result = analyzer.analyze(List.of());

        assertTrue(result.isEmpty());
    }
}
