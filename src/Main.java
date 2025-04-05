import reader.LogReader;
import reader.SimpleLogReader;
import model.LogEntry;

import java.util.List;

public class Main{
    public static void main(String[] args) {

        // define our file path
        String filePath = "logs/sample.log";

        // define our reader object and choose simple reader
        LogReader reader = new SimpleLogReader();

        try {
            List<LogEntry> logs = reader.readLogs(filePath);
            for (LogEntry entry : logs) {
                System.out.println(entry);
            }
        } catch (Exception e) {
            System.out.println("Failed to read logs: " + e.getMessage());
        }
    }
}