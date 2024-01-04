# CookieLogAnalyzer
This project is a Java-based command-line application designed to analyze cookie logs. It provides functionality to read a log file, filter data based on a specified date, and identify the most frequently occurring cookies on that day. The application is built using Spring Boot and Maven, making it easy to manage dependencies and build the project.

## Key Features:
- Analyzes cookie logs for a specific date.
- Identifies the most frequently occurring cookies.
- Command-line interface for easy usage.

## Project Structure:
Main Class: CookieLogAnalyzerApplication
Test Class: CookieLogAnalyzerApplicationTests

Usage:
To run the application, execute the generated JAR file with the appropriate command-line arguments. For example:

``` java -jar CookieLogAnalyzer-0.0.1-SNAPSHOT.jar -f /path/to/your/cookie_log.csv -d 2023-12-23
