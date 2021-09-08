# katas
My personal collection of coding katas & technical challenge solutions for fun, practice and job interviews.

## Potter Kata

>Once upon a time there was a series of 5 books about a very English hero called Harry. (At least when this Kata was invented, there were only 5. Since then they >have multiplied) Children all over the world thought he was fantastic, and, of course, so did the publisher. So in a gesture of immense generosity to mankind, (and >to increase sales) they set up the following pricing model to take advantage of Harry’s magical powers.
>
>One copy of any of the five books costs 8 EUR. If, however, you buy two different books from the series, you get a 5% discount on those two books. If you buy 3 >different books, you get a 10% discount. With 4 different books, you get a 20% discount. If you go the whole hog, and buy all 5, you get a huge 25% discount.
>
>Note that if you buy, say, four books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the fourth book still costs 8 >EUR.
>
>Potter mania is sweeping the country and parents of teenagers everywhere are queueing up with shopping baskets overflowing with Potter books. Your mission is to >write a piece of code to calculate the price of any conceivable shopping basket, giving as big a discount as possible.

[Full kata description](https://codingdojo.org/kata/Potter)

Solved in **Java**.

## Checkout

>This week, let’s implement the code for a supermarket checkout that calculates the total price of a number of items. In a normal supermarket, things are identified >using Stock Keeping Units, or SKUs. In our store, we’ll use individual letters of the alphabet (A, B, C, and so on). Our goods are priced individually. In >addition, some items are multipriced: buy n of them, and they’ll cost you y cents. For example, item ‘A’ might cost 50 cents individually, but this week we have a >special offer: buy three ‘A’s and they’ll cost you $1.30.
>[...]
>Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll recognize the two B’s and price them at 45 (for a total price so far of >95). Because the pricing changes frequently, we need to be able to pass in a set of pricing rules each time we start handling a checkout transaction.

[Full kata description](http://codekata.com/kata/kata09-back-to-the-checkout/)

Solved in **Java** and **Python**.

## Mars Rover

>A squad of robotic rovers are to be landed by NASA on a plateau on Mars.
>
>This plateau, which is curiously rectangular, must be navigated by the rovers so that their on board cameras can get a complete view of the surrounding terrain to >send back to Earth.
>
>A rover's position is represented by a combination of an x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is >divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.
>
>In order to control a rover, NASA sends a simple string of letters. The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 degrees left or >right respectively, without moving from its current spot.
>
>'M' means move forward one grid point, and maintain the same heading.
>
>Assume that the square directly North from (x, y) is (x, y+1).
>
>The output for each rover should be its final co-ordinates and heading.

[Full challenge description](https://code.google.com/archive/p/marsrovertechchallenge/)

Solved in **Java**.

