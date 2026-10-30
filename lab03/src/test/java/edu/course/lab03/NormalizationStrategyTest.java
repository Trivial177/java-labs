package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NormalizationStrategyTest {

    @Test
    void minMaxNormalizesValues() {
        NormalizationStrategy strategy = new MinMaxNormalization();

        double[] result = strategy.normalize(
            new double[]{10.0, 20.0, 30.0}
        );

        assertArrayEquals(
            new double[]{0.0, 0.5, 1.0},
            result
        );
    }

    @Test
    void minMaxDoesNotChangeInputArray() {
        double[] values = {10.0, 20.0, 30.0};

        NormalizationStrategy strategy = new MinMaxNormalization();
        strategy.normalize(values);

        assertArrayEquals(
            new double[]{10.0, 20.0, 30.0},
            values
        );
    }

    @Test
void meanCenteringNormalizesValues() {
    NormalizationStrategy strategy =
        new MeanCenteringNormalization();

    double[] result = strategy.normalize(
        new double[]{10.0, 20.0, 30.0}
    );

    assertArrayEquals(
        new double[]{-10.0, 0.0, 10.0},
        result
        );
    }

@Test
void meanCenteringDoesNotChangeInputArray() {
    double[] values = {10.0, 20.0, 30.0};

    NormalizationStrategy strategy =
        new MeanCenteringNormalization();

    strategy.normalize(values);

    assertArrayEquals(
        new double[]{10.0, 20.0, 30.0},
        values
        );
    }
    @Test
void featureProcessorWorksWithDifferentStrategies() {
    FeatureProcessor processor =
        new FeatureProcessor(new MinMaxNormalization());

    assertArrayEquals(
        new double[]{0.0, 0.5, 1.0},
        processor.process(new double[]{10.0, 20.0, 30.0})
        );

    processor =
        new FeatureProcessor(new MeanCenteringNormalization());

    assertArrayEquals(
        new double[]{-10.0, 0.0, 10.0},
        processor.process(new double[]{10.0, 20.0, 30.0})
        );
    }
}