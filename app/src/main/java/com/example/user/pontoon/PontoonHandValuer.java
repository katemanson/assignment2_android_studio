package com.example.user.pontoon;

import java.util.ArrayList;

/**
 * Created by user on 12/11/2016.
 */

public class PontoonHandValuer implements HandValuer {

    public int getRawHandValue(Hand hand) {

        // raw hand value makes Aces worth 11

        int rawHandValue = 0;
        int cardValue = 0;
        ArrayList<Card> cards = hand.getSet();

        for ( Card card : cards ) {
            switch ( card.getRank() ) {
                case TWO: cardValue = 2;
                    break;
                case THREE: cardValue = 3;
                    break;
                case FOUR: cardValue = 4;
                    break;
                case FIVE: cardValue = 5;
                    break;
                case SIX: cardValue = 6;
                    break;
                case SEVEN: cardValue = 7;
                    break;
                case EIGHT: cardValue = 8;
                    break;
                case NINE: cardValue = 9;
                    break;
                case TEN: case JACK: case QUEEN: case KING:
                    cardValue = 10;
                    break;
                case ACE: cardValue = 11;
                    break;
            }
            rawHandValue += cardValue;
        }
        return rawHandValue;
    }

    public int getHandValue(Hand hand) {

        /*
        final hand value (effectively) drops the value of an Ace to 1
        where counting that Ace as 11 would mean going bust;
        effect should be to continue counting 11 for Aces until
        this is no longer possible without going bust (aim being to get the highest-scoring
        hand you can, <= 21)
        */

        int handValue = getRawHandValue(hand);
        ArrayList<Card> cards = hand.getSet();

        for ( Card card : cards ) {
            if ( card.getRank() == Rank.ACE && handValue > 21 ) {
                handValue -= 10;
            }
        }

        return handValue;
    }

    public boolean checkIfMustTwist(Hand hand) {
        int handSize = hand.countCards();
        int handValue = getHandValue(hand);
        if ( handSize < 5 && handValue < 15 ) {
            return true;
        }
        return false;
    }

    public boolean canStickOrTwist(Hand hand) {
        int handSize = hand.countCards();
        int handValue = getHandValue(hand);
        if (handSize < 5 && (handValue >= 15 && handValue <= 21)) {
            return true;
        }
        return false;
    }

    public boolean checkIfBust(Hand hand) {

        int handSize = hand.countCards();
        int handValue = getHandValue(hand);

        if (handSize > 5 || handValue > 21) {
            return true;
        }
        return false;
    }

    public boolean checkForPontoon(Hand hand) {
        int handSize = hand.countCards();
        int handValue = getHandValue(hand);
        if (handSize == 2 && handValue == 21) {
            return true;
        }
        return false;
    }

    public boolean checkForFiveCardTrick(Hand hand) {
        int handSize = hand.countCards();
        int handValue = getHandValue(hand);
        if (handSize == 5 && handValue <= 21) {
            return true;
        }
        return false;
    }

}
