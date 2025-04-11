package analyzer;


import model.LogEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountByLevelAnalyzer implements LogAnalyzer{

    public void analyze(List<LogEntry> entries){
        HashMap<String, Integer> levelCounts = new HashMap<>();

        for (LogEntry entry : entries){
            // ENCAPSULATION IMPLEMENTATION: using the getter method to get the value of the private variable "level"
            String level = entry.getLevel();
        }
    }
}
