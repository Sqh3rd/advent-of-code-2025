package com.sqh3rd.inputparser;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class InputParser {
    @SneakyThrows
    static public <T> List<T> serializeInputByLines(Function<String, T> inputSerializer, Class<?> clazz) {
        var inputPath = Path.of(clazz.getResource("/input.txt").toURI());
        try (var lines = Files.lines(inputPath)) {
            return lines
                    .map(String::trim)
                    .map(inputSerializer)
                    .toList();
        }
    }

    @SneakyThrows
    static public <T> List<T> serializeInputBySeparator(Function<String, T> inputSerializer, String separator, Class<?> clazz) {
        var inputPath = Path.of(clazz.getResource("/input.txt").toURI());
        return Stream.of(Files.readString(inputPath))
                .map(it -> it.split(separator))
                .flatMap(Arrays::stream)
                .map(inputSerializer)
                .toList();
    }
}