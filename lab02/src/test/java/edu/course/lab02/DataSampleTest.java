package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataSampleTest {

    @Test
    void createsCorrectDataSample() {
        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.NEW,
            new double[]{1.0, 2.0, 3.0}
        );

        assertEquals("sample-1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
    }

    @Test
    void throwsExceptionForNullId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DataSample(
                null,
                "cat",
                SampleStatus.NEW,
                new double[]{1.0, 2.0}
            )
        );
    }

    @Test
    void throwsExceptionForEmptyId() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DataSample(
                "",
                "cat",
                SampleStatus.NEW,
                new double[]{1.0, 2.0}
            )
        );
    }

    @Test
    void throwsExceptionForNullLabel() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DataSample(
                "sample-1",
                null,
                SampleStatus.NEW,
                new double[]{1.0, 2.0}
            )
        );
    }

    @Test
    void throwsExceptionForEmptyLabel() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DataSample(
                "sample-1",
                "",
                SampleStatus.NEW,
                new double[]{1.0, 2.0}
            )
        );
    }

    @Test
    void changesStatus() {
        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.NEW,
            new double[]{1.0, 2.0}
        );

        sample.changeStatus(SampleStatus.READY);

        assertEquals(SampleStatus.READY, sample.getStatus());
    }

    @Test
    void returnsTrueWhenSampleIsReady() {
        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.READY,
            new double[]{1.0, 2.0}
        );

        assertTrue(sample.isReady());
    }

    @Test
    void returnsFalseWhenSampleIsNotReady() {
        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.NEW,
            new double[]{1.0, 2.0}
        );

        assertFalse(sample.isReady());
    }

    @Test
    void calculatesAverageFeatures() {
        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.NEW,
            new double[]{2.0, 4.0, 6.0}
        );

        assertEquals(4.0, sample.averageFeatures());
    }

    @Test
    void copiesFeaturesInConstructor() {
        double[] features = {1.0, 2.0, 3.0};

        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.NEW,
            features
        );

        features[0] = 100.0;

        assertEquals(1.0, sample.getFeatures()[0]);
    }

    @Test
    void returnsCopyOfFeatures() {
        DataSample sample = new DataSample(
            "sample-1",
            "cat",
            SampleStatus.NEW,
            new double[]{1.0, 2.0, 3.0}
        );

        double[] features = sample.getFeatures();
        features[0] = 100.0;

        assertEquals(1.0, sample.getFeatures()[0]);
    }
}