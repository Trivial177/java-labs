package edu.course.lab03;

public class MeanCenteringNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        double sum = 0;

        for (double value : values) {
            sum += value;
        }

        double mean = sum / values.length;

        double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - mean;
        }

        return result;
    }
}