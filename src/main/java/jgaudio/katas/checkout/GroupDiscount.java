package jgaudio.katas.checkout;

import java.util.Objects;

/**
 * For every {@link #groupSize} items you buy, you get {@link #discountUnits}
 * for free.
 */
public class GroupDiscount implements Discount {

  private final Item item;
  private final int groupSize;
  private final int discountUnits;

  public GroupDiscount(Item item, int groupSize, int discountUnits) {
    this.item = item;
    this.groupSize = groupSize;
    this.discountUnits = discountUnits;
  }

  public Item getItem() {
    return item;
  }

  public int getGroupSize() {
    return groupSize;
  }

  public int getDiscountUnits() {
    return discountUnits;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupDiscount that = (GroupDiscount) o;
    return groupSize == that.groupSize && discountUnits == that.discountUnits && item == that.item;
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, groupSize, discountUnits);
  }

  @Override
  public String toString() {
    return "GroupDiscount{" +
        "item=" + item +
        ", groupSize=" + groupSize +
        ", discountUnits=" + discountUnits +
        '}';
  }
}
