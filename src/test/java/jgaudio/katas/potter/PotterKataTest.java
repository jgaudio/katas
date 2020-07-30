// (C) king.com Ltd 2020
package jgaudio.katas.potter;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Missing test cases
Missing calculating actual price, but knowing best discount it's really straightforward
 */
class PotterKataTest {

    private static final Discount DISCOUNT_2 = new Discount(5, 2);
    private static final Discount DISCOUNT_3 = new Discount(10, 3);
    private static final Discount DISCOUNT_4 = new Discount(20, 4);
    private static final Discount DISCOUNT_5 = new Discount(25, 5);

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
    void testChooseBestDiscount() {

        potterKata.addBook("Book A", 2);
        potterKata.addBook("Book B", 2);
        potterKata.addBook("Book C", 2);
        potterKata.addBook("Book D", 1);
        potterKata.addBook("Book E", 1);

        final BestDiscount bestDiscount = potterKata.getBestDiscount();

        final BestDiscount expectedDiscount = new BestDiscount(Lists.newArrayList(DISCOUNT_4, DISCOUNT_4));

        assertThat(bestDiscount, is(expectedDiscount));
    }
}