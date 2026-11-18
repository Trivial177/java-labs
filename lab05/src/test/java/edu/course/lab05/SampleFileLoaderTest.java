package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SampleFileLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void loadsCorrectFile() throws IOException {
        Path file = tempDir.resolve("samples.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-101;cat;camera-1;128;READY"
            ),
            StandardCharsets.UTF_8
        );

        SampleFileLoader loader = new SampleFileLoader();
        LoadResult result = loader.load(file);

        assertEquals(1, result.getSamples().size());
        assertEquals(0, result.getErrors().size());

        DataSample sample = result.getSamples().get(0);

        assertEquals("S-101", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals("camera-1", sample.getSource());
        assertEquals(128, sample.getFeatureCount());
        assertEquals(SampleStatus.READY, sample.getStatus());
    }

    @Test
    void loadsUnicodeText() throws IOException {
        Path file = tempDir.resolve("unicode.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-102;кот;камера-1;64;READY"
            ),
            StandardCharsets.UTF_8
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(1, result.getSamples().size());
        assertEquals("кот", result.getSamples().get(0).getLabel());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = tempDir.resolve("empty.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-103;;camera-1;128;READY"
            ),
            StandardCharsets.UTF_8
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(0, result.getSamples().size());
        assertEquals(1, result.getErrors().size());
        assertEquals("label", result.getErrors().get(0).getField());
    }

    @Test
    void reportsInvalidNumber() throws IOException {
        Path file = tempDir.resolve("invalid-number.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-104;cat;camera-1;bad;READY"
            ),
            StandardCharsets.UTF_8
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(1, result.getErrors().size());
        assertEquals("featureCount", result.getErrors().get(0).getField());
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = tempDir.resolve("unknown-status.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-105;cat;camera-1;128;UNKNOWN"
            ),
            StandardCharsets.UTF_8
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(1, result.getErrors().size());
        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void reportsWrongColumnCount() throws IOException {
        Path file = tempDir.resolve("columns.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-106;cat;camera-1;128"
            ),
            StandardCharsets.UTF_8
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(1, result.getErrors().size());
        assertEquals("row", result.getErrors().get(0).getField());
    }

    @Test
    void keepsCorrectRowsAndReportsInvalidRows() throws IOException {
        Path file = tempDir.resolve("mixed.txt");

        Files.write(
            file,
            List.of(
                "id;label;source;featureCount;status",
                "S-107;cat;camera-1;128;READY",
                "S-108;dog;camera-2;bad;READY",
                "S-109;bird;camera-3;64;PROCESSED"
            ),
            StandardCharsets.UTF_8
        );

        LoadResult result = new SampleFileLoader().load(file);

        assertEquals(2, result.getSamples().size());
        assertEquals(1, result.getErrors().size());
        assertEquals(3, result.getErrors().get(0).getLineNumber());
    }

    @Test
    void missingFileCausesDataLoadingException() {
        Path file = tempDir.resolve("missing.txt");

        DataLoadingException exception = assertThrows(
            DataLoadingException.class,
            () -> new SampleFileLoader().load(file)
        );

        assertNotNull(exception.getCause());
    }
}