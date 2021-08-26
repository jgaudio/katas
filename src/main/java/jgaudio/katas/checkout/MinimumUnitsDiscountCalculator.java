package jgaudio.katas.checkout;

import java.util.List;

public class MinimumUnitsDiscountCalculator implements DiscountCalculator<MinimumUnitsDiscount> {

  @Override
  public double calculate(List<Item> items, Discount discount) {
    MinimumUnitsDiscount minimumUnitsDiscount = (MinimumUnitsDiscount) discount;
    long count = items.stream().filter(i -> i == minimumUnitsDiscount.getItem()).count();
    if (count >= minimumUnitsDiscount.getMinimumUnits()) {
      double totalPrice = items.stream().mapToDouble(Item::getUnitaryPrice).sum();
      return totalPrice * minimumUnitsDiscount.getDiscountPercentage();
    }
    return 0d;
  }
}
