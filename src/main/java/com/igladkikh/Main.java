package com.igladkikh;

import com.igladkikh.service.Service;
import com.igladkikh.storage.GroupStorage;
import com.igladkikh.storage.PositionStorage;
import com.igladkikh.util.FileUtil;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Main {
    private static final Service service = new Service(new PositionStorage(), new GroupStorage());

    public static void main(String[] args) throws IOException {
        long appStartTime = Instant.now().toEpochMilli();

        // проверяем наличие аргументов
        if (args.length == 0) {
            System.out.println("Не указано расположение исходного файла");
            return;
        }
        String inputFilePath = args[0];

        SoftReference<List<String>> lines = new SoftReference<>(FileUtil.loadUniqueLinesFromFile(inputFilePath));
        System.out.printf("Данные из файла: '%s' загружены за %d мс.\n", inputFilePath, Instant.now().toEpochMilli() - appStartTime);

        service.add(Objects.requireNonNull(lines.get()));
        FileUtil.writeResultToFile(service.getGroups());

        System.out.printf("Результат записан в файл: %s\n", FileUtil.OUTPUT_FILE_NAME);
        System.out.printf("Время работы программы: %d мс\n", Instant.now().toEpochMilli() - appStartTime);

        Runtime runtime = Runtime.getRuntime();
        // Объём памяти, доступный JVM в данное время
        long totalMemory = runtime.totalMemory();
        System.out.printf("Объём памяти, доступный JVM: %d Mb\n", totalMemory / 1024 / 1024);
        // Количество памяти JVM, свободное от занятых объектами
        long freeMemory = runtime.freeMemory();
        System.out.printf("Свободный объём памяти: %d Mb\n", freeMemory / 1024 / 1024);
        // Объём памяти, которую занимают объекты
        long usedMemory = totalMemory - freeMemory;
        System.out.printf("Используемая память: %d Mb\n", usedMemory / 1024 / 1024);
    }
}