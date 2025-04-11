import reader.*;
import strategy.*;
import analyzer.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // objects should always be of the interface type, not the concrete implementation itself
        LogReader reader = new SimpleLogReader(new SimpleLogParser());
        var entries = reader.readLogs("logs/sample.log");

        LogAnalyzer analyzer = new TimeRangeAnalyzer(
                LocalDateTime.of(2025, 4, 11, 9, 0),
                LocalDateTime.of(2025, 4, 11, 16, 0)
        );

        analyzer.analyze(entries);

    }
}