AnaLog - A Java Log File Analyzer
======================================

Project Overview
----------------

**AnaLog** is a Java-based Log Analyzer developed by **Prevail Uzodinma** as part of the coursework for the *Advanced Software Development* module. This project goes beyond fulfilling academic requirements---it serves as a practical demonstration of design principles, object-oriented programming, and architectural patterns applied in real-world Java development. It is designed to analyze and process log files. It offers multiple analysis options, including level-based, keyword-based, day-based, and time-range-based analysis. The tool uses Object-Oriented principles, design patterns such as Singleton, Factory, Strategy, and Decorator, and relies on Maven for project management and dependencies. The project is designed for maintainability, scalability, and extensibility, making it suitable for analyzing large log files with various filter criteria.


Features
--------

-   **Level-Based Analysis**: Analyzes logs by their severity levels (e.g., INFO, ERROR, DEBUG, etc.).

-   **Keyword Search Analysis**: Allows searching and filtering logs by specific keywords.

-   **Time-Based Analysis**: Analyzes logs based on the specific date and time of events.

-   **Time Range Analysis**: Provides analysis based on a specified time range (from start date to end date).

-   **Dynamic Summary**: Can generate a dynamic summary that includes total logs, unique levels, most frequent level/keyword, and date range.

-   **Object-Oriented Design**: Implements inheritance, polymorphism, and encapsulation.

-   **Design Patterns**: Utilizes Singleton, Factory, Strategy, and Decorator patterns to ensure flexibility and maintainability.


Requirements
------------

-   **JDK 21 or higher**: This project is built to work with JDK 21. You can download the latest JDK from [AdoptOpenJDK](https://adoptopenjdk.net/).

-   **Maven 3.6 or higher**: Maven is used for dependency management and building the project. Download Maven from [Maven Downloads](https://maven.apache.org/download.cgi).


Installation
------------

Follow these steps to set up the project on your local machine:

### 1\. Clone the Repository

Clone the repository to your local machine using Git:

```
git clone https://github.com/yourusername/LogAnalyzer.git
cd LogAnalyzer

```

### 2\. Set Up the Project

The project uses Maven for dependency management, so ensure Maven is installed. If you don't have Maven installed, follow the instructions on the [official Maven website](https://maven.apache.org/install.html).

Run the following Maven command to install the necessary dependencies:

```
mvn clean install

```

This will download the required libraries and packages and prepare the project for running.


Project Structure
-----------------

Here's an overview of the project directory structure:

```
LogAnalyzer/
├── README.md                # Project overview and documentation
├── pom.xml                  # Maven project configuration file
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── PrevailUzodinma/
│                   ├── Main.java               # Main entry point of the application
│                   ├── factory/                # Factory classes for creating analyzers
│                   ├── strategy/               # Strategy pattern for various analysis types
│                   ├── reader/                 # Log file reading classes
│                   ├── parser/                 # Parser classes for log format parsing
│                   ├── singleton/              # Singleton pattern for LogConfig
│                   ├── model/                  # Data models (LogEntry, etc.)
│                   ├── decorator/              # Decorator pattern for dynamic summaries
│    └── tests/                                 # Unit tests with JUNIT5
├── .gitignore               # Git ignore file for IDE and build artifacts
├── target/                  # Maven output (builds and packaged JAR)
└── logs/                    # Sample logs for testing

```


How to Run the Project
----------------------

### 1\. Build the Project

To compile and package the project into a `.jar` file, run the following command:

```
mvn clean package

```

This will create a `AnaLog.jar` file in the `target/` directory.

### 2\. Run the Application

Once the project is packaged, you can run the application using the following command from the `target/` directory:

```
java -jar AnaLog.jar <log-file-path>

```

Replace `<log-file-path>` with the path to the log file you wish to analyze. For example:

```
<<<<<<< HEAD
java -jar ./target/AnaLog.jar ./target/classes/logs/sample.log

```
Fallback: Use filepath `./target/classes/logs/sample.log` to make use of our sample log file. During the build Maven stores it in `target/classes/logs` directory.
=======
 java -jar target/AnaLog.jar ./logs/sample.log


```
Fallback: Use filepath `./logs/sample.log` to make use of our sample log file.
>>>>>>> 1cd5ea917932aee971780d5688ecd23309490256
### 3\. Interactive Menu

Upon running the application, you will be presented with a menu to choose an analysis type:

```
Welcome to the AnaLog - The Java Log Analyzer!
Choose an analysis type:
1. Level Analysis
2. Keyword Search Analysis
3. Day-based Analysis
4. Hour-based Analysis
5. Time range Analysis

```

You can select an analysis type and the tool will prompt for additional input based on your selection.


Usage
-----

### Sample Log Format

AnaLog expects each line of the log file to follow this format:

```
[2024-04-14 10:23:45] [INFO] Application started successfully
```

Where:

-   Timestamp = `[yyyy-MM-dd HH:mm:ss]`

-   Level = `[INFO]`, `[ERROR]`, `[DEBUG]`, etc.

-   Message = any free-text string

### Available Analysis Types

1.  **Level-Based Analysis**: Analyzes logs by severity level (e.g., INFO, ERROR).

2.  **Keyword Search Analysis**: Searches for specific keywords in log entries.

3.  **Day-Based Analysis**: Analyzes logs for a particular day.

4.  **Hour-Based Analysis**: Analyzes logs for a specific hour of the day.

5.  **Time Range Analysis**: Analyzes logs between a given start and end time.

### Summary Option

After selecting an analysis type, you will be asked whether you want a summary. If yes, the summary will be dynamically generated and include:

-   Total logs analyzed

-   Unique log levels

-   Most frequent level/keyword

-   Date range of logs


Design Patterns Used
--------------------

### 1\. **Singleton Pattern** - `LogConfig`

The `LogConfig` class is implemented as a Singleton to ensure only one instance of the configuration exists throughout the application. This helps in managing the log file path and any global configuration settings.

### 2\. **Factory Pattern** - `AnalyzerFactory`

The `AnalyzerFactory` is responsible for creating different types of log analyzers based on user input. This pattern ensures that the creation of analyzers is abstracted, making it easier to add new analysis types in the future.

### 3\. **Strategy Pattern** - Various Analysis Strategies

The Strategy pattern is used for the different analysis types (e.g., level analysis, time-based analysis). Each analysis type is encapsulated in its own strategy class, allowing the user to switch between different strategies dynamically.

### 4\. **Decorator Pattern** - `SummaryDecorator`

The `SummaryDecorator` pattern is used to add additional functionality (like generating a summary) to an existing analysis object without modifying its structure. This allows you to add or remove functionality dynamically.


Acknowledgments
---------------
Special thanks to the course lecturer and peers for feedback and support throughout the project.

-   [Maven](https://maven.apache.org/) for build automation.

-   [IntelliJ IDEA](https://www.jetbrains.com/idea/) for an amazing IDE.