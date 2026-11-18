package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReadingTest {

    @TempDir
    Path tempDir;

    @Test
    void readsUtf8File() throws IOException {
        Path file = tempDir.resolve("numbers.txt");

        Files.write(
            file,
            List.of("10", "bad", "20"),
            StandardCharsets.UTF_8
        );

        List<String> lines = Files.readAllLines(
            file,
            StandardCharsets.UTF_8
        );

        assertEquals(List.of("10", "bad", "20"), lines);
    }

    @Test
    void convertsValidNumbers() {
        List<String> lines = List.of("10", "20");
        List<Integer> numbers = new ArrayList<>();

        for (String line : lines) {
            numbers.add(Integer.parseInt(line));
        }

        assertEquals(List.of(10, 20), numbers);
    }

    @Test
    void badStringCausesNumberFormatException() {
        assertThrows(
            NumberFormatException.class,
            () -> Integer.parseInt("bad")
        );
    }
}