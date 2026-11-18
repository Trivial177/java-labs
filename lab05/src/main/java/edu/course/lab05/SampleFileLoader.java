package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SampleFileLoader {

    private static final String EXPECTED_HEADER =
        "id;label;source;featureCount;status";

    public LoadResult load(Path path) {
        List<String> lines;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException(
                "Cannot read " + path,
                e
            );
        }

        List<DataSample> samples = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        if (lines.isEmpty()) {
            errors.add(
                new ValidationError(
                    1,
                    "header",
                    "Header is missing"
                )
            );

            return new LoadResult(samples, errors);
        }

        if (!lines.get(0).equals(EXPECTED_HEADER)) {
            errors.add(
                new ValidationError(
                    1,
                    "header",
                    "Invalid header"
                )
            );

            return new LoadResult(samples, errors);
        }

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            if (line.isEmpty()) {
                continue;
            }

            String[] fields = line.split(";", -1);
            int lineNumber = i + 1;

            if (fields.length != 5) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "row",
                        "Expected 5 fields"
                    )
                );
                continue;
            }

            String id = fields[0];
            String label = fields[1];
            String source = fields[2];
            String featureCountText = fields[3];
            String statusText = fields[4];

            if (id.isEmpty()) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "id",
                        "Field is empty"
                    )
                );
                continue;
            }

            if (label.isEmpty()) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "label",
                        "Field is empty"
                    )
                );
                continue;
            }

            if (source.isEmpty()) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "source",
                        "Field is empty"
                    )
                );
                continue;
            }

            if (featureCountText.isEmpty()) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "featureCount",
                        "Field is empty"
                    )
                );
                continue;
            }

            if (statusText.isEmpty()) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "status",
                        "Field is empty"
                    )
                );
                continue;
            }

            int featureCount;

            try {
                featureCount = Integer.parseInt(featureCountText);
            } catch (NumberFormatException e) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "featureCount",
                        "Invalid number"
                    )
                );
                continue;
            }

            

            SampleStatus status;

            try {
                status = SampleStatus.valueOf(statusText);
            } catch (IllegalArgumentException e) {
                errors.add(
                    new ValidationError(
                        lineNumber,
                        "status",
                        "Unknown status"
                    )
                );
                continue;
            }

            DataSample sample = new DataSample(
                id,
                label,
                source,
                featureCount,
                status
            );

            samples.add(sample);
        }

        return new LoadResult(samples, errors);
    }
}