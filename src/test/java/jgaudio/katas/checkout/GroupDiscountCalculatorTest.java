package jgaudio.katas.checkout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupDiscountCalculatorTest {

  @Test
  void calculate_2for1_discount() {
    GroupDiscount groupDiscount = new GroupDiscount(Item.BANANA, 2, 1);
    GroupDiscountCalculator discountCalculator = new GroupDiscountCalculator();
    List<Item> items = Arrays.asList(Item.BANANA, Item.BANANA, Item.BANANA, Item.BANANA);
    Assertions.assertEquals(2 * Item.BANANA.getUnitaryPrice(), discountCalculator.calculate(items, groupDiscount));
  }
}