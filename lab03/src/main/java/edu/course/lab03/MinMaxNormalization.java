package edu.course.lab03;

public class MinMaxNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        double min = values[0];
        double max = values[0];

        for (double value : values) {
            if (value < min) {
                min = value;
            }

            if (value > max) {
                max = value;
            }
        }

        double[] result = new double[values.length];

        if (min == max) {
            return result;
        }

        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - min) / (max - min);
        }

        return result;
    }
}