package com.project.cookieloganalyzer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class CookieLogAnalyzerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CookieLogAnalyzerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String fileName = null;
        String dataStr = null;

        for (int i = 0; i < args.length; i++) {

            if ("-f".equals(args[i]) && i + 1 < args.length) {
                fileName = args[i + 1];
            } else if ("-d".equals(args[i]) && i + 1 < args.length) {
                dataStr = args[i + 1];
            }
        }

        if (fileName == null || dataStr == null) {
            System.out.println("Usage: -f <filename> -d <date>");
            System.exit(1);
        }

        /**Parse date*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = dateFormat.parse(dataStr);

        /**Read and process the log file*/
        Map<String, Integer> cookieCounts = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String cookie = parts[0];
                Date timestamp = dateFormat.parse(parts[1]);

                if (isSameDay(timestamp, date)) {
                    cookieCounts.put(cookie, cookieCounts.getOrDefault(cookie, 0) + 1);
                } else {
                    break;
                }
            }

        }
        int maxCount = Collections.max(cookieCounts.values());
        cookieCounts.entrySet().stream()
            .filter(entry -> entry.getValue() ==maxCount)
            .map(Map.Entry::getKey)
            .forEach(System.out::println);
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
            calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
            calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

}
