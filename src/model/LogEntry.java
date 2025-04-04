package model;

// this class models each line in the log
public class LogEntry {
    // declare field variables and set them as private
    private String timestamp;
    private String level;
    private String message;
    // implement our constructor
    public LogEntry(String timestamp, String level, String message){
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
    }


}


