package com.PrevailUzodinma.reader;

import com.PrevailUzodinma.model.LogEntry;
import com.PrevailUzodinma.singleton.LogConfig;
import com.PrevailUzodinma.parser.LogParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Refactoring SimpleLogReader to implement LogParser instead
public class SimpleLogReader implements LogReader {
    private final LogParser parser;

    // Create a constructor, so the parser is initialized when the reader is called
    public SimpleLogReader(LogParser parser){
        this.parser = parser;
    }


    public List<LogEntry> readLogs(String filePath){
        // Make an arraylist to dynamically store all the entries on log file as they are parsed
        List<LogEntry> entries = new ArrayList<>();

        // Check if the file exists and is readable
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: Log file not found at path: " + filePath);
            return Collections.emptyList();
        }

        if (!file.canRead()) {
            System.out.println("Error: Log file cannot be read due to permission issues.");
            return Collections.emptyList();
        }

        if (file.length() == 0) {
            System.out.println("Warning: This log file is empty.");
            return Collections.emptyList();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Feed the line into the LogParser
                LogEntry entry = parser.parse(line);
                if (entry != null) {
                    // Then add the result into the list of entries
                    entries.add(entry);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: Failed to read log file due to I/O error: " + e.getMessage());
            return Collections.emptyList();  // Return empty list on error
        }

        return entries;
    }

    // New method to use LogConfig singleton and read logs dynamically
    public List<LogEntry> readLogs() {
        String pathFromConfig = LogConfig.getInstance().getLogFilePath();
        return readLogs(pathFromConfig);  // Call the original readLogs method
    }
}
