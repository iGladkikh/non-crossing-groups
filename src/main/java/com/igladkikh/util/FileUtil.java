package com.igladkikh.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class FileUtil {
    public static final String OUTPUT_FILE_NAME = "---result.txt";

    private FileUtil() {
    }

    public static List<String> loadUniqueLinesFromFile(String inputFilePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath))) {
            return reader.lines()
                    .distinct()
                    .toList();
        }
    }

    public static void writeResultToFile(List<Set<String>> result) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME))) {
            writer.write("Количество групп с более чем одним элементом: %d\n".formatted(result.size()));
            for (int i = 0; i < result.size(); i++) {
                writer.write("Группа %d\n".formatted(i + 1));
                for (String line : result.get(i)) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
