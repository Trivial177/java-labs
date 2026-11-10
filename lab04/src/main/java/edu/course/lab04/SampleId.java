package edu.course.lab04;

import java.util.Objects;

public final class SampleId {

    private final String datasetName;
    private final int sampleNumber;

    public SampleId(String datasetName, int sampleNumber) {
        this.datasetName = datasetName;
        this.sampleNumber = sampleNumber;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public int getSampleNumber() {
        return sampleNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof SampleId)) {
            return false;
        }

        SampleId sampleId = (SampleId) object;

        return sampleNumber == sampleId.sampleNumber
            && Objects.equals(datasetName, sampleId.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datasetName, sampleNumber);
    }
}