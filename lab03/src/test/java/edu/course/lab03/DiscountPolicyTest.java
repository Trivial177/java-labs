package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountPolicyTest {

    @Test
    void noDiscountReturnsOriginalPrice() {
        DiscountPolicy policy = new NoDiscount();

        assertEquals(100.0, policy.apply(100.0));
    }

    @Test
    void percentDiscountAppliesDiscount() {
        DiscountPolicy policy = new PercentDiscount(20);

        assertEquals(80.0, policy.apply(100.0));
    }

    @Test
    void priceCalculatorWorksWithDifferentPolicies() {
        PriceCalculator calculator =
            new PriceCalculator(new NoDiscount());

        assertEquals(100.0, calculator.calculate(100.0));

        calculator =
            new PriceCalculator(new PercentDiscount(20));

        assertEquals(80.0, calculator.calculate(100.0));
    }
}