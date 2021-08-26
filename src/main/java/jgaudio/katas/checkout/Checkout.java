package jgaudio.katas.checkout;

import java.util.List;

public class Checkout {

  private final DiscountCalculators discountCalculators;

  public Checkout(DiscountCalculators discountCalculators) {
    this.discountCalculators = discountCalculators;
  }

  public double checkout(List<Item> items, List<Discount> discounts) {
    double totalPrice = items.stream().mapToDouble(Item::getUnitaryPrice).sum();
    double discountedAmount = discounts.stream().mapToDouble(d -> discountCalculators
        .getCalculator(d.getClass()).calculate(items, d)).sum();
    return totalPrice - discountedAmount;
  }
}
