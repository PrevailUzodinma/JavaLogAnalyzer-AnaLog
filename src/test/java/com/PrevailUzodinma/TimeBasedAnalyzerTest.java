package com.PrevailUzodinma;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.strategy.TimeBasedAnalyzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeBasedAnalyzerTest {

    private List<LogEntry> sampleLogs;

    @BeforeEach
    void setUp() {
        sampleLogs = List.of(
                log("2024-04-01T09:00:00", "INFO", "System initialized successfully."),
                log("2024-04-01T09:30:00", "ERROR", "Disk failure detected."),
                log("2024-04-01T10:00:00", "WARN", "Low disk space warning."),
                log("2024-04-02T10:15:00", "INFO", "User logged in.") // Different day
        );
    }

    private LogEntry log(String datetime, String level, String message) {
        return new LogEntry(LocalDateTime.parse(datetime), level, message);
    }

    @Test
    void testAnalyzeByDayFindsCorrectLogs() {
        TimeBasedAnalyzer analyzer = new TimeBasedAnalyzer("day", LocalDateTime.parse("2024-04-01T00:00:00"));
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertEquals(3, result.size());
        assertTrue(result.stream().allMatch(log -> log.getTimestamp().toLocalDate().isEqual(
                LocalDateTime.parse("2024-04-01T00:00:00").toLocalDate()
        )));
    }

    @Test
    void testAnalyzeByHourFindsCorrectLogs() {
        TimeBasedAnalyzer analyzer = new TimeBasedAnalyzer("hour", LocalDateTime.parse("2024-04-01T09:00:00"));
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(log ->
                log.getTimestamp().getHour() == 9 &&
                        log.getTimestamp().toLocalDate().isEqual(LocalDateTime.parse("2024-04-01T00:00:00").toLocalDate())
        ));
    }

    @Test
    void testAnalyzeNoLogsForGivenDay() {
        TimeBasedAnalyzer analyzer = new TimeBasedAnalyzer("day", LocalDateTime.parse("2024-04-03T00:00:00"));
        List<LogEntry> result = analyzer.analyze(sampleLogs);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeEmptyLogList() {
        TimeBasedAnalyzer analyzer = new TimeBasedAnalyzer("day", LocalDateTime.parse("2024-04-01T00:00:00"));
        List<LogEntry> result = analyzer.analyze(List.of());

        assertTrue(result.isEmpty());
    }
}
