package analyzer;

import model.LogEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountByLevelLogAnalyzer implements LogAnalyzer{

    public void analyze(List<LogEntry> entries){
        HashMap<String, Integer> levelCounts = new HashMap<>();

        for (LogEntry entry : entries){
            // ENCAPSULATION IMPLEMENTATION: using the getter method to get the value of the private variable "level"
            String level = entry.getLevel();
            levelCounts.put(level, levelCounts.getOrDefault(level, 0) + 1);
        }

        // Console Output for the analyzer
        System.out.println("Log Level Counts:");
        // for every entry in the hashmap, print
        for (Map.Entry<String, Integer> entry : levelCounts.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
