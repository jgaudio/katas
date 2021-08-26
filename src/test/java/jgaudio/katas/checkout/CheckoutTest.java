package jgaudio.katas.checkout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest {

  @Test
  public void testCheckout_NoDiscount() {
    Checkout checkout = new Checkout(new DiscountCalculators());
    List<Item> items = Arrays.asList(Item.COFFEE, Item.WATERMELON, Item.MILK, Item.BANANA);
    List<Discount> discounts = Collections.emptyList();
    Assertions.assertEquals(10d, checkout.checkout(items, discounts));
  }

  @Test
  public void testCheckout_2For1_discount() {
    DiscountCalculators discountCalculators = new DiscountCalculators();
    discountCalculators.register(GroupDiscount.class, new GroupDiscountCalculator());
    Checkout checkout = new Checkout(discountCalculators);
    List<Item> items = Arrays.asList(
        Item.COFFEE,
        Item.COFFEE,
        Item.BANANA,
        Item.BANANA,
        Item.WATERMELON,
        Item.MILK);

    List<Discount> discounts = Arrays.asList(new GroupDiscount(Item.COFFEE, 2, 1),
        new GroupDiscount(Item.BANANA, 2, 1));
    Assertions.assertEquals(10d, checkout.checkout(items, discounts));
  }

  @Test
  public void testCheckout_MinimumUnits_Discount() {
    DiscountCalculators discountCalculators = new DiscountCalculators();
    discountCalculators.register(MinimumUnitsDiscount.class, new MinimumUnitsDiscountCalculator());
    Checkout checkout = new Checkout(discountCalculators);
    List<Item> items = Arrays.asList(
        Item.COFFEE,
        Item.COFFEE,
        Item.BANANA,
        Item.WATERMELON,
        Item.MILK);

    List<Discount> discounts = Collections
        .singletonList(new MinimumUnitsDiscount(Item.COFFEE, 2, 0.2d));
    Assertions.assertEquals(10.4d, checkout.checkout(items, discounts));
  }

}

