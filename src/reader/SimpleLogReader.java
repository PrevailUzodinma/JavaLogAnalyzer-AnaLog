package reader;
import model.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SimpleLogReader implements LogReader {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<LogEntry> readLogs(String filePath) throws IOException {
        // create a List to contain LogEntry objects, added as they are read line by line, so we use ArrayList
        List<LogEntry> entries = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            // declare a variable to store each line - log of the file
            String line;

            // while the line there is a line
            while ((line = br.readLine()) != null){
               // split it into parts, to extract the relevant field
                String[] parts = line.split(" ", 3);

                if (parts.length >= 3){
                    // extract each field into variable time, level and message
                    LocalDateTime time = LocalDateTime.parse(parts[0] + " " + parts[1], formatter);
                    String level = parts[2].split(" ")[0];
                    String message = parts[2].substring(level.length()).trim();

                    // pass them as arguments into LogEntry, and then add them to the list of LogEntries
                    entries.add(new LogEntry(time,level,message));
                }
            }
        }

        return entries;
    }
}
