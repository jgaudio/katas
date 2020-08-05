// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class PriceCalculator {

    // All discounts
    private final Set<Discount> discounts;

    // calculation state
    private BestPrice bestPrice;

    public PriceCalculator(final Set<Discount> allDiscounts) {
        this.discounts = Collections.unmodifiableSet(allDiscounts);
    }

    public BestPrice calculateBestPrice(final Map<String, Integer> books) {

        // Build stateful calculation context
        // In a parallel alternative, this could be stateless and apply/revert discounts
        // could generate an immutable copy of the context
        final CalculationContext calculationContext = new CalculationContext(books);

        // Some heuristic to choose the discount that will lead faster
        // to the best discount chain
        final Discount initialDiscount = chooseBestInitialDiscount();
        exploreDiscount(calculationContext, initialDiscount);
        calculationContext.markVisited(initialDiscount);

        for (final Discount nextDiscount : this.discounts) {
            if (!nextDiscount.equals(initialDiscount)) {
                exploreDiscount(calculationContext, nextDiscount);
                calculationContext.markVisited(nextDiscount);
            }
        }

        return this.bestPrice;
    }

    private Discount chooseBestInitialDiscount() {
        // Choose the one that requires the less number of books
        // since it's the one that hast most chances of being applicable
        return discounts.iterator().next();
    }

    private void exploreDiscount(final CalculationContext context, final Discount nextDiscount) {

        context.applyDiscount(nextDiscount);

        if (bestPrice == null) {
            bestPrice = context.createBestPrice();
        } else if (context.getPrice() < bestPrice.getPrice()) {
            bestPrice = context.createBestPrice();
        }

        if (context.isAnyDiscountPossible()) {
            for (final Discount discount : discounts) {
                if (context.isDiscountApplicable(discount)) {
                    exploreDiscount(context, discount);
                }
            }
        }

        context.revertDiscount(nextDiscount);
    }
}
