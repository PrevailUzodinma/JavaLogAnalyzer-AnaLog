import reader.*;
import strategy.*;
import analyzer.*;

public class Main {
    public static void main(String[] args) {
        // objects should always be of the interface type, not the concrete implementation itself
        LogReader reader = new SimpleLogReader(new SimpleLogParser());
        var entries = reader.readLogs("logs/sample.log");

        LogAnalyzer analyzer = new KeywordSearchAnalyzer("server");
        analyzer.analyze(entries);
    }
}