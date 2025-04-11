package reader;

import model.LogEntry;
import strategy.LogParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Refactoring SimpleLogReader to implement LogParser instead

public class SimpleLogReader {
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
                LogEntry entry = parser.parse(line);
                if (entry != null){
                    entries.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}
