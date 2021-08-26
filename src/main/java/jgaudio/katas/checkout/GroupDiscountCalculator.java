package jgaudio.katas.checkout;

import java.util.List;

public class GroupDiscountCalculator implements DiscountCalculator<GroupDiscount> {

  @Override
  public double calculate(List<Item> items, Discount discount) {
    GroupDiscount groupDiscount = (GroupDiscount) discount;
    long itemCount = items.stream().filter(i -> i == groupDiscount.getItem()).count();
    long discountApplyTimes = itemCount / groupDiscount.getGroupSize();
    return discountApplyTimes * groupDiscount.getDiscountUnits() * groupDiscount.getItem().getUnitaryPrice();
  }
}
