package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        LocalDateTime startTime = LocalDateTime.now();

        execute();

        LocalDateTime finishTime = LocalDateTime.now();

        System.out.println("------");
        System.out.printf("Execution time: %s ms%n", ChronoUnit.MILLIS.between(startTime, finishTime));
    }

    private static void execute() throws IOException {
        String[] words = readAndPreprocessFile("src/edu/pro/txt/harry.txt");
        Map<String, Long> wordFrequencies = countWordFrequencies(words);
        printTopWords(wordFrequencies, 30);
    }

    private static String[] readAndPreprocessFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        content = content.replaceAll("[^A-Za-z ]", " ").toLowerCase(Locale.ROOT);
        return content.split("\\s+");
    }

    private static Map<String, Long> countWordFrequencies(String[] words) {
        return Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    private static void printTopWords(Map<String, Long> wordFrequencies, int limit) {
        wordFrequencies.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}