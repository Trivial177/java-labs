package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }
    @Test 
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test
void returnsTrueForPrimeNumber() {
    boolean result = CourseToolkit.isPrime(7);

    assertTrue(result);
}

@Test
void returnsFalseForCompositeNumber() {
    boolean result = CourseToolkit.isPrime(8);

    assertFalse(result);
}

@Test
void returnsFalseForNumberLessThanTwo() {
    boolean result = CourseToolkit.isPrime(1);

    assertFalse(result);
}

@Test
void returnsTrueForTwo() {
    boolean result = CourseToolkit.isPrime(2);

    assertTrue(result);
}

@Test
void returnsTrueForPalindrome() {
    boolean result = CourseToolkit.isPalindrome("level");

    assertTrue(result);
}

@Test
void returnsFalseForNonPalindrome() {
    boolean result = CourseToolkit.isPalindrome("hello");

    assertFalse(result);
}

@Test
void returnsFalseWhenCaseIsDifferent() {
    boolean result = CourseToolkit.isPalindrome("Level");

    assertFalse(result);
}

@Test
    void returnsAverageForNumbers() {
        double result = CourseToolkit.average(new int[]{1, 2, 3, 4});

        assertEquals(2.5, result);
    }

@Test
void returnsAverageForNegativeNumbers() {
    double result = CourseToolkit.average(new int[]{-2, -4, -6});

    assertEquals(-4.0, result);
}

@Test
void throwsExceptionForEmptyArray() {
    assertThrows(
        IllegalArgumentException.class,
        () -> CourseToolkit.average(new int[]{})
    );
}

}

