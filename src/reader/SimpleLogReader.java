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