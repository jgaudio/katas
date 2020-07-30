// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class DiscountCalculator {

    // All discounts
    private final Set<Discount> discounts;

    // calculation state
    private BestDiscount bestDiscount;

    public DiscountCalculator(final Set<Discount> allDiscounts) {
        this.discounts = Collections.unmodifiableSet(allDiscounts);
    }

    public BestDiscount calculateBestDiscount(final Map<String, Integer> books) {

        // Build stateful calculation context
        // In a parallel alternative, this could be stateless and apply/revert discounts
        // could generata an immutable copy of the context
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

        return this.bestDiscount;
    }

    private Discount chooseBestInitialDiscount() {
        // Just to get going
        return discounts.iterator().next();
    }

    private void exploreDiscount(final CalculationContext context, final Discount nextDiscount) {

        context.applyDiscount(nextDiscount);

        if (bestDiscount == null) {
            bestDiscount = context.createBestDiscount();
        } else if (context.getTotalDiscount() > bestDiscount.getTotalDiscount()) {
            bestDiscount = context.createBestDiscount();
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
