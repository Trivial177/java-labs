package edu.course.lab04;

import java.util.HashMap;

public class SampleRegistry {

    private final HashMap<SampleId, DataSample> samples = new HashMap<>();

    public void put(SampleId id, DataSample sample) {
        samples.put(id, sample);
    }

    public DataSample get(SampleId id) {
        return samples.get(id);
    }

    public DataSample remove(SampleId id) {
        return samples.remove(id);
    }

    public boolean contains(SampleId id) {
        return samples.containsKey(id);
    }

    public int size() {
        return samples.size();
    }
}