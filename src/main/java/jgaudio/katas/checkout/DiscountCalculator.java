package jgaudio.katas.checkout;

import java.util.List;

public interface DiscountCalculator<T extends Discount> {
  double calculate(List<Item> items, Discount discount);
}
