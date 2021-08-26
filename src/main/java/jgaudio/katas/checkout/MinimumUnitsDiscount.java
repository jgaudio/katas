package jgaudio.katas.checkout;

import java.util.Objects;

/**
 * Discounts a target percentage from the total price of the order (without considering other discounts)
 * if at least a minimum amount of units of certain item are bought.
 */
public class MinimumUnitsDiscount implements Discount {

  private final Item item;
  private final int minimumUnits;
  private final double discountPercentage;

  public MinimumUnitsDiscount(Item item, int minimumUnits, double discountPercentage) {
    this.item = item;
    this.minimumUnits = minimumUnits;
    this.discountPercentage = discountPercentage;
  }

  public Item getItem() {
    return item;
  }

  public int getMinimumUnits() {
    return minimumUnits;
  }

  public double getDiscountPercentage() {
    return discountPercentage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MinimumUnitsDiscount that = (MinimumUnitsDiscount) o;
    return minimumUnits == that.minimumUnits
        && Double.compare(that.discountPercentage, discountPercentage) == 0
        && item == that.item;
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, minimumUnits, discountPercentage);
  }

  @Override
  public String toString() {
    return "MinimumUnitsDiscount{" +
        "item=" + item +
        ", minimumUnits=" + minimumUnits +
        ", discountPercentage=" + discountPercentage +
        '}';
  }
}

