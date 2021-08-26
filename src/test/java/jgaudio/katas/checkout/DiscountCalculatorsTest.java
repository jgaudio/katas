package jgaudio.katas.checkout;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountCalculatorsTest {

  @Test
  void registerAndGetDiscountCalculator() {
    DiscountCalculators discountCalculators = new DiscountCalculators();
    GroupDiscountCalculator discountCalculator = new GroupDiscountCalculator();
    discountCalculators.register(GroupDiscount.class, discountCalculator);
    Assertions.assertEquals(discountCalculator, discountCalculators.getCalculator(GroupDiscount.class));
  }
}