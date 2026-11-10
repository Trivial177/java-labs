package edu.course.lab04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.HashMap;
import java.util.Objects;
class SampleRegistryTest {

    @Test
    void putsAndGetsSample() {
        SampleRegistry registry = new SampleRegistry();

        SampleId id = new SampleId("dataset", 1);
        DataSample sample = new DataSample("cat");

        registry.put(id, sample);

        assertEquals(sample, registry.get(id));
    }

    @Test
    void removesSample() {
        SampleRegistry registry = new SampleRegistry();

        SampleId id = new SampleId("dataset", 1);
        DataSample sample = new DataSample("cat");

        registry.put(id, sample);
        registry.remove(id);

        assertFalse(registry.contains(id));
    }

    @Test
    void checksKeyAndSize() {
        SampleRegistry registry = new SampleRegistry();

        SampleId id = new SampleId("dataset", 1);

        registry.put(id, new DataSample("cat"));

        assertTrue(registry.contains(id));
        assertEquals(1, registry.size());
    }
    @Test
    void equalKeysFindSameValue() {
        SampleRegistry registry = new SampleRegistry();

        SampleId firstId = new SampleId("dataset", 1);
        SampleId secondId = new SampleId("dataset", 1);

        DataSample sample = new DataSample("cat");

        registry.put(firstId, sample);

    assertEquals(sample, registry.get(secondId));
    }

    @Test
    void equalKeyReplacesValueWithoutIncreasingSize() {
        SampleRegistry registry = new SampleRegistry();

        SampleId firstId = new SampleId("dataset", 1);
        SampleId secondId = new SampleId("dataset", 1);

        DataSample firstSample = new DataSample("cat");
        DataSample secondSample = new DataSample("dog");

        registry.put(firstId, firstSample);
        registry.put(secondId, secondSample);

        assertEquals(secondSample, registry.get(firstId));
        assertEquals(1, registry.size());
    }
    @Test
    void sampleIdIsNotEqualToNullOrOtherType() {
        SampleId id = new SampleId("dataset", 1);

        assertFalse(id.equals(null));
        assertFalse(id.equals("dataset"));
    }
    @Test
    void differentKeysWithSameHashCodeStoreDifferentValues() {
        SampleRegistry registry = new SampleRegistry();

        SampleId firstId = new SampleId("Aa", 1);
        SampleId secondId = new SampleId("BB", 1);

        DataSample firstSample = new DataSample("cat");
        DataSample secondSample = new DataSample("dog");

        assertNotEquals(firstId, secondId);
        assertEquals(firstId.hashCode(), secondId.hashCode());

        registry.put(firstId, firstSample);
        registry.put(secondId, secondSample);

        assertEquals(firstSample, registry.get(firstId));
        assertEquals(secondSample, registry.get(secondId));
        assertEquals(2, registry.size());
    }
    @Test
    void mutableKeyCanBreakHashMapLookup() {
        HashMap<MutableKey, String> map = new HashMap<>();

        MutableKey key = new MutableKey("dataset");

        map.put(key, "value");

        key.setName("changed");

        assertFalse(map.containsKey(key));
    }

    private static class MutableKey {

        private String name;

        MutableKey(String name) {
            this.name = name;
        }

        void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (!(object instanceof MutableKey)) {
                return false;
            }

            MutableKey key = (MutableKey) object;

            return Objects.equals(name, key.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}