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
    public void canGetRawHandValue_Pontoon() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(21, values.getRawHandValue(hand));
    }

    @Test
    public void canGetRawHandValue_TwoAces() {
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        assertEquals(22, values.getRawHandValue(hand));
    }

    @Test
    public void canGetFinalHandValue_Pontoon() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(21, values.getHandValue(hand));
    }

    @Test
    public void canGetFinalHandValue_TwoAces() {
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        assertEquals(12, values.getHandValue(hand));
    }

    @Test
    public void canGetFinalHandValue_TwoAcesPlus() {
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(12, values.getHandValue(hand));
    }

    @Test
    public void canCheckIfBust_NotBust() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        assertEquals(false, values.checkIfBust(hand));
    }

    @Test
    public void canCheckIfBust_NotBust_WithAce() {
        hand.addCard(aceOfHearts);
        hand.addCard(kingOfHearts);
        hand.addCard(queenOfHearts);
        assertEquals(false, values.checkIfBust(hand));
    }

    @Test
    public void canCheckIfBust_NotBust_TwoAces() {
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        assertEquals(false, values.checkIfBust(hand));
    }

    @Test
    public void canCheckIfBust_NotBust_ThreeAces() {
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        assertEquals(false, values.checkIfBust(hand));
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
        hand.addCard(kingOfHearts);
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

    @Test
    public void canCheckForFiveCardTrick_FCT_WithAces() {
        hand.addCard(fiveOfHearts);
        hand.addCard(fiveOfDiamonds);
        hand.addCard(fourOfHearts);
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        assertEquals(true, values.checkForFiveCardTrick(hand));
    }

    @Test
    public void canGetTwistValue() {
        hand.addCard(fiveOfHearts);
        hand.addCard(fiveOfDiamonds);
        hand.addCard(fourOfHearts);
        hand.addCard(aceOfHearts);
        hand.addCard(aceOfHearts);
        assertEquals(26, values.getTwistValue(hand));
    }

}
