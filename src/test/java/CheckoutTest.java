import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CheckoutTest {

    private Map<String, Entry<Integer, Integer>> withOffers(int quantity, String pineapple, int offerPrice) {
        Map<String, Entry<Integer, Integer>> offers = new HashMap<>();
        offers.put(pineapple, new SimpleEntry<>(quantity, offerPrice));
        return offers;
    }

    private List<String> forFruits(String... fruits) {
        return Arrays.asList(fruits);
    }

    Map<String, Entry<Integer, Integer>> withNoOffers = new HashMap<>();

    private Checkout checkout;

    @Before
    public void before(){
        checkout = new Checkout();
    }

    @Test
    public void shouldSellAnApple() {
        int expectedPrice = 50;
        assertEquals(expectedPrice, checkout.pay(forFruits("apple"), withNoOffers));
    }

    @Test
    public void aPineappleCosts220() throws Exception {

        String pineapple = "pineapple";

        int expectedPrice = 220;
        assertEquals(expectedPrice, checkout.pay(forFruits(pineapple),
                withOffers(1, pineapple, 220)));
    }

    @Test
    public void aBananaCosts60() throws Exception {
        String banana = "banana";

        int expectedPrice = 60;
        assertEquals(expectedPrice, checkout.pay(forFruits(banana), withOffers(1, banana, 60)));
    }

    @Test
    public void whatDoesItHappenIfITryToSellStrawberries() throws Exception {
        String strawberry = "strawberry";

        int expectedPrice = 123;

        assertEquals(expectedPrice, checkout.pay(forFruits(strawberry), withNoOffers));
    }

    @Test
    public void fruits() {
        String apple = "apple";
        String pear = "pear";
        // TODO: Do we sell lychee?
        String lychee = "lychee";
        String pineapple = "pineapple";
        String banana = "banana";

        Map<String, Entry<Integer, Integer>> ll = withOffers(3, apple, 130);
        ll.put(pear, new SimpleEntry<>(2, 45));

        int expectedPrice = 455;
        assertEquals(expectedPrice, checkout.pay(
                forFruits(
                        apple, pear, apple,pear, lychee, apple, banana, pineapple), ll));
    }

}