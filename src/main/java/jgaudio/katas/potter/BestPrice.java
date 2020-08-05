// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.Objects;
import java.util.Set;

public final class BestPrice {

    private final Set<Discount> discounts;
    private final float price;

    public BestPrice(final Set<Discount> discounts, final float price) {
        this.discounts = discounts;
        this.price = price;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        BestPrice that = (BestPrice)o;
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
               ", price=" + price +
               '}';
    }
}
