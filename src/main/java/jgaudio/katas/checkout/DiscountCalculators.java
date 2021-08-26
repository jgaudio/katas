package jgaudio.katas.checkout;

import java.util.HashMap;
import java.util.Map;

public class DiscountCalculators {

  private final Map<Class<? extends Discount>, DiscountCalculator<?>> calculators;

  public DiscountCalculators() {
    this.calculators = new HashMap<>();
  }

  public <T extends Discount> void register(Class<T> discountClass,
      DiscountCalculator<T> discountCalculator) {
      this.calculators.put(discountClass, discountCalculator);
  }

  @SuppressWarnings("unchecked")
  public <T extends Discount> DiscountCalculator<T> getCalculator(Class<T> discountClass) {
    return (DiscountCalculator<T>) this.calculators.get(discountClass);
  }
}
