import reader.*;
import strategy.*;
import model.LogEntry;

import java.util.List;

public class Main{
    public static void main(String[] args) {

        // define our file path
        String filePath = "logs/sample.log";

        // define our reader object and choose simple reader, also indicate which parser to use
        SimpleLogReader logReader = new SimpleLogReader(new SimpleLogParser());

        // define where our Log entries will be placed
        List<LogEntry> logEntries = logReader.readLogs(filePath);

            for (LogEntry entry : logEntries) {
                System.out.println(entry);
            }
    }
}