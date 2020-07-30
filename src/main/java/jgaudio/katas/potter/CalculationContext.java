// (C) king.com Ltd 2020

package jgaudio.katas.potter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class CalculationContext {

    // Table over which to apply discounts
    private final int[] books;
    // How many different volumes are there?
    private int differentBooksCount;
    // How many copies in total?
    private int totalBooksCount;
    // Discounts applied so far
    private final Stack<Discount> discountsChain;
    // accumulated discount
    private double totalDiscount;

    // At any point the calculation can skip any path
    // if it contains any of this discounts
    private final Set<Discount> visited;

    public CalculationContext(final Map<String, Integer> books) {
        this.books = buildBooksTable(books);
        this.differentBooksCount = computeDifferentBooksCount();
        this.totalBooksCount = computeTotalBooks();
        this.discountsChain = new Stack<>();
        this.visited = new HashSet<>();
    }

    private int[] buildBooksTable(final Map<String, Integer> books) {
        final Set<String> keys = books.keySet();
        final int[] bookTable = new int[keys.size()];
        int idx = 0;
        for (final String key : keys) {
            bookTable[idx] = books.get(key);
            idx++;
        }
        return bookTable;
    }

    private int computeDifferentBooksCount() {
        int count = 0;
        for (int i = 0; i < this.books.length; i++) {
            if (this.books[i] > 0) {
                count++;
            }
        }
        return count;
    }

    private int computeTotalBooks() {
        int totalBooks = 0;
        for (int i = 0; i < this.books.length; i++) {
            totalBooks += this.books[i];
        }
        return totalBooks;
    }

    public void applyDiscount(final Discount discount) {
        int modificationsCount = 0;
        for (int i = 0; i < this.books.length && modificationsCount < discount.getRequiredDifferentBooks(); i++) {
            if (this.books[i] > 0) {
                modificationsCount++;
                this.totalBooksCount--;
            }
            this.books[i] -= 1;
            if (this.books[i] == 0) {
                differentBooksCount--;
            }
        }
        this.discountsChain.push(discount);
        this.totalDiscount += discount.getDiscount();
    }

    public void revertDiscount(final Discount discount) {
        int modificationsCount = 0;
        for (int i = 0; i < this.books.length && modificationsCount < discount.getRequiredDifferentBooks(); i++) {
            if (this.books[i] >= 0) {
                modificationsCount++;
                this.totalBooksCount++;
            }
            if (this.books[i] == 0) {
                this.differentBooksCount++;
            }
            this.books[i] += 1;
        }
        final Discount reverted = this.discountsChain.pop();
        this.totalDiscount -= reverted.getDiscount();
    }

    public boolean isDiscountApplicable(final Discount discount) {
        return !visited.contains(discount) && calculateOccurrences(discount) > 0;
    }

    private int calculateOccurrences(final Discount currentDiscount) {
        if (differentBooksCount >= currentDiscount.getRequiredDifferentBooks()) {
            return totalBooksCount / currentDiscount.getRequiredDifferentBooks();
        } else {
            return 0;
        }
    }

    public BestDiscount createBestDiscount() {
        return new BestDiscount(this.discountsChain.stream().collect(Collectors.toList()));
    }

    public boolean isAnyDiscountPossible() {
        return this.differentBooksCount >= 2;
    }

    public double getTotalDiscount() {
        return this.totalDiscount;
    }

    public void markVisited(final Discount discount) {
        this.visited.add(discount);
    }
}
