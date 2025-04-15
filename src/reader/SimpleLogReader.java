package reader;

import model.LogEntry;
import singleton.LogConfig;
import strategy.LogParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Refactoring SimpleLogReader to implement LogParser instead

public class SimpleLogReader implements LogReader {
    private final LogParser parser;
    // create a constructor, so the parser is initialized when the reader is called
    public SimpleLogReader(LogParser parser){
        this.parser = parser;
    }

    public List<LogEntry> readLogs(String filePath){
        // make an arraylist to dynamically store all the entries on log file as they are parsed
        List<LogEntry> entries = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            while((line = reader.readLine()) != null) {
                // feed the line into the LogParser
                LogEntry entry = parser.parse(line);
                if (entry != null){
                    // then add the result into the list of entries
                    entries.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    // New method to use LogConfig singleton and read logs dynamically
    public List<LogEntry> readLogs() {
        String pathFromConfig = LogConfig.getInstance().getLogFilePath();
        return readLogs(pathFromConfig);  // Call the original readLogs method
    }
}

