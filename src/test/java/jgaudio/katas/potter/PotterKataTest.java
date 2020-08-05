// (C) king.com Ltd 2020
package jgaudio.katas.potter;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Missing test cases
Missing calculating actual price, but knowing best discount it's really straightforward
 */
class PotterKataTest {

    private static final Discount DISCOUNT_2 = new Discount(0.95f, 2);
    private static final Discount DISCOUNT_3 = new Discount(0.9f, 3);
    private static final Discount DISCOUNT_4 = new Discount(0.8f, 4);
    private static final Discount DISCOUNT_5 = new Discount(0.75f, 5);

    private PotterKata potterKata;

    @BeforeEach
    void setUp() {
        potterKata = new PotterKata();
        potterKata.addDiscount(DISCOUNT_2);
        potterKata.addDiscount(DISCOUNT_3);
        potterKata.addDiscount(DISCOUNT_4);
        potterKata.addDiscount(DISCOUNT_5);
    }

    @Test
    void testEdgeCase1() {

        potterKata.addBook("Book A", 2);
        potterKata.addBook("Book B", 2);
        potterKata.addBook("Book C", 2);
        potterKata.addBook("Book D", 1);
        potterKata.addBook("Book E", 1);

        final BestPrice bestPrice = potterKata.getBestDiscount();

        final BestPrice expectedPrice = new BestPrice(Sets.newHashSet(DISCOUNT_4, DISCOUNT_4),4 * 8 * 0.8f * 2);

        assertThat(bestPrice, is(expectedPrice));
    }

    @Test
    void testEdgeCase2() {

        potterKata.addBook("Book A", 5);
        potterKata.addBook("Book B", 5);
        potterKata.addBook("Book C", 4);
        potterKata.addBook("Book D", 5);
        potterKata.addBook("Book E", 4);

        final BestPrice bestPrice = potterKata.getBestDiscount();

        final BestPrice expectedPrice = new BestPrice(Sets.newHashSet(DISCOUNT_5, DISCOUNT_5, DISCOUNT_5, DISCOUNT_4, DISCOUNT_4), 3 * (8 * 5 * 0.75f) + 2 * (8 * 4 * 0.8f));

        assertThat(bestPrice, is(expectedPrice));
    }
}