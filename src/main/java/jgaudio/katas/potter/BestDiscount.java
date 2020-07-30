// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.List;
import java.util.Objects;

public final class BestDiscount {

    private final List<Discount> discounts;
    private final double totalDiscount;

    public BestDiscount(final List<Discount> discounts) {
        this.discounts = discounts;
        this.totalDiscount = discounts.stream().mapToDouble(Discount::getDiscount).sum();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        BestDiscount that = (BestDiscount)o;
        return getDiscounts().equals(that.getDiscounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiscounts());
    }

    @Override
    public String toString() {
        return "BestDiscount{" +
               "discounts=" + discounts +
               ", totalDiscount=" + totalDiscount +
               '}';
    }
}
