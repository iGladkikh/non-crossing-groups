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

        SoftReference<List<String>> lines =
                new SoftReference<>(FileUtil.loadUniqueLinesFromFile(inputFilePath));
        System.out.printf("Данные из файла: '%s' загружены за %d мс.\n", inputFilePath,
                Instant.now().toEpochMilli() - appStartTime);

        service.add(Objects.requireNonNull(lines.get()));
        FileUtil.writeResultToFile(service.getGroups());

        System.out.printf("Результат записан в файл: %s\n", FileUtil.OUTPUT_FILE_NAME);
        System.out.printf("Время работы программы: %d мс", Instant.now().toEpochMilli() - appStartTime);
    }
}