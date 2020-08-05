// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.Objects;

public class Discount implements Comparable<Discount> {

    private static final int BASE_UNIT_PRICE = 8;
    private final float priceAdjustment;
    private final int requiredDifferentBooks;

    public Discount(float priceAdjustment, int requiredDifferentBooks) {
        this.priceAdjustment = priceAdjustment;
        this.requiredDifferentBooks = requiredDifferentBooks;
    }

    public float getDiscountedPrice() {
        return requiredDifferentBooks * BASE_UNIT_PRICE * priceAdjustment;
    }

    public int getRequiredDifferentBooks() {
        return requiredDifferentBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Discount discount = (Discount)o;
        return getRequiredDifferentBooks() == discount.getRequiredDifferentBooks();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequiredDifferentBooks());
    }

    @Override
    public int compareTo(Discount o) {
        return Integer.compare(requiredDifferentBooks, o.requiredDifferentBooks);
    }

    public String toString() {
        return this.requiredDifferentBooks + "x -> " + (100 * (1 - priceAdjustment)) + "%";
    }
}
