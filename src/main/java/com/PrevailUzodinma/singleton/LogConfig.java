package com.PrevailUzodinma.singleton;

import com.PrevailUzodinma.model.LogEntry;

import java.util.List;

public class LogConfig {
    private static LogConfig instance;

    // Application-wide config
    private String logFilePath = "logs/sample.log";
    private String timestampPattern = "yyyy-MM-dd HH"; // Example pattern
    private String defaultLogLevel = "INFO";

    // Private constructor to prevent instantiation
    private LogConfig() {}

    // Get the instance of LogConfig (singleton)
    public static LogConfig getInstance() {
        if (instance == null) {
            instance = new LogConfig();
        }
        return instance;
    }

    // Getters and Setters for config values
    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getTimestampPattern() {
        return timestampPattern;
    }

    public void setTimestampPattern(String timestampPattern) {
        this.timestampPattern = timestampPattern;
    }

    public String getDefaultLogLevel() {
        return defaultLogLevel;
    }

    public void setDefaultLogLevel(String defaultLogLevel) {
        this.defaultLogLevel = defaultLogLevel;
    }
}
