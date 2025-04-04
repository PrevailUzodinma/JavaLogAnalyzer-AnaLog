package reader;

import model.LogEntry;
import java.io.IOException;
import java.util.List;

public interface LogReader {
    // a method that reads logs from a file, the file path is passed as an argument
    List<LogEntry> readLogs(String filePath) throws IOException;
}
