package com.example.user.pontoon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 12/11/2016.
 */

public class PontoonHandValuerTest {

    PontoonHandValuer values;
    Hand hand;
    Card aceOfHearts;
    Card kingOfHearts;
    Card queenOfHearts;
    Card fiveOfHearts;
    Card fiveOfDiamonds;
    Card fourOfHearts;
    Card fourOfDiamonds;
    Card twoOfClubs;

    @Before
    public void before() {
        values = new PontoonHandValuer();
        hand = new Hand();
        aceOfHearts = new Card(Suit.HEARTS, Rank.ACE);
        kingOfHearts = new Card(Suit.HEARTS, Rank.KING);
        queenOfHearts = new Card(Suit.HEARTS, Rank.QUEEN);
        fiveOfHearts = new Card(Suit.HEARTS, Rank.FIVE);
        fiveOfDiamonds = new Card(Suit.HEARTS, Rank.FIVE);
        fourOfHearts = new Card(Suit.HEARTS, Rank.FOUR);
        fourOfDiamonds = new Card(Suit.DIAMONDS, Rank.FOUR);
        twoOfClubs = new Card(Suit.CLUBS, Rank.TWO);
    }

    @Test
    public void emptyHandHasValueZero() {
        assertEquals(0, values.getHandValue(hand));
    }

    @Test
    public void canGetHandValue() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(21, values.getHandValue(hand));
    }

    @Test
    public void canCheckIfBust_NotBust() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(false, values.checkIfBust(hand));
    }

    @Test
    public void canCheckIfBust_Bust() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        hand.addCard(queenOfHearts);
        assertEquals(true, values.checkIfBust(hand));
    }

    @Test
    public void canCheckForPontoon_NotPontoon_ThreeCards() {
        hand.addCard(aceOfHearts);
        hand.addCard(fiveOfHearts);
        hand.addCard(fiveOfDiamonds);
        assertEquals(false, values.checkForPontoon(hand));
    }

    @Test
    public void canCheckForPontoon_NotPontoon_ShortOnPoints() {
        hand.addCard(aceOfHearts);
        hand.addCard(fiveOfHearts);
        assertEquals(false, values.checkForPontoon(hand));
    }

    @Test
    public void canCheckForPontoon_Pontoon() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(true, values.checkForPontoon(hand));
    }

    @Test
    public void canCheckForFiveCardTrick_NoFCT_TooFewCards() {
        hand.addCard(fiveOfHearts);
        hand.addCard(fiveOfDiamonds);
        hand.addCard(fourOfHearts);
        hand.addCard(fourOfDiamonds);
        assertEquals(false, values.checkForFiveCardTrick(hand));
    }

    @Test
    public void canCheckForFiveCardTrick_NoFCT_Bust() {
        hand.addCard(fiveOfHearts);
        hand.addCard(fiveOfDiamonds);
        hand.addCard(fourOfHearts);
        hand.addCard(fourOfDiamonds);
        hand.addCard(aceOfHearts);
        assertEquals(false, values.checkForFiveCardTrick(hand));
    }

    @Test
    public void canCheckForFiveCardTrick_FCT() {
        hand.addCard(fiveOfHearts);
        hand.addCard(fiveOfDiamonds);
        hand.addCard(fourOfHearts);
        hand.addCard(fourOfDiamonds);
        hand.addCard(twoOfClubs);
        assertEquals(true, values.checkForFiveCardTrick(hand));
    }

}
