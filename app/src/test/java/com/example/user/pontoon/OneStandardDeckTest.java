package com.example.user.pontoon;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Created by user on 12/11/2016.
 */

public class OneStandardDeckTest {

    Deck deck;
    Card card;

    @Before
    public void before() {
        deck = new OneStandardDeck();
        card = new Card(Suit.HEARTS, Rank.QUEEN);
    }

    @Test
    public void deckStartsEmpty() {
        assertEquals(0, deck.countCards());
    }

    @Test
    public void canAddCard() {
        deck.addCard(card);
        assertEquals(1, deck.countCards());
    }

    @Test
    public void canBuildOneStandardDeck() {
        deck.buildDeck();
        assertEquals(52, deck.countCards());
    }

    @Test
    public void canGetFirstCard() {
        deck.buildDeck();
        Card firstCard = deck.getFirstCard();
        assertEquals(Rank.TWO, firstCard.getRank());
        assertEquals(Suit.HEARTS, firstCard.getSuit());
    }

    @Test
    public void canRemoveFirstCard() {
        deck.buildDeck();
        Card firstCard = deck.removeFirstCard();
        Card newFirstCard = deck.getFirstCard();
        assertEquals(Rank.TWO, firstCard.getRank());
        assertEquals(Suit.HEARTS, firstCard.getSuit());
        assertEquals(Rank.THREE, newFirstCard.getRank());
        assertEquals(Suit.HEARTS, newFirstCard.getSuit());
        assertEquals(51, deck.countCards());
    }

}
