// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PotterKata {

    private Set<Discount> discounts = new TreeSet<>();
    private Map<String, Integer> books = new HashMap<>();

    public void addDiscount(final Discount discount) {
        this.discounts.add(discount);
    }

    public void addBook(final String name, int qty) {
        this.books.compute(name, (bookName, repetitions) -> {
            if (repetitions == null) {
                return qty;
            } else {
                return repetitions + qty;
            }
        });
    }

    public BestPrice getBestDiscount() {
        final PriceCalculator priceCalculator = new PriceCalculator(discounts);
        return priceCalculator.calculateBestPrice(books);
    }

}
