package jgaudio.katas.checkout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MinimumUnitsDiscountCalculatorTest {

  @Test
  void test_BuyAtLeast2_Get20PercentDiscount() {
    MinimumUnitsDiscount minimumUnitsDiscount = new MinimumUnitsDiscount(Item.BANANA, 2, 0.2d);
    MinimumUnitsDiscountCalculator discountCalculator = new MinimumUnitsDiscountCalculator();
    List<Item> items = Arrays.asList(Item.BANANA, Item.BANANA, Item.MILK);
    assertEquals(1.2d, discountCalculator.calculate(items, minimumUnitsDiscount), 2e-14);
  }

}