package edu.course.lab02;

public class DataSample {

    private String id;
    private String label;
    private SampleStatus status;
    private double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.isEmpty()) {
        throw new IllegalArgumentException();
        }

        if (label == null || label.isEmpty()) {
        throw new IllegalArgumentException();
        }

        if (status == null) {
            throw new IllegalArgumentException();
        }

        if (features == null || features.length == 0) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.label = label;
        this.status = status;

        this.features = new double[features.length];

        for (int i = 0; i < features.length; i++) {
            this.features[i] = features[i];
        }
    }

    public void changeStatus(SampleStatus status) {
        if (status == null) {
            throw new IllegalArgumentException();
        }

        this.status = status;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeatures() {
        double sum = 0;

        for (double feature : features) {
            sum += feature;
        }

        return sum / features.length;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        double[] copy = new double[features.length];

        for (int i = 0; i < features.length; i++) {
            copy[i] = features[i];
        }

        return copy;
    }
}